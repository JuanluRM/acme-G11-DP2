
package acme.features.assistant.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;
import form.AssistantDashboard;

@Service
public class AssistantDashboardShowService extends AbstractService<Assistant, AssistantDashboard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Assistant.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final AssistantDashboard assistantDashboard;

		Integer totalNumberOfTutorialsWithTheoricalCourses;
		Integer totalNumberOfTutorialsWithHandsOnlCourses;

		Double averageLearningTimeByTutorials;
		Double deviationLearningTimeByTutorials;
		Double minimumLearningTimeByTutorials;
		Double maximumLearningTimeByTutorials;

		Double averageLearningTimeBySessions;
		Double deviationLearningTimeBySessions;
		Double minimumLearningTimeBySessions;
		Double maximumLearningTimeBySessions;

		int assistantId;
		assistantId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfTutorialsWithTheoricalCourses = this.repository.numTutorialsOnTheoryCourses(assistantId);
		totalNumberOfTutorialsWithHandsOnlCourses = this.repository.numTutorialsOnHandsOnCourses(assistantId);

		averageLearningTimeByTutorials = this.repository.avgTutorialsTime(assistantId);
		deviationLearningTimeByTutorials = this.repository.stdevTutorialsTime(assistantId);
		minimumLearningTimeByTutorials = this.repository.minTutorialsTime(assistantId);
		maximumLearningTimeByTutorials = this.repository.maxTutorialsTime(assistantId);

		averageLearningTimeBySessions = this.repository.avgSessionTutorialsTime(assistantId);
		deviationLearningTimeBySessions = this.repository.stdevSessionTutorialsTime(assistantId);
		minimumLearningTimeBySessions = this.repository.minSessionTutorialsTime(assistantId);
		maximumLearningTimeBySessions = this.repository.maxSessionTutorialsTime(assistantId);

		//Create dashboard

		assistantDashboard = new AssistantDashboard();

		assistantDashboard.setTotalNumberOfTutorialsWithTheoricalCourses(totalNumberOfTutorialsWithTheoricalCourses);
		assistantDashboard.setTotalNumberOfTutorialsWithHandsOnlCourses(totalNumberOfTutorialsWithHandsOnlCourses);

		assistantDashboard.setAverageLearningTimeByTutorials(averageLearningTimeByTutorials);
		assistantDashboard.setDeviationLearningTimeByTutorials(deviationLearningTimeByTutorials);
		assistantDashboard.setMinimumLearningTimeByTutorials(minimumLearningTimeByTutorials);
		assistantDashboard.setMaximumLearningTimeByTutorials(maximumLearningTimeByTutorials);

		assistantDashboard.setAverageLearningTimeBySessions(averageLearningTimeBySessions);
		assistantDashboard.setDeviationLearningTimeBySessions(deviationLearningTimeBySessions);
		assistantDashboard.setMinimumLearningTimeBySessions(minimumLearningTimeBySessions);
		assistantDashboard.setMaximumLearningTimeBySessions(maximumLearningTimeBySessions);

		super.getBuffer().setData(assistantDashboard);
	}

	@Override
	public void unbind(final AssistantDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, "totalNumberOfTutorialsWithTheoricalCourses", "totalNumberOfTutorialsWithHandsOnlCourses", "averageLearningTimeByTutorials", "deviationLearningTimeByTutorials", "minimumLearningTimeByTutorials",
			"maximumLearningTimeByTutorials", "averageLearningTimeBySessions", "deviationLearningTimeBySessions", "minimumLearningTimeBySessions", "maximumLearningTimeBySessions");

		super.getResponse().setData(tuple);
	}
}
