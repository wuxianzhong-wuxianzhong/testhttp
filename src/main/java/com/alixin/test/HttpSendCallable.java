package com.alixin.test;

import com.alixin.test.utils.HttpUtils;

import java.util.concurrent.Callable;

public class HttpSendCallable implements Callable<String> {


    @Override
    public String call() {
        String str = HttpUtils.doHttpPost("http://localhost:8080/ack","");
        return str;
    }
}
