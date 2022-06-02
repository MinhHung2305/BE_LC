package fis.com.vn.repository;

import fis.com.vn.model.entity.PowerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerTypeRepository extends JpaRepository<PowerType, Long> {
}
