
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Lecture;
import acme.entities.LectureType;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLecturesUpdateService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLecturesRepository repository;

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
		Lecture lecture;

		lecture = this.repository.findOneLectureById(super.getRequest().getData("id", int.class));
		status = super.getRequest().getPrincipal().hasRole(lecture.getLecturer());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Lecture lecture;
		int lectureId;

		lectureId = super.getRequest().getData("id", int.class);
		lecture = this.repository.findOneLectureById(lectureId);
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
			super.state(object.getEstimatedLearningTime() > 0, "estimatedLearningTime", "lecturer.lecture.error.estimatedLearningTime.negative");

	}

	@Override
	public void perform(final Lecture object) {
		assert object != null;

		this.repository.save(object);
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
