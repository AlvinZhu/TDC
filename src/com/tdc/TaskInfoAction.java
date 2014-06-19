package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Alvin on 2014/6/17.
 */
public class TaskInfoAction extends ActionSupport {
    private String taskId;
    private List pl;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String orderId) {
        this.taskId = orderId;
    }

    public List getPl() {
        return pl;
    }

    public void setPl(List pl) {
        this.pl = pl;
    }

    @Override
    public String execute() throws Exception {
        if (taskId.length() != 0) {
            Session sess = HibernateUtil.currentSession();
            //开始事务
            Transaction tx = sess.beginTransaction();
            //以HQL语句创建Query对象.
            //执行setString方法为HQL语句的参数赋值
            //Query调用list方法访问查询的全部实例
            setPl(sess.createQuery("from TaskInfoEntity as task where task.taskId=:id")
                    .setString("id", getTaskId())
                    .list());
            tx.commit();
            HibernateUtil.closeSession();
            System.out.println(pl.size());
            int pSize = pl.size();
            for (int i = 0; i < 10 - pSize; i++) {
                pl.add(new TaskInfoEntity());
            }
            System.out.println(pl.size());
            return SUCCESS;
        }
        return ERROR;
    }
}
