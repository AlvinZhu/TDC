<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form action="TaskInfoMetaQuery">
    <table>
        <tr>
            <td><s:text name="insert.planEndTime"/></td>
            <td><s:textfield name="planEndTime"/></td>
            <td><s:text name="insert.taskId1"/></td>
            <td><s:textfield name="taskId1"/></td>
            <td><s:text name="insert.taskId"/></td>
            <td><s:textfield name="taskId"/></td>

        </tr>
        <tr>
            <td><s:text name="insert.drawingNum"/></td>
            <td><s:textfield name="drawingNum"/></td>
            <td><s:text name="insert.drawingName"/></td>
            <td><s:textfield name="drawingName"/></td>
            <td><s:text name="insert.drawingId"/></td>
            <td><s:textfield name="drawingId"/></td>
                <%--<td></td>--%>
                <%--<td></td>--%>
        </tr>
        <tr>
                <%--<td></td>--%>
                <%--<td></td>--%>
            <td><s:text name="insert.num"/></td>
            <td><s:textfield name="num"/></td>
            <td><s:text name="insert.receiveTime"/></td>
            <td><s:textfield name="receiveTime"/></td>
            <td><s:text name="insert.epiboleEndTime"/></td>
            <td><s:textfield name="epiboleEndTime"/></td>
        </tr>
    </table>
    <s:submit key="query.submit" name="query"/>
    <s:reset key="all.reset"/>
</s:form>