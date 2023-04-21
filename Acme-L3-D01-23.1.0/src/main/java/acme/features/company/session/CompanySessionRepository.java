
package acme.features.company.session;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Practica;
import acme.entities.Session;
import acme.framework.repositories.AbstractRepository;

public interface CompanySessionRepository extends AbstractRepository {

	@Query("select s from Session s where s.id = :sessionId")
	Session findOneSessionById(int sessionId);

	@Query("select s from Session s where s.practica.id = :practicaId")
	Collection<Session> findManySessionsByPracticaId(int practicaId);

	@Query("select p from Practica p where p.id = :id")
	Practica findOnePracticaById(int id);

	@Query("select s.practica from Session s where s.id = :id")
	Practica findOnePracticaBySessionId(int id);

	@Query("select s from Session s where s.practica.id = :practicaId")
	Collection<Session> findSessionByPracticaId(int practicaId);
}
