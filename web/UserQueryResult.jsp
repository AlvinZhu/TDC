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
    <title><s:text name="user.title"/></title>
</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="UserBody.jsp"/>
<table class="table-striped">
    <tr>
        <td></td>
        <td></td>
        <td><s:text name="result.username"/></td>
        <td><s:text name="result.password"/></td>
        <td><s:text name="result.permission16"/></td>
        <td><s:text name="result.permission8"/></td>
        <td><s:text name="result.permission4"/></td>
        <td><s:text name="result.permission2"/></td>
        <td><s:text name="result.permission1"/></td>
    </tr>
    <s:iterator value="list" id="pl" status="st">
        <s:form action="User">
            <s:hidden name="oldUsername" value="%{#pl.oldUsername}"/>
            <tr>
                <td><s:submit key="result.update" name="update"/></td>
                <td><s:submit key="result.delete" name="delete"/></td>
                <td><s:textfield name="username" value="%{#pl.username}"/></td>
                <td><s:textfield name="password" value="%{#pl.password}"/></td>
                    <%--<td><s:password name="password2" value="%{#pl.password}"/></td>--%>
                <s:if test='%{(#pl.permission&16)!=0}'>
                    <td><s:checkbox name="permission16" value="true"/></td>
                </s:if>
                <s:else>
                    <td><s:checkbox name="permission16" value="false"/></td>
                </s:else>
                <s:if test='%{(#pl.permission&8)!=0}'>
                    <td><s:checkbox name="permission8" value="true"/></td>
                </s:if>
                <s:else>
                    <td><s:checkbox name="permission8" value="false"/></td>
                </s:else>
                <s:if test='%{(#pl.permission&4)!=0}'>
                    <td><s:checkbox name="permission4" value="true"/></td>
                </s:if>
                <s:else>
                    <td><s:checkbox name="permission4" value="false"/></td>
                </s:else>
                <s:if test='%{(#pl.permission&2)!=0}'>
                    <td><s:checkbox name="permission2" value="true"/></td>
                </s:if>
                <s:else>
                    <td><s:checkbox name="permission2" value="false"/></td>
                </s:else>
                <s:if test='%{(#pl.permission&1)!=0}'>
                    <td><s:checkbox name="permission1" value="true"/></td>
                </s:if>
                <s:else>
                    <td><s:checkbox name="permission1" value="false"/></td>
                </s:else>
            </tr>
        </s:form>
    </s:iterator>
</table>
</body>
</html>
