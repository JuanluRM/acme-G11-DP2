
package acme.testing.lecturer.courseLecture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Course;
import acme.framework.repositories.AbstractRepository;

public interface LecturerCourseLectureTestRepository extends AbstractRepository {

	@Query("select c from Course c where c.lecturer.userAccount.username = :username")
	Collection<Course> findManyCoursesByLecturerUsername(String username);

	@Query("select c from Course c where c.lecturer.userAccount.username = :username and c.publish = true")
	Collection<Course> findManyPublishCoursesByLecturerUsername(String username);
}
