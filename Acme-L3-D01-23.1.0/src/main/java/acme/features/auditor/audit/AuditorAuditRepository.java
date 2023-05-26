
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

	@Query("select a from Auditor a where a.userAccount.id = :accountId")
	Auditor findAuditorByAccountId(int accountId);

	@Query("select count(a)>0 from Audit a where a.code = :code and a.id != :id")
	boolean existsAuditWithCode(String code, int id);

	@Query("select c from Course c where c.publish = false")
	Collection<Course> findAllPublishedCourses();

	@Query("select c from Course c where c.id = :courseId")
	Course findCourseById(int courseId);
}
