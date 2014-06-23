<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form action="TaskInfoQuery">
    <s:textfield name="taskId" key="taskId"><s:text name="query.taskId"/></s:textfield>
    <s:textfield name="drawingNum" key="drawingNum"><s:text name="query.drawingNum"/></s:textfield>
    <s:submit key="query.search"/>
</s:form>