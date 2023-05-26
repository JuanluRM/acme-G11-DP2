
package acme.features.assistant.sessionTutorial;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SessionTutorial;
import acme.entities.SessionType;
import acme.entities.Tutorial;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantSessionTutorialCreateService extends AbstractService<Assistant, SessionTutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantSessionTutorialRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("masterId", int.class);

		super.getResponse().setChecked(status);

	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Tutorial tutorial;

		masterId = super.getRequest().getData("masterId", int.class);
		tutorial = this.repository.findOneTutorialById(masterId);
		status = tutorial != null && super.getRequest().getPrincipal().hasRole(tutorial.getAssistant());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		SessionTutorial object;
		int masterId;
		Tutorial tutorial;

		masterId = super.getRequest().getData("masterId", int.class);
		tutorial = this.repository.findOneTutorialById(masterId);

		object = new SessionTutorial();
		object.setIsPublished(false);
		object.setTutorial(tutorial);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final SessionTutorial object) {
		assert object != null;

		super.bind(object, "title", "tipoSesion", "summary", "startMoment", "finishMoment", "moreInfo");
	}

	@Override
	public void validate(final SessionTutorial object) {
		assert object != null;
		Date date;

		if (!super.getBuffer().getErrors().hasErrors("finishMoment"))
			super.state(object.getStartMoment().before(object.getFinishMoment()), "finishMoment", "tutorial.sessionTutorial.form.error.endAfterStart");

		if (!super.getBuffer().getErrors().hasErrors("startMoment")) {
			date = AssistantSessionTutorialCreateService.plusOneDay(Date.from(Instant.now()));
			super.state(object.getStartMoment().equals(date) || object.getStartMoment().after(date), "startMoment", "tutorial.sessionTutorial.form.error.oneDayAfter");
		}

		if (!super.getBuffer().getErrors().hasErrors("finishMoment"))
			super.state(AssistantSessionTutorialCreateService.oneToFiveHours(object), "finishMoment", "tutorial.sessionTutorial.form.error.hoursNotAllowed");

	}

	@Override
	public void perform(final SessionTutorial object) {
		assert object != null;

		object.setIsPublished(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final SessionTutorial object) {
		assert object != null;
		SelectChoices choices;
		Tuple tuple;
		choices = SelectChoices.from(SessionType.class, object.getTipoSesion());

		tuple = super.unbind(object, "title", "tipoSesion", "summary", "startMoment", "finishMoment", "moreInfo");
		tuple.put("masterId", object.getTutorial().getId());
		tuple.put("types", choices);

		super.getResponse().setData(tuple);
	}
	public static Date plusOneDay(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	private static boolean oneToFiveHours(final SessionTutorial object) {
		final long diffInMilliseconds = object.getFinishMoment().getTime() - object.getStartMoment().getTime();

		final long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMilliseconds);

		return 1 <= diffInHours && diffInHours <= 5;
	}
}
