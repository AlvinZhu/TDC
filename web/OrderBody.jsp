<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:text name="order.importTitle"/>
<s:form action="OrderImport" method="post" enctype="multipart/form-data">
    <s:file name="file"><s:text name="import.upload"/></s:file>
    <s:submit key="import.submit"/>
</s:form>
<hr/>
<s:text name="order.adminTitle"/>
<s:form action="Order">
    <table>
        <tr>
            <td><s:text name="insert.planEndTime"/></td>
            <td><s:textfield name="planEndTime"/></td>
            <td><s:text name="insert.taskId1"/></td>
            <td><s:textfield name="taskId1"/></td>
            <td><s:text name="insert.taskId"/></td>
            <td><s:textfield name="taskId"/></td>
            <td><s:text name="insert.drawingNum"/></td>
            <td><s:textfield name="drawingNum"/></td>
        </tr>
        <tr>
            <td><s:text name="insert.drawingName"/></td>
            <td><s:textfield name="drawingName"/></td>
            <td><s:text name="insert.drawingId"/></td>
            <td><s:textfield name="drawingId"/></td>
            <td><s:text name="insert.num"/></td>
            <td><s:textfield name="num"/></td>
            <td><s:text name="insert.procedureId"/></td>
            <td><s:textfield name="procedureId"/></td>
        </tr>
        <tr>
            <td><s:text name="insert.procedureName"/></td>
            <td><s:textfield name="procedureName"/></td>
            <td><s:text name="insert.workHour"/></td>
            <td><s:textfield name="workHour"/></td>
            <td><s:text name="insert.receiveTime"/></td>
            <td><s:textfield name="receiveTime"/></td>
            <td><s:text name="insert.epiboleEndTime"/></td>
            <td><s:textfield name="epiboleEndTime"/></td>
        </tr>
    </table>
    <s:submit key="query.submit" name="query"/>
    <s:submit key="query.export" name="export"/>
    <s:submit key="insert.submit" name="insert"/>
    <s:reset key="all.reset"/>
</s:form>