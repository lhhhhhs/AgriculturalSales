<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>茂名特产商城</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <link
            href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/asale.css"/>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/layui/css/layui.css"/>
    <script type="text/javascript"
            src="${APP_PATH}/static/layui/layui.js"></script>
</head>

<body>
<%--包含头--%>
<%@include file="common/head.jsp" %>
<%--包含搜索--%>
<%@include file="common/search.jsp" %>


<div class="bodyMain">

</div>
<div class="bodyMain ProductShow">
    <div class="top_slide_wrap L">
        <img src="${APP_PATH}${product.imgPath}" width="100%" height="100%" alt="商品展示"/>
    </div>
    <div class="ProductshowTxt R">
        <h3>${product.name}</h3>
        <div class="infoTxt L">
            <p><span>￥${product.price}</span>/元</p>
        </div>
        <div style="height: 240px;">
            ${product.info}
        </div>
        <div class="infoTxt L">
            <div class="FormNum L" ><span style="margin-top: -10px;">购买数量：</span>
                <div class="proForm L">
                    <input type="text" value="1" class="goodsNumber" name="number" id="product_amount">
                    <div class="click"><a class="JS_add" id="add"></a> <a class="JS_minus" id="reduce"></a></div>
                    个
                </div>
            </div>
            <div class="proSever R"> 想要了解更多，请联系客服
                或拔打客服热线:<span>400-800-xxxx</span></div>
        </div>
        <ul class="infoCart L">
            <a id="buyBtn"  class="JS_goods_buy"> 立即购买 </a> <a id="addCartBtn"  class="JS_goods_add"> 加入购物车 </a>
        </ul>
    </div>
</div>
<div class="ProInfoShow">
    <script>
        window.onload = function () {
            var oLi = document.getElementById("tab").getElementsByTagName("li");
            var oUl = document.getElementById("content").getElementsByTagName("ul");

            for (var i = 0; i < oLi.length; i++) {
                oLi[i].index = i;
                oLi[i].onmouseover = function () {
                    for (var n = 0; n < oLi.length; n++) oLi[n].className = "";
                    this.className = "current";
                    for (var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
                    oUl[this.index].style.display = "block"
                }
            }
        };
        $("#add").click(function () {
            var num = $("#product_amount").val();
            $("#product_amount").val(++num);
        });
        $("#reduce").click(function () {
            var num = $("#product_amount").val();
            if (num > 1) {
                $("#product_amount").val(--num);
            }
        })
    </script>
    <div id="outer">
        <div class="ProInfoTitle">
            <ul id="tab" class="bodyMain">
                <li class="current">详情描述</li>
                <li>商品评论（<c:if test="${empty count}">无</c:if><c:if test="${not empty count}">${count}</c:if>）</li>
            </ul>
        </div>
        <div id="content">
            <ul style="display:block;">
                <div style="text-align:center;"><img src="${APP_PATH}${product.detailsImgPath}" width="100%"  alt="详情图片"/>
                </div>
            </ul>
            <ul>
                <c:forEach items="${evaluationList}" var="evaluation" begin="0" end="5">
                <li><a href="${APP_PATH}/evaluationDetails?id=${evaluation.id}">
                    <h3>用户：${evaluation.uName}</h3>
                    <p>${evaluation.evaluationText}</p>
                    <span>${evaluation.createTime}</span> </a></li>
                </c:forEach>
                <div class="allMes">
                    <a href="${APP_PATH}/allEvaluation?id=${product.id}">查看所有商品评论 ></a>
                </div>

            </ul>
        </div>
    </div>

    <div class="modal fade bs-example-modal-lg" id="buyModel" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">创建订单</h4>
                </div>
                <form action="${APP_PATH}/BuyProduct" method="post">
                <div class="modal-body">
                    <div class="buyDetails" style="width: 100%; height: 400px;margin-left: 50px;">
                        <div><img src="${APP_PATH}${product.imgPath}" width="70%" height="300px;"/></div>
                        <input type="hidden" name="id" value="${product.id}">
                        <p style="font-size: 20px;">商品名称：${product.name}</p>
                        <p style="font-size: 20px;">单价：￥${product.price}</p>
                        <p style="font-size: 20px;" id="buyNum">数量：</p>
                        <input type="hidden" id="count_input" name="count" value="">
                        <p style="font-size: 20px;color: red" id="totalMoney">金额：￥</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">放弃</button>
                    <input type="submit" class="btn btn-danger" value="支付"/>
                </div>
                </form>
            </div>
        </div>
    </div>

</div>
<%--包含脚--%>
<%@include file="common/foot.jsp" %>

<script type="text/javascript">
    $("#buyBtn").click(function () {
        var count = $("#product_amount").val();
        var totalMoney = count*${product.price};
        $("#buyNum").text("数量：");
        $("#count_input").val(count);
        $("#totalMoney").text("金额：￥");
        $("#buyNum").append(count);
        $("#totalMoney").append(totalMoney);
        $('#buyModel').modal();
    });
    layui.use(['layer', 'form' ,'jquery'], function(){
        var layer = layui.layer,form = layui.form,$ = layui.$;
    $("#addCartBtn").click(function () {
        var num = $("#product_amount").val();
        $.ajax({
            url: "${APP_PATH}/addCart?pid=${product.id}&count="+num,
            type: "POST",
            //允许ajax携带cookie
            xhrFields: {
                withCredentials: true // 发送Ajax时，Request header中会带上 Cookie 信息。
            },
            crossDomain: true, // 发送Ajax时，Request header 中会包含跨域的额外信息，但不会含cookie（作用不明，不会影响请求头的携带）
            success: function (res) {
                if (res.code == 100) {
                    layer.msg("添加购物车成功！");
                } else {
                    layer.msg("添加购物车失败！");
                }
            }
        });
    })
    });


</script>

</body>
</html>

