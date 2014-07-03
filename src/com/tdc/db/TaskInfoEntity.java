package com.tdc.db;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Alvin on 2014/7/3.
 */
public class TaskInfoEntity {
    private String taskId;
    private int drawingNum;
    private int procedureId;
    private String procedureName;
    private BigDecimal workHour;
    private BigDecimal qualified;
    private BigDecimal unqualified;
    private Integer status;
    private Timestamp startTime;
    private Timestamp finishTime;
    private String workerId;
    private String deviceId;
    private String checkerId;
    private Timestamp checkTime;

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

    public BigDecimal getQualified() {
        return qualified;
    }

    public void setQualified(BigDecimal qualified) {
        this.qualified = qualified;
    }

    public BigDecimal getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(BigDecimal unqualified) {
        this.unqualified = unqualified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfoEntity that = (TaskInfoEntity) o;

        if (drawingNum != that.drawingNum) return false;
        if (procedureId != that.procedureId) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (checkerId != null ? !checkerId.equals(that.checkerId) : that.checkerId != null) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (qualified != null ? !qualified.equals(that.qualified) : that.qualified != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (unqualified != null ? !unqualified.equals(that.unqualified) : that.unqualified != null) return false;
        if (workHour != null ? !workHour.equals(that.workHour) : that.workHour != null) return false;
        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + drawingNum;
        result = 31 * result + procedureId;
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        result = 31 * result + (workHour != null ? workHour.hashCode() : 0);
        result = 31 * result + (qualified != null ? qualified.hashCode() : 0);
        result = 31 * result + (unqualified != null ? unqualified.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (workerId != null ? workerId.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (checkerId != null ? checkerId.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        return result;
    }
}
