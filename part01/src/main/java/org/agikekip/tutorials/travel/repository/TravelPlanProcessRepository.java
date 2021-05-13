package org.agikekip.tutorials.travel.repository;

import java.util.Optional;
import org.agikekip.tutorials.travel.domain.TravelPlanProcess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TravelPlanProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelPlanProcessRepository extends JpaRepository<TravelPlanProcess, Long> {
    Optional<TravelPlanProcess> findByProcessInstanceId(Long processInstanceId);
}
