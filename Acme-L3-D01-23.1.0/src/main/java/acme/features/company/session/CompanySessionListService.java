
package acme.features.company.session;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Session;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanySessionListService extends AbstractService<Company, Session> {

	@Autowired
	CompanySessionRepository sessionRepository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("practicaId", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Session> objects;
		int practicaId;

		practicaId = super.getRequest().getData("practicaId", int.class);
		objects = this.sessionRepository.findManySessionsByPracticaId(practicaId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "summary", "timePeriod", "link");

		super.getResponse().setData(tuple);
	}

}
