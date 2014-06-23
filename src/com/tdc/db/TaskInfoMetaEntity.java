package com.tdc.db;

import java.math.BigDecimal;

/**
 * Created by Alvin on 2014/6/22.
 */
public class TaskInfoMetaEntity {
    private int id;
    private String taskId;
    private Integer procedureId;
    private String procedureName;
    private BigDecimal workHour;
    private Integer drawingNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfoMetaEntity that = (TaskInfoMetaEntity) o;

        if (id != that.id) return false;
        if (procedureId != null ? !procedureId.equals(that.procedureId) : that.procedureId != null) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (workHour != null ? !workHour.equals(that.workHour) : that.workHour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (procedureId != null ? procedureId.hashCode() : 0);
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        result = 31 * result + (workHour != null ? workHour.hashCode() : 0);
        return result;
    }

    public Integer getDrawingNum() {
        return drawingNum;
    }

    public void setDrawingNum(Integer drawingNum) {
        this.drawingNum = drawingNum;
    }
}
