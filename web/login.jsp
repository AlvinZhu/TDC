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
    <title><s:text name="login.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<s:form action="Login">
    <table>
        <tr>
            <td><s:text name="login.username"/></td>
            <td><s:textfield name="username"/></td>
        </tr>
        <tr>
            <td><s:text name="login.password"/></td>
            <td><s:password name="password"/></td>
        </tr>
    </table>
    <s:submit key="login.submit"/>
    <s:reset key="all.reset"/>
</s:form>
</body>
</html>
