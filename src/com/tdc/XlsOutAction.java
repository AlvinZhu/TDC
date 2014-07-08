package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Alvin on 2014/7/3.
 */
public class XlsOutAction extends ActionSupport {
    private String fileName;
    private InputStream fileInputStream;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String execute() throws Exception {
        if (fileName == null || "".equals(fileName)) {
            fileName = "export.xls";
        }
        fileInputStream = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(File.separator + "res"), fileName));
        return SUCCESS;
    }
}
