
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.Lecture;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface LecturerLecturesRepository extends AbstractRepository {

	@Query("select l from Lecture l where l.id = :id")
	Lecture findOneLectureById(int id);

	@Query("select l from Lecture l where l.id in (select cl.lecture from CourseLecture cl where cl.course in (select c from Course c where c.id = :courseId))")
	Collection<Lecture> findLecturesByCourseId(int courseId);

	@Query("select c from Course c where c.id = :courseId")
	Course findOneCourseById(int courseId);

	@Query("select c from Course c where c.id in (select cl.course from CourseLecture cl where cl.lecture in (select l from Lecture l where l.id = :masterId))")
	Course findOneCourseByLectureId(int masterId);

}
