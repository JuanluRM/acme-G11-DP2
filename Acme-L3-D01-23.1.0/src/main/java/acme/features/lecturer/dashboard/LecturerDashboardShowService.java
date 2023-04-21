
package acme.features.lecturer.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;
import form.LecturerDashboard;

@Service
public class LecturerDashboardShowService extends AbstractService<Lecturer, LecturerDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerDashboardRepository repository;

	// AbstractServiceInterface -----------------------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);

	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Lecturer.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		LecturerDashboard lecturerDashboard;

		Integer totalNumberOfTheoricalLectures;
		Integer totalNumberOfHandsOnLectures;

		Double averageEstimatedLearningTimeOfLectures;
		Double averageEstimatedLearningTimeOfCourses;

		Double deviationEstimatedLearningTimeOfLectures;
		Double deviationEstimatedLearningTimeOfCourses;

		Double minimumEstimatedLearningTimeOfLectures;
		Double minimumEstimatedLearningTimeOfCourses;

		Double maximumEstimatedLearningTimeOfLectures;
		Double maximumEstimatedLearningTimeOfCourses;

		int lecturerId;
		lecturerId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfTheoricalLectures = this.repository.totalNumberOfTheoricalLecturesOfLecturer(lecturerId);
		totalNumberOfHandsOnLectures = this.repository.totalNumberOfHandsOnLecturesOfLecturer(lecturerId);

		averageEstimatedLearningTimeOfLectures = this.repository.averageEstimatedLearningTimeOfLecturesOfLecturer(lecturerId);
		averageEstimatedLearningTimeOfCourses = this.repository.averageEstimatedLearningTimeOfCoursesOfLecturer(lecturerId);

		deviationEstimatedLearningTimeOfLectures = this.repository.deviationEstimatedLearningTimeOfLecturesOfLecturer(lecturerId);
		deviationEstimatedLearningTimeOfCourses = this.repository.deviationEstimatedLearningTimeOfCoursesOfLecturer(lecturerId);

		minimumEstimatedLearningTimeOfLectures = this.repository.minimumEstimatedLearningTimeOfLecturesOfLecturer(lecturerId);
		minimumEstimatedLearningTimeOfCourses = this.repository.minimumEstimatedLearningTimeOfCoursesOfLecturer(lecturerId);

		maximumEstimatedLearningTimeOfCourses = this.repository.maximumEstimatedLearningTimeOfCoursesOfLecturer(lecturerId);
		maximumEstimatedLearningTimeOfLectures = this.repository.maximumEstimatedLearningTimeOfLecturesOfLecturer(lecturerId);

		//Create dashboard

		lecturerDashboard = new LecturerDashboard();

		lecturerDashboard.setTotalNTheoricalLectures(totalNumberOfTheoricalLectures);
		lecturerDashboard.setTotalNHandsOnLectures(totalNumberOfHandsOnLectures);

		lecturerDashboard.setAverageEstimatedLearningTimeByLectures(averageEstimatedLearningTimeOfLectures);
		lecturerDashboard.setAverageEstimatedLearningTimeByCourses(averageEstimatedLearningTimeOfCourses);

		lecturerDashboard.setDeviationEstimatedLearningTimeByLectures(deviationEstimatedLearningTimeOfLectures);
		lecturerDashboard.setDeviationEstimatedLearningTimeByCourses(deviationEstimatedLearningTimeOfCourses);

		lecturerDashboard.setMinimumEstimatedLearningTimeByLectures(minimumEstimatedLearningTimeOfLectures);
		lecturerDashboard.setMinimumEstimatedLearningTimeByCourses(minimumEstimatedLearningTimeOfCourses);

		lecturerDashboard.setMaximumEstimatedLearningTimeByLectures(maximumEstimatedLearningTimeOfLectures);
		lecturerDashboard.setMaximumEstimatedLearningTimeByCourses(maximumEstimatedLearningTimeOfCourses);

		super.getBuffer().setData(lecturerDashboard);
	}

	@Override
	public void unbind(final LecturerDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, "totalNTheoricalLectures", "totalNHandsOnLectures", "averageEstimatedLearningTimeByLectures", "deviationEstimatedLearningTimeByLectures", "minimumEstimatedLearningTimeByLectures",
			"maximumEstimatedLearningTimeByLectures", "averageEstimatedLearningTimeByCourses", "deviationEstimatedLearningTimeByCourses", "minimumEstimatedLearningTimeByCourses", "maximumEstimatedLearningTimeByCourses");

		super.getResponse().setData(tuple);
	}

}
