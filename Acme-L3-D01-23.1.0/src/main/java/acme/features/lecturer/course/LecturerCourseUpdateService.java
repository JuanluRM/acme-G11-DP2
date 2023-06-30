
package acme.features.lecturer.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseUpdateService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

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
		Course course;

		course = this.repository.findOneCourseById(super.getRequest().getData("id", int.class));
		status = super.getRequest().getPrincipal().hasRole(course.getLecturer());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;
		int courseId;

		courseId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCourseById(courseId);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "courseAbstract", "type", "retailPrice", "publish", "link");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Course course;

			course = this.repository.findOneCourseByCode(object.getCode());
			super.state(course == null || course.equals(object), "code", "lecturer.course.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("retailPrice"))
			super.state(object.getRetailPrice().getAmount() >= 0, "retailPrice", "lecturer.course.error.retailPrice.negative");

		if (!super.getBuffer().getErrors().hasErrors("retailPrice")) {
			final List<String> currencies = new ArrayList<>();
			currencies.add("EUR");
			currencies.add("USD");
			currencies.add("GBP");
			super.state(currencies.contains(object.getRetailPrice().getCurrency()), "retailPrice", "lecturer.course.error.retailPrice.currencyNotAllowed");
		}
	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "courseAbstract", "type", "retailPrice", "publish", "link");

		super.getResponse().setData(tuple);
	}

}
