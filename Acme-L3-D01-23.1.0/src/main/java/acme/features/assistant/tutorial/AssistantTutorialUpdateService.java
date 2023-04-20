
package acme.features.assistant.tutorial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.Tutorial;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantTutorialUpdateService extends AbstractService<Assistant, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int id;
		Tutorial tutorial;

		id = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findOneTutorialById(id);
		status = tutorial != null && !tutorial.getIsPublished() && super.getRequest().getPrincipal().getActiveRoleId() == tutorial.getAssistant().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		Tutorial tutorial;

		id = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findOneTutorialById(id);

		super.getBuffer().setData(tutorial);
	}

	@Override
	public void bind(final Tutorial object) {
		assert object != null;

		int courseId;
		Course course;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);
		super.bind(object, "code", "title", "summary", "goals");
		object.setCourse(course);
	}

	@Override
	public void validate(final Tutorial object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Tutorial existing;

			existing = this.repository.findOneTutorialByCode(object.getCode());
			super.state(existing == null || existing.equals(object), "code", "assistant.tutorial.form.error.code");

		}
	}

	@Override
	public void perform(final Tutorial object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Tutorial object) {
		assert object != null;

		SelectChoices choices;
		Collection<Course> courses;
		Tuple tuple;

		courses = this.repository.findPublishedCourses();
		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "title", "summary", "goals", "course");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
