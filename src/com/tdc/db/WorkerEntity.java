package com.tdc.db;

/**
 * Created by Alvin on 2014/7/2.
 */
public class WorkerEntity {
    private String workerId;
    private String deviceId;
    private String procedureName;

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

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerEntity that = (WorkerEntity) o;

        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
            return false;
        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workerId != null ? workerId.hashCode() : 0;
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
        return result;
    }
}
