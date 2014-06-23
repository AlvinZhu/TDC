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
<s:property value="filename"/>
<hr/>
<s:property value="contentType"/>
<table>
    <s:iterator value="list" id="p2" status="st">
        <tr>
            <td><s:property value="#p2.taskId"/></td>
            <td><s:property value="#p2.procedureId"/></td>
            <td><s:property value="#p2.procedureName"/></td>
            <td><s:property value="#p2.workHour"/></td>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
