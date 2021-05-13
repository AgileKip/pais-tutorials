package org.agikekip.tutorials.travel.process.travelPlanProcess;

import org.agikekip.tutorials.travel.repository.TaskInstanceRepository;
import org.agikekip.tutorials.travel.repository.TravelPlanProcessRepository;
import org.agikekip.tutorials.travel.service.TaskInstanceService;
import org.agikekip.tutorials.travel.service.TravelPlanService;
import org.agikekip.tutorials.travel.service.dto.TaskInstanceDTO;
import org.agikekip.tutorials.travel.service.dto.TravelPlanDTO;
import org.agikekip.tutorials.travel.service.dto.TravelPlanProcessDTO;
import org.agikekip.tutorials.travel.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskBookAHotelService {

    private final TaskInstanceService taskInstanceService;

    private final TravelPlanService travelPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskBookAHotelMapper taskBookAHotelMapper;

    public TaskBookAHotelService(
        TaskInstanceService taskInstanceService,
        TravelPlanService travelPlanService,
        TaskInstanceRepository taskInstanceRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskBookAHotelMapper taskBookAHotelMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelPlanService = travelPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskBookAHotelMapper = taskBookAHotelMapper;
    }

    public TaskBookAHotelContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskBookAHotelMapper::toTravelPlanProcessDTO)
            .orElseThrow();

        TaskBookAHotelContextDTO taskBookAHotelContext = new TaskBookAHotelContextDTO();
        taskBookAHotelContext.setTaskInstance(taskInstanceDTO);
        taskBookAHotelContext.setTravelPlanProcess(travelPlanProcess);

        return taskBookAHotelContext;
    }

    public TaskBookAHotelContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskBookAHotelContextDTO taskBookAHotelContext) {
        TravelPlanDTO travelPlanDTO = travelPlanService
            .findOne(taskBookAHotelContext.getTravelPlanProcess().getTravelPlan().getId())
            .orElseThrow();
        travelPlanDTO.setName(taskBookAHotelContext.getTravelPlanProcess().getTravelPlan().getName());
        travelPlanDTO.setStartDate(taskBookAHotelContext.getTravelPlanProcess().getTravelPlan().getStartDate());
        travelPlanDTO.setEndDate(taskBookAHotelContext.getTravelPlanProcess().getTravelPlan().getEndDate());
        travelPlanDTO.setHotelName(taskBookAHotelContext.getTravelPlanProcess().getTravelPlan().getHotelName());
        travelPlanDTO.setHotelBookingNumber(taskBookAHotelContext.getTravelPlanProcess().getTravelPlan().getHotelBookingNumber());
        travelPlanService.save(travelPlanDTO);
    }

    public void complete(TaskBookAHotelContextDTO taskBookAHotelContext) {
        save(taskBookAHotelContext);
        taskInstanceService.complete(taskBookAHotelContext.getTaskInstance(), taskBookAHotelContext.getTravelPlanProcess());
    }
}
