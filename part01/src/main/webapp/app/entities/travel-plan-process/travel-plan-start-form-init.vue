<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="travelPlanTutorialApp.travelPlanStartForm.home.createOrEditLabel"
          data-cy="TravelPlanStartFormCreateUpdateHeading"
          v-text="$t('travelPlanTutorialApp.travelPlanStartForm.home.createOrEditLabel')"
        >
          Create or edit a TravelPlanStartForm
        </h2>
        <div v-if="travelPlanProcess.processInstance">
          <akip-show-process-definition :processDefinition="travelPlanProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="travelPlanProcess.travelPlan">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanTutorialApp.travelPlanStartForm.name')"
                    for="travel-plan-start-form-name"
                    >Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="travel-plan-start-form-name"
                    data-cy="name"
                    :class="{
                      valid: !$v.travelPlanProcess.travelPlan.name.$invalid,
                      invalid: $v.travelPlanProcess.travelPlan.name.$invalid,
                    }"
                    v-model="$v.travelPlanProcess.travelPlan.name.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanTutorialApp.travelPlanStartForm.startDate')"
                    for="travel-plan-start-form-startDate"
                    >Start Date</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="startDate"
                    id="travel-plan-start-form-startDate"
                    data-cy="startDate"
                    :class="{
                      valid: !$v.travelPlanProcess.travelPlan.startDate.$invalid,
                      invalid: $v.travelPlanProcess.travelPlan.startDate.$invalid,
                    }"
                    v-model="$v.travelPlanProcess.travelPlan.startDate.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('travelPlanTutorialApp.travelPlanStartForm.endDate')"
                    for="travel-plan-start-form-endDate"
                    >End Date</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="endDate"
                    id="travel-plan-start-form-endDate"
                    data-cy="endDate"
                    :class="{
                      valid: !$v.travelPlanProcess.travelPlan.endDate.$invalid,
                      invalid: $v.travelPlanProcess.travelPlan.endDate.$invalid,
                    }"
                    v-model="$v.travelPlanProcess.travelPlan.endDate.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.travelPlanProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./travel-plan-start-form-init.component.ts"></script>
