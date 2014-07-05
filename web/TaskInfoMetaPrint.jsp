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
        for (i = 1; i < 3; i++) {
            if (i == d) eval("div" + i).style.visibility = "visible";
            else eval("div" + i).style.visibility = "hidden";
        }
//跳转到打印预览
        document.all.wb.ExecWB(6, 1);
//重新将所有的内容全部显示
        for (i = 1; i < 3; i++) {
            eval("div" + i).style.visibility = "visible";
        }
    }

    function printView() {
//eval("div0").style.visibility = "hidden";
        eval("div1").style.visibility = "hidden";
        eval("div2").style.visibility = "visible";
        document.all.wb.ExecWB(7, 1);
//重新将所有的内容全部显示
        for (i = 1; i < 3; i++) {
            eval("div" + i).style.visibility = "visible";
        }
    }
</script>
<head>
    <title>打印</title>
    <style type="text/css">
        table.stats {
            border-collapse: collapse;
        }

        table.stats td {
            table-layout: fixed;
            height: 28px;
            border: 1px #000 solid;
        }

        P {
            page-break-after: always
        }
    </style>
</head>
<body>
<%--<jsp:include page="head.jsp"/>--%>
<div id=div2>
    <s:iterator value="listSet" id="list" status="st">
        <s:if test='%{#st.index!=listSize}'>
            <P>
        </s:if>
        <table width="720" border="2" align="center" cellspacing="0">
            <s:if test='%{#st.index==0}'>
            <caption style="text-align: center">
                No:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QR01-010
            </caption>
            <tbody>
            <tr>
                <td colspan="11" rowspan="2" align="center" valign="middle" nowrap
                    style="text-align: center; font-size: xx-large; font-weight: bold;"><img src="logo.png" width="103"
                                                                                             height="34" alt=""/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产&nbsp;品&nbsp;质&nbsp;量&nbsp;跟&nbsp;踪&nbsp;卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td align="center" valign="middle" nowrap style="font-size: small">制单日期</td>
                <td align="center" valign="middle" nowrap style="font-size: small">计划数量</td>
            </tr>
            <tr>
                <td align="center" valign="middle" nowrap style="font-size: small"><s:property value="%{date}"/></td>
                <td align="center" valign="middle" nowrap style="font-size: small"><s:property value="%{num}"/></td>
            </tr>
            <tr>
                <td colspan="3" align="center" valign="middle" nowrap style="text-align: center; font-size: small;">产品名称
                </td>
                <td colspan="4" align="center" valign="middle" nowrap style="text-align: center; font-size: small;">
                    生产令/批次号
                </td>
                <td colspan="3" align="center" valign="middle" nowrap style="text-align: center; font-size: small;">
                    材料名称或牌号
                </td>
                <td colspan="3" align="center" valign="middle" nowrap style="text-align: center; font-size: small;">材料规格
                </td>
            </tr>
            <tr>
                <td colspan="3" align="center" valign="middle" style="text-align: center; font-size: small;"><s:property
                        value="%{drawingName}"/></td>
                <td colspan="4" align="center" valign="middle" style="text-align: center; font-size: small;"><s:property
                        value="%{taskId}"/></td>
                <td colspan="3" align="center" valign="middle" style="text-align: center; font-size: small;">&nbsp;</td>
                <td colspan="3" align="center" valign="middle" style="text-align: center; font-size: small;">&nbsp;</td>
            </tr>
            <tr>
                <td height="12" colspan="13" align="center" valign="middle"
                    style="text-align: center; font-size: 24;"></td>
            </tr>
            </s:if>
            <tr>
                <td colspan="3" align="center" valign="middle" style="font-size: small">工序</td>
                <td rowspan="3" align="center" valign="middle" style="font-size: small">操作者<br/>签字</td>
                <td colspan="5" align="center" valign="middle" style="font-size: small">专检/终检结果</td>
                <td colspan="2" rowspan="3" align="center" valign="middle" nowrap style="font-size: small">&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td colspan="2" rowspan="3" align="center" valign="middle" style="font-size: small">工序二维码</td>
            </tr>
            <tr>
                <td rowspan="2" align="center" valign="middle" style="font-size: small">工<br/>序<br/>号</td>
                <td rowspan="2" align="center" valign="middle" style="font-size: small">工序<br/>名称</td>
                <td rowspan="2" align="center" valign="middle" style="font-size: small">本序<br/>定额</td>
                <td rowspan="2" align="center" valign="middle" style="font-size: small">合格<br/>数量</td>
                <td colspan="2" align="center" valign="middle" style="font-size: small">不合格</td>
                <td rowspan="2" align="center" valign="middle" style="font-size: small">检验员<br/>签章</td>
                <td rowspan="2" align="center" valign="middle" style="font-size: small">检验日期</td>
            </tr>
            <tr>
                <td align="center" valign="middle" style="text-align: center; font-size: small;">数量</td>
                <td align="center" valign="middle" style="text-align: center; font-size: small;">不合格审<br/>
                    理单号
                </td>
            </tr>
            <s:iterator value="#list" id="p2" status="st2">
                <tr>
                    <s:if test='%{#p2.procedureId!=0}'>
                        <td height="120" align="center" valign="middle" style="text-align: center"><s:property
                                value="#p2.procedureId"/></td>
                    </s:if>
                    <s:else>
                        <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    </s:else>
                    <td height="120" align="center" valign="middle" style="text-align: center"><s:property
                            value="#p2.procedureName"/></td>
                    <td height="120" align="center" valign="middle" style="text-align: center"><s:property
                            value="#p2.workHour"/></td>
                    <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <td height="120" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <td height="120" colspan="2" align="center" valign="middle" style="text-align: center">&nbsp;</td>
                    <s:if test='%{#p2.procedureId!=0}'>
                        <td height="120" colspan="2" align="center" valign="middle" style="text-align: center"><img
                                style="height:100px;width:100px"
                                src="<s:url value="TdcOut.action?procedureId=%{#p2.procedureId}"/>"/></td>
                    </s:if>
                    <s:else>
                        <td height="120" colspan="2" align="center" valign="middle" style="text-align: center"><img
                                style="height:100px;width:100px" src=""/>
                        </td>
                    </s:else>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <s:if test='%{#st.index!=listSize}'>
            </P>
        </s:if>
    </s:iterator>
    <h1 style="text-align: center"><img src="logo.png" width="103" height="34" alt=""/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原材料（领）备料单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </h1>
    <table width="720" border="2" align="center" cellspacing="0">
        <caption style="text-align: center; width: 720px; height: 20px;">
            制 单 人： &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            &nbsp;
            &nbsp; &nbsp; &nbsp; 制单日期：<s:property value="%{date}"/>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            &nbsp;
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            &nbsp;
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; QR04-003
        </caption>
        <tbody>
        <tr>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">生产令号</td>
            <td width="240" height="40" colspan="3" align="center" valign="middle" style="text-align: center">
                <s:property
                        value="%{taskId}"/></td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">产品名称</td>
            <td width="160" height="40" colspan="2" align="center" valign="middle" style="text-align: center">
                <s:property
                        value="%{drawingName}"/></td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">计划数量</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center"><s:property
                    value="%{num}"/></td>
        </tr>
        <tr>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">产品图号</td>
            <td width="240" height="40" colspan="3" align="center" valign="middle" style="text-align: center">
                <s:property
                        value="%{drawingId}"/></td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">毛坯尺寸</td>
            <td width="160" height="40" colspan="2" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">毛坯重量<br/>（kg）
            </td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">&nbsp;</td>
        </tr>
        <tr>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">材料牌号</td>
            <td width="240" height="40" colspan="3" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">材料规格</td>
            <td width="320" height="40" colspan="4" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
        </tr>
        <tr>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">
                下&nbsp;料&nbsp;人
            </td>
            <td width="160" height="40" colspan="2" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">
                检&nbsp;验&nbsp;员
            </td>
            <td width="160" height="40" colspan="2" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">
                库&nbsp;管&nbsp;员
            </td>
            <td width="160" height="40" colspan="2" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
        </tr>
        <tr>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">
                领&nbsp;料&nbsp;人
            </td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">&nbsp;</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">领料日期</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">&nbsp;</td>
            <td width="80" height="40" colspan="1" align="center" valign="middle" style="text-align: center">备&nbsp;&nbsp;&nbsp;&nbsp;注</td>
            <td width="320" height="40" colspan="4" align="center" valign="middle" style="text-align: center">
                &nbsp;</td>
        </tr>
        </tbody>
    </table>
</div>
<div id=div1 align="center">
    <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0>
    </OBJECT>
    <input type=button value=页面设置 onClick=document.all.wb.ExecWB(8,1)>
    <input type=button value=打印预览 onclick="printView()"/>
    <input type=button value=打印 onclick=print("2")>
    <input type=button value=关闭 onClick=document.all.wb.ExecWB(45,1)>
</div>
</body>
</html>
