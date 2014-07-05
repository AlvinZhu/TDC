<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 2014/6/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form action="User">
    <table class="stats">
        <tr>
            <td><s:text name="insert.username"/></td>
            <td><s:textfield name="username"/></td>
            <td><s:text name="insert.password"/></td>
            <td><s:textfield name="password"/></td>
                <%--<td><s:text name="insert.password2"/></td>--%>
                <%--<td><s:password name="password2"/></td>--%>
        </tr>
    </table>
    <table class="stats">
        <tr>
            <td><s:text name="insert.permission16"/><s:checkbox name="permission16"/></td>
            <td><s:text name="insert.permission8"/><s:checkbox name="permission8"/></td>
            <td><s:text name="insert.permission4"/><s:checkbox name="permission4"/></td>
            <td><s:text name="insert.permission2"/><s:checkbox name="permission2"/></td>
            <td><s:text name="insert.permission1"/><s:checkbox name="permission1"/></td>
        </tr>
    </table>
    <s:submit key="query.submit" name="query"/>
    <s:submit key="insert.submit" name="insert"/>
    <s:reset key="all.reset"/>
</s:form>