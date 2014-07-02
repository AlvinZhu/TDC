package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.xls.XlsEntity;
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
    private List<XlsEntity> list;

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

    public List<XlsEntity> getList() {
        return list;
    }

    public void setList(List<XlsEntity> list) {
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
    
    private List<XlsEntity> readXls() throws IOException {
        InputStream inputStream;
        HSSFWorkbook hssfWorkbook;
        XlsEntity xlsEntity;
        HSSFSheet hssfSheet;
        HSSFRow hssfRow;
        HSSFCell hssfCell;
        List<XlsEntity> list;

        if (null == file) {
            return null;
        }

        inputStream = new FileInputStream(file);
        hssfWorkbook = new HSSFWorkbook(inputStream);
        list = new ArrayList<XlsEntity>();
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
                xlsEntity = new XlsEntity();

                hssfCell = hssfRow.getCell(0);
                xlsEntity.setProcessRegion(getValue(hssfCell));

                hssfCell = hssfRow.getCell(1);
                xlsEntity.setAnnualPlan(getValue(hssfCell));

                hssfCell = hssfRow.getCell(2);
                xlsEntity.setMonthlyPlan(getValue(hssfCell));

                hssfCell = hssfRow.getCell(3);
                xlsEntity.setPlansetTags(getValue(hssfCell));

                hssfCell = hssfRow.getCell(4);
                xlsEntity.setPlanEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(5);
                xlsEntity.setTrialEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(6);
                xlsEntity.setTaskId1(getValue(hssfCell));

                hssfCell = hssfRow.getCell(7);
                xlsEntity.setTaskId2(getValue(hssfCell));

                hssfCell = hssfRow.getCell(8);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsEntity.setTaskId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(9);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsEntity.setDrawingNum(getValue(hssfCell));

                hssfCell = hssfRow.getCell(10);
                xlsEntity.setDrawingName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(11);
                xlsEntity.setDrawingId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(12);
                xlsEntity.setNum(getValue(hssfCell));

                hssfCell = hssfRow.getCell(13);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsEntity.setProcedureId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(14);
                xlsEntity.setProcedureName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(15);
                xlsEntity.setWorkHour(getValue(hssfCell));

                hssfCell = hssfRow.getCell(16);
                xlsEntity.setTaskTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(17);
                xlsEntity.setReceiveTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(18);
                xlsEntity.setEpiboleStatus(getValue(hssfCell));

                hssfCell = hssfRow.getCell(19);
                xlsEntity.setEpiboleCheckTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(20);
                xlsEntity.setEpiboleFactory(getValue(hssfCell));

                hssfCell = hssfRow.getCell(21);
                xlsEntity.setTaskType(getValue(hssfCell));

                hssfCell = hssfRow.getCell(22);
                xlsEntity.setApplicant(getValue(hssfCell));

                hssfCell = hssfRow.getCell(23);
                xlsEntity.setEpiboleEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(24);
                xlsEntity.setPlanType(getValue(hssfCell));

                list.add(xlsEntity);
            }
        }
        return list;
    }

    /*
        public class XlsDto2Excel {
    public static void xlsDto2Excel(List<XlsDto> xls) throws Exception {
        // 获取总列数
        int CountColumnNum = xls.size();
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();
        XlsDto xlsDto = null;
        // sheet 对应一个工作页
        HSSFSheet sheet = hwb.createSheet("pldrxkxxmb");
        HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
        HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
        String[] names = new String[CountColumnNum];
        names[0] = "学号";
        names[1] = "姓名";
        names[2] = "学院";
        names[3] = "课程名";
        names[4] = "成绩";
        for (int j = 0; j < CountColumnNum; j++) {
            firstcell[j] = firstrow.createCell(j);
            firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
        }
        for (int i = 0; i < xls.size(); i++) {
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            xlsDto = xls.get(i);
            for (int colu = 0; colu <= 4; colu++) {
                // 在一行内循环
                HSSFCell xh = row.createCell(0);
                xh.setCellValue(xlsDto.getXh());
                HSSFCell xm = row.createCell(1);
                xm.setCellValue(xlsDto.getXm());
                HSSFCell yxsmc = row.createCell(2);
                yxsmc.setCellValue(xlsDto.getYxsmc());
                HSSFCell kcm = row.createCell(3);
                kcm.setCellValue(xlsDto.getKcm());
                HSSFCell cj = row.createCell(4);
                cj.setCellValue(xlsDto.getCj());
                (xlsDto.getMessage());
            }
        }
        // 创建文件输出流，准备输出电子表格
        OutputStream out = new FileOutputStream("POI2Excel/pldrxkxxmb.xls");
        hwb.write(out);
        out.close();
        System.out.println("数据库导出成功");
    }

    }
    */
    @Override
    public String execute() throws Exception {
        if (file == null) {
            return ERROR;
        }
        list = readXls();
        if (list.size() != 0) {
            Session session = HibernateUtil.currentSession();
            Transaction transaction = session.beginTransaction();
            for (XlsEntity aList : list) {
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