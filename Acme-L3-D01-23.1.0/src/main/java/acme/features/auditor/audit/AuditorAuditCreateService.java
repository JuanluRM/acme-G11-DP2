
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.entities.Course;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.controllers.HttpMethod;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditCreateService extends AbstractService<Auditor, Audit> {

	@Autowired
	protected AuditorAuditRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {

		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Auditor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		Audit object;

		object = new Audit();
		object.setDraftMode(true);
		super.getBuffer().setData(object);

	}

	@Override
	public void bind(final Audit object) {

		assert object != null;

		final int courseId = super.getRequest().getData("course", int.class);
		final Course course = this.repository.findCourseById(courseId);

		super.bind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
		final Auditor auditor = this.repository.findAuditorByAccountId(super.getRequest().getPrincipal().getAccountId());
		object.setAuditor(auditor);
		object.setCourse(course);
	}

	@Override
	public void validate(final Audit object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {

			final boolean existing = this.repository.existsAuditWithCode(object.getCode(), object.getId());
			super.state(!existing, "code", "auditor.audit.error.code.duplicated");
		}

	}

	@Override
	public void perform(final Audit object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final Audit object) {

		assert object != null;
		Collection<Course> courses;
		SelectChoices coursesChoices;
		Tuple tuple;
		courses = this.repository.findAllCourses();
		coursesChoices = SelectChoices.from(courses, "title", object.getCourse());
		tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
		tuple.put("course", coursesChoices.getSelected().getKey());
		tuple.put("courses", coursesChoices);
		super.getResponse().setData(tuple);

	}
	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals(HttpMethod.POST))
			PrincipalHelper.handleUpdate();
	}

}
