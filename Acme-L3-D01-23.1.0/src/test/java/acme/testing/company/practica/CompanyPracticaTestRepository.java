
package acme.testing.company.practica;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Practica;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

public interface CompanyPracticaTestRepository extends AbstractRepository {

	@Query("select c from Company c where c.userAccount.username = :username")
	Collection<Company> findManyCoursesByLecturerUsername(String username);

	@Query("select p from Practica p where p.company.userAccount.username = :username")
	Collection<Practica> findManyPracticasByCompanyUsername(String username);
}
