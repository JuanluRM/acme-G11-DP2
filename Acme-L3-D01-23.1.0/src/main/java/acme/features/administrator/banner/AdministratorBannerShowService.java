
package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerShowService extends AbstractService<Administrator, Banner> {

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
	public void unbind(final Banner object) {
		assert object != null;

		final Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "start", "end", "picture", "slogan", "link");

		super.getResponse().setData(tuple);
	}

}
