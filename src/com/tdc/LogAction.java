package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoEntity;
import com.tdc.db.TaskInfoMetaComparator;
import com.tdc.db.TaskInfoMetaEntity;
import com.tdc.db.WorkerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alvin on 2014/6/24.
 */
public class LogAction extends ActionSupport {
    private String taskId;
    private String drawingNum;
    private String procedureId;

    private BigDecimal qualified;
    private BigDecimal unqualified;
    private String workerId;
//    private String deviceId;

    private String type;
    private String time;

    private String result;

    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    @Override
    public String execute() throws Exception {

        if (isInvalid(type)) {
            result = ERROR;
            return SUCCESS;
        }

        List<WorkerEntity> workerList = null;
        List<TaskInfoMetaEntity> taskInfoList = null;
        List<TaskInfoEntity> logList;

        Session sess;
        Transaction tx;

//        if (isInvalid(workerId) || isInvalid(deviceId)) {
        if (isInvalid(workerId)) {
            result = ERROR;
            return SUCCESS;
        } else {
            sess = HibernateUtil.currentSession();
            tx = sess.beginTransaction();
            workerList = sess.createQuery("from WorkerEntity as worker where worker.workerId=:workerId")
                    .setString("workerId", workerId)
                    .list();
            tx.commit();
            HibernateUtil.closeSession();

            if (workerList.size() != 1) {
                result = ERROR;
                return SUCCESS;
            }
        }

        if ("checkuser".equals(type)) {
            result = SUCCESS;
            return SUCCESS;
        }

        if (isInvalid(taskId) || isInvalid(drawingNum) || isInvalid(procedureId)) {
            result = ERROR;
            return SUCCESS;
        } else {
            sess = HibernateUtil.currentSession();
            tx = sess.beginTransaction();
            taskInfoList = sess.createQuery("from TaskInfoMetaEntity as task where task.taskId=:taskId and task.drawingNum=:drawingNum and task.procedureId=:procedureId")
                    .setString("taskId", taskId)
                    .setString("drawingNum", drawingNum)
                    .setString("procedureId", procedureId)
                    .list();
            tx.commit();
            HibernateUtil.closeSession();

            if (taskInfoList.size() != 1) {
                result = ERROR;
                return SUCCESS;
            }
        }

        if (!workerList.get(0).getProcedureName().equals(taskInfoList.get(0).getProcedureName())) {
            result = "nopermission";
            return SUCCESS;
        }


        if ("begin".equals(type)) {
            TaskInfoMetaEntity task = taskInfoList.get(0);

            sess = HibernateUtil.currentSession();
            tx = sess.beginTransaction();
            taskInfoList = sess.createQuery("from TaskInfoMetaEntity as task where task.taskId=:taskId and task.drawingNum=:drawingNum")
                    .setString("taskId", taskId)
                    .setString("drawingNum", drawingNum)
                    .list();
            tx.commit();
            HibernateUtil.closeSession();

            TaskInfoMetaComparator comparator = new TaskInfoMetaComparator();
            Collections.sort(taskInfoList, comparator);

            for (int i = 0; i < taskInfoList.size(); i++) {
                if (task.getProcedureId() == taskInfoList.get(i).getProcedureId()) {
                    if (i != 0) {
                        Integer preProcedureId = taskInfoList.get(i - 1).getProcedureId();
                        sess = HibernateUtil.currentSession();
                        tx = sess.beginTransaction();
                        logList = sess.createQuery("from TaskInfoEntity as task where task.taskId=:id and task.drawingNum=:num and task.procedureId=:pid")
                                .setString("id", getTaskId())
                                .setString("num", getDrawingNum().toString())
                                .setString("pid", preProcedureId.toString())
                                .list();
                        tx.commit();
                        HibernateUtil.closeSession();

                        TaskInfoEntity log = null;
                        if (logList.size() >= 1) {
                            for (TaskInfoEntity aTask : logList) {
                                if (null != aTask.getFinishTime()) {
                                    log = aTask;
                                    break;
                                }
                            }
                            if (null == log) {
                                result = "unready";
                                return SUCCESS;
                            }

                        } else {
                            result = "unready";
                            return SUCCESS;
                        }
                    }
                    break;
                }
            }
            sess = HibernateUtil.currentSession();
            tx = sess.beginTransaction();
            TaskInfoEntity log = new TaskInfoEntity();

            log.setTaskId(taskId);
            log.setDrawingNum(Integer.parseInt(drawingNum));
            log.setProcedureId(Integer.parseInt(procedureId));
            log.setProcedureName(task.getProcedureName());
            log.setWorkHour(task.getWorkHour());
            log.setWorkerId(workerId);
//            log.setDeviceId(getDeviceId());
            log.setDeviceId(workerList.get(0).getDeviceId());
            log.setStartTime(Timestamp.valueOf(time));

            sess.save(log);
            tx.commit();
            HibernateUtil.closeSession();
        }
        if ("end".equals(type)) {
            sess = HibernateUtil.currentSession();
            tx = sess.beginTransaction();
            logList = sess.createQuery("from TaskInfoEntity as task where task.taskId=:id and task.drawingNum=:num and task.procedureId=:pid and task.workerId=:wid")
//            logList = sess.createQuery("from TaskInfoEntity as task where task.taskId=:id and task.drawingNum=:num and task.procedureId=:pid and task.deviceId=:did and task.workerId=:wid")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum().toString())
                    .setString("pid", getProcedureId().toString())
//                    .setString("did", workerList.get(0).getDeviceId())
//                    .setString("did", getDeviceId())
                    .setString("wid", getWorkerId())
                    .list();


            TaskInfoEntity task = null;
            if (logList.size() != 0) {
                for (TaskInfoEntity aTask : logList) {
                    if (null == aTask.getFinishTime()) {
                        task = aTask;
                        break;
                    }
                }
            }

            if (null == task) {
                tx.commit();
                HibernateUtil.closeSession();
                result = "unready";
                return SUCCESS;
            }

            task.setFinishTime(Timestamp.valueOf(time));
            task.setQualified(qualified);
            task.setUnqualified(unqualified);

//            tx.begin();

            sess.flush();
            sess.clear();
            tx.commit();
            HibernateUtil.closeSession();
        }

        result = SUCCESS;
        return SUCCESS;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDrawingNum() {
        return drawingNum;
    }

    public void setDrawingNum(String drawingNum) {
        this.drawingNum = drawingNum;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
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

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

//    public String getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(String deviceId) {
//        this.deviceId = deviceId;
//    }
}
