
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.Lecture;
import acme.entities.LectureType;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLecturesShowService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLecturesRepository repository;

	// AbstractServiceInterface -----------------------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);

	}

	@Override
	public void authorise() {
		boolean status;
		int lectureId;
		Collection<Course> courses;

		boolean lecturerLecture;
		Lecture lecture;

		lectureId = super.getRequest().getData("id", int.class);
		courses = this.repository.findCoursesByLectureId(lectureId);

		lecture = this.repository.findOneLectureById(lectureId);
		lecturerLecture = super.getRequest().getPrincipal().hasRole(lecture.getLecturer());

		if (courses != null)
			status = courses != null && super.getRequest().getPrincipal().hasRole(Lecturer.class) && lecturerLecture;
		else
			status = super.getRequest().getPrincipal().hasRole(Lecturer.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Lecture object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneLectureById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "lectureAbstract", "estimatedLearningTime", "body", "type", "publish", "link");
		tuple.put("lectureTypes", SelectChoices.from(LectureType.class, object.getType()));

		super.getResponse().setData(tuple);
	}

}
