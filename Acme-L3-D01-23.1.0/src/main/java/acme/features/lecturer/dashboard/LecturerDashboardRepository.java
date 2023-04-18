
package acme.features.lecturer.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface LecturerDashboardRepository extends AbstractRepository {

	//Total number of lectures
	@Query("select count(l) from Lecture l where l.lecturer.id = :lecturerId and l.type = 'THEORICAL'")
	Integer totalNumberOfTheoricalLecturesOfLecturer(int lecturerId);

	@Query("select count(l) from Lecture l where l.lecturer.id = :lecturerId and l.type = 'HANDS_ON'")
	Integer totalNumberOfHandsOnLecturesOfLecturer(int lecturerId);

	//Average time of lectures
	@Query("select avg(l.estimatedLearningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double averageEstimatedLearningTimeOfLecturesOfLecturer(final int lecturerId);

	@Query("select avg(l.estimatedLearningTime) from Lecture l where l.id in (select cl.lecture from CourseLecture cl where cl.course in (select c from Course c where c.lecturer.id = :lecturerId))")
	Double averageEstimatedLearningTimeOfCoursesOfLecturer(int lecturerId);

	//Deviation time of lectures 
	@Query("select sqrt(avg(l.estimatedLearningTime * l.estimatedLearningTime) - avg(l.estimatedLearningTime) * avg(l.estimatedLearningTime)) from Lecture l where l.lecturer.id = :lecturerId")
	Double deviationEstimatedLearningTimeOfLecturesOfLecturer(int lecturerId);

	@Query("select sqrt(avg(l.estimatedLearningTime * l.estimatedLearningTime) - avg(l.estimatedLearningTime) * avg(l.estimatedLearningTime)) from Lecture l where l.id in (select cl.lecture from CourseLecture cl where cl.course in (select c from Course c where c.lecturer.id = :lecturerId))")
	Double deviationEstimatedLearningTimeOfCoursesOfLecturer(int lecturerId);

	//Minimun time of lectures
	@Query("select min(l.estimatedLearningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double minimumEstimatedLearningTimeOfLecturesOfLecturer(int lecturerId);

	@Query("select min(l.estimatedLearningTime) from Lecture l where l.id in (select cl.lecture from CourseLecture cl where cl.course in (select c from Course c where c.lecturer.id = :lecturerId))")
	Double minimumEstimatedLearningTimeOfCoursesOfLecturer(int lecturerId);

	//Maximum time of lectures
	@Query("select max(l.estimatedLearningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double maximumEstimatedLearningTimeOfLecturesOfLecturer(int lecturerId);

	@Query("select max(l.estimatedLearningTime) from Lecture l where l.id in (select cl.lecture from CourseLecture cl where cl.course in (select c from Course c where c.lecturer.id = :lecturerId))")
	Double maximumEstimatedLearningTimeOfCoursesOfLecturer(int lecturerId);

}
