<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="query.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="TaskInfoMetaQueryBody.jsp"/>
<table class="table-striped">
    <tr>
        <td></td>
        <%--<td></td>--%>
        <td><s:text name="result.planEndTime"/></td>
        <td><s:text name="result.taskId1"/></td>
        <td><s:text name="result.taskId"/></td>
        <td><s:text name="result.drawingNum"/></td>
        <td><s:text name="result.drawingName"/></td>
        <td><s:text name="result.drawingId"/></td>
        <td><s:text name="result.num"/></td>
        <%--<td><s:text name="result.procedureId"/></td>--%>
        <%--<td><s:text name="result.procedureName"/></td>--%>
        <%--<td><s:text name="result.workHour"/></td>--%>
        <td><s:text name="result.receiveTime"/></td>
        <td><s:text name="result.epiboleEndTime"/></td>
    </tr>
    <s:iterator value="list" id="pl" status="st">
        <s:form action="TaskInfoMetaQuery">
            <%--<s:hidden name="oldTaskId" value="%{#pl.taskId}"/>--%>
            <%--<s:hidden name="oldDrawingNum" value="%{#pl.drawingNum}"/>--%>
            <%--<s:hidden name="oldProcedureId" value="%{#pl.procedureId}"/>--%>
            <tr>
                <td><s:submit key="result.select" name="select"/></td>
                    <%--<td><s:submit key="result.delete" name="delete"/></td>--%>
                <td><s:textfield name="planEndTime" value="%{getText('global.date', {#pl.planEndTime})}"/></td>
                <td><s:textfield name="taskId1" value="%{#pl.taskId1}"/></td>
                <td><s:textfield name="taskId" value="%{#pl.taskId}"/></td>
                <td><s:textfield name="drawingNum" value="%{#pl.drawingNum}"/></td>
                <td><s:textfield name="drawingName" value="%{#pl.drawingName}"/></td>
                <td><s:textfield name="drawingId" value="%{#pl.drawingId}"/></td>
                <td><s:textfield name="num" value="%{#pl.num}"/></td>
                    <%--<td><s:textfield name="procedureId" value="%{#pl.procedureId}"/></td>--%>
                    <%--<td><s:textfield name="procedureName" value="%{#pl.procedureName}"/></td>--%>
                    <%--<td><s:textfield name="workHour" value="%{#pl.workHour}"/></td>--%>
                <td><s:textfield name="receiveTime" value="%{getText('global.datetime',{#pl.receiveTime})}"/></td>
                <td><s:textfield name="epiboleEndTime" value="%{getText('global.date',{#pl.epiboleEndTime})}"/></td>
            </tr>
        </s:form>
    </s:iterator>
</table>
</body>
</html>