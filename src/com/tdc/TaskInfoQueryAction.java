package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Alvin on 2014/6/17.
 */
public class TaskInfoQueryAction extends ActionSupport {
    private String taskId;
    private List<TaskInfoEntity> resultListNew;

    private String drawingNum;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<TaskInfoEntity> getResultListNew() {
        return resultListNew;
    }

    public void setResultListNew(List<TaskInfoEntity> resultListNew) {
        this.resultListNew = resultListNew;
    }

    @Override
    public String execute() throws Exception {
        if (taskId.length() != 0) {
            Session sess = HibernateUtil.currentSession();

            Transaction tx = sess.beginTransaction();

            setResultListNew(sess.createQuery("from TaskInfoEntity as task where task.taskId=:id and task.drawingNum=:num")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum())
                    .list());

            tx.commit();
            HibernateUtil.closeSession();

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
