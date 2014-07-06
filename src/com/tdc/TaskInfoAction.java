package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.db.TaskInfoEntity;
import com.tdc.db.WorkerEntity;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Alvin on 2014/6/30.
 */
public class TaskInfoAction extends ActionSupport implements SessionAware {
    private Map session;

    private String insert;
    private String query;
    private String update;
    private String delete;
    private String export;

    private String oldTaskId;
    private String oldDrawingNum;
    private String oldProcedureId;

    //    private String planEndTime;
//    private String taskId1;
    private String taskId;
    private String drawingNum;
    //    private String drawingName;
//    private String drawingId;
//    private String num;
    private String procedureId;
    private String procedureName;
    private BigDecimal workHour;
    //    private String receiveTime;
//    private String epiboleEndTime;
    private BigDecimal qualified;
    private BigDecimal unqualified;
    private String status;
    private String startTime;
    private String finishTime;
    private String workerId;
    private String deviceId;
    private String checkerId;
    private String checkTime;

    private List<TaskInfoEntity> list;

//    private String genWorkerId(){
//        String id = "";
//
//        Session sess = HibernateUtil.currentSession();
//        Transaction tx = sess.beginTransaction();
//        List<WorkerEntity> workerList = sess.createQuery("from WorkerEntity as worker where worker.workerId=:workerId")
//                .setString("workerId", workerId)
//                .list();
//        tx.commit();
//        HibernateUtil.closeSession();
//
//        if (workerList.size() == 1){
//            id = workerId;
//        }
//
//        sess = HibernateUtil.currentSession();
//        tx = sess.beginTransaction();
//        workerList = sess.createQuery("from WorkerEntity as worker where worker.workerName=:workerName")
//                .setString("workerName", workerId)
//                .list();
//        tx.commit();
//        HibernateUtil.closeSession();
//
//        if (workerList.size() == 1){
//            id = workerList.get(0).getWorkerId();
//        }
//
//        return id;
//    }

    private String genWorkerName(String id){
        String name = "";

        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();
        List<WorkerEntity> workerList = sess.createQuery("from WorkerEntity as worker where worker.workerId=:workerId")
                .setString("workerId", id)
                .list();
        tx.commit();
        HibernateUtil.closeSession();

        if (workerList.size() == 1){
            name = workerList.get(0).getWorkerName();
        }

        return name;
    }

    @Override
    public String execute() throws Exception {
        String permission = (String) session.get("permission");
        if (permission == null) {
            return ERROR;
        }
        if ((Integer.parseInt(permission) & 2) == 0) {
            return ERROR;
        }

        if (insert != null) {
            return insert();
        }

        if (query != null) {
            return query();
        }

        if (export != null) {
            query();
            return export();
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

            TaskInfoEntity task = new TaskInfoEntity();

//            if (!getPlanEndTime().equals("")) {
//                order.setPlanEndTime(Date.valueOf(getPlanEndTime()));
//            }
//            order.setTaskId1(getTaskId1());
            task.setTaskId(getTaskId());
            if (!getDrawingNum().equals("")) {
                task.setDrawingNum(Integer.parseInt(getDrawingNum()));
            }
//            order.setDrawingName(getDrawingName());
//            order.setDrawingId(getDrawingId());
//            if (!getNum().equals("")) {
//                order.setNum(Integer.parseInt(getNum()));
//            }
            if (!getProcedureId().equals("")) {
                task.setProcedureId(Integer.parseInt(getProcedureId()));
            }
            task.setProcedureName(getProcedureName());
            task.setWorkHour(getWorkHour());
//            if (!getReceiveTime().equals("")) {
//                order.setReceiveTime(Timestamp.valueOf(getReceiveTime()));
//            }
//            if (!getEpiboleEndTime().equals("")) {
//                order.setEpiboleEndTime(Date.valueOf(getEpiboleEndTime()));
//            }
            task.setQualified(getQualified());
            task.setUnqualified(getUnqualified());
            if (!getStatus().equals("")) {
                task.setStatus(Integer.valueOf(getStatus()));
            }
            if (!getStartTime().equals("")) {
                task.setStartTime(Timestamp.valueOf(getStartTime()));
            }
            if (!getFinishTime().equals("")) {
                task.setFinishTime(Timestamp.valueOf(getFinishTime()));
            }
            task.setWorkerId(getWorkerId());
            task.setDeviceId(getDeviceId());
            task.setCheckerId(getCheckerId());
            if (!getCheckTime().equals("")) {
                task.setCheckTime(Timestamp.valueOf(getCheckTime()));
            }

            sess.saveOrUpdate(task);

            transaction.commit();
            HibernateUtil.closeSession();
        }

        query();
        return "insert";
    }


    public String query() throws Exception {
        String permission = (String) session.get("permission");
        session.clear();
        session.put("permission", permission);
//        session.put("planEndTime", planEndTime);
//        session.put("taskId1", taskId1);
        session.put("taskId", taskId);

        session.put("drawingNum", drawingNum);

//        session.put("drawingName", drawingName);
//        session.put("drawingId", drawingId);

//        session.put("num", num);


        session.put("procedureId", procedureId);

        session.put("procedureName", procedureName);
        if (workHour != null) {
            session.put("workHour", workHour.toString());
        }
//        session.put("receiveTime", receiveTime);
//        session.put("epiboleEndTime", epiboleEndTime);
        if (workHour != null) {
            session.put("qualified", qualified.toString());
        }
        if (unqualified != null) {
            session.put("unqualified", unqualified.toString());
        }
        session.put("status", status);
        session.put("startTime", startTime);
        session.put("finishTime", finishTime);
        session.put("workerId", workerId);
        session.put("deviceId", deviceId);
        session.put("checkerId", checkerId);
        session.put("checkTime", checkTime);

        Session sess = HibernateUtil.currentSession();
        Transaction transaction = sess.beginTransaction();

        String hql = "from TaskInfoEntity as task ";

        int count = 0;

//        if (!planEndTime.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.planEndTime like :planEndTime ";
//        }
//
//        if (!taskId1.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.taskId1 like :taskId1 ";
//        }

        if (!taskId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.taskId like :taskId ";
        }

        if (!drawingNum.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.drawingNum=:drawingNum ";
        }
//        if (!drawingName.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.drawingName like :drawingName ";
//        }
//        if (!drawingId.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.drawingId like :drawingId ";
//        }
//        if (!num.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.num=:num ";
//        }
        if (!procedureId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.procedureId=:procedureId ";
        }
        if (!procedureName.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.procedureName like :procedureName ";
        }
        if (workHour != null) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.workHour=:workHour ";
        }
//        if (!receiveTime.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.receiveTime like :receiveTime ";
//        }
//        if (!epiboleEndTime.equals("")) {
//            count++;
//            if (count == 1) {
//                hql += "where ";
//            } else {
//                hql += "and ";
//            }
//            hql += "task.epiboleEndTime like :epiboleEndTime";
//        }
        if (qualified != null) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.qualified=:qualified ";
        }

        if (unqualified != null) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.unqualified=:unqualified ";
        }

        if (!status.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.status like :status ";
        }

        if (!startTime.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.startTime like :startTime ";
        }

        if (!finishTime.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.finishTime like :finishTime ";
        }

        if (!workerId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.workerId like :workerId ";
        }

        if (!deviceId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.deviceId like :deviceId ";
        }

        if (!checkerId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.checkerId like :checkerId ";
        }

        if (!checkTime.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "task.checkTime like :checkTime ";
        }

        Query query = sess.createQuery(hql);

//        if (!planEndTime.equals("")) {
//            query.setString("planEndTime", "%" + planEndTime + "%");
//        }
//
//        if (!taskId1.equals("")) {
//            query.setString("taskId1", "%" + taskId1 + "%");
//        }

        if (!taskId.equals("")) {
            query.setString("taskId", "%" + taskId + "%");
        }

        if (!drawingNum.equals("")) {
            query.setString("drawingNum", drawingNum);
        }
//        if (!drawingName.equals("")) {
//            query.setString("drawingName", "%" + drawingName + "%");
//        }
//        if (!drawingId.equals("")) {
//            query.setString("drawingId", "%" + drawingId + "%");
//        }
//        if (!num.equals("")) {
//            query.setString("num", num);
//        }
        if (!procedureId.equals("")) {
            query.setString("procedureId", procedureId);
        }
        if (!procedureName.equals("")) {
            query.setString("procedureName", "%" + procedureName + "%");
        }
        if (workHour != null) {
            query.setParameter("workHour", workHour);
        }
//        if (!receiveTime.equals("")) {
//            query.setString("receiveTime", "%" + receiveTime + "%");
//        }
//        if (!epiboleEndTime.equals("")) {
//            query.setString("epiboleEndTime", "%" + epiboleEndTime + "%");
//        }
        if (qualified != null) {
            query.setParameter("qualified", qualified);
        }
        if (unqualified != null) {
            query.setParameter("unqualified", unqualified);
        }
        if (!status.equals("")) {
            query.setString("status", "%" + status + "%");
        }
        if (!startTime.equals("")) {
            query.setString("startTime", "%" + startTime + "%");
        }
        if (!finishTime.equals("")) {
            query.setString("finishTime", "%" + finishTime + "%");
        }
        if (!getWorkerId().equals("")) {
            query.setString("workerId", "%" + getWorkerId() + "%");
        }
        if (!deviceId.equals("")) {
            query.setString("deviceId", "%" + deviceId + "%");
        }
        if (!checkerId.equals("")) {
            query.setString("checkerId", "%" + checkerId + "%");
        }
        if (!checkTime.equals("")) {
            query.setString("checkTime", "%" + checkTime + "%");
        }

        list = query.list();

        transaction.commit();
        HibernateUtil.closeSession();

        return "query";
    }

    public String export() throws Exception {
        int countRow = list.size();
        int countColumn = 25;

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow HSSFRow = hssfSheet.createRow(0);

        HSSFRow.createCell(0).setCellValue(new HSSFRichTextString("加工区域"));
        HSSFRow.createCell(1).setCellValue(new HSSFRichTextString("年度计划"));
        HSSFRow.createCell(2).setCellValue(new HSSFRichTextString("月度计划"));
        HSSFRow.createCell(3).setCellValue(new HSSFRichTextString("计划组特殊标记"));
        HSSFRow.createCell(4).setCellValue(new HSSFRichTextString("计划完成时间"));
        HSSFRow.createCell(5).setCellValue(new HSSFRichTextString("试装计划完成时间"));
        HSSFRow.createCell(6).setCellValue(new HSSFRichTextString("任务单号"));
        HSSFRow.createCell(7).setCellValue(new HSSFRichTextString("任务流水号"));
        HSSFRow.createCell(8).setCellValue(new HSSFRichTextString("生产令号"));
        HSSFRow.createCell(9).setCellValue(new HSSFRichTextString("图纸序号"));
        HSSFRow.createCell(10).setCellValue(new HSSFRichTextString("图纸名称"));
        HSSFRow.createCell(11).setCellValue(new HSSFRichTextString("图纸编号"));
        HSSFRow.createCell(12).setCellValue(new HSSFRichTextString("件数"));

        HSSFRow.createCell(16).setCellValue(new HSSFRichTextString("任务安排时间"));
        HSSFRow.createCell(17).setCellValue(new HSSFRichTextString("签收时间"));
        HSSFRow.createCell(18).setCellValue(new HSSFRichTextString("外包状态"));
        HSSFRow.createCell(19).setCellValue(new HSSFRichTextString("外协送验时间"));
        HSSFRow.createCell(20).setCellValue(new HSSFRichTextString("外包厂家"));
        HSSFRow.createCell(21).setCellValue(new HSSFRichTextString("任务类别"));
        HSSFRow.createCell(22).setCellValue(new HSSFRichTextString("提出人"));
        HSSFRow.createCell(23).setCellValue(new HSSFRichTextString("外包计划时间"));
        HSSFRow.createCell(24).setCellValue(new HSSFRichTextString("计划形式"));

        HSSFRow.createCell(13).setCellValue(new HSSFRichTextString("工序号"));
        HSSFRow.createCell(14).setCellValue(new HSSFRichTextString("工序名称"));
        HSSFRow.createCell(15).setCellValue(new HSSFRichTextString("工序工时"));

        HSSFRow.createCell(25).setCellValue(new HSSFRichTextString("合格件数"));
        HSSFRow.createCell(26).setCellValue(new HSSFRichTextString("报废件数"));
        HSSFRow.createCell(27).setCellValue(new HSSFRichTextString("状态"));
        HSSFRow.createCell(28).setCellValue(new HSSFRichTextString("开始时间"));
        HSSFRow.createCell(29).setCellValue(new HSSFRichTextString("完成时间"));
        HSSFRow.createCell(30).setCellValue(new HSSFRichTextString("操作者"));
        HSSFRow.createCell(31).setCellValue(new HSSFRichTextString("设备编号"));
        HSSFRow.createCell(32).setCellValue(new HSSFRichTextString("检验员"));
        HSSFRow.createCell(33).setCellValue(new HSSFRichTextString("检验时间"));



        List<OrderEntity> orderEntityList;
        OrderEntity order = null;
        for (int i = 0; i < countRow; i++) {
            HSSFRow = hssfSheet.createRow(i + 1);
            TaskInfoEntity task = list.get(i);


            if (order == null
                    || !order.getTaskId().equals(task.getTaskId())
                    || order.getDrawingNum() != task.getDrawingNum()
                    || order.getProcedureId() != task.getProcedureId()) {
                Session sess = HibernateUtil.currentSession();
                Transaction tx = sess.beginTransaction();
                orderEntityList = sess.createQuery("from OrderEntity as task where task.taskId=:taskId and task.drawingNum=:drawingNum and task.procedureId=:procedureId ")
                        .setString("taskId", task.getTaskId())
                        .setString("drawingNum", String.valueOf(task.getDrawingNum()))
                        .setString("procedureId", String.valueOf(task.getProcedureId()))
                        .list();

                tx.commit();
                HibernateUtil.closeSession();
                order = orderEntityList.get(0);
            }

            if (order.getProcessRegion() != null) {
                HSSFRow.createCell(0).setCellValue(order.getProcessRegion());
            }
            if (order.getAnnualPlan() != null) {
                HSSFRow.createCell(1).setCellValue(order.getAnnualPlan());
            }
            if (order.getMonthlyPlan() != null) {
                HSSFRow.createCell(2).setCellValue(order.getMonthlyPlan());
            }
            if (order.getPlansetTags() != null) {
                HSSFRow.createCell(3).setCellValue(order.getPlansetTags());
            }
            if (order.getPlanEndTime().toString() != null) {
                HSSFRow.createCell(4).setCellValue(order.getPlanEndTime().toString());
            }
            if (order.getTrialEndTime() != null) {
                HSSFRow.createCell(5).setCellValue(order.getTrialEndTime());
            }
            if (order.getTaskId1() != null) {
                HSSFRow.createCell(6).setCellValue(order.getTaskId1());
            }
            if (order.getTaskId2() != null) {
                HSSFRow.createCell(7).setCellValue(order.getTaskId2());
            }
            if (order.getTaskId() != null) {
                HSSFRow.createCell(8).setCellValue(order.getTaskId());
            }

            HSSFRow.createCell(9).setCellValue(order.getDrawingNum());

            if (order.getDrawingName() != null) {
                HSSFRow.createCell(10).setCellValue(order.getDrawingName());
            }
            if (order.getDrawingId() != null) {
                HSSFRow.createCell(11).setCellValue(order.getDrawingId());
            }
            if (order.getNum() != null) {
                HSSFRow.createCell(12).setCellValue(order.getNum());
            }

            if (order.getTaskTime() != null) {
                HSSFRow.createCell(16).setCellValue(order.getTaskTime());
            }
            if (order.getReceiveTime() != null) {
                HSSFRow.createCell(17).setCellValue(order.getReceiveTime().toString());
            }
            if (order.getEpiboleStatus() != null) {
                HSSFRow.createCell(18).setCellValue(order.getEpiboleStatus());
            }
            if (order.getEpiboleCheckTime() != null) {
                HSSFRow.createCell(19).setCellValue(order.getEpiboleCheckTime());
            }
            if (order.getEpiboleFactory() != null) {
                HSSFRow.createCell(20).setCellValue(order.getEpiboleFactory());
            }
            if (order.getTaskType() != null) {
                HSSFRow.createCell(21).setCellValue(order.getTaskType());
            }
            if (order.getApplicant() != null) {
                HSSFRow.createCell(22).setCellValue(order.getApplicant());
            }
            if (order.getEpiboleEndTime() != null) {
                HSSFRow.createCell(23).setCellValue(order.getEpiboleEndTime().toString());
            }
            if (order.getPlanType() != null) {
                HSSFRow.createCell(24).setCellValue(order.getPlanType());
            }

            HSSFRow.createCell(13).setCellValue(task.getProcedureId());

            if (task.getProcedureName() != null) {
                HSSFRow.createCell(14).setCellValue(task.getProcedureName());
            }
            if (task.getWorkHour() != null) {
                HSSFRow.createCell(15).setCellValue(task.getWorkHour().doubleValue());
            }

            if (task.getQualified() != null) {
                HSSFRow.createCell(25).setCellValue(task.getQualified().doubleValue());
            }
            if (task.getUnqualified() != null) {
                HSSFRow.createCell(26).setCellValue(task.getUnqualified().doubleValue());
            }
            if (task.getStatus() != null) {
                HSSFRow.createCell(27).setCellValue(task.getStatus());
            }
            if (task.getStartTime() != null) {
                HSSFRow.createCell(28).setCellValue(task.getStartTime().toString());
            }
            if (task.getFinishTime() != null) {
                HSSFRow.createCell(29).setCellValue(task.getFinishTime().toString());
            }
            if (task.getWorkerId() != null) {
                HSSFRow.createCell(30).setCellValue(genWorkerName(task.getWorkerId()));
            }
            if (task.getDeviceId() != null) {
                HSSFRow.createCell(31).setCellValue(task.getDeviceId());
            }
            if (task.getCheckerId() != null) {
                HSSFRow.createCell(32).setCellValue(task.getCheckerId());
            }
            if (task.getCheckTime() != null) {
                HSSFRow.createCell(33).setCellValue(task.getCheckTime().toString());
            }

        }


        File file = new File(ServletActionContext.getServletContext().getRealPath(File.separator + "res"), "log.xls");
        OutputStream out = new FileOutputStream(file);
        hssfWorkbook.write(out);
        out.close();

        return "export";
    }

    public String update() throws Exception {

        if (!taskId.equals("") && !drawingNum.equals("") && !procedureId.equals("")) {
            Session sess = HibernateUtil.currentSession();
            Transaction transaction = sess.beginTransaction();

            int deletedEntities = sess.createQuery("delete from TaskInfoEntity as task where task.taskId=:taskId and task.drawingNum=:drawingNum and task.procedureId=:procedureId")
                    .setString("taskId", oldTaskId)
                    .setString("drawingNum", oldDrawingNum)
                    .setString("procedureId", oldProcedureId)
                    .executeUpdate();

            TaskInfoEntity task = new TaskInfoEntity();

//            if (!getPlanEndTime().equals("")) {
//                order.setPlanEndTime(Date.valueOf(getPlanEndTime()));
//            }
//            order.setTaskId1(getTaskId1());
            task.setTaskId(getTaskId());
            if (!getDrawingNum().equals("")) {
                task.setDrawingNum(Integer.parseInt(getDrawingNum()));
            }
//            order.setDrawingName(getDrawingName());
//            order.setDrawingId(getDrawingId());
//            if (!getNum().equals("")) {
//                order.setNum(Integer.parseInt(getNum()));
//            }
            if (!getProcedureId().equals("")) {
                task.setProcedureId(Integer.parseInt(getProcedureId()));
            }
            task.setProcedureName(getProcedureName());
            task.setWorkHour(getWorkHour());
//            if (!getReceiveTime().equals("")) {
//                order.setReceiveTime(Timestamp.valueOf(getReceiveTime()));
//            }
//            if (!getEpiboleEndTime().equals("")) {
//                order.setEpiboleEndTime(Date.valueOf(getEpiboleEndTime()));
//            }
            task.setQualified(getQualified());
            task.setUnqualified(getUnqualified());
            if (!getStatus().equals("")) {
                task.setStatus(Integer.valueOf(getStatus()));
            }
            if (!getStartTime().equals("")) {
                task.setStartTime(Timestamp.valueOf(getStartTime()));
            }
            if (!getFinishTime().equals("")) {
                task.setFinishTime(Timestamp.valueOf(getFinishTime()));
            }
            task.setWorkerId(getWorkerId());
            task.setDeviceId(getDeviceId());
            task.setCheckerId(getCheckerId());
            if (!getCheckTime().equals("")) {
                task.setCheckTime(Timestamp.valueOf(getCheckTime()));
            }

            sess.saveOrUpdate(task);

            transaction.commit();
            HibernateUtil.closeSession();
        }

//        planEndTime = (String) session.get("planEndTime");
//        taskId1 = (String) session.get("taskId1");
        taskId = (String) session.get("taskId");

        drawingNum = (String) session.get("drawingNum");

//        drawingName = (String) session.get("drawingName");
//        drawingId = (String) session.get("drawingId");

//        num = (String) session.get("num");

        procedureId = (String) session.get("procedureId");

        procedureName = (String) session.get("procedureName");
        if (session.get("workHour") != null) {
            workHour = BigDecimal.valueOf(Double.parseDouble((String) session.get("workHour")));
        } else {
            workHour = null;
        }

        if (session.get("qualified") != null) {
            qualified = BigDecimal.valueOf(Double.parseDouble((String) session.get("qualified")));
        } else {
            qualified = null;
        }
        if (session.get("unqualified") != null) {
            unqualified = BigDecimal.valueOf(Double.parseDouble((String) session.get("unqualified")));
        } else {
            unqualified = null;
        }
//        receiveTime = (String) session.get("receiveTime");
//        epiboleEndTime = (String) session.get("epiboleEndTime");
        status = (String) session.get("status");
        startTime = (String) session.get("startTime");
        finishTime = (String) session.get("finishTime");
        workerId = (String) session.get("workerId");
        deviceId = (String) session.get("deviceId");
        checkerId = (String) session.get("checkerId");
        checkTime = (String) session.get("checkTime");

        query();
        return "update";
    }

    public String delete() throws Exception {
        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();

        int deletedEntities = sess.createQuery("delete from TaskInfoEntity as task where task.taskId=:taskId and task.drawingNum=:drawingNum and task.procedureId=:procedureId")
                .setString("taskId", getTaskId())
                .setString("drawingNum", getDrawingNum())
                .setString("procedureId", getProcedureId())
                .executeUpdate();

        tx.commit();
        HibernateUtil.closeSession();

//        planEndTime = (String) session.get("planEndTime");
//        taskId1 = (String) session.get("taskId1");
        taskId = (String) session.get("taskId");

        drawingNum = (String) session.get("drawingNum");

//        drawingName = (String) session.get("drawingName");
//        drawingId = (String) session.get("drawingId");

//        num = (String) session.get("num");

        procedureId = (String) session.get("procedureId");

        procedureName = (String) session.get("procedureName");
        if (session.get("workHour") != null) {
            workHour = BigDecimal.valueOf(Double.parseDouble((String) session.get("workHour")));
        } else {
            workHour = null;
        }

        if (session.get("qualified") != null) {
            qualified = BigDecimal.valueOf(Double.parseDouble((String) session.get("qualified")));
        } else {
            qualified = null;
        }
        if (session.get("unqualified") != null) {
            unqualified = BigDecimal.valueOf(Double.parseDouble((String) session.get("unqualified")));
        } else {
            unqualified = null;
        }
//        receiveTime = (String) session.get("receiveTime");
//        epiboleEndTime = (String) session.get("epiboleEndTime");
        status = (String) session.get("status");
        startTime = (String) session.get("startTime");
        finishTime = (String) session.get("finishTime");
        workerId = (String) session.get("workerId");
        deviceId = (String) session.get("deviceId");
        checkerId = (String) session.get("checkerId");
        checkTime = (String) session.get("checkTime");

        query();
        return "delete";
    }

//    public String getPlanEndTime() {
//        return planEndTime;
//    }
//
//    public void setPlanEndTime(String planEndTime) {
//        this.planEndTime = planEndTime;
//    }
//
//    public String getTaskId1() {
//        return taskId1;
//    }
//
//    public void setTaskId1(String taskId1) {
//        this.taskId1 = taskId1;
//    }

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

//    public String getDrawingName() {
//        return drawingName;
//    }
//
//    public void setDrawingName(String drawingName) {
//        this.drawingName = drawingName;
//    }
//
//    public String getDrawingId() {
//        return drawingId;
//    }
//
//    public void setDrawingId(String drawingId) {
//        this.drawingId = drawingId;
//    }
//
//    public String getNum() {
//        return num;
//    }
//
//    public void setNum(String num) {
//        this.num = num;
//    }

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

//    public String getReceiveTime() {
//        return receiveTime;
//    }
//
//    public void setReceiveTime(String receiveTime) {
//        this.receiveTime = receiveTime;
//    }
//
//    public String getEpiboleEndTime() {
//        return epiboleEndTime;
//    }
//
//    public void setEpiboleEndTime(String epiboleEndTime) {
//        this.epiboleEndTime = epiboleEndTime;
//    }

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

    public List<TaskInfoEntity> getList() {
        return list;
    }

    public void setList(List<TaskInfoEntity> list) {
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

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}
