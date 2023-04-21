
package acme.features.auditor.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Audit;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditController extends AbstractController<Auditor, Audit> {

	@Autowired
	protected AuditorAuditListMineService	listMineService;

	@Autowired
	protected AuditorAuditShowService		showService;

	@Autowired
	protected AuditorAuditUpdateService		updateService;

	@Autowired
	protected AuditorAuditCreateService		createService;

	@Autowired
	protected AuditorAuditDeleteService		deleteService;

	@Autowired
	protected AuditorAuditPublishService	publishService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("create", this.createService);
		super.addCustomCommand("publish", "update", this.publishService);
		super.addCustomCommand("list-mine", "list", this.listMineService);

	}
}
