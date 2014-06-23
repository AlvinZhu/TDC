package com.tdc;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.TaskInfoMetaEntity;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alvin on 2014/6/19.
 */
public class TaskInfoUpdateAction extends ActionSupport {
    private String taskId;
    private List<TaskInfoMetaEntity> resultListNew;
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


    @Override
    public String execute() throws Exception {
        if (resultListNew.size() != 0) {
            Session sess = HibernateUtil.currentSession();

            Transaction tx = sess.beginTransaction();

            int deletedEntities = sess.createQuery("delete from TaskInfoMetaEntity as task where task.taskId=:id and task.drawingNum=:num")
                    .setString("id", getTaskId())
                    .setString("num", getDrawingNum())
                    .executeUpdate();
            tx.commit();

            HibernateUtil.closeSession();


            for (int i = 0; i < 12; i++) {
                Integer id = resultListNew.get(i).getProcedureId();
                while ((null != id) && (i + 1 != id)) {
                    TaskInfoMetaEntity task = resultListNew.set(id - 1, resultListNew.get(i));
                    resultListNew.set(i, task);
                    id = resultListNew.get(i).getProcedureId();
                }
                //resultListNew.get(i).setProcedureId(i);
            }

            //TaskInfoMetaComparator comparator=new TaskInfoMetaComparator();
            //Collections.sort(resultListNew, comparator);

            for (TaskInfoMetaEntity aResultListNew : resultListNew) {
                if (aResultListNew.getProcedureName().equals("")) {
                    continue;
                }
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
                bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 350, 350, hints);
                Path path = new File(ServletActionContext.getServletContext().getRealPath(File.separator), "tdc" + aResultListNew.getProcedureId() + ".jpg").toPath();
                MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
                //System.out.println(path);
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
