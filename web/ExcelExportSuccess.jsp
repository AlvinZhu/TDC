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
    <title></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="ExcelExportBody.jsp"/>
<table class="stats">
    <tr>
        <td>工序</td>
        <td>工序名称</td>
        <td>工时</td>
        <td>合格数量</td>
        <td>报废数量</td>
        <td>开始时间</td>
        <td>完成时间</td>
    </tr>
    <s:iterator value="resultListNew" id="p2" status="st">
        <tr>
            <td><s:property value="#p2.procedureId"/></td>
            <td><s:property value="#p2.procedureName"/></td>
            <td><s:property value="#p2.workHour"/></td>
            <td><s:property value="#p2.qualified"/></td>
            <td><s:property value="#p2.unqualified"/></td>
            <td><s:property value="#p2.startTime"/></td>
            <td><s:property value="#p2.finishTime"/></td>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>