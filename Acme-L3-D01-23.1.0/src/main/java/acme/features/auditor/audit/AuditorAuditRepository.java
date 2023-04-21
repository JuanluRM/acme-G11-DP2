
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.AuditRecord;
import acme.entities.Course;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AuditorAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id=:id")
	Audit findOneAuditById(int id);

	@Query("select au from Auditor au where au.id=:id")
	Auditor findOneAuditorById(int id);

	@Query("select a from Audit a where a.auditor.id=:auditorId")
	Collection<Audit> findManyAuditsByAuditorId(int auditorId);

	@Query("select au from Auditor au")
	Collection<Auditor> findAllAuditors();

	@Query("select c from Course c")
	Collection<Course> findAllCourses();

	@Query("select c from Course c where c.id=:id")
	Course findOneCourseById(int id);

	@Query("select ar from AuditRecord ar where ar.audit.id=:id")
	Collection<AuditRecord> findManyAuditingRecordsByAuditId(int id);
}
