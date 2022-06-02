package fis.com.vn.repository;

import fis.com.vn.model.entity.CharacterSetSwift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterSetSwiftRepository extends JpaRepository<CharacterSetSwift, Long> {
    @Query(value = "Select c From CharacterSetSwift c where 1=1 and LOWER(characterSet) like :characterSet%")
    List<CharacterSetSwift> findAllByCharacterSet(@Param("characterSet") String characterSet);
}
