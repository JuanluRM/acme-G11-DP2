
package acme.features.any.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyCourseListService extends AbstractService<Any, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyCourseRepository repository;

	// AbstractServiceInterface -----------------------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);

	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Course> objects;
		objects = this.repository.findPublishCourses();

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "type", "retailPrice");
		tuple.put("type", this.repository.courseType(object.getId()));
		super.getResponse().setData(tuple);
	}

}
