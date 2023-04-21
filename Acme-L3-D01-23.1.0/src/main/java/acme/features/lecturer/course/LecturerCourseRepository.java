
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.CourseType;
import acme.entities.Lecture;
import acme.entities.LectureType;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerCourseRepository extends AbstractRepository {

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from Course c where c.lecturer.id = :id")
	Collection<Course> findCoursesByLecturerId(int id);

	@Query("select l from Lecture l where l.id in (select cl.lecture from CourseLecture cl where cl.course in (select c from Course c where c.id = :courseId))")
	Collection<Lecture> findLecturesByCourseId(int courseId);

	@Query("select l from Lecturer l where l.id = :id")
	Lecturer findOneLecturerById(int id);

	@Query("select c from Course c where c.code = :code")
	Course findOneCourseByCode(String code);

	@Query("select count(cl) > 0 from CourseLecture cl where cl.lecture.publish = false AND cl.course.id = :courseId")
	boolean findLecturesNotPublishedByCourse(int courseId);

	@Query("select count(cl) > 0 from CourseLecture cl where cl.lecture.type = acme.entities.LectureType.HANDS_ON and cl.course.id = :courseId")
	boolean hasACourseHandsOnLectures(int courseId);

	@Query("select count(cl) from CourseLecture cl where cl.lecture.type = :type AND cl.course.id = :id")
	Integer numOfLecturesOfOneTypeByCourse(int id, LectureType type);

	default CourseType courseType(final int id) {
		final Integer theoricalLectures = this.numOfLecturesOfOneTypeByCourse(id, LectureType.THEORICAL);
		final Integer handsOnLectures = this.numOfLecturesOfOneTypeByCourse(id, LectureType.HANDS_ON);

		if (theoricalLectures > handsOnLectures)
			return CourseType.THEORICAL;
		else if (theoricalLectures < handsOnLectures)
			return CourseType.HANDS_ON;
		else
			return CourseType.BALANCED;

	}

	@Query("select count(cl) > 0 from CourseLecture cl where cl.course.id = :courseId")
	boolean hasLectures(int courseId);
}
