
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
public class AssistantTutorialCreateService extends AbstractService<Assistant, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Tutorial object;
		Assistant assistant;

		assistant = this.repository.findOneAssistantById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Tutorial();
		object.setIsPublished(false);
		object.setAssistant(assistant);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Tutorial object) {
		assert object != null;

		int assistantId;
		Assistant assistant;
		int courseId;
		Course course;

		assistantId = super.getRequest().getPrincipal().getActiveRoleId();
		assistant = this.repository.findOneAssistantById(assistantId);

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);
		object.setAssistant(assistant);
		object.setCourse(course);
		super.bind(object, "code", "title", "summary", "startMoment", "finishMoment", "goals");

	}

	@Override
	public void validate(final Tutorial object) {
		assert object != null;

		Collection<String> codes;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			codes = this.repository.findAllCodes();
			super.state(!codes.contains(object.getCode()), "code", "assistant.tutorial.form.error.code");
		}
	}

	@Override
	public void perform(final Tutorial object) {
		assert object != null;

		object.setIsPublished(false);
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

		tuple = super.unbind(object, "code", "title", "summary", "startMoment", "finishMoment", "goals", "course");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
