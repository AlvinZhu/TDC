package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Alvin on 2014/6/19.
 */
public class TaskInfoUpdateAction extends ActionSupport {
    private String taskId;
    private List<TaskInfoEntity> resultListNew;

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
        if (resultListNew.size() != 0) {
            Session sess = HibernateUtil.currentSession();

            Transaction tx = sess.beginTransaction();

            int deletedEntities = sess.createQuery("delete from TaskInfoEntity as task where task.taskId=:id")
                    .setString("id", getTaskId())
                    .executeUpdate();
            tx.commit();

            HibernateUtil.closeSession();
            System.out.println(deletedEntities);

            sess = HibernateUtil.currentSession();
            tx = sess.beginTransaction();

            for (int i = 0; i < 10; i++) {
                if (0 == resultListNew.get(i).getProcedureId()) {
                    continue;
                }
                TaskInfoEntity task = new TaskInfoEntity();
                task.setTaskId(getTaskId());
                task.setProcedureId(resultListNew.get(i).getProcedureId());
                task.setProcedureName(resultListNew.get(i).getProcedureName());
                task.setWorkHour(resultListNew.get(i).getWorkHour());
                sess.save(task);
            }
            tx.commit();

            HibernateUtil.closeSession();

            int pSize = resultListNew.size();
            System.out.println(pSize);

            return SUCCESS;
        }
        return ERROR;
    }
}
