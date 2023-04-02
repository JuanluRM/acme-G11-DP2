
package acme.features.company.practica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Practica;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticaShowService extends AbstractService<Company, Practica> {

	@Autowired
	PracticaRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int practicaId;
		Practica practica;

		practicaId = super.getRequest().getData("id", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		status = practica != null && super.getRequest().getPrincipal().hasRole(practica.getCompany());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Practica object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticaById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Practica object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "summary", "goals", "estimatedTotalTime");

		super.getResponse().setData(tuple);
	}

}
