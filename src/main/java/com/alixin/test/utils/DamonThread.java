package com.alixin.test.utils;

import org.apache.log4j.Logger;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class DamonThread extends Thread {
    private Logger logger = Logger.getLogger(this.getClass());
    private FutureTask<String> task;
    private int count;

    public DamonThread(FutureTask task, int count) {
        this.task = task;
        this.count = count;
    }


    @Override
    public void run() {
        try {
            //获取请求任务的返回值，规定时间为1s
            String str = task.get(count*1000, TimeUnit.MILLISECONDS);
            System.out.println("第"+count+"次响应response : "+str);
            logger.info("status : "+200+"==>第"+count+"次响应response : "+str);
        } catch (Exception e) {
            //记录异常
            logger.error("第"+count+"个请求"+e.toString());
            //立即退出
            System.exit(0);
        }
    }
}
