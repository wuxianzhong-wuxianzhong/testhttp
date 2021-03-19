package com.alixin.test.service;

import com.alixin.test.HttpSendCallable;
import com.alixin.test.utils.DamonThread;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class ThreadService {

    private Logger logger = Logger.getLogger(this.getClass());

    public void startThreads(int n) {
        //自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                n,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(n));
        for(int i=1; i<=n; i++){
            //创建任务实例
            HttpSendCallable httpSendCallable = new HttpSendCallable();
            FutureTask<String> task = new FutureTask<>(httpSendCallable);
            //监视请求任务
            DamonThread thread = new DamonThread(task, i);
            thread.start();
            //发请求
            System.out.println("第"+i+"次请求任务");
            logger.info("第"+i+"次请求任务");
            executor.execute(task);

        }
    }

}
