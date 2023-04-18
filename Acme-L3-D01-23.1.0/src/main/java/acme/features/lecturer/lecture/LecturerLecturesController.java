
package acme.features.lecturer.lecture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Lecture;
import acme.framework.controllers.AbstractController;
import acme.roles.Lecturer;

@Controller
public class LecturerLecturesController extends AbstractController<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLecturesListService	listService;

	@Autowired
	protected LecturerLecturesShowService	showService;

	@Autowired
	protected LecturerLecturesCreateService	createService;

	//	@Autowired
	//	protected LecturerLecturesUpdateService		updateService;
	//
	//	@Autowired
	//	protected LecturerLecturesDeleteService		deleteService;
	//
	//	@Autowired
	//	protected LecturerLecturesPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);

		super.addBasicCommand("create", this.createService);
		//super.addBasicCommand("update", this.updateService);
		//super.addBasicCommand("delete", this.deleteService);
		//super.addBasicCommand("publish", this.publishService);
	}

}
