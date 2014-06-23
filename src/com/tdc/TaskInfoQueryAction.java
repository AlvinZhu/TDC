package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.db.TaskInfoMetaEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Alvin on 2014/6/17.
 */
public class TaskInfoQueryAction extends ActionSupport {
    private String taskId;
    private List<TaskInfoMetaEntity> resultListNew;
    private List<OrderEntity> resultListOld;
    private String drawingNum;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<TaskInfoMetaEntity> getResultListNew() {
        return resultListNew;
    }

    public void setResultListNew(List<TaskInfoMetaEntity> resultListNew) {
        this.resultListNew = resultListNew;
    }

    public List<OrderEntity> getResultListOld() {
        return resultListOld;
    }

    public void setResultListOld(List<OrderEntity> resultListOld) {
        this.resultListOld = resultListOld;
    }

    @Override
    public String execute() throws Exception {
        if (taskId.length() != 0) {
            Session sess = HibernateUtil.currentSession();

            Transaction tx = sess.beginTransaction();

            setResultListOld(sess.createQuery("from OrderEntity as order where order.taskId=:id and order.drawingNum=:num")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum())
                    .list());

            setResultListNew(sess.createQuery("from TaskInfoMetaEntity as task where task.taskId=:id and task.drawingNum=:num")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum())
                    .list());

            tx.commit();
            HibernateUtil.closeSession();

            int pSize = resultListOld.size();
            for (int i = 0; i < 12 - pSize; i++) {
                resultListOld.add(new OrderEntity());
            }

            pSize = resultListNew.size();
            if (0 == pSize) {
                TaskInfoMetaEntity task;
                for (int i = 0; i < 12; i++) {
                    task = new TaskInfoMetaEntity();
                    task.setTaskId(resultListOld.get(i).getTaskId());
                    task.setDrawingNum(resultListOld.get(i).getDrawingNum());
                    task.setProcedureId(resultListOld.get(i).getProcedureId());
                    task.setProcedureName(resultListOld.get(i).getProcedureName());
                    task.setWorkHour(resultListOld.get(i).getWorkHour());
                    resultListNew.add(task);
                }
            } else {
                for (int i = 0; i < 12 - pSize; i++) {
                    resultListNew.add(new TaskInfoMetaEntity());
                }
            }

            return SUCCESS;
        }
        return ERROR;
    }

    public String getDrawingNum() {
        return drawingNum;
    }

    public void setDrawingNum(String drawingNum) {
        this.drawingNum = drawingNum;
    }
}
