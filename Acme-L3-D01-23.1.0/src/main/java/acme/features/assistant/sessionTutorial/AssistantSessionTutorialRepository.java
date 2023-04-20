
package acme.features.assistant.sessionTutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SessionTutorial;
import acme.entities.Tutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AssistantSessionTutorialRepository extends AbstractRepository {

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findOneTutorialById(int id);

	@Query("select s from SessionTutorial s where s.tutorial.id = :masterId")
	Collection<SessionTutorial> findManySessionsByMasterId(int masterId);

	@Query("select s.tutorial from SessionTutorial s where s.id = :id")
	Tutorial findOneTutorialBySessionTutorialId(int id);

	@Query("select s from SessionTutorial s where s.id = :id")
	SessionTutorial findOneSessionTutorialById(int id);

}
