
package acme.features.auditor.auditingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditingRecordRepository extends AbstractRepository {

	@Query("select ar from AuditRecord ar where ar.audit.id=:id")
	Collection<AuditRecord> findManyAuditingRecordsByMasterId(int id);

	@Query("select a from Audit a where a.id = :id")
	Audit findOneAuditById(int id);

	@Query("select ar.audit from AuditRecord ar where ar.id=:id")
	Audit findOneAuditByAuditingRecordId(int id);

	@Query("select ar from AuditRecord ar where ar.id=:id")
	AuditRecord findOneAuditingRecordById(int id);

}
