
package form;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNTheoricalLectures;
	Integer						totalNHandsOnLectures;

	Double						averageEstimatedLearningTimeByLectures;
	Double						averageEstimatedLearningTimeByCourses;

	Double						deviationEstimatedLearningTimeByLectures;
	Double						deviationEstimatedLearningTimeByCourses;

	Double						minimumEstimatedLearningTimeByLectures;
	Double						minimumEstimatedLearningTimeByCourses;

	Double						maximumEstimatedLearningTimeByLectures;
	Double						maximumEstimatedLearningTimeByCourses;

}
