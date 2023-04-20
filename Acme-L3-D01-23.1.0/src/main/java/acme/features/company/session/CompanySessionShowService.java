
package acme.features.company.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Session;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanySessionShowService extends AbstractService<Company, Session> {

	@Autowired
	CompanySessionRepository repository;


	@Override
	public void check() {
		boolean status;

		System.out.println(super.getRequest());

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int sessionId;
		Session session;

		sessionId = super.getRequest().getData("id", int.class);
		session = this.repository.findOneSessionById(sessionId);
		status = session != null && super.getRequest().getPrincipal().hasRole(session.getPractica().getCompany());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Session object;
		int sessionId;

		sessionId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSessionById(sessionId);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "summary", "timePeriod", "link");

		super.getResponse().setData(tuple);
	}
}
