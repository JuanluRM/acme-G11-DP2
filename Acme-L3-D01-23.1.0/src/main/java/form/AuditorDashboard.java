
package form;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	protected static final long	serialVersionUID	= 1L;

	//Map<SessionType, Integer>	totalNumAuditsForTheoryCourses;
	//Map<SessionType, Integer>	totalNumAuditsForHandsOnCourses;

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
