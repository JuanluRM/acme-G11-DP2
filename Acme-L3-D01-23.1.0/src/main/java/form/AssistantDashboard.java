
package form;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssistantDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfTutorialsWithTheoricalCourses;
	Integer						totalNumberOfTutorialsWithHandsOnlCourses;

	Double						averageLearningTimeByTutorials;
	Double						deviationLearningTimeByTutorials;
	Double						minimumLearningTimeByTutorials;
	Double						maximumLearningTimeByTutorials;

	Double						averageLearningTimeBySessions;
	Double						deviationLearningTimeBySessions;
	Double						minimumLearningTimeBySessions;
	Double						maximumLearningTimeBySessions;

}
