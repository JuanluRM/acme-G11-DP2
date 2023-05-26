
package acme.features.student;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Activity;
import acme.entities.Enrolment;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentActivityListService extends AbstractService<Student, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("enrolmentId", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final Collection<Activity> activities;
		Enrolment enrolment;
		final int enrolmentId = super.getRequest().getData("enrolmentId", int.class);

		activities = this.repository.findAllActivitiesByEnrolment(enrolmentId);
		enrolment = this.repository.findEnrolmentById(enrolmentId);
		super.getResponse().setGlobal("isFinalised", enrolment.getIsFinalised());
		super.getResponse().setGlobal("enrolmentId", enrolmentId);

		super.getBuffer().setData(activities);
	}

	@Override
	public void unbind(final Activity object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "title", "activityAbstract", "activityType", "startPeriod", "endPeriod", "link");
		super.getResponse().setData(tuple);
	}

}
