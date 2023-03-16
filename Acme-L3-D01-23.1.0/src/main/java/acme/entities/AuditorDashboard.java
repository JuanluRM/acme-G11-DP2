
package acme.entities;

import java.io.Serializable;

public class AuditorDashboard implements Serializable {

	protected static final long	serialVersionUID	= 1L;

	//Map<SessionType, Integer>	totalNumAuditsForTheoryCourses;
	//Map<SessionType, Integer>	totalNumAuditsForHandsOnCourses;

	Double						averageAudits;
	Double						deviationAudits;
	Double						minAudits;
	Double						maxAudits;

	Double						averageTimeAuditingRecords;
	Double						deviationTimeAuditingRecords;
	Double						minTimeAuditingRecords;
	Double						maxTimeAuditingRecords;

}
