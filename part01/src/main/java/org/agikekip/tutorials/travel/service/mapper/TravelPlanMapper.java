package org.agikekip.tutorials.travel.service.mapper;

import org.agikekip.tutorials.travel.domain.*;
import org.agikekip.tutorials.travel.service.dto.TravelPlanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TravelPlan} and its DTO {@link TravelPlanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TravelPlanMapper extends EntityMapper<TravelPlanDTO, TravelPlan> {}
