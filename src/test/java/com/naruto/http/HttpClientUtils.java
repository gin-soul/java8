package com.naruto.http;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static CloseableHttpClient localHttpClient;

    private static int timeOutMilliSecond = 30 * 1000; // 默认超时时间:30s，获取连接的timeout和获取socket数据的timeout都是30s

    private static String charset = "utf-8";

    private static int maxTotal = 500;

    // 连接池链接耗尽等待时间
    private static int connectionRequestTimeout = 2000;

    private static int maxPerRoute = 10;

    private final static Object syncLock = new Object();

    private static PoolingHttpClientConnectionManager connectionManager = null;

    private static HttpClientBuilder httpBulder = null;


    static {
        //初始化连接池管理器，设置http的状态参数
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        httpBulder = HttpClients.custom();
        httpBulder.setConnectionManager(connectionManager);
    }

    private static CloseableHttpClient getHttpClient() {
        if (localHttpClient == null) {
            synchronized (syncLock) {
                if (localHttpClient == null) {
                    localHttpClient = createHttpClient();
                }
            }
        }
        return localHttpClient;

    }

    private static CloseableHttpClient createHttpClient(int invokeTimeout) {
        if (invokeTimeout > 0 && invokeTimeout < 60 * 30 * 1000) {
            timeOutMilliSecond = invokeTimeout;
        }
        Builder builder = RequestConfig.custom().setSocketTimeout(timeOutMilliSecond)
                .setConnectTimeout(timeOutMilliSecond).setConnectionRequestTimeout(connectionRequestTimeout);

        RequestConfig defaultRequestConfig = builder.build();
        // Create an HttpClient with the given custom dependencies and
        // configuration.
        HttpClientBuilder httpClientBuilder = httpBulder.setDefaultRequestConfig(defaultRequestConfig);

        CloseableHttpClient httpClient = httpClientBuilder.build();

        return httpClient;
    }


    private static CloseableHttpClient getHttpClient(int invokeTimeout) {
        synchronized (syncLock) {
            localHttpClient = createHttpClient(invokeTimeout);
        }
        return localHttpClient;

    }

    private static CloseableHttpClient createHttpClient() {
        Builder builder = RequestConfig.custom().setSocketTimeout(timeOutMilliSecond)
                .setConnectTimeout(timeOutMilliSecond);

        RequestConfig defaultRequestConfig = builder.build();
        HttpClientBuilder httpClientBuilder = httpBulder.setDefaultRequestConfig(defaultRequestConfig);
        CloseableHttpClient httpClient = httpClientBuilder.build();

        return httpClient;
    }

    public static String get(String url) {
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            httpget.setHeader("Cookie", "_td_token_=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJCN0JBMEZGQjE5MENGNTg1QzI2MDM5QUJBQ0FENzhEQiIsInN1YiI6ImFkbWluIiwiaXNzIjoiZnJhdWRtZXRyaXgiLCJpYXQiOjE1NjAxNDg2NDcsImV4cCI6MTU2MDc1MzQ0N30.7s4mf7vybliNso5u1hpctUTbwV1tqIsW-cDE4jiGrwQ; _uid_=c693e0ec0a2e4bf8b71eef8152d88a29; _td_account_=admin");
            response = getHttpClient().execute(httpget, HttpClientContext.create());
            Header[] allHeaders = response.getAllHeaders();
            for (Header allHeader : allHeaders) {
                System.out.println(allHeader.getName() + "    value:  " + allHeader.getValue());
            }
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return null;
    }

    public static String post(String url, Map<String, String> paramMap) throws Exception {
        CloseableHttpClient httpClient = getHttpClient();

        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsPair = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            paramsPair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsPair, charset));
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public static String post(String url, String postContent) throws Exception {
        CloseableHttpClient httpClient = getHttpClient();

        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(postContent, charset));

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public static String getPutByHttpClient(String url,Map<String,Object> bodyParam, Map<String,String> header) {
        HttpPut httpPut = null;
        String result = null;
        HttpResponse response = null;
        HttpEntity resEntity;
        CloseableHttpClient hc = getHttpClient();
        try{
            logger.info("getPutByHttpClient url:" + url+" body="+bodyParam+" header= "+header);
            httpPut = new HttpPut(url);
            httpPut.setHeader("content-type", "application/json;charset=utf-8");
            if(header!=null){
                //httpPut.setHeader("handlerUrl", header.get("handlerUrl"));
                //httpPut.setHeader("callbackEvent", header.get("callbackEvent"));
            }
            //设置参数
            String body= JSONObject.toJSONString(bodyParam);
            resEntity = new StringEntity(body,"utf-8");
            httpPut.setEntity(resEntity);
            Long startTime=System.currentTimeMillis();
            logger.info("getPutByHttpClient begin startTime="+startTime);
            response = hc.execute(httpPut);
            logger.info("getPutByHttpClient end httpStatus="+response.getStatusLine().getStatusCode()+"total time="+(System.currentTimeMillis()-startTime));
            if(response != null){
                resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"utf-8");
                }
            }
            //处理http返回码401的情况
            if (response.getStatusLine().getStatusCode() == 401) {
                logger.error("getPutByHttpClient  http 401 account="+header.get("account")+"result="+result);
                result="{\"IsSuccess\":\"401false\"}";
            }
        }catch(Exception e){
            logger.error("getPutByHttpClient error \n",e);
        } finally {
            if(httpPut!=null){
                httpPut.releaseConnection();
            }
            if(response!=null){
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String personId = "53012719731216353X";
        String url = "http://10.18.31.101:8088/uniteApi/serviceConfigTest?testConfig=%5B%7B%22paramName%22%3A%22S_DC_VB_FORGCODE%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_DC_VB_FCOMPANYNAME%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_DC_VS_IDNO%22%2C%22paramValue%22%3A%22"+ personId +"%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_DC_VS_NAME%22%2C%22paramValue%22%3A%22%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_ET_VB_BUYERCODE%22%2C%22paramValue%22%3A%22%22%2C%22mustInput%22%3Afalse%7D%5D&uuid=b10def1e-2438-4ef9-ab6e-3020dd250828";
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("tokenId", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1ZmVmMzI2OTFiMzc0OWFiYTBiNWUyM2Q1NTEyOGFjMiIsInN1YiI6InJvb2tpZSIsImlzcyI6ImZyYXVkbWV0cml4IiwiaWF0IjoxNTM0MjEzODA0LCJleHAiOjE1MzQ4MTg2MDR9.uFmVE0Z2Oa92PRpMF6DpjWB5ST-hxnr7gssWWJlO8eE");


        try {
            String json = getPutByHttpClient(url, paramsMap, null);
            System.out.println(json);
            Map<String, Object> read = JsonPath.read(json, "$.data");
            System.out.println(read.get("N_ET_VD_PRODUCTIDCNT"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
