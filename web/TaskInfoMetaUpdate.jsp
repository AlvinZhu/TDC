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
    <title><s:text name="update.title"/></title>
    <style type="text/css">
        <!--
        #left {
            float: left;
            height: auto;
            width: auto;
        }

        #right {
            float: left;
            height: auto;
            width: auto;
        }

        -->
    </style>

</head>
<body>
<jsp:include page="head.jsp"/>
<jsp:include page="TaskInfoMetaQueryBody.jsp"/>
<div id="left">
    <table class="stats">
        <tr>
            <td colspan="3">原工艺单</td>
        </tr>
        <tr>
            <td>工序</td>
            <td>工序名称</td>
            <td>工时</td>
        </tr>
        <s:iterator value="resultListOld" id="p">
            <tr>
                <td><s:property value="#p.procedureId"/></td>
                <td><s:property value="#p.procedureName"/></td>
                <td><s:property value="#p.workHour"/></td>
            </tr>
        </s:iterator>
    </table>
</div>
<div id="right">
    <s:form action="TaskInfoMetaUpdate">
        <s:hidden name="taskId" value="%{taskId}"/>
        <s:hidden name="drawingNum" value="%{drawingNum}"/>
        <table class="stats">
            <tr>
                <td colspan="3">新工艺单</td>
            </tr>
            <tr>
                <td>工序</td>
                <td>工序名称</td>
                <td>工时</td>
            </tr>
            <s:iterator value="resultListNew" id="p2" status="st">
                <tr>
                    <td><s:textfield name="resultListNew[%{#st.index}].procedureId" value="%{#p2.procedureId}"/></td>
                    <td><s:textfield name="resultListNew[%{#st.index}].procedureName"
                                     value="%{#p2.procedureName}"/></td>
                    <td><s:textfield name="resultListNew[%{#st.index}].workHour" value="%{#p2.workHour}"/></td>
                </tr>
            </s:iterator>
        </table>
        <s:submit key="query.update"/>
    </s:form>
</div>
</body>
</html>
