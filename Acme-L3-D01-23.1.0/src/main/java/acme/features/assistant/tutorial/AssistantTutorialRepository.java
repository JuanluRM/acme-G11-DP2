
package acme.features.assistant.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.SessionTutorial;
import acme.entities.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Assistant;

@Repository
public interface AssistantTutorialRepository extends AbstractRepository {

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findOneTutorialById(int id);

	@Query("select t from Tutorial t where t.code = :code")
	Tutorial findOneTutorialByCode(String code);

	@Query("select a from Assistant a where a.id = :id")
	Assistant findOneAssistantById(int id);

	@Query("select t from Tutorial t where t.assistant.id = :assistantId")
	Collection<Tutorial> findManyTutorialsByAssistantId(int assistantId);

	@Query("select t.course from Tutorial t where t.id = :id")
	Course findOneCourseByTutorialId(int id);

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from Course c")
	Collection<Course> findAllCourses();

	@Query("select c from Course c where c.publish = true")
	Collection<Course> findPublishedCourses();

	@Query("select t.code from Tutorial t")
	Collection<String> findAllCodes();

	@Query("select s from SessionTutorial s where s.tutorial.id = :id")
	Collection<SessionTutorial> findManySessionsByTutorialId(int id);
}
