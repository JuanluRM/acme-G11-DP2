
package acme.features.any.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.entities.Course;
import acme.framework.components.accounts.Any;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AnyAuditShowService extends AbstractService<Any, Audit> {

	@Autowired
	protected AnyAuditRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final boolean status;
		final int id;
		final Audit audit;
		id = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditById(id);
		status = audit != null && !audit.isDraftMode();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Audit object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Audit object) {
		assert object != null;

		Tuple tuple;
		final Collection<Course> courses;
		final Collection<Auditor> auditors;
		final SelectChoices coursesChoices;
		final SelectChoices auditorsChoices;

		courses = this.repository.findAllCourses();
		coursesChoices = SelectChoices.from(courses, "title", object.getCourse());

		auditors = this.repository.findAllAuditors();
		auditorsChoices = SelectChoices.from(auditors, "professionalId", object.getAuditor());

		tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
		tuple.put("course", coursesChoices.getSelected().getKey());
		tuple.put("courses", coursesChoices);
		tuple.put("auditor", auditorsChoices.getSelected().getKey());
		tuple.put("auditors", auditorsChoices);

		super.getResponse().setData(tuple);
	}
}
