
package acme.forms;

import java.io.Serializable;
import java.util.Map;

import acme.entities.Course;
import acme.entities.Lecture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LecturerDashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNTheoryLectures;
	Integer						totalNHandsOnLectures;

	Map<Lecture, Double>		averageLearningTimeByLectures;
	Map<Course, Double>			averageLearningTimeByCourses;

	Map<Lecture, Double>		deviationLearningTimeByLectures;
	Map<Course, Double>			deviationLearningTimeByCourses;

	Map<Lecture, Double>		minimumLearningTimeByLectures;
	Map<Course, Double>			minimumLearningTimeByCourses;

	Map<Lecture, Double>		maximumLearningTimeByLectures;
	Map<Course, Double>			maximumLearningTimeByCourses;

}
