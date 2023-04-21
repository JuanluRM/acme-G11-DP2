
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository repository;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int auditRecordId;
		Audit audit;
		auditRecordId = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditById(auditRecordId);
		status = audit != null && audit.isDraftMode() && super.getRequest().getPrincipal().hasRole(audit.getAuditor());
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final AuditRecord object;
		int auditRecordId;
		Audit audit;
		auditRecordId = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditById(auditRecordId);
		object = new AuditRecord();
		object.setSubject("");
		object.setAssessment("");
		object.setStartAudition(null);
		object.setEndAudition(null);
		object.setMark("");
		object.setAudit(audit);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;
		super.bind(object, "subject", "assessment", "startAudition", "endAudition", "mark", "link");
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "subject", "assessment", "startAudition", "endAudition", "mark", "link");
		tuple.put("id", super.getRequest().getData("id", int.class));
		tuple.put("draftMode", object.getAudit().isDraftMode());
		super.getResponse().setData(tuple);
	}

}
