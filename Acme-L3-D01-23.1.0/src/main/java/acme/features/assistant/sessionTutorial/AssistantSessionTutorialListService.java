
package acme.features.assistant.sessionTutorial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SessionTutorial;
import acme.entities.Tutorial;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantSessionTutorialListService extends AbstractService<Assistant, SessionTutorial> {
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
		status = tutorial != null && super.getRequest().getPrincipal().hasRole(tutorial.getAssistant()) && super.getRequest().getPrincipal().getActiveRoleId() == tutorial.getAssistant().getId();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<SessionTutorial> objects;
		int masterId;

		masterId = super.getRequest().getData("masterId", int.class);
		objects = this.repository.findManySessionsByMasterId(masterId);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final SessionTutorial object) {
		assert object != null;

		Tuple tuple;
		final String lang = super.getRequest().getLocale().getISO3Language();
		tuple = super.unbind(object, "title", "tipoSesion");
		final boolean isPublished = object.getIsPublished();

		if (lang.equals("spa")) {
			if (isPublished)
				tuple.put("isPublished", "Publicado");
			else
				tuple.put("isPublished", "No Publicado");
		} else if (lang.equals("eng"))
			if (isPublished)
				tuple.put("isPublished", "Published");
			else
				tuple.put("isPublished", "Not Published");

		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<SessionTutorial> objects) {
		assert objects != null;

		int masterId;
		Tutorial tutorial;
		masterId = super.getRequest().getData("masterId", int.class);
		tutorial = this.repository.findOneTutorialById(masterId);
		final Boolean showCreate = tutorial.getIsPublished() && super.getRequest().getPrincipal().hasRole(tutorial.getAssistant());
		super.getResponse().setGlobal("masterId", masterId);
		super.getResponse().setGlobal("showCreate", showCreate);

	}

}
