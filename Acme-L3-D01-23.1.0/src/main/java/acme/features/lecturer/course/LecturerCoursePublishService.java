
package acme.features.lecturer.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCoursePublishService extends AbstractService<Lecturer, Course> {

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
		status = course != null && super.getRequest().getPrincipal().hasRole(course.getLecturer());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCourseById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "courseAbstract", "retailPrice", "link", "publish");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;
		//		if (!super.getBuffer().getErrors().hasErrors("code")) {
		//			Course course;
		//
		//			course = this.repository.findOneCourseByCode(object.getCode());
		//			super.state(course == null || course.getId() == object.getId(), "code", "lecturer.course.error.code.duplicated");
		//		}

		if (!super.getBuffer().getErrors().hasErrors("retailPrice"))
			super.state(object.getRetailPrice().getAmount() >= 0, "retailPrice", "lecturer.course.error.retailPrice.negative");

		super.state(!this.repository.findLecturesNotPublishedByCourse(object.getId()), "*", "lecturer.course.error.lectureNotPublished");

		if (!this.repository.hasLectures(object.getId()))
			super.state(this.repository.hasLectures(object.getId()), "*", "lecturer.course.error.noLectures");
		else
			super.state(this.repository.hasACourseHandsOnLectures(object.getId()), "*", "lecturer.course.error.fullTheorical");

	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		object.setPublish(true);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "courseAbstract", "retailPrice", "link", "publish");

		super.getResponse().setData(tuple);
	}

}
