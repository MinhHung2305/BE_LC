package fis.com.vn.repository;

import fis.com.vn.model.entity.National;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalRepository extends JpaRepository<National, Long> {
}
