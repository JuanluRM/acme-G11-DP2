
package acme.features.any.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Audit;
import acme.entities.Course;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AnyAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.draftMode = false")
	Collection<Audit> findAuditsPublished();

	@Query("select a from Audit a where a.id=:id")
	Audit findOneAuditById(int id);

	@Query("select c from Course c")
	Collection<Course> findAllCourses();

	@Query("select au from Auditor au")
	Collection<Auditor> findAllAuditors();

}
