
package acme.entities;

import java.io.Serializable;
import java.util.Map;

public class AuditorDashboard implements Serializable {

	protected static final long	serialVersionUID	= 1L;

	Integer						totalNumAuditsForTheoryCourses;
	Integer						totalNumAuditsForHandsOnCourses;

	Double						averageAudits;
	Double						deviationAudits;
	Double						minAudits;
	Double						maxAudits;

	Map<String, Double>			averageTimeAuditingRecords;
	Map<String, Double>			deviationTimeAuditingRecords;
	Map<String, Double>			minTimeAuditingRecords;
	Map<String, Double>			maxTimeAuditingRecords;

}
