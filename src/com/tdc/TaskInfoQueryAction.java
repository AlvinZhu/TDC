package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoEntity;
import com.tdc.db.TaskInfoOldEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Alvin on 2014/6/17.
 */
public class TaskInfoQueryAction extends ActionSupport {
    private String taskId;
    private List resultListNew;
    private List resultListOld;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List getResultListNew() {
        return resultListNew;
    }

    public void setResultListNew(List resultListNew) {
        this.resultListNew = resultListNew;
    }

    public List getResultListOld() {
        return resultListOld;
    }

    public void setResultListOld(List resultListOld) {
        this.resultListOld = resultListOld;
    }

    @Override
    public String execute() throws Exception {
        if (taskId.length() != 0) {
            Session sess = HibernateUtil.currentSession();

            Transaction tx = sess.beginTransaction();

            setResultListOld(sess.createQuery("from TaskInfoOldEntity as task where task.taskId=:id")
                    .setString("id", getTaskId())
                    .list());

            setResultListNew(sess.createQuery("from TaskInfoEntity as task where task.taskId=:id")
                    .setString("id", getTaskId())
                    .list());

            tx.commit();
            HibernateUtil.closeSession();

            int pSize = resultListOld.size();
            for (int i = 0; i < 10 - pSize; i++) {
                resultListOld.add(new TaskInfoOldEntity());
            }

            pSize = resultListNew.size();
            if (0 == pSize) {
                setResultListNew(getResultListOld());
            } else {
                for (int i = 0; i < 10 - pSize; i++) {
                    resultListNew.add(new TaskInfoEntity());
                }
            }

            return SUCCESS;
        }
        return ERROR;
    }
}
