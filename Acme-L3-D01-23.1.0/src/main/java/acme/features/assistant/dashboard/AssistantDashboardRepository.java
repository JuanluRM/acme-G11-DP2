
package acme.features.assistant.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SessionTutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AssistantDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Tutorial t where t.course.type=2 and t.assistant.id = :assistantId")
	Integer numTutorialsOnHandsOnCourses(int assistantId);

	@Query("select count(t) from Tutorial t where t.course.type=0 and t.assistant.id = :assistantId")
	Integer numTutorialsOnTheoryCourses(int assistantId);

	@Query("select s from SessionTutorial s where s.tutorial.id = :tutorialId")
	Collection<SessionTutorial> getSessionTutorialsFromTutorialId(int tutorialId);

	@Query("select t.id from Tutorial t where t.assistant.id= :assistantId")
	Collection<Integer> getTutorialIdsFromAssistantId(int assistantId);

	@Query("select avg(TIME_TO_SEC(TIMEDIFF(t.finishMoment, t.startMoment)) / 3600.0) from Tutorial t where t.assistant.id= :assistantId")
	Double avgTutorialsTime(int assistantId);

	@Query("select min(TIME_TO_SEC(TIMEDIFF(t.finishMoment, t.startMoment)) / 3600.0) from Tutorial t where t.assistant.id= :assistantId")
	Double minTutorialsTime(int assistantId);

	@Query("select max(TIME_TO_SEC(TIMEDIFF(t.finishMoment, t.startMoment)) / 3600.0) from Tutorial t where t.assistant.id= :assistantId")
	Double maxTutorialsTime(int assistantId);

	@Query("select stddev(TIME_TO_SEC(TIMEDIFF(t.finishMoment, t.startMoment)) / 3600.0) from Tutorial t where t.assistant.id= :assistantId")
	Double stdevTutorialsTime(int assistantId);

	@Query("select avg(TIME_TO_SEC(TIMEDIFF(s.finishMoment, s.startMoment)) / 3600.0) from SessionTutorial s where s.tutorial.assistant.id= :assistantId")
	Double avgSessionTutorialsTime(int assistantId);

	@Query("select min(TIME_TO_SEC(TIMEDIFF(s.finishMoment, s.startMoment)) / 3600.0) from SessionTutorial s where s.tutorial.assistant.id= :assistantId")
	Double minSessionTutorialsTime(int assistantId);

	@Query("select max(TIME_TO_SEC(TIMEDIFF(s.finishMoment, s.startMoment)) / 3600.0) from SessionTutorial s where s.tutorial.assistant.id= :assistantId")
	Double maxSessionTutorialsTime(int assistantId);

	@Query("select stddev(TIME_TO_SEC(TIMEDIFF(s.finishMoment, s.startMoment)) / 3600.0) from SessionTutorial s where s.tutorial.assistant.id= :assistantId")
	Double stdevSessionTutorialsTime(int assistantId);

}
