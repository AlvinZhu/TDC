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
<jsp:include page="head.jsp"/>
<table class="stats">
    <tr>
        <td colspan="2">新工艺单</td>
        <td colspan="1"><s:text name="print.taskId"/><s:property value="%{taskId}"/></td>
        <td colspan="1"><s:text name="print.drawingNum"/><s:property value="%{drawingNum}"/></td>
    </tr>
    <tr>
        <td>工序</td>
        <td>工序名称</td>
        <td>工时</td>
        <td>二维码</td>
    </tr>
    <s:iterator value="resultListNew" id="p2" status="st">
        <tr>
            <s:if test='%{#p2.procedureName!=""}'>
            <td><s:property value="#p2.procedureId"/></td>
            <td><s:property value="#p2.procedureName"/></td>
            <td><s:property value="#p2.workHour"/></td>
                <td><img style="height:100px;width:100px" src="<s:url value="TdcOut.servlet?pid=%{#p2.procedureId}"/>"/>
                </td>
            </s:if>
        </tr>
    </s:iterator>
</table>

</body>
</html>
