
package acme.features.company.practica;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Practica;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PracticaRepository extends AbstractRepository {

	@Query("select p from Practica p where p.company.id = :id")
	Collection<Practica> findPracticasByCompany(int id);

	@Query("select p from Practica p where p.id = :id")
	Practica findOnePracticaById(int id);

}
