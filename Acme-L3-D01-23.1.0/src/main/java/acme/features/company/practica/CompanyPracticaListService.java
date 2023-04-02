
package acme.features.company.practica;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Practica;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticaListService extends AbstractService<Company, Practica> {

	@Autowired
	PracticaRepository repository;


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
		Collection<Practica> object;
		int companyId;

		companyId = super.getRequest().getPrincipal().getActiveRoleId();
		object = this.repository.findPracticasByCompany(companyId);

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
