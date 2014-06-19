package com.tdc.db;

import java.io.Serializable;

/**
 * Created by Alvin on 2014/6/19.
 */
public class TaskInfoEntityPK implements Serializable {
    private String taskId;
    private int procedureId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfoEntityPK that = (TaskInfoEntityPK) o;

        if (procedureId != that.procedureId) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + procedureId;
        return result;
    }
}
