
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AuditRecord;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordPublishService extends AbstractService<Auditor, AuditRecord> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuditorAuditRecordRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {

		boolean status;
		final AuditRecord ar = this.repository.findOneAuditRecordById(super.getRequest().getData("id", int.class));
		status = super.getRequest().getPrincipal().hasRole(ar.getAudit().getAuditor()) && ar.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;

		object = this.repository.findOneAuditRecordById(super.getRequest().getData("id", int.class));
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "subject", "assessment", "startAudition", "endAudition", "link", "draftMode");

	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("startAudition") && !super.getBuffer().getErrors().hasErrors("endAudition"))
			if (!MomentHelper.isBefore(object.getStartAudition(), object.getEndAudition()))
				super.state(false, "startAudition", "auditor.auditrecord.error.date.startAfterFinish");
			else
				super.state(!(object.getHoursFromPeriod() < 1), "startAudition", "auditor.auditrecord.error.date.shortPeriod");

	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;
		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		final Tuple tuple;
		//final SelectChoices marks = SelectChoices.from(Mark.class, object.getMark());

		tuple = super.unbind(object, "subject", "assessment", "startAudition", "endAudition", "link", "draftMode");
		//tuple.put("marks", marks);

		super.getResponse().setData(tuple);
	}

}
