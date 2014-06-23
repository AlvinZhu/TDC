package com.tdc.xls;

import java.math.BigDecimal;

/**
 * Created by Alvin on 2014/6/22.
 */
public class XlsEntity {
    private String processRegion;
    private String annualPlan;
    private String monthlyPlan;
    private String plansetTags;
    private String planEndTime;
    private String trialEndTime;
    private String taskId1;
    private String taskId2;
    private String taskId;
    private Integer drawingNum;
    private String drawingName;
    private String drawingId;
    private Integer num;
    private Integer procedureId;
    private String procedureName;
    private BigDecimal workHour;
    private String taskTime;
    private String receiveTime;
    private String epiboleStatus;
    private String epiboleCheckTime;
    private String epiboleFactory;
    private String taskType;
    private String applicant;
    private String epiboleEndTime;
    private String planType;

    public String getProcessRegion() {
        return processRegion;
    }

    public void setProcessRegion(String processRegion) {
        this.processRegion = processRegion;
    }

    public String getAnnualPlan() {
        return annualPlan;
    }

    public void setAnnualPlan(String annualPlan) {
        this.annualPlan = annualPlan;
    }

    public String getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthlyPlan(String monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public String getPlansetTags() {
        return plansetTags;
    }

    public void setPlansetTags(String plansetTags) {
        this.plansetTags = plansetTags;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getTrialEndTime() {
        return trialEndTime;
    }

    public void setTrialEndTime(String trialEndTime) {
        this.trialEndTime = trialEndTime;
    }

    public String getTaskId1() {
        return taskId1;
    }

    public void setTaskId1(String taskId1) {
        this.taskId1 = taskId1;
    }

    public String getTaskId2() {
        return taskId2;
    }

    public void setTaskId2(String taskId2) {
        this.taskId2 = taskId2;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getDrawingNum() {
        return drawingNum;
    }

    public void setDrawingNum(Integer drawingNum) {
        this.drawingNum = drawingNum;
    }

    public String getDrawingName() {
        return drawingName;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }

    public String getDrawingId() {
        return drawingId;
    }

    public void setDrawingId(String drawingId) {
        this.drawingId = drawingId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public BigDecimal getWorkHour() {
        return workHour;
    }

    public void setWorkHour(BigDecimal workHour) {
        this.workHour = workHour;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getEpiboleStatus() {
        return epiboleStatus;
    }

    public void setEpiboleStatus(String epiboleStatus) {
        this.epiboleStatus = epiboleStatus;
    }

    public String getEpiboleCheckTime() {
        return epiboleCheckTime;
    }

    public void setEpiboleCheckTime(String epiboleCheckTime) {
        this.epiboleCheckTime = epiboleCheckTime;
    }

    public String getEpiboleFactory() {
        return epiboleFactory;
    }

    public void setEpiboleFactory(String epiboleFactory) {
        this.epiboleFactory = epiboleFactory;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getEpiboleEndTime() {
        return epiboleEndTime;
    }

    public void setEpiboleEndTime(String epiboleEndTime) {
        this.epiboleEndTime = epiboleEndTime;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XlsEntity that = (XlsEntity) o;

        if (annualPlan != null ? !annualPlan.equals(that.annualPlan) : that.annualPlan != null) return false;
        if (applicant != null ? !applicant.equals(that.applicant) : that.applicant != null) return false;
        if (drawingId != null ? !drawingId.equals(that.drawingId) : that.drawingId != null) return false;
        if (drawingName != null ? !drawingName.equals(that.drawingName) : that.drawingName != null) return false;
        if (drawingNum != null ? !drawingNum.equals(that.drawingNum) : that.drawingNum != null) return false;
        if (epiboleCheckTime != null ? !epiboleCheckTime.equals(that.epiboleCheckTime) : that.epiboleCheckTime != null)
            return false;
        if (epiboleEndTime != null ? !epiboleEndTime.equals(that.epiboleEndTime) : that.epiboleEndTime != null)
            return false;
        if (epiboleFactory != null ? !epiboleFactory.equals(that.epiboleFactory) : that.epiboleFactory != null)
            return false;
        if (epiboleStatus != null ? !epiboleStatus.equals(that.epiboleStatus) : that.epiboleStatus != null)
            return false;
        if (monthlyPlan != null ? !monthlyPlan.equals(that.monthlyPlan) : that.monthlyPlan != null) return false;
        if (num != null ? !num.equals(that.num) : that.num != null) return false;
        if (planEndTime != null ? !planEndTime.equals(that.planEndTime) : that.planEndTime != null) return false;
        if (planType != null ? !planType.equals(that.planType) : that.planType != null) return false;
        if (plansetTags != null ? !plansetTags.equals(that.plansetTags) : that.plansetTags != null) return false;
        if (procedureId != null ? !procedureId.equals(that.procedureId) : that.procedureId != null) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (processRegion != null ? !processRegion.equals(that.processRegion) : that.processRegion != null)
            return false;
        if (receiveTime != null ? !receiveTime.equals(that.receiveTime) : that.receiveTime != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (taskId1 != null ? !taskId1.equals(that.taskId1) : that.taskId1 != null) return false;
        if (taskId2 != null ? !taskId2.equals(that.taskId2) : that.taskId2 != null) return false;
        if (taskTime != null ? !taskTime.equals(that.taskTime) : that.taskTime != null) return false;
        if (taskType != null ? !taskType.equals(that.taskType) : that.taskType != null) return false;
        if (trialEndTime != null ? !trialEndTime.equals(that.trialEndTime) : that.trialEndTime != null) return false;
        if (workHour != null ? !workHour.equals(that.workHour) : that.workHour != null) return false;

        return true;
    }
}
