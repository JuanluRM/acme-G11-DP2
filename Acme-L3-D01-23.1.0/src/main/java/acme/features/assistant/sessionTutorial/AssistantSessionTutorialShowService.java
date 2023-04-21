
package acme.features.assistant.sessionTutorial;

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
public class AssistantSessionTutorialShowService extends AbstractService<Assistant, SessionTutorial> {

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
		int SessionTutorialId;
		Tutorial tutorial;

		SessionTutorialId = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findOneTutorialBySessionTutorialId(SessionTutorialId);
		status = tutorial != null && (tutorial.getIsPublished() || super.getRequest().getPrincipal().hasRole(tutorial.getAssistant()));

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		SessionTutorial object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSessionTutorialById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final SessionTutorial object) {
		assert object != null;
		SelectChoices choices;
		Tuple tuple;
		choices = SelectChoices.from(SessionType.class, object.getTipoSesion());

		tuple = super.unbind(object, "title", "tipoSesion", "summary", "startMoment", "finishMoment", "moreInfo", "isPublished");
		tuple.put("masterId", object.getTutorial().getId());
		tuple.put("types", choices);

		super.getResponse().setData(tuple);
	}
}
