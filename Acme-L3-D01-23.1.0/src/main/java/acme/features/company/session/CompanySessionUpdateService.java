
package acme.features.company.session;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Practica;
import acme.entities.Session;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanySessionUpdateService extends AbstractService<Company, Session> {

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
		int id;
		Practica practica;

		id = super.getRequest().getData("id", int.class);
		practica = this.repository.findOnePracticaBySessionId(id);
		status = practica != null && practica.getDraftMode() && super.getRequest().getPrincipal().hasRole(practica.getCompany());

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
	public void bind(final Session object) {
		assert object != null;

		super.bind(object, "title", "summary", "startDate", "endDate", "link");
	}

	@Override
	public void validate(final Session object) {
		Date date;
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("endDate"))
			super.state(object.getStartDate().before(object.getEndDate()), "endDate", "company.sessionPractica.form.error.endAfterStart");

		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			date = CompanySessionCreateService.plusOneWeek(Date.from(Instant.now()));
			super.state(object.getStartDate().after(date) || object.getStartDate().equals(date), "startDate", "company.sessionPractica.form.error.oneWeekAhead");
		}

		if (!super.getBuffer().getErrors().hasErrors("endDate")) {
			date = CompanySessionCreateService.plusOneWeek(object.getStartDate());
			super.state(object.getEndDate().after(date) || object.getStartDate().equals(date), "endDate", "company.sessionPractica.form.error.oneWeekLong");
		}
	}

	@Override
	public void perform(final Session object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "summary", "startDate", "endDate", "link");

		super.getResponse().setData(tuple);
	}

}
