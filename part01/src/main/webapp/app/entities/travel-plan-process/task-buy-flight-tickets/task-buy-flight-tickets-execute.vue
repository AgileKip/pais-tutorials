<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('travelPlanTutorialApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanTutorialApp.taskBuyFlightTickets.name')" for="travel-plan-name"
                >name</label
              >
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="name"
                id="travel-plan-name"
                data-cy="name"
                v-model="taskContext.travelPlanProcess.travelPlan.name"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelPlanTutorialApp.taskBuyFlightTickets.startDate')"
                for="travel-plan-startDate"
                >startDate</label
              >
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="startDate"
                id="travel-plan-startDate"
                data-cy="startDate"
                v-model="taskContext.travelPlanProcess.travelPlan.startDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanTutorialApp.taskBuyFlightTickets.endDate')" for="travel-plan-endDate"
                >endDate</label
              >
              <input
                readonly
                type="text"
                class="form-control form-control-sm"
                name="endDate"
                id="travel-plan-endDate"
                data-cy="endDate"
                v-model="taskContext.travelPlanProcess.travelPlan.endDate"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelPlanTutorialApp.taskBuyFlightTickets.airlineCompanyName')"
                for="travel-plan-airlineCompanyName"
                >airlineCompanyName</label
              >
              <input
                type="text"
                class="form-control"
                name="airlineCompanyName"
                id="task-buy-flight-tickets-airlineCompanyName"
                data-cy="airlineCompanyName"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.airlineCompanyName.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.airlineCompanyName.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.airlineCompanyName.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelPlanTutorialApp.taskBuyFlightTickets.airlineTicketNumber')"
                for="travel-plan-airlineTicketNumber"
                >airlineTicketNumber</label
              >
              <input
                type="text"
                class="form-control"
                name="airlineTicketNumber"
                id="task-buy-flight-tickets-airlineTicketNumber"
                data-cy="airlineTicketNumber"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.airlineTicketNumber.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.airlineTicketNumber.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.airlineTicketNumber.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-buy-flight-tickets-execute.component.ts"></script>
