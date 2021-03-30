<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/echarts.js"></script>
    <link href="${APP_PATH}/static/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
<div>
    <%--包含导航--%>
    <%@include file="../common/managerNav.jsp" %>

    <div class="layui-body" style="background-color: #F2F2F2">
        <!-- 内容主体区域 -->
        <div class="layui-row">
            <div class="layui-col-md5">
                <div class="layui-col-md12" style="background-color:white;margin: 10px;">
                    <div class="layui-card">
                        <div class="layui-card-header">快捷方式</div>
                        <div class="layui-card-body" style="">
                            <div class="layui-row">
                                <div class="layui-col-md3 "
                                     style="margin-left: 20px;height: 80px;background-color: #F2F2F2">
                                    <a href="${APP_PATH}/orderInfoManager">
                                        <div class="layui-row" style="height: 50%;text-align:center;margin-top: 20px;">
                                            <i class="layui-icon " style="margin:0 auto;font-size:50px;">&#xe63c;</i>
                                        </div>
                                        <div class="layui-row" style="height: 30%;text-align:center">
                                            <cite style="margin:0 auto;">订单管理</cite>
                                        </div>
                                    </a>
                                </div>
                                <div class="layui-col-md3"
                                     style="margin-left: 20px;height: 80px;background-color: #F2F2F2">
                                    <a href="${APP_PATH}/productManager">
                                        <div class="layui-row" style="height: 50%;text-align:center;margin-top: 20px;">
                                            <i class="layui-icon " style="margin:0 auto;font-size:50px;">&#xe627;</i>
                                        </div>
                                        <div class="layui-row" style="height: 30%;text-align:center">
                                            <cite style="margin:0 auto;">商品管理</cite>
                                        </div>
                                    </a>
                                </div>
                                <div class="layui-col-md3"
                                     style="margin-left: 20px;height: 80px;background-color: #F2F2F2">
                                    <a  onclick="launchFullscreen(document.documentElement);">
                                        <div class="layui-row" style="height: 50%;text-align:center;margin-top: 20px;">
                                            <i class="layui-icon " style="margin:0 auto;font-size:50px;">&#xe622;</i>
                                        </div>
                                        <div class="layui-row" style="height: 30%;text-align:center">
                                            <cite  style="margin:0 auto;">全屏</cite>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="layui-row" style="margin-top: 20px;">
                                <div class="layui-col-md3 "
                                     style="margin-left: 20px;height: 80px;background-color: #F2F2F2">
                                    <a href="${APP_PATH}/chatAdmin">
                                        <div class="layui-row" style="height: 50%;text-align:center;margin-top: 20px;">
                                            <i class="layui-icon " style="margin:0 auto;font-size:50px;">&#xe626;</i>
                                        </div>
                                        <div class="layui-row" style="height: 30%;text-align:center">
                                            <cite style="margin:0 auto;">客服</cite>
                                        </div>
                                    </a>
                                </div>
                                <div class="layui-col-md3"
                                     style="margin-left: 20px;height: 80px;background-color: #F2F2F2">
                                    <a href="${APP_PATH}/edit">
                                        <div class="layui-row" style="height: 50%;text-align:center;margin-top: 20px;">
                                            <i class="layui-icon " style="margin:0 auto;font-size:50px;">&#xe614;</i>
                                        </div>
                                        <div class="layui-row" style="height: 30%;text-align:center">
                                            <cite style="margin:0 auto;">设置</cite>
                                        </div>
                                    </a>
                                </div>
                                <div class="layui-col-md3"
                                     style="margin-left: 20px;height: 80px;background-color: #F2F2F2">
                                    <a href="${APP_PATH}/exitManager">
                                        <div class="layui-row" style="height: 50%;text-align:center;margin-top: 20px;">
                                            <i class="layui-icon " style="margin:0 auto;font-size:50px;">&#xe682;</i>
                                        </div>
                                        <div class="layui-row" style="height: 30%;text-align:center">
                                            <cite style="margin:0 auto;">关闭</cite>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12" style="background-color:white;margin: 10px;">
                    <div class="layui-card">
                        <div class="layui-col-md12" style="height: 400px;margin-top: 40px;background-color: white"
                             id="kindSales">

                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="margin-left: 60px;margin-top: 10px;">
                <div class="layui-col-md12" style="height: 700px;width:100%;background-color: white" id="productSales">

                </div>
            </div>
        </div>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © maomingtechan.com
    </div>
</div>
<script>
    layui.use(['form', 'element', 'layer', 'carousel', 'jquery', 'table', 'laydate', 'layedit'], function () {
        var element = layui.element;
        var layer = layui.layer;
        var carousel = layui.carousel;
        var $ = layui.jquery;
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var layedit = layui.layedit;
        var windowUpdateIndex;
        $("#admin").addClass("layui-this");
    });

    //饼状图
    var chartDom = document.getElementById('productSales');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        title: {
            text: '种类销量对比'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: [<c:forEach items="${kind1s}" var="kind1">'${kind1.name}',</c:forEach>]
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [<c:forEach items="${timeList}" var="time">'${time}',</c:forEach>]
        },
        yAxis: {
            type: 'value'
        },
        series: [
            <c:forEach items="${kindSalesMap}" var="SalesMap">
            {
                name: '${SalesMap.key}',
                type: 'line',
                data: [<c:forEach items="${SalesMap.value}" var="sales">${sales},</c:forEach>]
            },
            </c:forEach>
        ]
    };

    option && myChart.setOption(option);

    //折线图
    var chartDom1 = document.getElementById('kindSales');
    var myChart1 = echarts.init(chartDom1);
    var option1;

    option1 = {
        title: {
            text: '商品销量对比',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series: [
            {
                name: '销量',
                type: 'pie',
                radius: '50%',
                data: [
                    <c:forEach items="${salesRallyMap}" var="item">
                    {value: ${item.value}, name: '${item.key}'},
                    </c:forEach>
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    option1 && myChart1.setOption(option1);

    function launchFullscreen(element) {
        if(element.requestFullscreen) {
            element.requestFullscreen();
        } else if(element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if(element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
        } else if(element.msRequestFullscreen) {
            element.msRequestFullscreen();
        }
    }

    function window_close(){
        if (navigator.userAgent.indexOf("Firefox") != -1 || navigator.userAgent.indexOf("Chrome") !=-1) {
            window.location.href="http://localhost/fastflow/winform/cn/myprocessform.aspx";
            window.close();
        } else {
            window.opener = null;
            window.open("", "_self");
            window.close();
        }
    }
</script>
</body>
</html>

