package com.AgriculturalSales.test;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        System.out.println("创建任务时间：" + new Date());
        // 创建一个定长线程池，支持定时及周期性任务执行
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        // 建立一个延时任务，10秒钟之后执行
        ScheduledFuture<String> futrue = executor.schedule(new MyTask("haha"), 10, TimeUnit.SECONDS);
        try {
            // 如果任务执行完成，future.get()方法会返回一个null。注意，future.get()方法会产生阻塞
            System.out.println(futrue.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 当前线程执行完之后，关闭与线程池的连接
        executor.shutdown();
    }
}
class MyTask implements Callable<String> {

    private String name;

    public MyTask(String name) {
        super();
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("任务开始执行时间：" + new Date());
        return name;
    }
}
