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
    <title>查询结果</title>
</head>
<body>
<%@include file="TaskInfoQuery.jsp" %>
<s:form>
    <table>
        <tr>
            <td colspan="3">原工艺单</td>
            <td colspan="3">新工艺单</td>
        </tr>
        <tr>
            <td>工序</td>
            <td>工序名称</td>
            <td>工时</td>
            <td>工序</td>
            <td>工序名称</td>
            <td>工时</td>
        </tr>

        <s:iterator value="pl" id="p" status="st">
            <tr>
                <td><s:property value="#p.procedureId"/></td>
                <td><s:property value="#p.procedureName"/></td>
                <td><s:property value="#p.workHour"/></td>
                <td><s:textfield name="procedureId%{#st.index}" value="%{#p.procedureId}"/></td>
                <td><s:textfield name="procedureName%{#st.index}" value="%{#p.procedureName}"/></td>
                <td><s:textfield name="workHour%{#st.index}" value="%{#p.workHour}"/></td>
            </tr>
        </s:iterator>
    </table>
    <s:submit key="update"/>
</s:form>


</body>
</html>
