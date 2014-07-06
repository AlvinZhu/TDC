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
    <title><s:text name="index.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<s:text name="index.welcome"/>
<br/>
<s:a href="res/client.apk"><s:text name="apk.download"/></s:a>
</body>
</html>
