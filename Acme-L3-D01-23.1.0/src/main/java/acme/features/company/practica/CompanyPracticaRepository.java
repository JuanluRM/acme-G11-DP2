
package acme.features.company.practica;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.Practica;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface CompanyPracticaRepository extends AbstractRepository {

	@Query("select p from Practica p where p.company.id = :id")
	Collection<Practica> findPracticasByCompany(int id);

	@Query("select c from Company c where c.id = :id")
	Company findOneCompanyById(int id);

	@Query("select p from Practica p where p.id = :id")
	Practica findOnePracticaById(int id);

	@Query("select p from Practica p where p.code = :code")
	Practica findOnePracticaByCode(String code);

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from Course c")
	Collection<Course> findAllCourses();

	@Query("select c from Course c where c.publish = true")
	Collection<Course> findPublishedCourses();
}
