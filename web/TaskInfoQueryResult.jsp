<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/22
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="taskInfo.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="TaskInfoBody.jsp"/>
<table>
    <tr>
        <td></td>
        <td></td>
        <td><s:text name="result.taskId"/></td>
        <td><s:text name="result.drawingNum"/></td>
        <td><s:text name="result.procedureId"/></td>
        <td><s:text name="result.procedureName"/></td>
        <td><s:text name="result.workHour"/></td>
        <td><s:text name="result.qualified"/></td>
        <td><s:text name="result.unqualified"/></td>
        <td><s:text name="result.status"/></td>
        <td><s:text name="result.startTime"/></td>
        <td><s:text name="result.finishTime"/></td>
        <td><s:text name="result.workerId"/></td>
        <td><s:text name="result.deviceId"/></td>
        <td><s:text name="result.checkerId"/></td>
        <td><s:text name="result.checkTime"/></td>
    </tr>
    <s:iterator value="list" id="pl" status="st">
        <s:form action="TaskInfo">
            <s:hidden name="oldTaskId" value="%{#pl.taskId}"/>
            <s:hidden name="oldDrawingNum" value="%{#pl.drawingNum}"/>
            <s:hidden name="oldProcedureId" value="%{#pl.procedureId}"/>
            <tr>
                <td><s:submit key="result.update" name="update"/></td>
                <td><s:submit key="result.delete" name="delete"/></td>
                    <%--<td><s:textfield name="planEndTime" value="%{getText('global.date', {#pl.planEndTime})}"/></td>--%>
                    <%--<td><s:textfield name="taskId1" value="%{#pl.taskId1}"/></td>--%>
                <td><s:textfield name="taskId" value="%{#pl.taskId}"/></td>
                <td><s:textfield name="drawingNum" value="%{#pl.drawingNum}"/></td>
                    <%--<td><s:textfield name="drawingName" value="%{#pl.drawingName}"/></td>--%>
                    <%--<td><s:textfield name="drawingId" value="%{#pl.drawingId}"/></td>--%>
                    <%--<td><s:textfield name="num" value="%{#pl.num}"/></td>--%>
                <td><s:textfield name="procedureId" value="%{#pl.procedureId}"/></td>
                <td><s:textfield name="procedureName" value="%{#pl.procedureName}"/></td>
                <td><s:textfield name="workHour" value="%{#pl.workHour}"/></td>
                    <%--<td><s:textfield name="receiveTime" value="%{getText('global.datetime',{#pl.receiveTime})}"/></td>--%>
                    <%--<td><s:textfield name="epiboleEndTime" value="%{getText('global.date',{#pl.epiboleEndTime})}"/></td>--%>
                <td><s:textfield name="qualified" value="%{#pl.qualified}"/></td>
                <td><s:textfield name="unqualified" value="%{#pl.unqualified}"/></td>
                <td><s:textfield name="status" value="%{#pl.status}"/></td>
                <td><s:textfield name="startTime" value="%{getText('global.datetime',{#pl.startTime})}"/></td>
                <td><s:textfield name="finishTime" value="%{getText('global.datetime',{#pl.finishTime})}"/></td>
                <td><s:textfield name="workerId" value="%{#pl.workerId}"/></td>
                <td><s:textfield name="deviceId" value="%{#pl.deviceId}"/></td>
                <td><s:textfield name="checkerId" value="%{#pl.checkerId}"/></td>
                <td><s:textfield name="checkTime" value="%{getText('global.datetime',{#pl.checkTime})}"/></td>
            </tr>
        </s:form>
    </s:iterator>
</table>
</body>
</html>
