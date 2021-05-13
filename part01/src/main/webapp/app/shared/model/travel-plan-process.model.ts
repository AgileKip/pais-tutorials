import { IProcessInstance } from '@/shared/model/process-instance.model';
import { ITravelPlan } from '@/shared/model/travel-plan.model';

export interface ITravelPlanProcess {
  id?: number;
  processInstance?: IProcessInstance | null;
  travelPlan?: ITravelPlan | null;
}

export class TravelPlanProcess implements ITravelPlanProcess {
  constructor(public id?: number, public processInstance?: IProcessInstance | null, public travelPlan?: ITravelPlan | null) {}
}
