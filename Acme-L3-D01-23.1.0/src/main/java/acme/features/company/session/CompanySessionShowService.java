
package acme.features.company.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Practica;
import acme.entities.Session;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanySessionShowService extends AbstractService<Company, Session> {

	@Autowired
	protected CompanySessionRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int sessionId;
		Practica practica;

		sessionId = super.getRequest().getData("id", int.class);
		practica = this.repository.findOnePracticaBySessionId(sessionId);
		status = practica != null && super.getRequest().getPrincipal().hasRole(practica.getCompany());
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Session object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSessionById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "summary", "startDate", "endDate", "link");
		tuple.put("practicaId", object.getPractica().getId());
		tuple.put("draftMode", object.getPractica().getDraftMode());

		super.getResponse().setData(tuple);
	}

}
