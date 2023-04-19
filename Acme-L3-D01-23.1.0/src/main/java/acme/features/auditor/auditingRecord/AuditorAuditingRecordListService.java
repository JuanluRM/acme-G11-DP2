
package acme.features.auditor.auditingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditingRecordListService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditingRecordRepository repository;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Audit audit;
		masterId = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditById(masterId);
		status = audit != null && (!audit.isDraftMode() || super.getRequest().getPrincipal().hasRole(audit.getAuditor()));
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<AuditRecord> objects;
		int masterId;
		masterId = super.getRequest().getData("id", int.class);
		objects = this.repository.findManyAuditingRecordsByMasterId(masterId);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "subject", "assessment", "startAudition", "endAudition", "mark", "link");
		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<AuditRecord> objects) {
		assert objects != null;
		int masterId;
		Audit audit;
		boolean showCreate;
		masterId = super.getRequest().getData("id", int.class);
		audit = this.repository.findOneAuditById(masterId);
		showCreate = audit.isDraftMode() && super.getRequest().getPrincipal().hasRole(audit.getAuditor());
		super.getResponse().setGlobal("id", masterId);
		super.getResponse().setGlobal("showCreate", showCreate);
	}

}
