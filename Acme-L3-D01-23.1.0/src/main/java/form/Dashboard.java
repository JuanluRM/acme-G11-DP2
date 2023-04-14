
package form;

import java.util.Map;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<String, Integer>		numberPerPrincipal;
	Double						ratioOfCompletePeeps;
	Double						ratioOfCriticalBulletin;
	Map<String, Double>			averageBudgetPerCurrency;
	Map<String, Double>			minimumBudgetPerCurrency;
	Map<String, Double>			maximumBudgetPerCurrency;
	Map<String, Double>			standardDeviationBudgetPerCurrency;
	Double						averageNotesLast10Weeks;
	Double						minimumNotesLast10Weeks;
	Double						maximumNotesLast10Weeks;
	Double						standardDeviationNotesLast10Weeks;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
