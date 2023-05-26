
package acme.features.auditor.auditRecord;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select ar from AuditRecord ar where ar.audit.id=:auditId")
	Collection<AuditRecord> findManyAuditRecordsByMasterId(int auditId);

	@Query("select a from Audit a where a.id = :id")
	Audit findOneAuditById(int id);

	@Query("select ar.audit from AuditRecord ar where ar.id=:id")
	Audit findOneAuditByAuditRecordId(int id);

	@Query("select ar from AuditRecord ar where ar.id=:id")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select max(ar.endAudition) from AuditRecord ar where ar.audit.id = :auditId")
	Date findMaximumAuditRecordsDateByAuditId(int auditId);

	@Query("select p from Audit p where p.id = :id")
	Audit findAuditById(int id);
}
