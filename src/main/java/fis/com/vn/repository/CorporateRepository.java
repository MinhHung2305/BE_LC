package fis.com.vn.repository;

import fis.com.vn.model.entity.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateRepository extends JpaRepository<Corporate, Long> {

    boolean existsByCorporateCode(String corporateCode);

    boolean existsByuser1DeputyIdentifyEmail(String user1DeputyIdentifyEmail);

    boolean existsByUser2DeputyIdentifyEmail(String user2DeputyIdentifyEmail);
}
