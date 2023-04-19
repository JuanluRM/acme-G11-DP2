
package acme.features.auditor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;
import form.AuditorDashboard;

@Service
public class AuditorDashboardService extends AbstractService<Auditor, AuditorDashboard> {

	@Autowired
	protected AuditorDashboardRepository repository;

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

		final int id = this.repository.findAuditorByAccountId(super.getRequest().getPrincipal().getAccountId()).getId();

		AuditorDashboard dashboard;
		Double totalNumberOfAudits;
		//Double averageNumberOfAuditingRecords;
		Double deviationOfAuditingRecords;
		Double minimumNumberOfAuditingRecords;
		Double maximumNumberOfAuditingRecords;
		//Double averageTimeOfAuditingRecords;
		//Double timeDeviationOfAuditingRecords;
		//Double minimumTimeOfAuditingRecords;
		//Double maximumTimeOfAuditingRecords;

		totalNumberOfAudits = this.repository.totalNumberOfAudits(id);
		//averageNumberOfAuditingRecords = this.repository.averageNumberOfAuditingRecords(id);
		minimumNumberOfAuditingRecords = this.repository.minimumNumberOfAuditingRecords(id);
		maximumNumberOfAuditingRecords = this.repository.maximumNumberOfAuditingRecords(id);
		deviationOfAuditingRecords = this.repository.deviationOfAuditingRecords(id);
		//averageTimeOfAuditingRecords = this.repository.averageTimeOfAuditingRecords(id);
		//timeDeviationOfAuditingRecords = this.repository.timeDeviationOfAuditingRecords(id);
		//minimumTimeOfAuditingRecords = this.repository.minimumTimeOfAuditingRecords(id);
		//maximumTimeOfAuditingRecords = this.repository.maximumTimeOfAuditingRecords(id);

		dashboard = new AuditorDashboard();
		dashboard.setTotalNumberOfAudits(totalNumberOfAudits);
		//dashboard.setAverageAuditingRecord(averageNumberOfAuditingRecords);
		dashboard.setMinAuditingRecord(minimumNumberOfAuditingRecords);
		dashboard.setMaxAuditingRecord(maximumNumberOfAuditingRecords);
		dashboard.setDeviationAuditingRecord(deviationOfAuditingRecords);
		//dashboard.setAverageTimeOfAuditingRecords(averageTimeOfAuditingRecords);
		//dashboard.setTimeDeviationOfAuditingRecords(timeDeviationOfAuditingRecords);
		//dashboard.setMinimumTimeOfAuditingRecords(minimumTimeOfAuditingRecords);
		//dashboard.setMaximumTimeOfAuditingRecords(maximumTimeOfAuditingRecords);

		super.getBuffer().setData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, "totalNumberOfAudits", "minimumNumberOfAuditingRecords", "maximumNumberOfAuditingRecords", "deviationOfAuditingRecords");

		super.getResponse().setData(tuple);
	}
}
