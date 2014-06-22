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
    <title>打印</title>
</head>
<body>

<table>
    <tr>
        <td colspan="2">新工艺单</td>
        <td colspan="1">taskId:</td>
        <td colspan="1"><s:property value="%{taskId}"/></td>
    </tr>
    <tr>
        <td>工序</td>
        <td>工序名称</td>
        <td>工时</td>
        <td>二维码</td>
    </tr>
    <s:iterator value="resultListNew" id="p2" status="st">
        <tr>
            <td><s:property value="#p2.procedureId"/></td>
            <td><s:property value="#p2.procedureName"/></td>
            <td><s:property value="#p2.workHour"/></td>
            <td><img style="height:350px;width:350px" src="<s:url value="TdcOut.servlet?pid=%{#p2.procedureId}"/>"/>
            </td>
        </tr>
    </s:iterator>
</table>

</body>
</html>
