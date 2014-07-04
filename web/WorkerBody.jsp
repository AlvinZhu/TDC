<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:text name="worker.importTitle"/>
<s:form action="WorkerImport" method="post" enctype="multipart/form-data">
    <s:file name="file"><s:text name="import.upload"/></s:file>
    <s:submit key="import.submit"/>
</s:form>
<hr/>
<s:text name="worker.adminTitle"/>
<s:form action="Worker">
    <table>
        <tr>
            <td><s:text name="insert.workerId"/></td>
            <td><s:textfield name="workerId"/></td>
            <td><s:text name="insert.workerName"/></td>
            <td><s:textfield name="workerName"/></td>
            <td><s:text name="insert.deviceId"/></td>
            <td><s:textfield name="deviceId"/></td>
            <td><s:text name="insert.procedureName"/></td>
            <td><s:textfield name="procedureName"/></td>
        </tr>
    </table>
    <s:submit key="query.submit" name="query"/>
    <s:submit key="query.export" name="export"/>
    <s:submit key="insert.submit" name="insert"/>
    <s:reset key="all.reset"/>
</s:form>