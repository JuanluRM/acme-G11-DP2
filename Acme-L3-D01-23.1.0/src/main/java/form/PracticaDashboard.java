
package form;

import java.io.Serializable;
import java.util.List;

public class PracticaDashboard implements Serializable {

	protected static final long	serialVersionUID	= 1L;

	List<Integer>				totalNumPracticaByMonth;

	Double						averagePeriodLengthOfSessions;
	Double						deviationPeriodLengthOfSessions;
	Double						minPeriodLengthOfSessions;
	Double						maxPeriodLengthOfSessions;

	Double						averagePeriodLengthOfPractica;
	Double						deviationPeriodLengthPractica;
	Double						minPeriodLengthPractica;
	Double						maxPeriodLengthPractica;

}
