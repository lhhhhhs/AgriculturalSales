<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bodyMain header"> <a href="${APP_PATH}/goIndex" class="L logo" target="_blank"><img src="images/logo.gif" width="160" height="90" alt="茂名特产商城logo"/></a>
    <div class="phcSearch L">
        <form action="${APP_PATH}/product" method="get" id="searchForm" name="searchForm"  class="search-form">
            <input class="search_input L" type="text" name="KeyWord" id="keyword"  placeholder="请输入您想要的商品" autocomplete="off">
            <input type="submit" class="submit_btn L" value=""/>
        </form>
        <div class="search-hot" id="hotKeywordsShow"> <a href="${APP_PATH}/product" class="hot-item">U盘定制</a> <a href="" class="hot-item">展会礼品</a> <a href="#" class="hot-item">地产礼品</a> <a href="#" class="hot-item">小米礼品定制</a> <a href="#" class="hot-item">水杯定制</a> <a href="#" class="hot-item">双肩包定制</a></div>
    </div>
</div>