<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/22
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    body {
        background-color: #c7edcc;
    }

    table.stats {
        border-collapse: collapse;
    }

    table.stats td {
        table-layout: fixed;
        height: 28px;
        border: 1px #000 solid;
    }

    .table-striped tbody tr:nth-child(odd) td,
    .table-striped tbody tr:nth-child(odd) th {
        background-color: #ffffff;
    }

    P {
        page-break-after: always
    }
</style>
<table class="stats" width="100%" align="center">
    <tbody>
    <tr>
        <td nowrap><s:a href="index.jsp"><s:text
                name="index.title"/></s:a></td>
        <% String permission = (String) session.getAttribute("permission");
            int p;
            if (permission != null) {
                p = Integer.parseInt(permission);
                if ((p & 8) != 0) { %>
        <td nowrap><s:a href="Order.jsp"><s:text name="index.excelImport"/></s:a></td>
        <% }
            if ((p & 4) != 0) { %>
        <td nowrap><s:a href="TaskInfoMetaQuery.jsp"><s:text name="index.taskInfo"/></s:a></td>
        <% }
            if ((p & 2) != 0) { %>
        <td nowrap><s:a href="TaskInfo.jsp"><s:text name="index.excelExport"/></s:a></td>
        <% }
            if ((p & 1) != 0) { %>
        <td nowrap><s:a href="Worker.jsp"><s:text name="index.worker"/></s:a></td>
        <% } %>
        <td width="100%" nowrap>&nbsp;</td>
        <% if ((p & 16) != 0) { %>
        <td nowrap><s:a href="User.jsp"><s:text name="index.user"/></s:a></td>
        <% } %>
        <td nowrap><s:a href="Logout.action"><s:text
                name="index.logout"/></s:a></td>
        <% } else { %>
        <td width="100%" nowrap>&nbsp;</td>
        <td nowrap><s:a href="login.jsp"><s:text
                name="index.login"/></s:a></td>
        <% } %>
    </tr>
    </tbody>
</table>
<br/>
