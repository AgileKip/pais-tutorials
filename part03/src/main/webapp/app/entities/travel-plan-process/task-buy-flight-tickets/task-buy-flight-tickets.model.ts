import { ITaskInstance } from '@/shared/model/task-instance.model';
import { ITravelPlanProcess } from '@/shared/model/travel-plan-process.model';

export class TaskBuyFlightTicketsContext {
  taskInstance?: ITaskInstance = {};
  travelPlanProcess?: ITravelPlanProcess = {};
}
