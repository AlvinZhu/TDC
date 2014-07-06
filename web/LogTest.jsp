<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form action="Log">
    <table class="stats">
        <tr>
            <td>taskId</td>
            <td><s:textfield name="taskId"/></td>
            <td>drawingNum</td>
            <td><s:textfield name="drawingNum"/></td>
            <td>procedureId</td>
            <td><s:textfield name="procedureId"/></td>
            <td>qualified</td>
            <td><s:textfield name="qualified"/></td>
        </tr>
        <tr>
            <td>unqualified</td>
            <td><s:textfield name="unqualified"/></td>
            <td>workerId</td>
            <td><s:textfield name="workerId"/></td>
            <td>type</td>
            <td><s:textfield name="type"/></td>
            <td>time</td>
            <td><s:textfield name="time"/></td>
        </tr>
    </table>
    <s:submit key="query.submit"/>
    <s:reset key="all.reset"/>
</s:form>