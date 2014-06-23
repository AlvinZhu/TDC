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
<s:form action="FileUpload" method="post" enctype="multipart/form-data">
    <s:file name="upload"><s:text name="import.upload"/></s:file>
    <s:submit key="import.submit"/>
</s:form>
</body>
</html>
