package org.agikekip.tutorials.travel.process.travelPlanProcess;

import org.agikekip.tutorials.travel.domain.TravelPlan;
import org.agikekip.tutorials.travel.domain.TravelPlanProcess;
import org.agikekip.tutorials.travel.service.dto.TravelPlanDTO;
import org.agikekip.tutorials.travel.service.dto.TravelPlanProcessDTO;
import org.agikekip.tutorials.travel.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskBuyFlightTicketsMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    TravelPlanProcessDTO toTravelPlanProcessDTO(TravelPlanProcess travelPlanProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "airlineCompanyName", source = "airlineCompanyName")
    @Mapping(target = "airlineTicketNumber", source = "airlineTicketNumber")
    TravelPlanDTO toTravelPlanDTO(TravelPlan travelPlan);
}
