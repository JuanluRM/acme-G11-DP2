
package acme.features.auditor.auditorDashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AuditRecord;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AuditorAuditorDashboardRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = :accountId")
	Auditor findAuditorByAccountId(int accountId);

	@Query("select count(a) from Audit a where a.auditor.id = :id")
	Double totalNumberOfAudits(int id);

	@Query("select avg(select count(ar) from AuditRecord ar where ar.audit.id = a.id) from Audit a where a.auditor.id = :id")
	Double averageAuditingRecord(int id);

	@Query("select count(ar) from AuditRecord ar where ar.audit.auditor.id = :id group by ar.audit ")
	List<Integer> numberOfRecordsByAudit(int id);

	@Query("select min(select count(ar) from AuditRecord ar where ar.audit.id = a.id) from Audit a where a.auditor.id = :id")
	Double minAuditingRecord(int id);

	@Query("select max(select count(ar) from AuditRecord ar where ar.audit.id = a.id) from Audit a where a.auditor.id = :id")
	Double maxAuditingRecord(int id);

	@Query("select ar from AuditRecord ar where ar.audit.auditor.id = :id")
	List<AuditRecord> findAllAuditingRecordsByAuditorId(int id);

	default Double deviationAuditingRecord(final int id) {
		final List<Integer> numberOfRecords = this.numberOfRecordsByAudit(id);
		final Double average = numberOfRecords.stream().mapToInt(Integer::intValue).average().orElse(0);
		final List<Double> squaredDistancesToMean = numberOfRecords.stream().map(n -> Math.pow(n - average, 2.)).collect(Collectors.toList());
		final Double averageSquaredDistancesToMean = squaredDistancesToMean.stream().mapToDouble(Double::doubleValue).average().orElse(0);
		return Math.sqrt(averageSquaredDistancesToMean);
	}

}
