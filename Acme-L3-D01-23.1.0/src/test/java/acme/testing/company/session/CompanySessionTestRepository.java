
package acme.testing.company.session;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Practica;
import acme.entities.Session;
import acme.framework.repositories.AbstractRepository;

public interface CompanySessionTestRepository extends AbstractRepository {

	@Query("select s from Session s where s.practica = :practica")
	Collection<Session> findManySessionsByPractica(Practica practica);

	@Query("select p from Practica p where p.company.userAccount.username = :username")
	Collection<Practica> findManyPracticasByCompanyUsername(String username);
}
