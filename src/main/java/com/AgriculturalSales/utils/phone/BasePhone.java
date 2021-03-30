package com.AgriculturalSales.utils.phone;

import cn.hutool.setting.dialect.Props;
import com.AgriculturalSales.bean.Msg;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePhone {

    private static Props props = new Props("phone.properties");
    private static String host = props.getProperty("host");
    private static String path = props.getProperty("path");
    private static String method = props.getProperty("method");
    private static String appcode = props.getProperty("appcode");
    private static String tpl_id = props.getProperty("tpl_id");

    public static SMS phoneCode(String phone){
        SMS sms = new SMS();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //生成验证码
        String codeNumber = RandomStringUtils.randomNumeric(4);
//        String code = "code:" + codeNumber;
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【茂名特产】您的验证码是："+ codeNumber +"，5分钟内有效！");
        querys.put("mobile", phone);
        //消息实体
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            //状态行
            StatusLine statusLine = response.getStatusLine();
            //状态码
            int statusCode = statusLine.getStatusCode();
            String reasonPhrase = statusLine.getReasonPhrase();
            String text = EntityUtils.toString(response.getEntity());
             sms = JSON.parseObject(text, SMS.class);
            if("OK".equalsIgnoreCase(reasonPhrase) && 200 == statusCode && 1 == sms.getSuccessCounts()){
                sms.setCode(codeNumber);
                return sms;
            }else {
                return sms;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return sms;
        }

    }
}
