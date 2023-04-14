
package acme.features.administrator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.controllers.AbstractController;

@Controller
public class AdministratorOfferController extends AbstractController<Administrator, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorOfferListService	listService;

	//	@Autowired
	//	protected AdministratorCompanyListRecentService		listRecentService;
	//
	@Autowired
	protected AdministratorOfferShowService	showService;
	//
	//	@Autowired
	//	protected AdministratorAnnouncementCreateService	createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}
}
