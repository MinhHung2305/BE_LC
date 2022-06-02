package fis.com.vn.repository;

import fis.com.vn.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product p JOIN contract c on c.contract_id = p.contract_id" +
            " WHERE c.contract_id = :contractId and p.application_opening_lc_id is null", nativeQuery = true)
    public List<Product> getAllProductByContract(@Param("contractId") Integer contractId);

    @Query("SELECT p FROM Product p JOIN p.applicationOpeningLc c WHERE c.id = :applicationOpeningLcId")
    List<Product> findAllByApplicationOpeningLcId(@Param("applicationOpeningLcId") Long applicationOpeningLcId);
}
