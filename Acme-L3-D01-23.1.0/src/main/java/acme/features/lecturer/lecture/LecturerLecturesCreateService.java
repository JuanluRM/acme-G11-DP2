
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.CourseLecture;
import acme.entities.Lecture;
import acme.entities.LectureType;
import acme.framework.components.jsp.SelectChoices;
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

		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Course course;

		if (super.getRequest().hasData("masterId", int.class)) {
			masterId = super.getRequest().getData("masterId", int.class);
			course = this.repository.findOneCourseById(masterId);
			status = course != null && course.getPublish() == false && super.getRequest().getPrincipal().hasRole(course.getLecturer());
		} else
			status = super.getRequest().getPrincipal().hasRole(Lecturer.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int userAccountId;
		Lecture lecture;
		Lecturer lecturer;

		userAccountId = super.getRequest().getPrincipal().getActiveRoleId();

		lecturer = this.repository.findOneLecturerById(userAccountId);

		lecture = new Lecture();
		lecture.setPublish(false);
		lecture.setLecturer(lecturer);

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

		if (super.getRequest().hasData("masterId", int.class)) {
			final CourseLecture cl = new CourseLecture();
			cl.setCourse(this.repository.findOneCourseById(super.getRequest().getData("masterId", int.class)));
			cl.setLecture(object);
			this.repository.save(cl);
		}
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		int masterId;
		Tuple tuple;

		tuple = super.unbind(object, "title", "lectureAbstract", "estimatedLearningTime", "body", "type", "publish", "link");
		tuple.put("lectureTypes", SelectChoices.from(LectureType.class, object.getType()));

		if (super.getRequest().hasData("masterId", int.class)) {
			masterId = super.getRequest().getData("masterId", int.class);
			super.getResponse().setGlobal("masterId", masterId);
		}

		super.getResponse().setData(tuple);
	}

}
