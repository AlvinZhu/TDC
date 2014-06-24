package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Alvin on 2014/6/24.
 */
public class LogAction extends ActionSupport {
    private String taskId;
    private Integer drawingNum;
    private Integer procedureId;
    private String procedureName;
    //private BigDecimal workHour;
    private BigDecimal qualified;
    private BigDecimal unqualified;
    private String workerId;
    private String deviceId;

    private String type;
    private String time;

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

    public Integer getDrawingNum() {
        return drawingNum;
    }

    public void setDrawingNum(Integer drawingNum) {
        this.drawingNum = drawingNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String execute() throws Exception {

        if (null == taskId || null == drawingNum || null == procedureId || null == procedureName || null == deviceId || null == workerId) {
            return ERROR;
        }

        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();
        if (null != type) {
            if (type.equals("begin")) {
                TaskInfoEntity log = new TaskInfoEntity();

                log.setTaskId(taskId);
                log.setDrawingNum(drawingNum);
                log.setProcedureId(procedureId);
                log.setProcedureName(procedureName);
                //log.setQualified(qualified);
                //log.setUnqualified(unqualified);
                log.setWorkerId(workerId);
                log.setDeviceId(deviceId);
                log.setStartTime(Timestamp.valueOf(time));

                sess.save(log);
            } else if (type.equals("end")) {
                if (qualified.doubleValue() == 0 && unqualified.doubleValue() == 0) {
                    return ERROR;
                }
                List<TaskInfoEntity> taskList = sess.createQuery("from TaskInfoEntity as task where task.taskId=:id and task.drawingNum=:num and task.procedureId=:pid and task.deviceId=:did and task.workerId=:wid")
                        .setString("id", getTaskId())
                        .setString("num", getDrawingNum().toString())
                        .setString("pid", getProcedureId().toString())
                        .setString("did", getWorkerId())
                        .setString("wid", getWorkerId())
                        .list();

                TaskInfoEntity task = null;
                if (taskList.size() == 1) {
                    task = taskList.get(0);

                } else if (taskList.size() > 1) {
                    for (TaskInfoEntity aTask : taskList) {
                        if (null == aTask.getFinishTime()) {
                            task = taskList.get(0);
                            break;
                        }
                    }
                    if (null == task) {
                        tx.commit();
                        HibernateUtil.closeSession();
                        return ERROR;
                    }

                } else {
                    tx.commit();
                    HibernateUtil.closeSession();
                    return ERROR;
                }

                task.setFinishTime(Timestamp.valueOf(time));
                task.setQualified(qualified);
                task.setUnqualified(unqualified);

                sess.flush();
                sess.clear();
            } else {
                tx.commit();
                HibernateUtil.closeSession();
                return ERROR;
            }
        }
        tx.commit();
        HibernateUtil.closeSession();
        return SUCCESS;
    }

}
