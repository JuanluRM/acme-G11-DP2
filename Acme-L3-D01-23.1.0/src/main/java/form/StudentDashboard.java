
package form;

import java.io.Serializable;
import java.util.Map;

public class StudentDashboard implements Serializable {

	protected static final long	serialVersionUID	= 1L;

	Integer						totalNumStudentsForTheoryCourses;
	Integer						totalNumStudentsForHandsOnCourses;

	Double						averageStudents;
	Double						deviationStudents;
	Double						minStudents;
	Double						maxStudents;

	Map<String, Double>			averageTimeStudentRecords;
	Map<String, Double>			deviationTimeStudentRecords;
	Map<String, Double>			minTimeStudentRecords;
	Map<String, Double>			maxTimeStudentRecords;
}
