<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/17
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<s:form action="Log">
    <table>
        <tr>
            <td><s:textfield name="taskId" value="G12714P01-C(FJ05-29)">taskId:</s:textfield></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>


        </tr>


        <s:textfield name="taskId" value="G12714P01-C(FJ05-29)">taskId:</s:textfield>
        <s:textfield name="drawingNum" value="18">drawingNum</s:textfield>
        <s:textfield name="procedureId" value="4">procedureId:</s:textfield>
        <s:textfield name="procedureName" value="铣">procedureName:</s:textfield>
        <s:textfield name="qualified" value="12">qualified:</s:textfield>
        <s:textfield name="unqualified" value="3">unqualified:</s:textfield>
        <s:textfield name="workerId" value="123">workerId:</s:textfield>
        <s:textfield name="deviceId" value="23">deviceId:</s:textfield>
        <s:textfield name="type" value="begin">type:</s:textfield>
        <s:textfield name="time" value="2014-06-23 14:01:06">time:</s:textfield>
        <s:submit key="update.submit"/>
    </table>
</s:form>
</body>
</html>
