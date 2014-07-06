package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Alvin on 2014/6/23.
 */
public class TdcOutAction extends ActionSupport {
    private String fileName;
    private String taskId;
    private String drawingNum;
    private String procedureId;
    private InputStream fileInputStream;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String execute() throws Exception {
        fileName = "tdc" + getTaskId() + "_"+ getDrawingNum() + "_"+ getProcedureId() + ".jpg";
        fileInputStream = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(File.separator + "res"), fileName));
        return SUCCESS;
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
}
