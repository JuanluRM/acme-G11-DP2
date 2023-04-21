
package acme.features.company.session;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Practica;
import acme.entities.Session;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanySessionCreateService extends AbstractService<Company, Session> {

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
		System.out.println(super.getRequest().getData("practicaId", int.class));
		practica = this.repository.findOnePracticaById(practicaId);
		status = practica != null && super.getRequest().getPrincipal().hasRole(practica.getCompany());
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final Session object;
		int practicaId;
		Practica practica;
		practicaId = super.getRequest().getData("practicaId", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		object = new Session();
		object.setPractica(practica);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Session object) {
		assert object != null;
		super.bind(object, "title", "summary", "startDate", "endDate", "link");
	}

	@Override
	public void validate(final Session object) {
		assert object != null;
		boolean confirmation;
		Date date;

		confirmation = object.getPractica().getPublished() ? false : super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "company.sessionPracticum.form.error.confirmation");

		if (!super.getBuffer().getErrors().hasErrors("endDate"))
			super.state(object.getStartDate().before(object.getEndDate()), "endDate", "company.session.form.error.endAfterStart");

		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			date = CompanySessionCreateService.plusOneWeek(Date.from(Instant.now()));
			super.state(object.getStartDate().equals(date) || object.getStartDate().after(date), "startDate", "company.session.form.error.oneWeekAhead");
		}

		if (!super.getBuffer().getErrors().hasErrors("endDate")) {
			date = CompanySessionCreateService.plusOneWeek(object.getStartDate());
			super.state(object.getStartDate().equals(date) || object.getEndDate().after(date), "endDate", "company.session.form.error.oneWeekLong");
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
		tuple.put("practicaId", super.getRequest().getData("practicaId", int.class));
		tuple.put("published", object.getPractica().getPublished());
		tuple.put("confirmation", false);
		super.getResponse().setData(tuple);
	}

	public static Date plusOneWeek(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		return calendar.getTime();
	}

}
