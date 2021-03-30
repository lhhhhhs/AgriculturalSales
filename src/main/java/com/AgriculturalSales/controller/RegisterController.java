package com.AgriculturalSales.controller;

import cn.hutool.db.Session;
import com.AgriculturalSales.bean.Msg;
import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.service.UserService;
import com.AgriculturalSales.utils.phone.BasePhone;
import com.AgriculturalSales.utils.phone.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.*;

@Controller
public class RegisterController {


    @Autowired
    UserService userService;


    /**
     * 获得手机号并发送验证码，返回结果
     * @param phone
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public Msg sendSms(String phone, HttpSession session){
        System.out.println("1111");
        SMS sms = BasePhone.phoneCode(phone);
        System.out.println(sms);
//        SMS sms = new SMS("1234", "OK", 1);
        if(sms != null){
            session.setAttribute("code", sms.getCode());
            // 创建一个定长线程池，支持定时及周期性任务执行
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            // 延时任务
            System.out.println("验证码5分钟后销毁：" + new Date());
            // 建立一个延时任务，5分钟之后执行
            ScheduledFuture<String> futrue = executor.schedule(new MyTask(session), 5, TimeUnit.MINUTES);
            try {
                System.out.println(futrue.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 当前线程执行完之后，关闭与线程池的连接
            executor.shutdown();
            return Msg.success().add("sms", sms);
        }else {
            return Msg.fail();
        }
    }




    /**
     * 检验验证码
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public Msg checkCode(String code,HttpSession session){
        String sessionCode = (String) session.getAttribute("code");
        if(code.equalsIgnoreCase(sessionCode)){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

    /**
     * 注册用户
     * @param user
     * @param session
     * @param checkPwd
     * @param code
     * @return
     */
    @RequestMapping("/saveUser")
    @ResponseBody
    public Msg saveUser(User user,HttpSession session,String checkPwd,String code){
        String sessionCode = (String) session.getAttribute("code");
        boolean checkedPhone = userService.checkedPhone(user.getPhone());
        if(code.equalsIgnoreCase(sessionCode)){
            if (checkedPhone) {
                //注册成功
                userService.save(user);
                return Msg.success();
            }else {
                return Msg.fail().add("msg", "手机号已被注册！");
            }
        }else {
            return Msg.fail().add("msg", "验证码错误！");
        }
    }
}

class MyTask implements Callable<String> {

    private HttpSession session;

    public MyTask(HttpSession session) {
        super();
        this.session = session;
    }

    @Override
    public String call() throws Exception {
        System.out.println("销毁验证码：" + new Date());
        System.out.println("session中的验证码被销毁！");
        session.setAttribute("code", "dashfu912379sdhkcxkhviu2134789xjkchkvhu08ukchkvh-=/.213./123/-=");
        return "OK";
    }
}
