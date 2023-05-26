
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
		System.out.println("Entrada en metodo check");
		boolean status;
		System.out.println(super.getRequest().hasData("practicaId", int.class));
		status = super.getRequest().hasData("practicaId", int.class);
		super.getResponse().setChecked(status);
		System.out.println("Entrada en metodo check");

	}

	@Override
	public void authorise() {
		System.out.println("Entrada en metodo authorise");

		boolean status;
		int practicaId;
		Practica practica;
		practicaId = super.getRequest().getData("practicaId", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		status = practica != null && super.getRequest().getPrincipal().hasRole(practica.getCompany());
		super.getResponse().setAuthorised(status);
		System.out.println("Salida en metodo authorise");

	}

	@Override
	public void load() {
		System.out.println("Entrada en metodo load");
		final Session object;
		int practicaId;
		Practica practica;
		practicaId = super.getRequest().getData("practicaId", int.class);
		practica = this.repository.findOnePracticaById(practicaId);
		object = new Session();
		object.setPractica(practica);
		super.getBuffer().setData(object);
		System.out.println("Salida en metodo load");

	}

	@Override
	public void bind(final Session object) {
		System.out.println("Entrada en metodo bind");

		assert object != null;
		super.bind(object, "title", "summary", "startDate", "endDate", "moreInfo");
		System.out.println("Salida en metodo bind");

	}

	@Override
	public void validate(final Session object) {
		System.out.println("Entrada en metodo validate");

		assert object != null;
		boolean confirmation;
		Date date;
		System.out.println("Aqui llegamos");
		confirmation = object.getPractica().getDraftMode() ? true : super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "company.session.form.error.confirmation");
		System.out.println("Aqui llegamos 2");

		if (!super.getBuffer().getErrors().hasErrors("endDate"))
			super.state(object.getStartDate().before(object.getEndDate()), "endDate", "company.sessionPractica.form.error.endAfterStart");
		System.out.println("Aqui llegamos 3");

		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			date = CompanySessionCreateService.plusOneWeek(Date.from(Instant.now()));
			super.state(object.getStartDate().equals(date) || object.getStartDate().after(date), "startDate", "company.sessionPractica.form.error.oneWeekAhead");
		}

		if (!super.getBuffer().getErrors().hasErrors("endDate")) {
			date = CompanySessionCreateService.plusOneWeek(object.getStartDate());
			super.state(object.getStartDate().equals(date) || object.getEndDate().after(date), "endDate", "company.sessionPractica.form.error.oneWeekLong");
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
		tuple.put("draftMode", object.getPractica().getDraftMode());
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
