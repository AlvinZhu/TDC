<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/22
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    table.stats {
        border-collapse: collapse;
    }

    table.stats td {
        table-layout: fixed;
        height: 28px;
        border: 1px #000 solid;
    }
     P {page-break-after: always}
</style>
<div id=div0>
    <table class="stats">
        <tr>
            <td><s:a href="Order.jsp"><s:text name="index.excelImport"/></s:a></td>
            <td><s:a href="TaskInfoMetaQuery.jsp"><s:text name="index.taskInfo"/></s:a></td>
            <td><s:a href="TaskInfo.jsp"><s:text name="index.excelExport"/></s:a></td>
            <td><s:a href="Worker.jsp"><s:text name="index.worker"/></s:a></td>
        </tr>
    </table>
    <hr/>
</div>