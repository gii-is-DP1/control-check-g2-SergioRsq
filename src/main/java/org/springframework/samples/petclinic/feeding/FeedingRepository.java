package org.springframework.samples.petclinic.feeding;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface FeedingRepository extends CrudRepository<Feeding, Integer> {
    List<Feeding> findAll() throws DataAccessException;
    
    @Query("SELECT feedingType FROM FeedingType feedingType")
    List<FeedingType> findAllFeedingTypes();
    
    @Query("SELECT feedingType FROM FeedingType feedingType WHERE feedingType.name LIKE :name%")
    FeedingType findFeedingTypeByName(@Param("name") String name) throws DataAccessException;
    
    Optional<Feeding> findById(int id);
    
    Feeding save(@Valid Feeding p);
}
