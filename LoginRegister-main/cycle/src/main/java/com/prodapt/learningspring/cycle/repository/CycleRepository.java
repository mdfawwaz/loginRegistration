package com.prodapt.learningspring.cycle.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prodapt.learningspring.cycle.entity.Cycle;
import java.util.List;

@Repository
public interface CycleRepository extends CrudRepository<Cycle, Long> {

    List<Cycle> findByAvailable(boolean available);

    // You can add more custom query methods here if needed
}