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
<script>
    function print(d) {
        for (i = 0; i < 3; i++) {
            if (i == d) eval("div" + i).style.visibility = "visible";
            else eval("div" + i).style.visibility = "hidden";
        }
//跳转到打印预览
        document.all.wb.ExecWB(6, 1);
//重新将所有的内容全部显示
        for (i = 0; i < 3; i++) {
            eval("div" + i).style.visibility = "visible";
        }
    }

    function printView() {
        eval("div0").style.visibility = "hidden";
        eval("div1").style.visibility = "hidden";
        eval("div2").style.visibility = "visible";
        document.all.wb.ExecWB(7, 1);
//重新将所有的内容全部显示
        for (i = 0; i < 3; i++) {
            eval("div" + i).style.visibility = "visible";
        }
    }
</script>
<head>
    <title>打印</title>
</head>
<body>
<jsp:include page="head.jsp"/>
<div id=div2>
    <table>
    <tr>
        <td colspan="2">新工艺单</td>
        <td colspan="1"><s:text name="print.taskId"/><s:property value="%{taskId}"/></td>
        <td colspan="1"><s:text name="print.drawingNum"/><s:property value="%{drawingNum}"/></td>
    </tr>
    </table>
    <table class="stats">
        <tr>
            <td>工序</td>
        <td>工序名称</td>
        <td>工时</td>
        <td>二维码</td>
            <td>工序</td>
            <td>工序名称</td>
            <td>工时</td>
            <td>二维码</td>
        </tr>
        <s:iterator value="resultListNew" id="p2" status="st">
            <s:if test="#st.odd==true">
        <tr>
            </s:if>
            <td><s:property value="#p2.procedureId"/></td>
            <td><s:property value="#p2.procedureName"/></td>
            <td><s:property value="#p2.workHour"/></td>
            <s:if test='%{#p2.procedureName!=""}'>
                <td><img style="height:100px;width:100px"
                         src="<s:url value="TdcOut.action?procedureId=%{#p2.procedureId}"/>"/>
                </td>
            </s:if>
            <s:else>
                <td><img style="height:100px;width:100px" src=""/>
                </td>
            </s:else>
            <s:if test="#st.even==true">
        </tr>
            </s:if>
    </s:iterator>
</table>
</div>
<div id=div1>
    <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0>
    </OBJECT>
    <input type=button value=页面设置 onClick=document.all.wb.ExecWB(8,1)>
    <input type=button value=打印预览 onclick="printView()"/>
    <input type=button value=打印 onclick=print("2")>
    <input type=button value=关闭 onClick=document.all.wb.ExecWB(45,1)>
</div>
</body>
</html>
