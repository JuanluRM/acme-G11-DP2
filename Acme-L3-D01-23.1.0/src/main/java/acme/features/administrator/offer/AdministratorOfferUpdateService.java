
package acme.features.administrator.offer;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AdministratorOfferUpdateService extends AbstractService<Administrator, Offer> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorOfferRepository repository;

	// AbstractService<Employer, Company> -------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Offer object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneOfferById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Offer object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "heading", "summary", "start", "end", "price", "link");
	}

	@Override
	public void validate(final Offer object) {
		assert object != null;

		//ERRORS WITH START DATE

		if (!super.getBuffer().getErrors().hasErrors("start")) {
			boolean startDateStatus;

			startDateStatus = MomentHelper.isAfter(object.getStart(), object.getInstantiationMoment());

			super.state(startDateStatus, "start", "administrator.offer.error.start.beforeInstantiation");
		}

		if (!super.getBuffer().getErrors().hasErrors("start")) {
			boolean startDateStatus;

			startDateStatus = MomentHelper.isLongEnough(object.getStart(), object.getInstantiationMoment(), 1, ChronoUnit.DAYS);

			super.state(startDateStatus, "start", "administrator.offer.error.start.oneDayAtLeast");
		}

		if (!super.getBuffer().getErrors().hasErrors("start")) {
			boolean startDateStatus;
			Date inferiorLimitDate;
			Date upperLimitDate;

			inferiorLimitDate = new Date(946681200000l); // HINT This is Jan 1 2000 at 00:00
			upperLimitDate = new Date(4133977140000l); // HINT This is Dec 31 2100 at 23:59

			startDateStatus = MomentHelper.isAfterOrEqual(object.getStart(), inferiorLimitDate);
			startDateStatus &= MomentHelper.isBeforeOrEqual(object.getStart(), upperLimitDate);

			super.state(startDateStatus, "start", "administrator.offer.error.outOfBounds");
		}

		//ERRORS WITH END DATE

		if (!super.getBuffer().getErrors().hasErrors("end")) {
			boolean endDateStatus;

			endDateStatus = MomentHelper.isAfter(object.getEnd(), object.getInstantiationMoment());

			super.state(endDateStatus, "end", "administrator.offer.error.end.beforeInstantiation");
		}

		if (!super.getBuffer().getErrors().hasErrors("end") && !super.getBuffer().getErrors().hasErrors("start")) {
			boolean endDateStatus;

			endDateStatus = MomentHelper.isBefore(object.getStart(), object.getEnd());

			super.state(endDateStatus, "end", "administrator.offer.error.end.beforeStartDate");
		}

		if (!super.getBuffer().getErrors().hasErrors("end") && !super.getBuffer().getErrors().hasErrors("start")) {
			boolean endDateStatus;

			endDateStatus = MomentHelper.isLongEnough(object.getStart(), object.getEnd(), 7, ChronoUnit.DAYS);

			super.state(endDateStatus, "end", "administrator.offer.error.end.notLongEnough");
		}

		if (!super.getBuffer().getErrors().hasErrors("end")) {
			boolean endDateStatus;
			Date inferiorLimitDate;
			Date upperLimitDate;

			inferiorLimitDate = new Date(946681200000l); // HINT This is Jan 1 2000 at 00:00
			upperLimitDate = new Date(4133977140000l); // HINT This is Dec 31 2100 at 23:59

			endDateStatus = MomentHelper.isAfterOrEqual(object.getEnd(), inferiorLimitDate);
			endDateStatus &= MomentHelper.isBeforeOrEqual(object.getEnd(), upperLimitDate);

			super.state(endDateStatus, "end", "administrator.offer.error.outOfBounds");
		}

		//ERRORS WITH PRICE

		if (!super.getBuffer().getErrors().hasErrors("price"))
			super.state(object.getPrice().getAmount() >= 0, "price", "administrator.offer.error.price.negative");

		if (!super.getBuffer().getErrors().hasErrors("price")) {
			final List<String> currencies = new ArrayList<>();
			currencies.add("EUR");
			currencies.add("USD");
			currencies.add("GBP");
			super.state(currencies.contains(object.getPrice().getCurrency()), "price", "administrator.offer.error.price.currencyNotAllowed");
		}
	}

	@Override
	public void perform(final Offer object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Offer object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "heading", "summary", "start", "end", "price", "link");

		super.getResponse().setData(tuple);
	}

}
