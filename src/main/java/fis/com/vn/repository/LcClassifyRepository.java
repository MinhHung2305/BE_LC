package fis.com.vn.repository;

import fis.com.vn.model.entity.LcClassify;
import fis.com.vn.model.entity.National;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LcClassifyRepository extends JpaRepository<LcClassify, Integer> {
}
