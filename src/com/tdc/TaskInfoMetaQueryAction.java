package com.tdc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.db.TaskInfoMetaEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Alvin on 2014/6/17.
 */
public class TaskInfoMetaQueryAction extends ActionSupport {
    private String query;

    private String planEndTime;
    private String taskId1;
    private String taskId;
    private String drawingNum;
    private String drawingName;
    private String drawingId;
    private String num;
    //private String procedureId;
    //private String procedureName;
    //private BigDecimal workHour;
    private String receiveTime;
    private String epiboleEndTime;

    private List<OrderEntity> list;

    private List<TaskInfoMetaEntity> resultListNew;
    private List<OrderEntity> resultListOld;


    private String query() throws Exception {
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
//        if (!procedureId.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "order.procedureId=:procedureId ";
//        }
//        if (!procedureName.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "order.procedureName like :procedureName ";
//        }
//        if (workHour != null) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "order.workHour=:workHour ";
//        }
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
//        if (!procedureId.equals("")) {
//            query.setString("procedureId", procedureId);
//        }
//        if (!procedureName.equals("")) {
//            query.setString("procedureName", "%" + procedureName + "%");
//        }
//        if (workHour != null) {
//            query.setParameter("workHour", workHour);
//        }
        if (!receiveTime.equals("")) {
            query.setString("receiveTime", "%" + receiveTime + "%");
        }
        if (!epiboleEndTime.equals("")) {
            query.setString("epiboleEndTime", "%" + epiboleEndTime + "%");
        }

        list = query.list();

        transaction.commit();
        HibernateUtil.closeSession();

        Set<OrderEntity> set = new HashSet<OrderEntity>();
        List<OrderEntity> newList = new ArrayList<OrderEntity>();
        for (Iterator<OrderEntity> iterator = list.iterator(); iterator.hasNext(); ) {
            OrderEntity element = iterator.next();
            element.setWorkHour(BigDecimal.valueOf(0));
            element.setProcedureId(0);
            element.setProcedureName("");
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);

        return "query";
    }

    @Override
    public String execute() throws Exception {
        String permission = (String) ActionContext.getContext().getSession().get("permission");
        if (permission == null) {
            return ERROR;
        }
        if ((Integer.parseInt(permission) & 4) == 0) {
            return ERROR;
        }

        if (query != null) {
            return query();
        }
        if (!"".equals(taskId)) {
            Session sess = HibernateUtil.currentSession();

            Transaction tx = sess.beginTransaction();

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
//        if (!procedureId.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "order.procedureId=:procedureId ";
//        }
//        if (!procedureName.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "order.procedureName like :procedureName ";
//        }
//        if (workHour != null) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "order.workHour=:workHour ";
//        }
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
//        if (!procedureId.equals("")) {
//            query.setString("procedureId", procedureId);
//        }
//        if (!procedureName.equals("")) {
//            query.setString("procedureName", "%" + procedureName + "%");
//        }
//        if (workHour != null) {
//            query.setParameter("workHour", workHour);
//        }
            if (!receiveTime.equals("")) {
                query.setString("receiveTime", "%" + receiveTime + "%");
            }
            if (!epiboleEndTime.equals("")) {
                query.setString("epiboleEndTime", "%" + epiboleEndTime + "%");
            }
            resultListOld = query.list();

            this.taskId = resultListOld.get(0).getTaskId();
            this.drawingNum = Integer.toString(resultListOld.get(0).getDrawingNum());

            setResultListNew(sess.createQuery("from TaskInfoMetaEntity as task where task.taskId=:id and task.drawingNum=:num")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum())
                    .list());

            tx.commit();
            HibernateUtil.closeSession();

            int pSize = resultListOld.size();
            for (int i = 0; i < 16 - pSize; i++) {
                resultListOld.add(new OrderEntity());
            }

            pSize = resultListNew.size();
            if (0 == pSize) {
                TaskInfoMetaEntity task;
                for (int i = 0; i < 16; i++) {
                    task = new TaskInfoMetaEntity();
                    task.setTaskId(resultListOld.get(i).getTaskId());
                    task.setDrawingNum(resultListOld.get(i).getDrawingNum());
                    task.setProcedureId(resultListOld.get(i).getProcedureId());
                    task.setProcedureName(resultListOld.get(i).getProcedureName());
                    task.setWorkHour(resultListOld.get(i).getWorkHour());
                    resultListNew.add(task);
                }
            } else {
                for (int i = 0; i < 16 - pSize; i++) {
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

//    public String getProcedureId() {
//        return procedureId;
//    }
//
//    public void setProcedureId(String procedureId) {
//        this.procedureId = procedureId;
//    }
//
//    public String getProcedureName() {
//        return procedureName;
//    }
//
//    public void setProcedureName(String procedureName) {
//        this.procedureName = procedureName;
//    }
//
//    public BigDecimal getWorkHour() {
//        return workHour;
//    }
//
//    public void setWorkHour(BigDecimal workHour) {
//        this.workHour = workHour;
//    }

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

    public List<OrderEntity> getList() {
        return list;
    }

    public void setList(List<OrderEntity> list) {
        this.list = list;
    }

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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
