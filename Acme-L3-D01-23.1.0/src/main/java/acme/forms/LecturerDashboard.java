
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LecturerDashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNTheoryLectures;
	Integer						totalNHandsOnLectures;

	Double						averageLearningTimeByLectures;
	Double						averageLearningTimeByCourses;

	Double						deviationLearningTimeByLectures;
	Double						deviationLearningTimeByCourses;

	Double						minimumLearningTimeByLectures;
	Double						minimumLearningTimeByCourses;

	Double						maximumLearningTimeByLectures;
	Double						maximumLearningTimeByCourses;

}
