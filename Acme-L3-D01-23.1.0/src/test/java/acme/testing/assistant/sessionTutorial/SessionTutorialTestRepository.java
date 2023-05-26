
package acme.testing.assistant.sessionTutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.SessionTutorial;
import acme.entities.Tutorial;
import acme.framework.repositories.AbstractRepository;

public interface SessionTutorialTestRepository extends AbstractRepository {

	@Query("select j from Tutorial j where j.assistant.userAccount.username = :username")
	Collection<Tutorial> findManyTutorialByAssistantUsername(String username);

	@Query("select j from SessionTutorial j where j.tutorial = :tutorial")
	Collection<SessionTutorial> findManySessionsByTutorial(Tutorial tutorial);

}
