package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Alvin on 2014/6/30.
 */
public class OrderAction extends ActionSupport implements SessionAware {
    private Map session;
    private String insert;
    private String query;
    private String update;
    private String delete;

    private String oldTaskId;
    private String oldDrawingNum;
    private String oldProcedureId;

    private String planEndTime;
    private String taskId1;
    private String taskId;
    private String drawingNum;
    private String drawingName;
    private String drawingId;
    private String num;
    private String procedureId;
    private String procedureName;
    private BigDecimal workHour;
    private String receiveTime;
    private String epiboleEndTime;

    private List<OrderEntity> list;

    @Override
    public String execute() throws Exception {
        if (insert != null) {
            return insert();
        }

        if (query != null) {
            return query();
        }

        if (update != null) {
            return update();
        }

        if (delete != null) {
            return delete();
        }

        return ERROR;
    }

    public String insert() throws Exception {
        if (!taskId.equals("") && !drawingNum.equals("") && !procedureId.equals("")) {
            Session sess = HibernateUtil.currentSession();
            Transaction transaction = sess.beginTransaction();

            OrderEntity order = new OrderEntity();

            if (!getPlanEndTime().equals("")) {
                order.setPlanEndTime(Date.valueOf(getPlanEndTime()));
            }
            order.setTaskId1(getTaskId1());
            order.setTaskId(getTaskId());
            if (!getDrawingNum().equals("")) {
                order.setDrawingNum(Integer.parseInt(getDrawingNum()));
            }
            order.setDrawingName(getDrawingName());
            order.setDrawingId(getDrawingId());
            if (!getNum().equals("")) {
                order.setNum(Integer.parseInt(getNum()));
            }
            if (getProcedureId() != "") {
                order.setProcedureId(Integer.parseInt(getProcedureId()));
            }
            order.setProcedureName(getProcedureName());
            order.setWorkHour(getWorkHour());
            if (!getReceiveTime().equals("")) {
                order.setReceiveTime(Timestamp.valueOf(getReceiveTime()));
            }
            if (!getEpiboleEndTime().equals("")) {
                order.setEpiboleEndTime(Date.valueOf(getEpiboleEndTime()));
            }

            sess.saveOrUpdate(order);

            transaction.commit();
            HibernateUtil.closeSession();
        }

        query();
        return "insert";
    }


    private String query() throws Exception {
        session.clear();
        session.put("planEndTime", planEndTime);
        session.put("taskId1", taskId1);
        session.put("taskId", taskId);

        session.put("drawingNum", drawingNum);

        session.put("drawingName", drawingName);
        session.put("drawingId", drawingId);

        session.put("num", num);


        session.put("procedureId", procedureId);

        session.put("procedureName", procedureName);
        if (workHour != null) {
            session.put("workHour", workHour.toString());
        }
        session.put("receiveTime", receiveTime);
        session.put("epiboleEndTime", epiboleEndTime);


        Session sess = HibernateUtil.currentSession();
        Transaction transaction = sess.beginTransaction();

        String hql = "from OrderEntity as order ";

        int count = 0;

        if (!planEndTime.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.planEndTime like :planEndTime ";
        }

        if (!taskId1.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.taskId1 like :taskId1 ";
        }

        if (!taskId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.taskId like :taskId ";
        }

        if (!drawingNum.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.drawingNum=:drawingNum ";
        }
        if (!drawingName.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.drawingName like :drawingName ";
        }
        if (!drawingId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.drawingId like :drawingId ";
        }
        if (!num.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.num=:num ";
        }
        if (!procedureId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.procedureId=:procedureId ";
        }
        if (!procedureName.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.procedureName like :procedureName ";
        }
        if (workHour != null) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.workHour=:workHour ";
        }
        if (!receiveTime.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.receiveTime like :receiveTime ";
        }
        if (!epiboleEndTime.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "order.epiboleEndTime like :epiboleEndTime";
        }

        Query query = sess.createQuery(hql);

        if (!planEndTime.equals("")) {
            query.setString("planEndTime", "%" + planEndTime + "%");
        }

        if (!taskId1.equals("")) {
            query.setString("taskId1", "%" + taskId1 + "%");
        }

        if (!taskId.equals("")) {
            query.setString("taskId", "%" + taskId + "%");
        }

        if (!drawingNum.equals("")) {
            query.setString("drawingNum", drawingNum);
        }
        if (!drawingName.equals("")) {
            query.setString("drawingName", "%" + drawingName + "%");
        }
        if (!drawingId.equals("")) {
            query.setString("drawingId", "%" + drawingId + "%");
        }
        if (!num.equals("")) {
            query.setString("num", num);
        }
        if (!procedureId.equals("")) {
            query.setString("procedureId", procedureId);
        }
        if (!procedureName.equals("")) {
            query.setString("procedureName", "%" + procedureName + "%");
        }
        if (workHour != null) {
            query.setParameter("workHour", workHour);
        }
        if (!receiveTime.equals("")) {
            query.setString("receiveTime", "%" + receiveTime + "%");
        }
        if (!epiboleEndTime.equals("")) {
            query.setString("epiboleEndTime", "%" + epiboleEndTime + "%");
        }

        list = query.list();

        transaction.commit();
        HibernateUtil.closeSession();

        return "query";
    }

    public String update() throws Exception {
        if (!taskId.equals("") && !drawingNum.equals("") && !procedureId.equals("")) {
            Session sess = HibernateUtil.currentSession();
            Transaction transaction = sess.beginTransaction();

            int deletedEntities = sess.createQuery("delete from OrderEntity as order where order.taskId=:taskId and order.drawingNum=:drawingNum and order.procedureId=:procedureId")
                    .setString("taskId", oldTaskId)
                    .setString("drawingNum", oldDrawingNum)
                    .setString("procedureId", oldProcedureId)
                    .executeUpdate();

            OrderEntity order = new OrderEntity();

            if (!getPlanEndTime().equals("")) {
                order.setPlanEndTime(Date.valueOf(getPlanEndTime()));
            }
            order.setTaskId1(getTaskId1());
            order.setTaskId(getTaskId());
            if (!getDrawingNum().equals("")) {
                order.setDrawingNum(Integer.parseInt(getDrawingNum()));
            }
            order.setDrawingName(getDrawingName());
            order.setDrawingId(getDrawingId());
            if (!getNum().equals("")) {
                order.setNum(Integer.parseInt(getNum()));
            }
            if (!getProcedureId().equals("")) {
                order.setProcedureId(Integer.parseInt(getProcedureId()));
            }
            order.setProcedureName(getProcedureName());
            order.setWorkHour(getWorkHour());
            if (!getReceiveTime().equals("")) {
                order.setReceiveTime(Timestamp.valueOf(getReceiveTime()));
            }
            if (!getEpiboleEndTime().equals("")) {
                order.setEpiboleEndTime(Date.valueOf(getEpiboleEndTime()));
            }

            sess.saveOrUpdate(order);

            transaction.commit();
            HibernateUtil.closeSession();
        }

        planEndTime = (String) session.get("planEndTime");
        taskId1 = (String) session.get("taskId1");
        taskId = (String) session.get("taskId");

        drawingNum = (String) session.get("drawingNum");

        drawingName = (String) session.get("drawingName");
        drawingId = (String) session.get("drawingId");

        num = (String) session.get("num");

        procedureId = (String) session.get("procedureId");

        procedureName = (String) session.get("procedureName");
        if (session.get("workHour") != null) {
            workHour = BigDecimal.valueOf(Double.parseDouble((String) session.get("workHour")));
        } else {
            workHour = null;
        }
        receiveTime = (String) session.get("receiveTime");
        epiboleEndTime = (String) session.get("epiboleEndTime");

        query();
        return "update";
    }

    public String delete() throws Exception {
        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();

        int deletedEntities = sess.createQuery("delete from OrderEntity as order where order.taskId=:taskId and order.drawingNum=:drawingNum and order.procedureId=:procedureId")
                .setString("taskId", getTaskId())
                .setString("drawingNum", getDrawingNum())
                .setString("procedureId", getProcedureId())
                .executeUpdate();

        tx.commit();
        HibernateUtil.closeSession();

        planEndTime = (String) session.get("planEndTime");
        taskId1 = (String) session.get("taskId1");
        taskId = (String) session.get("taskId");

        drawingNum = (String) session.get("drawingNum");

        drawingName = (String) session.get("drawingName");
        drawingId = (String) session.get("drawingId");

        num = (String) session.get("num");

        procedureId = (String) session.get("procedureId");

        procedureName = (String) session.get("procedureName");
        if (session.get("workHour") != null) {
            workHour = BigDecimal.valueOf(Double.parseDouble((String) session.get("workHour")));
        } else {
            workHour = null;
        }
        receiveTime = (String) session.get("receiveTime");
        epiboleEndTime = (String) session.get("epiboleEndTime");

        query();
        return "delete";
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getTaskId1() {
        return taskId1;
    }

    public void setTaskId1(String taskId1) {
        this.taskId1 = taskId1;
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

    public String getDrawingName() {
        return drawingName;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }

    public String getDrawingId() {
        return drawingId;
    }

    public void setDrawingId(String drawingId) {
        this.drawingId = drawingId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
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

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getEpiboleEndTime() {
        return epiboleEndTime;
    }

    public void setEpiboleEndTime(String epiboleEndTime) {
        this.epiboleEndTime = epiboleEndTime;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<OrderEntity> getList() {
        return list;
    }

    public void setList(List<OrderEntity> list) {
        this.list = list;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }

    public String getOldTaskId() {
        return oldTaskId;
    }

    public void setOldTaskId(String oldTaskId) {
        this.oldTaskId = oldTaskId;
    }

    public String getOldDrawingNum() {
        return oldDrawingNum;
    }

    public void setOldDrawingNum(String oldDrawingNum) {
        this.oldDrawingNum = oldDrawingNum;
    }

    public String getOldProcedureId() {
        return oldProcedureId;
    }

    public void setOldProcedureId(String oldProcedureId) {
        this.oldProcedureId = oldProcedureId;
    }
}
