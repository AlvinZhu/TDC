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
    <title><s:text name="worker.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="WorkerBody.jsp"/>
<table>
    <tr>
        <td></td>
        <td></td>
        <td><s:text name="result.workerId"/></td>
        <td><s:text name="result.deviceId"/></td>
        <td><s:text name="result.procedureName"/></td>
        <td><s:text name="result.workerName"/></td>
    </tr>
    <s:iterator value="list" id="pl" status="st">
        <s:form action="Worker">
            <s:hidden name="oldWorkerId" value="%{#pl.workerId}"/>
            <tr>
                <td><s:submit key="result.update" name="update"/></td>
                <td><s:submit key="result.delete" name="delete"/></td>
                <td><s:textfield name="workerId" value="%{#pl.workerId}"/></td>
                <td><s:textfield name="deviceId" value="%{#pl.deviceId}"/></td>
                <td><s:textfield name="procedureName" value="%{#pl.procedureName}"/></td>
                <td><s:textfield name="workerName" value="%{#pl.workerName}"/></td>
            </tr>
        </s:form>
    </s:iterator>
</table>
</body>
</html>
