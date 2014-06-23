package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.OrderEntity;
import com.tdc.xls.XlsEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvin on 2014/6/22.
 */
public class FileUploadAction extends ActionSupport {
    private String contentType;
    private String filename;
    private File upload;
    private List<XlsEntity> list;

    public void setUploadContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setUploadFileName(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public List<XlsEntity> getList() {
        return list;
    }

    public void setList(List<XlsEntity> list) {
        this.list = list;
    }

    private List<XlsEntity> readXls() throws IOException {
        InputStream is;
        HSSFWorkbook hssfWorkbook;
        XlsEntity xlsDto;
        HSSFSheet hssfSheet;
        HSSFRow hssfRow;
        HSSFCell hssfCell;
        List<XlsEntity> list;

        if (null == upload) {
            return null;
        }

        is = new FileInputStream(upload);
        hssfWorkbook = new HSSFWorkbook(is);
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
                xlsDto = new XlsEntity();

                hssfCell = hssfRow.getCell(0);
                xlsDto.setProcessRegion(getValue(hssfCell));

                hssfCell = hssfRow.getCell(1);
                xlsDto.setAnnualPlan(getValue(hssfCell));

                hssfCell = hssfRow.getCell(2);
                xlsDto.setMonthlyPlan(getValue(hssfCell));

                hssfCell = hssfRow.getCell(3);
                xlsDto.setPlansetTags(getValue(hssfCell));

                hssfCell = hssfRow.getCell(4);
                xlsDto.setPlanEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(5);
                xlsDto.setTrialEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(6);
                xlsDto.setTaskId1(getValue(hssfCell));

                hssfCell = hssfRow.getCell(7);
                xlsDto.setTaskId2(getValue(hssfCell));

                hssfCell = hssfRow.getCell(8);
                xlsDto.setTaskId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(9);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsDto.setDrawingNum(Float.valueOf(getValue(hssfCell)).intValue());

                hssfCell = hssfRow.getCell(10);
                xlsDto.setDrawingName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(11);
                xlsDto.setDrawingId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(12);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsDto.setNum(Float.valueOf(getValue(hssfCell)).intValue());

                hssfCell = hssfRow.getCell(13);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsDto.setProcedureId(Float.valueOf(getValue(hssfCell)).intValue());

                hssfCell = hssfRow.getCell(14);
                xlsDto.setProcedureName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(15);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                xlsDto.setWorkHour(BigDecimal.valueOf(Double.parseDouble(getValue(hssfCell))));

                hssfCell = hssfRow.getCell(16);
                xlsDto.setTaskTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(17);
                xlsDto.setReceiveTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(18);
                xlsDto.setEpiboleStatus(getValue(hssfCell));

                hssfCell = hssfRow.getCell(19);
                xlsDto.setEpiboleCheckTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(20);
                xlsDto.setEpiboleFactory(getValue(hssfCell));

                hssfCell = hssfRow.getCell(21);
                xlsDto.setTaskType(getValue(hssfCell));

                hssfCell = hssfRow.getCell(22);
                xlsDto.setApplicant(getValue(hssfCell));

                hssfCell = hssfRow.getCell(23);
                xlsDto.setEpiboleEndTime(getValue(hssfCell));

                hssfCell = hssfRow.getCell(24);
                xlsDto.setPlanType(getValue(hssfCell));

                list.add(xlsDto);
            }
        }
        return list;
    }

    private String getValue(HSSFCell hssfCell) {
        if (null == hssfCell) {
            return "";
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
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
        list = readXls();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Session sess = HibernateUtil.currentSession();

                Transaction tx = sess.beginTransaction();

                int deletedEntities = sess.createQuery("delete from OrderEntity as order where order.taskId=:id")
                        .setString("id", list.get(i).getTaskId())
                        .executeUpdate();
                tx.commit();

                HibernateUtil.closeSession();
            }


            for (int i = 0; i < list.size(); i++) {
                Session sess = HibernateUtil.currentSession();
                Transaction tx = sess.beginTransaction();
                OrderEntity order = new OrderEntity();

                order.setProcessRegion(list.get(i).getProcessRegion());
                order.setAnnualPlan(list.get(i).getAnnualPlan());
                order.setMonthlyPlan(list.get(i).getMonthlyPlan());
                order.setPlansetTags(list.get(i).getPlansetTags());
                order.setPlanEndTime(list.get(i).getPlanEndTime());
                order.setTrialEndTime(list.get(i).getTrialEndTime());
                order.setTaskId1(list.get(i).getTaskId1());
                order.setTaskId2(list.get(i).getTaskId2());
                order.setTaskId(list.get(i).getTaskId());
                order.setDrawingNum(list.get(i).getDrawingNum());
                order.setDrawingName(list.get(i).getDrawingName());
                order.setDrawingId(list.get(i).getDrawingId());
                order.setNum(list.get(i).getNum());
                order.setProcedureId(list.get(i).getProcedureId());
                order.setProcedureName(list.get(i).getProcedureName());
                order.setWorkHour(list.get(i).getWorkHour());
                order.setTaskTime(list.get(i).getTaskTime());
                order.setReceiveTime(list.get(i).getReceiveTime());
                order.setEpiboleStatus(list.get(i).getEpiboleStatus());
                order.setEpiboleCheckTime(list.get(i).getEpiboleCheckTime());
                order.setEpiboleFactory(list.get(i).getEpiboleFactory());
                order.setTaskType(list.get(i).getTaskType());
                order.setApplicant(list.get(i).getApplicant());
                order.setEpiboleEndTime(list.get(i).getEpiboleEndTime());
                order.setPlanType(list.get(i).getPlanType());

                sess.save(order);
                tx.commit();

                HibernateUtil.closeSession();
            }

        }

        return SUCCESS;
    }

}