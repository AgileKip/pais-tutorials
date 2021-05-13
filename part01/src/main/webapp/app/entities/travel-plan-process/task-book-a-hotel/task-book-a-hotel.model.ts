import { ITaskInstance } from '@/shared/model/task-instance.model';
import { ITravelPlanProcess } from '@/shared/model/travel-plan-process.model';

export class TaskBookAHotelContext {
  taskInstance?: ITaskInstance = {};
  travelPlanProcess?: ITravelPlanProcess = {};
}
