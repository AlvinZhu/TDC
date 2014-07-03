<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form action="TaskInfo">
    <table>
        <tr>
                <%--<td><s:text name="insert.planEndTime"/></td>--%>
                <%--<td><s:textfield name="planEndTime"/></td>--%>
                <%--<td><s:text name="insert.taskId1"/></td>--%>
                <%--<td><s:textfield name="taskId1"/></td>--%>
            <td><s:text name="insert.taskId"/></td>
            <td><s:textfield name="taskId"/></td>
            <td><s:text name="insert.drawingNum"/></td>
            <td><s:textfield name="drawingNum"/></td>
            <td><s:text name="insert.procedureId"/></td>
            <td><s:textfield name="procedureId"/></td>
            <td><s:text name="insert.procedureName"/></td>
            <td><s:textfield name="procedureName"/></td>
        </tr>
        <tr>
            <td><s:text name="insert.workHour"/></td>
            <td><s:textfield name="workHour"/></td>
            <td><s:text name="insert.qualified"/></td>
            <td><s:textfield name="qualified"/></td>
            <td><s:text name="insert.unqualified"/></td>
            <td><s:textfield name="unqualified"/></td>
            <td><s:text name="insert.status"/></td>
            <td><s:textfield name="status"/></td>
                <%--<td><s:text name="insert.drawingName"/></td>--%>
                <%--<td><s:textfield name="drawingName"/></td>--%>
                <%--<td><s:text name="insert.drawingId"/></td>--%>
                <%--<td><s:textfield name="drawingId"/></td>--%>
                <%--<td><s:text name="insert.num"/></td>--%>
                <%--<td><s:textfield name="num"/></td>--%>

        </tr>
        <tr>
            <td><s:text name="insert.startTime"/></td>
            <td><s:textfield name="startTime"/></td>
            <td><s:text name="insert.finishTime"/></td>
            <td><s:textfield name="finishTime"/></td>
            <td><s:text name="insert.workerId"/></td>
            <td><s:textfield name="workerId"/></td>
            <td><s:text name="insert.deviceId"/></td>
            <td><s:textfield name="deviceId"/></td>
                <%--<td><s:text name="insert.receiveTime"/></td>--%>
                <%--<td><s:textfield name="receiveTime"/></td>--%>
                <%--<td><s:text name="insert.epiboleEndTime"/></td>--%>
                <%--<td><s:textfield name="epiboleEndTime"/></td>--%>
        </tr>
        <tr>
            <td><s:text name="insert.checkerId"/></td>
            <td><s:textfield name="checkerId"/></td>
            <td><s:text name="insert.checkTime"/></td>
            <td><s:textfield name="checkTime"/></td>
        </tr>
    </table>
    <s:submit key="query.submit" name="query"/>
    <s:submit key="query.export" name="export"/>
    <s:submit key="insert.submit" name="insert"/>
    <s:reset key="all.reset"/>
</s:form>