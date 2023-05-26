
package acme.features.any.banner;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Banner;
import acme.framework.repositories.AbstractRepository;

public interface RandomBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.start <= :date and b.end > :date")
	List<Banner> findActiveBanners(Date date);

}
