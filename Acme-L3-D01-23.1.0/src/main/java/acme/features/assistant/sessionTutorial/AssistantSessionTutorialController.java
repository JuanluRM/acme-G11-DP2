
package acme.features.assistant.sessionTutorial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.SessionTutorial;
import acme.framework.controllers.AbstractController;
import acme.roles.Assistant;

@Controller
public class AssistantSessionTutorialController extends AbstractController<Assistant, SessionTutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantSessionTutorialListService		listService;

	@Autowired
	protected AssistantSessionTutorialShowService		showService;

	@Autowired
	protected AssistantSessionTutorialCreateService		createService;

	@Autowired
	protected AssistantSessionTutorialDeleteService		deleteService;

	@Autowired
	protected AssistantSessionTutorialUpdateService		updateService;

	@Autowired
	protected AssistantSessionTutorialPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);

		super.addCustomCommand("publish", "update", this.publishService);
	}

}
