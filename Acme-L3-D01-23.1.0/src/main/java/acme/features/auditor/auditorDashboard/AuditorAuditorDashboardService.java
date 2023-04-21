
package acme.features.auditor.auditorDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;
import form.AuditorDashboard;

@Service
public class AuditorAuditorDashboardService extends AbstractService<Auditor, AuditorDashboard> {

	@Autowired
	protected AuditorAuditorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {

		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Auditor.class));

	}

	@Override
	public void load() {

		int id;
		id = this.repository.findAuditorByAccountId(super.getRequest().getPrincipal().getAccountId()).getId();

		AuditorDashboard Auditordashboard;
		Double totalNumberOfAudits;
		Double averageAuditingRecord;
		Double deviationAuditingRecord;
		Double minAuditingRecord;
		Double maxAuditingRecord;

		final Double averageTimeAuditingRecords;
		final Double deviationTimeAuditingRecords;
		final Double minTimeAuditingRecords;
		final Double maxTimeAuditingRecords;

		totalNumberOfAudits = this.repository.totalNumberOfAudits(id);
		averageAuditingRecord = this.repository.averageAuditingRecord(id);
		minAuditingRecord = this.repository.minAuditingRecord(id);
		maxAuditingRecord = this.repository.maxAuditingRecord(id);
		deviationAuditingRecord = this.repository.deviationAuditingRecord(id);

		//averageTimeAuditingRecords = this.repository.averageTimeOfAuditingRecords(id);
		//deviationTimeAuditingRecords = this.repository.deviationTimeAuditingRecords(id);
		//minTimeAuditingRecords = this.repository.minTimeAuditingRecords(id);
		//maxTimeAuditingRecords = this.repository.maxTimeAuditingRecords(id);

		Auditordashboard = new AuditorDashboard();
		Auditordashboard.setTotalNumberOfAudits(totalNumberOfAudits);
		Auditordashboard.setAverageAuditingRecord(averageAuditingRecord);
		Auditordashboard.setMinAuditingRecord(minAuditingRecord);
		Auditordashboard.setMaxAuditingRecord(maxAuditingRecord);
		Auditordashboard.setDeviationAuditingRecord(deviationAuditingRecord);

		//Auditordashboard.setaverageTimeAuditingRecords(averageTimeAuditingRecords);
		//Auditordashboard.setTdeviationTimeAuditingRecords(deviationTimeAuditingRecords);
		//Auditordashboard.setminTimeAuditingRecords(minTimeAuditingRecords);
		//Auditordashboard.setmaxTimeAuditingRecords(maxTimeAuditingRecords);

		super.getBuffer().setData(Auditordashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, "totalNumberOfAudits", "averageAuditingRecord", "deviationAuditingRecord", "maxAuditingRecord", "minAuditingRecord");

		super.getResponse().setData(tuple);
	}
}
