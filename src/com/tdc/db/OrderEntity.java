package com.tdc.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Alvin on 2014/7/3.
 */
public class OrderEntity {
    private String processRegion;
    private String annualPlan;
    private String monthlyPlan;
    private String plansetTags;
    private Date planEndTime;
    private String trialEndTime;
    private String taskId1;
    private String taskId2;
    private String taskId;
    private int drawingNum;
    private String drawingName;
    private String drawingId;
    private Integer num;
    private int procedureId;
    private String procedureName;
    private BigDecimal workHour;
    private String taskTime;
    private Timestamp receiveTime;
    private String epiboleStatus;
    private String epiboleCheckTime;
    private String epiboleFactory;
    private String taskType;
    private String applicant;
    private Date epiboleEndTime;
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

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
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

    public int getDrawingNum() {
        return drawingNum;
    }

    public void setDrawingNum(int drawingNum) {
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

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
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

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
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

    public Date getEpiboleEndTime() {
        return epiboleEndTime;
    }

    public void setEpiboleEndTime(Date epiboleEndTime) {
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

        OrderEntity that = (OrderEntity) o;

        if (drawingNum != that.drawingNum) return false;
        if (procedureId != that.procedureId) return false;
        if (annualPlan != null ? !annualPlan.equals(that.annualPlan) : that.annualPlan != null) return false;
        if (applicant != null ? !applicant.equals(that.applicant) : that.applicant != null) return false;
        if (drawingId != null ? !drawingId.equals(that.drawingId) : that.drawingId != null) return false;
        if (drawingName != null ? !drawingName.equals(that.drawingName) : that.drawingName != null) return false;
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

    @Override
    public int hashCode() {
        int result = processRegion != null ? processRegion.hashCode() : 0;
        result = 31 * result + (annualPlan != null ? annualPlan.hashCode() : 0);
        result = 31 * result + (monthlyPlan != null ? monthlyPlan.hashCode() : 0);
        result = 31 * result + (plansetTags != null ? plansetTags.hashCode() : 0);
        result = 31 * result + (planEndTime != null ? planEndTime.hashCode() : 0);
        result = 31 * result + (trialEndTime != null ? trialEndTime.hashCode() : 0);
        result = 31 * result + (taskId1 != null ? taskId1.hashCode() : 0);
        result = 31 * result + (taskId2 != null ? taskId2.hashCode() : 0);
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + drawingNum;
        result = 31 * result + (drawingName != null ? drawingName.hashCode() : 0);
        result = 31 * result + (drawingId != null ? drawingId.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + procedureId;
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        result = 31 * result + (workHour != null ? workHour.hashCode() : 0);
        result = 31 * result + (taskTime != null ? taskTime.hashCode() : 0);
        result = 31 * result + (receiveTime != null ? receiveTime.hashCode() : 0);
        result = 31 * result + (epiboleStatus != null ? epiboleStatus.hashCode() : 0);
        result = 31 * result + (epiboleCheckTime != null ? epiboleCheckTime.hashCode() : 0);
        result = 31 * result + (epiboleFactory != null ? epiboleFactory.hashCode() : 0);
        result = 31 * result + (taskType != null ? taskType.hashCode() : 0);
        result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
        result = 31 * result + (epiboleEndTime != null ? epiboleEndTime.hashCode() : 0);
        result = 31 * result + (planType != null ? planType.hashCode() : 0);
        return result;
    }
}
