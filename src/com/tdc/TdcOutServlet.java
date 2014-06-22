package com.tdc;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tdc.db.TaskInfoEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alvin on 2014/6/20.
 */
public class TdcOutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response)
            throws ServletException, IOException {

        String pid = requset.getParameter("pid");
        String content = null;
        HttpSession session = requset.getSession();
        List<TaskInfoEntity> list = (List<TaskInfoEntity>) session.getAttribute("list");
        String taskId = (String) session.getAttribute("taskid");

        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(pid) == list.get(i).getProcedureId()) {
                content = taskId + "_" + list.get(i).getProcedureId() + "_" + list.get(i).getProcedureName();
                break;
            }
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 350, 350, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
    }
}
