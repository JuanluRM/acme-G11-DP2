
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
public class CompanyPracticaCreateService extends AbstractService<Company, Practica> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticaRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		Boolean status;

		status = super.getRequest().getPrincipal().hasRole(Company.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practica object;
		Company company;

		company = this.repository.findOneCompanyById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Practica();
		object.setDraftMode(true);
		object.setCompany(company);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Practica object) {
		assert object != null;

		int companyId;
		Company company;
		int courseId;
		Course course;

		companyId = super.getRequest().getPrincipal().getActiveRoleId();
		company = this.repository.findOneCompanyById(companyId);

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);
		object.setCourse(course);
		object.setCompany(company);
		super.bind(object, "code", "title", "summary", "goals", "estimatedTotalTime");

	}

	@Override
	public void validate(final Practica object) {
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Practica practica;

			practica = this.repository.findOnePracticaByCode(object.getCode());
			super.state(practica == null, "code", "company.practica.error.code.duplicated");
		}
	}

	@Override
	public void perform(final Practica object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Practica object) {
		assert object != null;

		SelectChoices choices;
		Collection<Course> courses;
		Tuple tuple;

		courses = this.repository.findPublishedCourses();
		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "title", "summary", "goals", "estimatedTotalTime", "course");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
