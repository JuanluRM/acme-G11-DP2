
package acme.features.company.practica;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Course;
import acme.entities.Practica;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Controller
public class CompanyPracticaDeleteService extends AbstractService<Company, Practica> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticaRepository repository;

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
		Practica practica;

		id = super.getRequest().getData("id", int.class);
		practica = this.repository.findOnePracticaById(id);
		status = practica != null && !practica.getPublished() && super.getRequest().getPrincipal().getActiveRoleId() == practica.getCompany().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practica practica;
		int id;

		id = super.getRequest().getData("id", int.class);
		practica = this.repository.findOnePracticaById(id);

		super.getBuffer().setData(practica);
	}

	@Override
	public void bind(final Practica object) {
		assert object != null;

		int courseId;
		Course course;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);
		super.bind(object, "code", "title", "summary", "goals", "estimatedTotalTime", "published");
		object.setCourse(course);
	}

	@Override
	public void validate(final Practica object) {
		assert object != null;
	}

	@Override
	public void perform(final Practica object) {
		assert object != null;

		final Collection<Practica> practicas;

		practicas = this.repository.findPracticasByCompany(object.getId());
		this.repository.delete(object);
		this.repository.deleteAll(practicas);
	}

	@Override
	public void unbind(final Practica object) {
		assert object != null;

		SelectChoices choices;
		Collection<Course> courses;
		Tuple tuple;

		courses = this.repository.findPublishedCourses();
		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "title", "summary", "goals", "estimatedTotalTime", "published", "course");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
