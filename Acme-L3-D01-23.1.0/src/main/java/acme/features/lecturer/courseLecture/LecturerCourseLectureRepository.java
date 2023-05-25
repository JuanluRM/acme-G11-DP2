
package acme.features.lecturer.courseLecture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.Lecture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface LecturerCourseLectureRepository extends AbstractRepository {

	//	@Query("select cl from CourseLecture cl where cl.course.id = :id")
	//	Collection<CourseLecture> findCourseLectureByCourseId(int id);

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select distinct(l) from Lecture l where l not in (select l from Lecture l left join CourseLecture cl on l.id = cl.lecture.id where cl.course.id = :courseId) and l.lecturer.id = :lecturerId")
	Collection<Lecture> findAllLecturesNotInThisCourse(int courseId, int lecturerId);

	@Query("select l from Lecture l where l.id = :id")
	Lecture findOneLectureById(int id);

}
