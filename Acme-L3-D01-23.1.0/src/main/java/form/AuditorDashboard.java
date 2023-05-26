
package form;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	protected static final long	serialVersionUID	= 1L;

	Double						totalNumberOfAudits;

	Double						averageAuditingRecord;
	Double						deviationAuditingRecord;
	Double						minAuditingRecord;
	Double						maxAuditingRecord;

	Double						averageTimeAuditingRecords;
	Double						deviationTimeAuditingRecords;
	Double						minTimeAuditingRecords;
	Double						maxTimeAuditingRecords;

}
