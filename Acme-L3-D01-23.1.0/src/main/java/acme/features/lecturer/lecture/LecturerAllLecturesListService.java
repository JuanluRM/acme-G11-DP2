
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerAllLecturesListService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLecturesRepository repository;

	// AbstractServiceInterface -----------------------------------------------------------


	@Override
	public void check() {
		final boolean status;

		super.getResponse().setChecked(true);

	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Lecturer.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<Lecture> objects;
		int lecturerId;

		lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		objects = this.repository.findLecturesByLecturerId(lecturerId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		Tuple tuple;
		final Course lectureCourse;

		tuple = super.unbind(object, "title", "lectureAbstract", "type", "publish");

		super.getResponse().setData(tuple);
	}

}
