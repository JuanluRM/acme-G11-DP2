
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordListService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository repository;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("auditId", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int auditId;
		Audit audit;
		auditId = super.getRequest().getData("auditId", int.class);
		audit = this.repository.findOneAuditById(auditId);
		status = audit != null && (!audit.isDraftMode() || super.getRequest().getPrincipal().hasRole(audit.getAuditor()));
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<AuditRecord> objects;
		int auditId;
		auditId = super.getRequest().getData("auditId", int.class);
		objects = this.repository.findManyAuditRecordsByMasterId(auditId);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "subject", "assessment");
		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<AuditRecord> objects) {
		assert objects != null;
		int auditId;
		Audit audit;
		boolean showCreate;
		auditId = super.getRequest().getData("auditId", int.class);
		audit = this.repository.findOneAuditById(auditId);
		showCreate = audit.isDraftMode() && super.getRequest().getPrincipal().hasRole(audit.getAuditor());
		super.getResponse().setGlobal("id", auditId);
		super.getResponse().setGlobal("showCreate", showCreate);
	}

}
