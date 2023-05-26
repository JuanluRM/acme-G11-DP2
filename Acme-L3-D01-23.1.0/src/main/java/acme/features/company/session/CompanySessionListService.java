
package acme.features.company.session;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Practica;
import acme.entities.Session;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanySessionListService extends AbstractService<Company, Session> {

	@Autowired
	protected CompanySessionRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("practicaId", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int practicaId;
		Practica practica;

		practicaId = super.getRequest().getData("practicaId", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		status = practica != null && super.getRequest().getPrincipal().hasRole(practica.getCompany());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<Session> objects;
		int practicaId;

		practicaId = super.getRequest().getData("practicaId", int.class);
		objects = this.repository.findSessionByPracticaId(practicaId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "summary", "practica.title");

		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<Session> objects) {
		assert objects != null;
		int practicaId;
		Practica practica;
		final boolean showCreate;
		final boolean draftMode;
		practicaId = super.getRequest().getData("practicaId", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		showCreate = super.getRequest().getPrincipal().hasRole(practica.getCompany());
		draftMode = practica.getDraftMode();
		super.getResponse().setGlobal("practicaId", practicaId);
		super.getResponse().setGlobal("showCreate", showCreate);
		super.getResponse().setGlobal("draftMode", draftMode);
	}

}
