
package acme.features.student;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Enrolment;
import acme.framework.controllers.AbstractController;
import acme.roles.Student;

@Controller
public class StudentEnrolmentController extends AbstractController<Student, Enrolment> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentListService	listService;

	//	@Autowired
	//	protected AdministratorCompanyListRecentService		listRecentService;
	//
	@Autowired
	protected StudentEnrolmentShowService	showService;
	//
	//	@Autowired
	//	protected AdministratorAnnouncementCreateService	createService;

	// Constructors -----------------------------------------------------------
	@Autowired
	protected StudentEnrolmentCreateService	createService;

	@Autowired
	protected StudentEnrolmentUpdateService	updateService;

	@Autowired
	protected StudentEnrolmentDeleteService	deleteService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
	}
}
