package com.tdc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.xls.OrderXlsEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvin on 2014/6/22.
 */
public class OrderImportAction extends ActionSupport {
    private String contentType;
    private String filename;
    private File file;
    private List<OrderXlsEntity> list;

    public void setFileContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setFileFileName(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<OrderXlsEntity> getList() {
        return list;
    }

    public void setList(List<OrderXlsEntity> list) {
        this.list = list;
    }

    private String getValue(HSSFCell hssfCell) {
        if (null == hssfCell) {
            return "";
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_STRING) {
            return String.valueOf(hssfCell.getStringCellValue());
        } else {
            return "";
        }
    }

    private List<OrderXlsEntity> readXls() throws IOException {
        InputStream inputStream;
        HSSFWorkbook hssfWorkbook;
        OrderXlsEntity orderXlsEntity;
        HSSFSheet hssfSheet;
        HSSFRow hssfRow;
        HSSFCell hssfCell;
        List<OrderXlsEntity> list;

        if (null == file) {
            return null;
        }

        inputStream = new FileInputStream(file);
        hssfWorkbook = new HSSFWorkbook(inputStream);
        list = new ArrayList<OrderXlsEntity>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                orderXlsEntity = new OrderXlsEntity();

                hssfCell = hssfRow.getCell(0);
                orderXlsEntity.setProcessRegion(getValue(hssfCell));

                hssfCell = hssfRow.getCell(1);
                orderXlsEntity.setAnnualPlan(getValue(hssfCell));

                hssfCell = hssfRow.getCell(2);
                orderXlsEntity.setMonthlyPlan(getValue(hssfCell));

                hssfCell = hssfRow.getCell(3);
                orderXlsEntity.setPlansetTags(getValue(hssfCell));

                hssfCell = hssfRow.getCell(4);
                orderXlsEntity.setPlanEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(5);
                orderXlsEntity.setTrialEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(6);
                orderXlsEntity.setTaskId1(getValue(hssfCell));

                hssfCell = hssfRow.getCell(7);
                orderXlsEntity.setTaskId2(getValue(hssfCell));

                hssfCell = hssfRow.getCell(8);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                orderXlsEntity.setTaskId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(9);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                orderXlsEntity.setDrawingNum(getValue(hssfCell));

                hssfCell = hssfRow.getCell(10);
                orderXlsEntity.setDrawingName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(11);
                orderXlsEntity.setDrawingId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(12);
                orderXlsEntity.setNum(getValue(hssfCell));

                hssfCell = hssfRow.getCell(13);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                orderXlsEntity.setProcedureId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(14);
                orderXlsEntity.setProcedureName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(15);
                orderXlsEntity.setWorkHour(getValue(hssfCell));

                hssfCell = hssfRow.getCell(16);
                orderXlsEntity.setTaskTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(17);
                orderXlsEntity.setReceiveTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(18);
                orderXlsEntity.setEpiboleStatus(getValue(hssfCell));

                hssfCell = hssfRow.getCell(19);
                orderXlsEntity.setEpiboleCheckTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(20);
                orderXlsEntity.setEpiboleFactory(getValue(hssfCell));

                hssfCell = hssfRow.getCell(21);
                orderXlsEntity.setTaskType(getValue(hssfCell));

                hssfCell = hssfRow.getCell(22);
                orderXlsEntity.setApplicant(getValue(hssfCell));

                hssfCell = hssfRow.getCell(23);
                orderXlsEntity.setEpiboleEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(24);
                orderXlsEntity.setPlanType(getValue(hssfCell));

                list.add(orderXlsEntity);
            }
        }
        return list;
    }

    @Override
    public String execute() throws Exception {
        String permission = (String) ActionContext.getContext().getSession().get("permission");
        if (permission == null){
            return ERROR;
        }
        if ((Integer.parseInt(permission) & 8) == 0){
            return ERROR;
        }

        if (file == null) {
            return ERROR;
        }
        list = readXls();
        if (list.size() != 0) {
            Session session = HibernateUtil.currentSession();
            Transaction transaction = session.beginTransaction();
            for (OrderXlsEntity aList : list) {
                OrderEntity order = new OrderEntity();

                order.setProcessRegion(aList.getProcessRegion());
                order.setAnnualPlan(aList.getAnnualPlan());
                order.setMonthlyPlan(aList.getMonthlyPlan());
                order.setPlansetTags(aList.getPlansetTags());
                order.setPlanEndTime(Date.valueOf(aList.getPlanEndTime()));
                order.setTrialEndTime(aList.getTrialEndTime());
                order.setTaskId1(aList.getTaskId1());
                order.setTaskId2(aList.getTaskId2());
                order.setTaskId(aList.getTaskId());
                order.setDrawingNum(Float.valueOf(aList.getDrawingNum()).intValue());
                order.setDrawingName(aList.getDrawingName());
                order.setDrawingId(aList.getDrawingId());
                order.setNum(Float.valueOf(aList.getNum()).intValue());
                order.setProcedureId(Float.valueOf(aList.getProcedureId()).intValue());
                order.setProcedureName(aList.getProcedureName());
                order.setWorkHour(BigDecimal.valueOf(Float.valueOf(aList.getWorkHour())));
                order.setTaskTime(aList.getTaskTime());
                if (!aList.getReceiveTime().equals("")) {
                    order.setReceiveTime(Timestamp.valueOf(aList.getReceiveTime()));
                }
                order.setEpiboleStatus(aList.getEpiboleStatus());
                order.setEpiboleCheckTime(aList.getEpiboleCheckTime());
                order.setEpiboleFactory(aList.getEpiboleFactory());
                order.setTaskType(aList.getTaskType());
                order.setApplicant(aList.getApplicant());
                order.setEpiboleEndTime(Date.valueOf(aList.getEpiboleEndTime()));
                order.setPlanType(aList.getPlanType());

                session.saveOrUpdate(order);
            }
            transaction.commit();
            HibernateUtil.closeSession();

        }

        return SUCCESS;
    }

}