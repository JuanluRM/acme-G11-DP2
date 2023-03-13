
package acme.forms;

import java.util.Map;

import acme.entities.tutorial.SessionType;
import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssistantDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<SessionType, Integer>	TotalNumberOfSessionType;

	Double						averageLearningTimeByTutorials;
	Double						deviationLearningTimeByTutorials;
	Double						minimumLearningTimeByTutoarials;
	Double						maximumLearningTimeByTutorials;

	Double						averageLearningTimeBySessions;
	Double						deviationLearningTimeBySessions;
	Double						minimumLearningTimeBySessions;
	Double						maximumLearningTimeBySessions;

}
