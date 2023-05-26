
package acme.features.administrator.banner;

import java.time.temporal.ChronoUnit;

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

		if (!super.getBuffer().getErrors().hasErrors("start"))
			super.state(MomentHelper.isAfterOrEqual(object.getStart(), object.getInstantiationMoment()), "start", "administrator.banner.error.start.beforeInstantiation");

		if (!super.getBuffer().getErrors().hasErrors("end"))
			super.state(MomentHelper.isAfterOrEqual(object.getEnd(), object.getStart()), "end", "administrator.banner.error.end.beforeStartDate");

		super.state(MomentHelper.isLongEnough(object.getStart(), object.getEnd(), 7, ChronoUnit.DAYS), "*", "administrator.banner.error.end.notLongEnough");
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

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
