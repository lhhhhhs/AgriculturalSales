package com.AgriculturalSales.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocketTest/{userId}")//@ServerEndpoint把当前类变成websocket服务类
public class Websocket {

    public static String phone;

    private static String userId;
    // 连接的用户
    private static Map<String, Session> onlines = new HashMap<String, Session>();

    // 连接时执行
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        this.userId = userId;
        if (userId.equals("admin")) {
            System.out.println("客服上线！");
            onlines.put(userId, session);
        }
        if (onlines.containsKey("admin")&&!userId.equals("admin")) {
            if (onlines.size() >= 2) {
                System.out.println("onlines.size() = " + onlines.size());
                System.out.println("已满！");
                session.getBasicRemote().sendText("已满！");
            }else {
                onlines.put(userId, session);
                System.out.println("新链接！"+userId);
                session.getBasicRemote().sendText("hello!有什么可以帮助您？");
            }
        } else {
                System.out.println("客服不在线！");
        }

    }

    // 关闭时执行
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        if (onlines.containsKey(userId)) {
            onlines.remove(userId);
        }
        System.out.println("连接close：" + this.userId + " 关闭");
    }

    // 收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) throws IOException {
        System.out.println(message);
        System.out.println("userId = " + userId);
        if (userId.equals("admin")) {
                sendMessageToUser(userId, phone, message);
        } else {
            sendMessageToUser(phone, "admin", message);
        }
        System.out.println(onlines.toString());
    }


    // 连接错误时执行
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户id为：" + this.userId + "的连接发送错误");
        error.printStackTrace();
    }

    /**
     * 广播消息给所有人
     **/
    private void sendMessageToAll(String message) {
        Set<String> users = onlines.keySet();
        for (String user : users) {
            try {
                if (onlines.get(user).isOpen()) {
                    ((Session) onlines.get(user)).getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }

    /******
     *  给某个用户发送消息
     * @Param userName 发消息的name
     * **/
    private void sendMessageToUser(String userName, String receiveName, String message) {
        Set<String> users = onlines.keySet();
        for (String user : users) {
            if (user.equals(receiveName)) {
                try {
                    if (onlines.get(user).isOpen()) {
                        System.out.println("user---" + user);
                        ((Session) onlines.get(user)).getBasicRemote().sendText(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
