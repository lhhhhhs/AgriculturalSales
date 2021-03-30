package com.AgriculturalSales.test;

import com.AgriculturalSales.utils.phone.BasePhone;
import com.AgriculturalSales.utils.phone.HttpUtils;
import com.AgriculturalSales.utils.phone.SMS;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SmsTest {

    @Test
    public void sendSms(){
        //请求地址
        String host = "https://dxyzm.market.alicloudapi.com";
        //请求服务
        String path = "/chuangxin/dxjk";
        //请求方式
        String method = "POST";
        String appcode = "fccf30a257eb4937bda29569c74b3c13";//开通服务后 买家中心-查看AppCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【创信】你的验证码是：5873，3分钟内有效！；");
        querys.put("mobile", "17339824610");
        //消息实体
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Sms(){
        String phone = "17339824610";

        SMS sms = BasePhone.phoneCode(phone);
        if(0==sms.getSuccessCounts()) {
            System.out.println(sms.getSuccessCounts());
            System.out.println(sms.getMessage());
        }else {
            System.out.println(sms.getSuccessCounts());
            System.out.println(sms.getMessage());
            System.out.println(sms.getCode());
        }
    }

}
