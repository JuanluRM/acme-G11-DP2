
package acme.features.auditor.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;
import form.AuditorDashboard;

@Controller
public class AuditorDashboardController extends AbstractController<Auditor, AuditorDashboard> {

	@Autowired
	protected AuditorDashboardService showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
	}
}
