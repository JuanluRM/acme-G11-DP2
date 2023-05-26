
package acme.features.assistant.sessionTutorial;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SessionTutorial;
import acme.entities.SessionType;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantSessionTutorialUpdateService extends AbstractService<Assistant, SessionTutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantSessionTutorialRepository repository;

	// AbstractService interface ----------------------------------------------


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
		SessionTutorial SessionTutorial;

		id = super.getRequest().getData("id", int.class);
		SessionTutorial = this.repository.findOneSessionTutorialById(id);
		status = SessionTutorial != null && !SessionTutorial.getIsPublished() && super.getRequest().getPrincipal().getActiveRoleId() == SessionTutorial.getTutorial().getAssistant().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		SessionTutorial SessionTutorial;

		id = super.getRequest().getData("id", int.class);
		SessionTutorial = this.repository.findOneSessionTutorialById(id);

		super.getBuffer().setData(SessionTutorial);
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
			date = AssistantSessionTutorialUpdateService.plusOneDay(Date.from(Instant.now()));
			super.state(object.getStartMoment().equals(date) || object.getStartMoment().after(date), "startMoment", "tutorial.sessionTutorial.form.error.oneDayAfter");
		}

		if (!super.getBuffer().getErrors().hasErrors("finishMoment"))
			super.state(AssistantSessionTutorialUpdateService.oneToFiveHours(object), "finishMoment", "tutorial.sessionTutorial.form.error.hoursNotAllowed");

	}

	@Override
	public void perform(final SessionTutorial object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final SessionTutorial object) {
		assert object != null;

		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(SessionType.class, object.getTipoSesion());

		tuple = super.unbind(object, "title", "tipoSesion", "summary", "startMoment", "finishMoment", "moreInfo");

		tuple.put("types", choices);
		tuple.put("masterId", object.getTutorial().getId());

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
