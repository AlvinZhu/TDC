package com.tdc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.WorkerEntity;
import com.tdc.xls.WorkerXlsEntity;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvin on 2014/7/4.
 */
public class WorkerImportAction extends ActionSupport {
    private String contentType;
    private String filename;
    private File file;
    private List<WorkerXlsEntity> list;

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

    public List<WorkerXlsEntity> getList() {
        return list;
    }

    public void setList(List<WorkerXlsEntity> list) {
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

    private List<WorkerXlsEntity> readXls() throws IOException {
        InputStream inputStream;
        HSSFWorkbook hssfWorkbook;
        WorkerXlsEntity workerXlsEntity;
        HSSFSheet hssfSheet;
        HSSFRow hssfRow;
        HSSFCell hssfCell;
        List<WorkerXlsEntity> list;

        if (null == file) {
            return null;
        }

        inputStream = new FileInputStream(file);
        hssfWorkbook = new HSSFWorkbook(inputStream);
        list = new ArrayList<WorkerXlsEntity>();
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
                workerXlsEntity = new WorkerXlsEntity();

                hssfCell = hssfRow.getCell(0);
                if (getValue(hssfCell).equals("")) {
                    break;
                }
                workerXlsEntity.setWorkerId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(1);
                workerXlsEntity.setDeviceId(getValue(hssfCell));

                hssfCell = hssfRow.getCell(2);
                workerXlsEntity.setProcedureName(getValue(hssfCell));

                hssfCell = hssfRow.getCell(3);
                workerXlsEntity.setWorkerName(getValue(hssfCell));

                list.add(workerXlsEntity);
            }
        }
        return list;
    }

    @Override
    public String execute() throws Exception {
        String permission = (String) ActionContext.getContext().getSession().get("permission");
        if (permission == null) {
            return ERROR;
        }
        if ((Integer.parseInt(permission) & 1) == 0) {
            return ERROR;
        }
        if (file == null) {
            return ERROR;
        }
        list = readXls();
        if (list.size() != 0) {
            Session session = HibernateUtil.currentSession();
            Transaction transaction = session.beginTransaction();
            for (WorkerXlsEntity aList : list) {
                WorkerEntity worker = new WorkerEntity();

                worker.setWorkerId(aList.getWorkerId());
                worker.setDeviceId(aList.getDeviceId());
                worker.setProcedureName(aList.getProcedureName());
                worker.setWorkerName(aList.getWorkerName());

                session.saveOrUpdate(worker);
            }
            transaction.commit();
            HibernateUtil.closeSession();

        }

        return SUCCESS;
    }

}