package org.agikekip.tutorials.travel.process.travelPlanProcess;

import org.agikekip.tutorials.travel.service.dto.TaskInstanceDTO;
import org.agikekip.tutorials.travel.service.dto.TravelPlanProcessDTO;

public class TaskBuyFlightTicketsContextDTO {

    private TravelPlanProcessDTO travelPlanProcess;
    private TaskInstanceDTO taskInstance;

    public TravelPlanProcessDTO getTravelPlanProcess() {
        return travelPlanProcess;
    }

    public void setTravelPlanProcess(TravelPlanProcessDTO travelPlanProcess) {
        this.travelPlanProcess = travelPlanProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
