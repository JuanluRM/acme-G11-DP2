
package acme.features.lecturer.courseLecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.CourseLecture;
import acme.entities.Lecture;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseLectureCreateService extends AbstractService<Lecturer, CourseLecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseLectureRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {

		boolean status;

		status = super.getRequest().hasData("courseId", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int courseId;
		final Course course;

		courseId = super.getRequest().getData("courseId", int.class);
		course = this.repository.findOneCourseById(courseId);
		status = course != null && super.getRequest().getPrincipal().hasRole(course.getLecturer());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int courseId;
		CourseLecture courseLecture;
		Course course;

		courseId = super.getRequest().getData("courseId", int.class);
		course = this.repository.findOneCourseById(courseId);

		courseLecture = new CourseLecture();
		courseLecture.setCourse(course);

		super.getBuffer().setData(courseLecture);
	}

	@Override
	public void bind(final CourseLecture object) {
		assert object != null;

		int lectureId;
		int courseId;
		Lecture lecture;
		Course course;

		lectureId = super.getRequest().getData("lecture", int.class);
		lecture = this.repository.findOneLectureById(lectureId);

		courseId = super.getRequest().getData("courseId", int.class);
		course = this.repository.findOneCourseById(courseId);

		object.setLecture(lecture);
		object.setCourse(course);
	}

	@Override
	public void validate(final CourseLecture object) {
		assert object != null;
	}

	@Override
	public void perform(final CourseLecture object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final CourseLecture object) {
		assert object != null;

		int courseId;
		int lecturerId;

		Course course;
		Collection<Lecture> lecturesNotInThisCourse;
		SelectChoices lectureChoices;

		final Tuple tuple;

		courseId = super.getRequest().getData("courseId", int.class);
		course = this.repository.findOneCourseById(courseId);
		lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		lecturesNotInThisCourse = this.repository.findAllPublishLecturesNotInThisCourse(courseId, lecturerId);
		lectureChoices = SelectChoices.from(lecturesNotInThisCourse, "title", object.getLecture());

		tuple = super.unbind(object, "lecture");
		tuple.put("lecture", lectureChoices.getSelected().getKey());
		tuple.put("lectureChoices", lectureChoices);
		tuple.put("courseId", courseId);
		tuple.put("courseCode", course.getCode());

		super.getResponse().setGlobal("courseId", courseId);
		super.getResponse().setData(tuple);
	}

}
