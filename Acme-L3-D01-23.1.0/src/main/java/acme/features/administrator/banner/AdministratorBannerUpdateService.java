
package acme.features.administrator.banner;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerUpdateService extends AbstractService<Administrator, Banner> {

	@Autowired
	protected AdministratorBannerRepository repository;


	@Override
	public void check() {

		final boolean status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {

		final boolean status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Banner object;
		final int bannerId;

		bannerId = super.getRequest().getData("id", int.class);
		object = this.repository.findBannerById(bannerId);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "start", "end", "picture", "slogan", "link");

	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		//ERRORS WITH START DATE

		if (!super.getBuffer().getErrors().hasErrors("start")) {
			boolean startDateStatus;

			startDateStatus = MomentHelper.isAfter(object.getStart(), object.getInstantiationMoment());

			super.state(startDateStatus, "start", "administrator.banner.error.start.beforeInstantiation");
		}

		if (!super.getBuffer().getErrors().hasErrors("start")) {
			boolean startDateStatus;
			Date inferiorLimitDate;
			Date upperLimitDate;

			inferiorLimitDate = new Date(946681200000l); // HINT This is Jan 1 2000 at 00:00
			upperLimitDate = new Date(4133977140000l); // HINT This is Dec 31 2100 at 23:59

			startDateStatus = MomentHelper.isAfterOrEqual(object.getStart(), inferiorLimitDate);
			startDateStatus &= MomentHelper.isBeforeOrEqual(object.getStart(), upperLimitDate);

			super.state(startDateStatus, "start", "administrator.banner.error.outOfBounds");
		}

		//ERRORS WITH END DATE

		if (!super.getBuffer().getErrors().hasErrors("end")) {
			boolean endDateStatus;

			endDateStatus = MomentHelper.isAfter(object.getEnd(), object.getInstantiationMoment());

			super.state(endDateStatus, "end", "administrator.banner.error.end.beforeInstantiation");
		}

		if (!super.getBuffer().getErrors().hasErrors("end") && !super.getBuffer().getErrors().hasErrors("start")) {
			boolean endDateStatus;

			endDateStatus = MomentHelper.isBefore(object.getStart(), object.getEnd());

			super.state(endDateStatus, "end", "administrator.banner.error.end.beforeStartDate");
		}

		if (!super.getBuffer().getErrors().hasErrors("end") && !super.getBuffer().getErrors().hasErrors("start")) {
			boolean endDateStatus;

			endDateStatus = MomentHelper.isLongEnough(object.getStart(), object.getEnd(), 7, ChronoUnit.DAYS);

			super.state(endDateStatus, "end", "administrator.banner.error.end.notLongEnough");
		}

		if (!super.getBuffer().getErrors().hasErrors("end")) {
			boolean endDateStatus;
			Date inferiorLimitDate;
			Date upperLimitDate;

			inferiorLimitDate = new Date(946681200000l); // HINT This is Jan 1 2000 at 00:00
			upperLimitDate = new Date(4133977140000l); // HINT This is Dec 31 2100 at 23:59

			endDateStatus = MomentHelper.isAfterOrEqual(object.getEnd(), inferiorLimitDate);
			endDateStatus &= MomentHelper.isBeforeOrEqual(object.getEnd(), upperLimitDate);

			super.state(endDateStatus, "end", "administrator.banner.error.outOfBounds");
		}

	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		Date currentMoment;

		currentMoment = MomentHelper.getCurrentMoment();
		object.setInstantiationMoment(currentMoment);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		final Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "start", "end", "picture", "slogan", "link");

		super.getResponse().setData(tuple);
	}
}
