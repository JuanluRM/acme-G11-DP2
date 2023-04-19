
package acme.features.administrator.banner;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Banner;
import acme.framework.helpers.MomentHelper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBannerRepository extends AbstractRepository {

	@Query("SELECT b FROM Banner b")
	Collection<Banner> findAllBanners();

	@Query("SELECT b FROM Banner b WHERE b.id = :bannerId")
	Banner findBannerById(int bannerId);

	@Query("SELECT b FROM Banner b WHERE :currentDate BETWEEN b.start AND b.end")
	Collection<Banner> findAllActiveBanners(Date currentDate);

	default Banner getActiveRandomBanner() {
		final List<Banner> banners = this.findAllActiveBanners(MomentHelper.getCurrentMoment()).stream().collect(Collectors.toList());
		final Random random = new Random();
		if (!banners.isEmpty())
			return banners.get(random.nextInt(banners.size()));
		else
			return null;
	}
}
