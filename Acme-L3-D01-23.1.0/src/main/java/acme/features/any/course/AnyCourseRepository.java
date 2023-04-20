
package acme.features.any.course;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.CourseType;
import acme.entities.LectureType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyCourseRepository extends AbstractRepository {

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from Course c where c.publish = true")
	Collection<Course> findPublishCourses();

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

}
