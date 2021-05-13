<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('travelPlanProcessApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('travelPlanProcessApp.taskBookAHotel.name')" for="travel-plan-name">name</label>
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
              <label class="form-control-label" v-text="$t('travelPlanProcessApp.taskBookAHotel.startDate')" for="travel-plan-startDate"
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
              <label class="form-control-label" v-text="$t('travelPlanProcessApp.taskBookAHotel.endDate')" for="travel-plan-endDate"
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
              <label class="form-control-label" v-text="$t('travelPlanProcessApp.taskBookAHotel.hotelName')" for="travel-plan-hotelName"
                >hotelName</label
              >
              <input
                type="text"
                class="form-control"
                name="hotelName"
                id="task-book-a-hotel-hotelName"
                data-cy="hotelName"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.hotelName.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.hotelName.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.hotelName.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('travelPlanProcessApp.taskBookAHotel.hotelBookingNumber')"
                for="travel-plan-hotelBookingNumber"
                >hotelBookingNumber</label
              >
              <input
                type="text"
                class="form-control"
                name="hotelBookingNumber"
                id="task-book-a-hotel-hotelBookingNumber"
                data-cy="hotelBookingNumber"
                :class="{
                  valid: !$v.taskContext.travelPlanProcess.travelPlan.hotelBookingNumber.$invalid,
                  invalid: $v.taskContext.travelPlanProcess.travelPlan.hotelBookingNumber.$invalid,
                }"
                v-model="$v.taskContext.travelPlanProcess.travelPlan.hotelBookingNumber.$model"
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

<script lang="ts" src="./task-book-a-hotel-execute.component.ts"></script>
