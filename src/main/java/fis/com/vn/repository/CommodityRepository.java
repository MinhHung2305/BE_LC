package fis.com.vn.repository;

import fis.com.vn.model.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Integer> {
    Commodity getCommoditiesByCommoditiesId(Integer commoditiesId);
}
