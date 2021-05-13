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
public class TaskRentACarService {

    private final TaskInstanceService taskInstanceService;

    private final TravelPlanService travelPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRentACarMapper taskRentACarMapper;

    public TaskRentACarService(
        TaskInstanceService taskInstanceService,
        TravelPlanService travelPlanService,
        TaskInstanceRepository taskInstanceRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRentACarMapper taskRentACarMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelPlanService = travelPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRentACarMapper = taskRentACarMapper;
    }

    public TaskRentACarContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRentACarMapper::toTravelPlanProcessDTO)
            .orElseThrow();

        TaskRentACarContextDTO taskRentACarContext = new TaskRentACarContextDTO();
        taskRentACarContext.setTaskInstance(taskInstanceDTO);
        taskRentACarContext.setTravelPlanProcess(travelPlanProcess);

        return taskRentACarContext;
    }

    public TaskRentACarContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRentACarContextDTO taskRentACarContext) {
        TravelPlanDTO travelPlanDTO = travelPlanService
            .findOne(taskRentACarContext.getTravelPlanProcess().getTravelPlan().getId())
            .orElseThrow();
        travelPlanDTO.setName(taskRentACarContext.getTravelPlanProcess().getTravelPlan().getName());
        travelPlanDTO.setStartDate(taskRentACarContext.getTravelPlanProcess().getTravelPlan().getStartDate());
        travelPlanDTO.setEndDate(taskRentACarContext.getTravelPlanProcess().getTravelPlan().getEndDate());
        travelPlanDTO.setCarCompanyName(taskRentACarContext.getTravelPlanProcess().getTravelPlan().getCarCompanyName());
        travelPlanDTO.setCarBookingNumber(taskRentACarContext.getTravelPlanProcess().getTravelPlan().getCarBookingNumber());
        travelPlanService.save(travelPlanDTO);
    }

    public void complete(TaskRentACarContextDTO taskRentACarContext) {
        save(taskRentACarContext);
        taskInstanceService.complete(taskRentACarContext.getTaskInstance(), taskRentACarContext.getTravelPlanProcess());
    }
}
