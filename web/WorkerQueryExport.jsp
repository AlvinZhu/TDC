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
    <title><s:text name="order.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="WorkerBody.jsp"/>
<s:a href="XlsOut.action?fileName=worker.xls"><s:text name="export.download"/></s:a>
</body>
</html>
