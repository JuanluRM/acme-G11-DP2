/*
 * EmployerJobUpdateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

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
public class CompanyPracticaUpdateService extends AbstractService<Company, Practica> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticaRepository repository;

	// AbstractService<Employer, Job> -------------------------------------


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
		status = practica != null && practica.getDraftMode() && super.getRequest().getPrincipal().getActiveRoleId() == practica.getCompany().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		Practica practica;

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
		super.bind(object, "code", "title", "summary", "goals", "estimatedTotalTime");
		object.setCourse(course);
	}

	@Override
	public void validate(final Practica object) {
		assert object != null;

		//		if (!super.getBuffer().getErrors().hasErrors("code")) {
		//			Practica practica;
		//
		//			practica = this.repository.findOnePracticaByCode(object.getCode());
		//			super.state(practica == null, "code", "company.practica.error.code.duplicated");
		//		}

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
