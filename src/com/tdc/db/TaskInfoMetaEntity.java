package com.tdc.db;

import java.math.BigDecimal;

/**
 * Created by Alvin on 2014/7/3.
 */
public class TaskInfoMetaEntity {
    private String taskId;
    private int drawingNum;
    private int procedureId;
    private String procedureName;
    private BigDecimal workHour;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfoMetaEntity that = (TaskInfoMetaEntity) o;

        if (drawingNum != that.drawingNum) return false;
        if (procedureId != that.procedureId) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (workHour != null ? !workHour.equals(that.workHour) : that.workHour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + drawingNum;
        result = 31 * result + procedureId;
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        result = 31 * result + (workHour != null ? workHour.hashCode() : 0);
        return result;
    }
}
