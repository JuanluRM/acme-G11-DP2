
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.CourseLecture;
import acme.entities.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLecturesCreateService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLecturesRepository repository;

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
		Course course;

		courseId = super.getRequest().getData("courseId", int.class);
		course = this.repository.findOneCourseById(courseId);
		status = course != null && super.getRequest().getPrincipal().hasRole(course.getLecturer());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Lecture lecture;

		lecture = new Lecture();
		lecture.setTitle("");
		lecture.setLectureAbstract("");
		lecture.setEstimatedLearningTime(0.0);
		lecture.setBody("");
		lecture.setPublish(false);
		lecture.setLink("");

		super.getBuffer().setData(lecture);
	}

	@Override
	public void bind(final Lecture object) {
		assert object != null;

		super.bind(object, "title", "lectureAbstract", "estimatedLearningTime", "body", "type", "publish", "link");
	}

	@Override
	public void validate(final Lecture object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("estimatedLearningTime"))
			super.state(object.getEstimatedLearningTime() > 0, "learningTime", "lecturer.course.error.LearningTime.negative");
	}

	@Override
	public void perform(final Lecture object) {
		assert object != null;

		this.repository.save(object);
		if (super.getRequest().hasData("courseId", int.class)) {
			final CourseLecture cl = new CourseLecture();
			cl.setCourse(this.repository.findOneCourseById(super.getRequest().getData("courseId", int.class)));
			cl.setLecture(object);
			this.repository.save(cl);
		}
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "lectureAbstract", "estimatedLearningTime", "body", "type", "link");
		//tuple.put("courseId", super.getRequest().getData("courseId", int.class));
		super.getResponse().setData(tuple);
	}

}
