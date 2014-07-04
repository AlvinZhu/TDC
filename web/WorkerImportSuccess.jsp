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
<s:text name="import.success"/>
<br/>
<s:text name="import.filename"/>
<s:property value="filename"/>
<br/>
<s:text name="import.fileType"/>
<s:property value="contentType"/>
<br/>
<s:text name="import.count"/>
<s:property value="%{list.size()}"/>

</body>
</html>
