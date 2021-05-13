package org.agilekip.tutorials.travel.process.travelPlanProcess;

import org.agilekip.tutorials.travel.repository.TaskInstanceRepository;
import org.agilekip.tutorials.travel.repository.TravelPlanProcessRepository;
import org.agilekip.tutorials.travel.service.TaskInstanceService;
import org.agilekip.tutorials.travel.service.TravelPlanService;
import org.agilekip.tutorials.travel.service.dto.TaskInstanceDTO;
import org.agilekip.tutorials.travel.service.dto.TravelPlanDTO;
import org.agilekip.tutorials.travel.service.dto.TravelPlanProcessDTO;
import org.agilekip.tutorials.travel.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskBuyFlightTicketsService {

    private final TaskInstanceService taskInstanceService;

    private final TravelPlanService travelPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskBuyFlightTicketsMapper taskBuyFlightTicketsMapper;

    public TaskBuyFlightTicketsService(
        TaskInstanceService taskInstanceService,
        TravelPlanService travelPlanService,
        TaskInstanceRepository taskInstanceRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskBuyFlightTicketsMapper taskBuyFlightTicketsMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelPlanService = travelPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskBuyFlightTicketsMapper = taskBuyFlightTicketsMapper;
    }

    public TaskBuyFlightTicketsContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskBuyFlightTicketsMapper::toTravelPlanProcessDTO)
            .orElseThrow();

        TaskBuyFlightTicketsContextDTO taskBuyFlightTicketsContext = new TaskBuyFlightTicketsContextDTO();
        taskBuyFlightTicketsContext.setTaskInstance(taskInstanceDTO);
        taskBuyFlightTicketsContext.setTravelPlanProcess(travelPlanProcess);

        return taskBuyFlightTicketsContext;
    }

    public TaskBuyFlightTicketsContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskBuyFlightTicketsContextDTO taskBuyFlightTicketsContext) {
        TravelPlanDTO travelPlanDTO = travelPlanService
            .findOne(taskBuyFlightTicketsContext.getTravelPlanProcess().getTravelPlan().getId())
            .orElseThrow();
        travelPlanDTO.setName(taskBuyFlightTicketsContext.getTravelPlanProcess().getTravelPlan().getName());
        travelPlanDTO.setStartDate(taskBuyFlightTicketsContext.getTravelPlanProcess().getTravelPlan().getStartDate());
        travelPlanDTO.setEndDate(taskBuyFlightTicketsContext.getTravelPlanProcess().getTravelPlan().getEndDate());
        travelPlanDTO.setAirlineCompanyName(taskBuyFlightTicketsContext.getTravelPlanProcess().getTravelPlan().getAirlineCompanyName());
        travelPlanDTO.setAirlineTicketNumber(taskBuyFlightTicketsContext.getTravelPlanProcess().getTravelPlan().getAirlineTicketNumber());
        travelPlanService.save(travelPlanDTO);
    }

    public void complete(TaskBuyFlightTicketsContextDTO taskBuyFlightTicketsContext) {
        save(taskBuyFlightTicketsContext);
        taskInstanceService.complete(taskBuyFlightTicketsContext.getTaskInstance(), taskBuyFlightTicketsContext.getTravelPlanProcess());
    }
}
