
package acme.features.company.practica;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.Practica;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticaShowService extends AbstractService<Company, Practica> {

	@Autowired
	CompanyPracticaRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int practicaId;
		Practica practica;

		practicaId = super.getRequest().getData("id", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		status = practica != null && super.getRequest().getPrincipal().hasRole(practica.getCompany());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practica object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticaById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Practica object) {
		assert object != null;

		int assistantId;
		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;

		if (!object.getDraftMode())
			courses = this.repository.findAllCourses();
		else {
			assistantId = super.getRequest().getPrincipal().getActiveRoleId();
			courses = this.repository.findPublishedCourses();
		}

		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "title", "summary", "goals", "estimatedTotalTime", "draftMode", "course");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
