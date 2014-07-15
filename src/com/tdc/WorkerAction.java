package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Alvin on 2014/7/4.
 */
public class WorkerAction extends ActionSupport implements SessionAware {
    private Map session;

    private String insert;
    private String query;
    private String update;
    private String delete;
    private String export;

    private String oldWorkerId;

    private String workerId;
        private String deviceId;
    private String procedureName;
    private String workerName;

    private List<WorkerEntity> list;

    @Override
    public String execute() throws Exception {
        String permission = (String) session.get("permission");
        if (permission == null) {
            return ERROR;
        }
        if ((Integer.parseInt(permission) & 1) == 0) {
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
        if (!workerId.equals("")) {
            Session sess = HibernateUtil.currentSession();
            Transaction transaction = sess.beginTransaction();
            List list1 = sess.createQuery("from WorkerEntity as worker where worker.workerId=:workerId")
                    .setString("workerId", workerId)
                    .list();

            if (list1.size() == 0) {
                WorkerEntity worker = new WorkerEntity();

                worker.setWorkerId(getWorkerId());
                worker.setWorkerName(getWorkerName());
            worker.setDeviceId(getDeviceId());
                worker.setProcedureName(getProcedureName());

                sess.save(worker);
            }
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

        session.put("workerId", workerId);
        session.put("deviceId", deviceId);
        session.put("procedureName", procedureName);
        session.put("workerName", workerName);

        Session sess = HibernateUtil.currentSession();
        Transaction transaction = sess.beginTransaction();

        String hql = "from WorkerEntity as worker ";

        int count = 0;

        if (!workerId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "worker.workerId like :workerId ";
        }

        if (!deviceId.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "worker.deviceId like :deviceId ";
        }

        if (!procedureName.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "worker.procedureName like :procedureName ";
        }

        if (!workerName.equals("")) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "worker.workerName like :workerName ";
        }

        Query query = sess.createQuery(hql);

        if (!workerId.equals("")) {
            query.setString("workerId", "%" + workerId + "%");
        }

        if (!deviceId.equals("")) {
            query.setString("deviceId", "%" + deviceId + "%");
        }

        if (!procedureName.equals("")) {
            query.setString("procedureName", "%" + procedureName + "%");
        }

        if (!workerName.equals("")) {
            query.setString("workerName", "%" + workerName + "%");
        }

        list = query.list();

        transaction.commit();
        HibernateUtil.closeSession();

        return "query";
    }

    public String export() throws Exception {
        int countRow = list.size();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow HSSFRow = hssfSheet.createRow(0);

        HSSFRow.createCell(0).setCellValue(new HSSFRichTextString("员工编号"));
        HSSFRow.createCell(1).setCellValue(new HSSFRichTextString("设备编号"));
        HSSFRow.createCell(2).setCellValue(new HSSFRichTextString("工序名称"));
        HSSFRow.createCell(3).setCellValue(new HSSFRichTextString("姓名"));

        for (int i = 0; i < countRow; i++) {
            HSSFRow = hssfSheet.createRow(i + 1);
            WorkerEntity worker = list.get(i);

            if (worker.getWorkerId() != null) {
                HSSFRow.createCell(0).setCellValue(worker.getWorkerId());
            }

            if (worker.getDeviceId() != null) {
                HSSFRow.createCell(1).setCellValue(worker.getDeviceId());
            }

            if (worker.getProcedureName() != null) {
                HSSFRow.createCell(2).setCellValue(worker.getProcedureName());
            }

            if (worker.getWorkerName() != null) {
                HSSFRow.createCell(3).setCellValue(worker.getWorkerName());
            }
        }

        File file = new File(ServletActionContext.getServletContext().getRealPath(File.separator + "res"), "worker.xls");
        OutputStream out = new FileOutputStream(file);
        hssfWorkbook.write(out);
        out.close();

        return "export";
    }

    public String update() throws Exception {
        if (!workerId.equals("")) {
            Session sess = HibernateUtil.currentSession();
            Transaction transaction = sess.beginTransaction();

            int deletedEntities = sess.createQuery("delete from WorkerEntity as worker where worker.workerId=:workerId ")
                    .setString("workerId", oldWorkerId)
                    .executeUpdate();

            WorkerEntity worker = new WorkerEntity();

            worker.setWorkerId(getWorkerId());
            worker.setWorkerName(getWorkerName());
            worker.setDeviceId(getDeviceId());
            worker.setProcedureName(getProcedureName());

            sess.saveOrUpdate(worker);

            transaction.commit();
            HibernateUtil.closeSession();
        }

        workerId = (String) session.get("workerId");
        deviceId = (String) session.get("deviceId");
        procedureName = (String) session.get("procedureName");
        workerName = (String) session.get("workerName");

        query();
        return "update";
    }

    public String delete() throws Exception {
        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();

        int deletedEntities = sess.createQuery("delete from WorkerEntity as worker where worker.workerId=:workerId ")
                .setString("workerId", workerId)
                .executeUpdate();

        tx.commit();
        HibernateUtil.closeSession();

        workerId = (String) session.get("workerId");
        deviceId = (String) session.get("deviceId");
        procedureName = (String) session.get("procedureName");
        workerName = (String) session.get("workerName");

        query();
        return "delete";
    }

    public Map getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
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

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
    }

    public String getOldWorkerId() {
        return oldWorkerId;
    }

    public void setOldWorkerId(String oldWorkerId) {
        this.oldWorkerId = oldWorkerId;
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

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public List<WorkerEntity> getList() {
        return list;
    }

    public void setList(List<WorkerEntity> list) {
        this.list = list;
    }
}
