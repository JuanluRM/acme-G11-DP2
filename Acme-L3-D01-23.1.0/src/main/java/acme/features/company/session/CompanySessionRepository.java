
package acme.features.company.session;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Session;
import acme.framework.repositories.AbstractRepository;

public interface CompanySessionRepository extends AbstractRepository {

	@Query("select s from Session s where s.id = :sessionId")
	Session findOneSessionById(int sessionId);

	@Query("select s from Session s where s.practica.id = :practicaId")
	Collection<Session> findManySessionsByPracticaId(int practicaId);
}
