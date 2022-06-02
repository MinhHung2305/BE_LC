package fis.com.vn.repository;

import fis.com.vn.model.entity.StandardSwift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardSwiftRepository extends JpaRepository<StandardSwift, Long> {
    @Query("Select ss From StandardSwift ss Join ss.standardSwiftPowerType sspt Join sspt.powerType pt where pt.id = :powerTypeId")
    List<StandardSwift> findAllByPowerType(@Param("powerTypeId") Long powerTypeId);

}
