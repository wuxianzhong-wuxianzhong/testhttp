package com.alixin.test.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtils {
    private static String charSet = "UTF-8";
    private static CloseableHttpClient httpClient = null;
    private static CloseableHttpResponse response = null;
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    public static String doHttpPost(String url, String jsonStr) {

        try {
            httpClient = HttpClients.createDefault();
            //创建httpPost
            HttpPost httpPost = new HttpPost(url);
            //请求头
            httpPost.setHeader("Accept", "application/json");
            //请求数据
            StringEntity entity = new StringEntity(jsonStr, charSet);
            entity.setContentType("text/json");
            entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(entity);

            //发送post请求
            response = httpClient.execute(httpPost);

            //获取本次响应的状态码
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }else{
                throw new Exception("返回状态不是200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //捕捉异常，并打印到error.log文件中
            logger.error(e.toString());
            System.exit(0);
        }
        return null;
    }
}
