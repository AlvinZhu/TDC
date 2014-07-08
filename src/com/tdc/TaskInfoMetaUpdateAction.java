package com.tdc;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.db.TaskInfoMetaComparator;
import com.tdc.db.TaskInfoMetaEntity;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Alvin on 2014/6/19.
 */
public class TaskInfoMetaUpdateAction extends ActionSupport {
    private List<TaskInfoMetaEntity> resultListNew;
    private List<List<TaskInfoMetaEntity>> listSet;
    private int listSize;

    private String taskId;
    private String drawingNum;
    private String drawingName;
    private String drawingId;
    private Integer num;
    private String date;
    private Boolean printAll;

    public TaskInfoMetaUpdateAction() {
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

        if (resultListNew.size() != 0) {
            Session sess = HibernateUtil.currentSession();
            Transaction tx = sess.beginTransaction();


            List<OrderEntity> list = sess.createQuery("from OrderEntity as order where order.taskId=:taskId and order.drawingNum=:drawingNum")
                    .setString("taskId", taskId)
                    .setString("drawingNum", drawingNum)
                    .list();

            int deletedEntities = sess.createQuery("delete from TaskInfoMetaEntity as task where task.taskId=:id and task.drawingNum=:num")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum())
                    .executeUpdate();

            tx.commit();
            HibernateUtil.closeSession();


            drawingName = list.get(0).getDrawingName();
            drawingId = list.get(0).getDrawingId();
            num = list.get(0).getNum();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.format(new Date());
//            for (int i = 0; i < 16; i++) {
//                Integer id = resultListNew.get(i).getProcedureId();
//                while ((null != id) && (i + 1 != id)) {
//                    TaskInfoMetaEntity task = resultListNew.set(id - 1, resultListNew.get(i));
//                    resultListNew.set(i, task);
//                    id = resultListNew.get(i).getProcedureId();
//                }
//                //resultListNew.get(i).setProcedureId(i);
//            }

            for (int i = 15; i >= 0; i--) {
                if (resultListNew.get(i).getProcedureId() == 0 || resultListNew.get(i).getProcedureName().equals("")) {
                    resultListNew.remove(i);
                }
            }
            TaskInfoMetaComparator comparator = new TaskInfoMetaComparator();
            Collections.sort(resultListNew, comparator);


            for (TaskInfoMetaEntity aResultListNew : resultListNew) {
                sess = HibernateUtil.currentSession();
                tx = sess.beginTransaction();

                aResultListNew.setTaskId(getTaskId());
                aResultListNew.setDrawingNum(Integer.parseInt(getDrawingNum()));

                sess.save(aResultListNew);

                tx.commit();
                HibernateUtil.closeSession();

                String content = getTaskId() + "_" + getDrawingNum() + "_" + aResultListNew.getProcedureId() + "_" + aResultListNew.getProcedureName();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                Map hints = new HashMap();
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                BitMatrix bitMatrix = null;
                bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 100, 100, hints);
                Path path = new File(ServletActionContext.getServletContext().getRealPath(File.separator + "res"), "tdc" + aResultListNew.getTaskId() + "_" + aResultListNew.getDrawingNum() + "_" + aResultListNew.getProcedureId() + ".jpg").toPath();
                MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
                //System.out.println(path);
            }

            int count = resultListNew.size();
            Iterator<TaskInfoMetaEntity> iterator = resultListNew.iterator();
            List<TaskInfoMetaEntity> tmpList;
            listSet = new ArrayList<List<TaskInfoMetaEntity>>();
            if (count > 7) {
                tmpList = new ArrayList<TaskInfoMetaEntity>();
                for (int i = 0; i < 7; i++) {
                    tmpList.add(iterator.next());
                }
                listSet.add(tmpList);
                count -= 7;
            } else if (count > 4) {
                tmpList = new ArrayList<TaskInfoMetaEntity>();
                while (iterator.hasNext()) {
                    tmpList.add(iterator.next());
                }

                for (int i = 0; i < 7 - count; i++) {
                    tmpList.add(new TaskInfoMetaEntity());
                }
                listSet.add(tmpList);
            }
            while (count > 8) {
                tmpList = new ArrayList<TaskInfoMetaEntity>();
                for (int i = 0; i < 8; i++) {
                    tmpList.add(iterator.next());
                }
                listSet.add(tmpList);
                count -= 8;
            }
            if (iterator.hasNext()) {
                tmpList = new ArrayList<TaskInfoMetaEntity>();
                while (iterator.hasNext()) {
                    tmpList.add(iterator.next());
                }
                if (count > 5) {
                    for (int i = 0; i < 8 - count; i++) {
                        tmpList.add(new TaskInfoMetaEntity());
                    }
                }
                listSet.add(tmpList);
            }
            listSize = listSet.size() - 1;

            return SUCCESS;
        }
        return ERROR;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<List<TaskInfoMetaEntity>> getListSet() {
        return listSet;
    }

    public void setListSet(List<List<TaskInfoMetaEntity>> listSet) {
        this.listSet = listSet;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public void setPrintAll(Boolean printAll) {
        this.printAll = printAll;
    }

    public Boolean getPrintAll() {
        return printAll;
    }
}
