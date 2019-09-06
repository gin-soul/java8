package com.naruto.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

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
			response = getHttpClient().execute(httpget, HttpClientContext.create());
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
		for (Entry<String, String> entry : paramMap.entrySet()) {
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

	public static List<String> toArrayByFileReader1(String name) {
		// 使用ArrayList来存储每行读取到的字符串
		List<String> arrayList = new LinkedList<>();
		try {
			FileReader fr = new FileReader(name);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Test
	public void test0() {
		queryEquipmentByPersonIds("personId", "equipment0");
	}

	/**
	 * 将list均分成多份
	 * @param source 要拆分的list
	 * @param n 每份的个数
	 * @param <T> 类型
	 * @return List<List<T>>
	 */
	public static <T> List<List<T>> averageAssign(List<T> source,int n){
		List<List<T>> result=new ArrayList<List<T>>();
		//(先计算出余数)
		int remaider=source.size()%n;
		//然后是商
		int number=source.size()/n;
		//偏移量
		int offset=0;
		for(int i=0;i<n;i++){
			List<T> value=null;
			if(remaider>0){
				value=source.subList(i*number+offset, (i+1)*number+offset+1);
				remaider--;
				offset++;
			}else{
				value=source.subList(i*number+offset, (i+1)*number+offset);
			}
			result.add(value);
		}
		return result;
	}



	private void queryEquipmentByPersonIds(String personIds, String fileName) {
		long begin = System.currentTimeMillis();
		List<String> strings = toArrayByFileReader1("D:\\java\\code\\naruto\\java8\\src\\main\\resources\\" + personIds+".txt");
		Queue<Equipment> equipmentList = new ConcurrentLinkedQueue<>();
		//另一种并发安全的list
		//List<Equipment> equipmentList = Collections.synchronizedList(new LinkedList<>());

		//将list分割成20个小list
		List<List<String>> lists = averageAssign(strings, 20);
		for (List<String> list : lists) {
			EquipmentThread equipmentThread = new EquipmentThread(list, equipmentList);
			Thread thread = new Thread(equipmentThread);
			thread.start();
		}
		ExportExcelUtil<Equipment> util = new ExportExcelUtil<>();
		String[] columnNames = {
				"身份证",
				"买受人全部设备的ID列表",
				"买受人全部设备数量",
				"买受人历史合作不同事业部数量",
				"买受人首次成交距今的年份数",
				"买受人最近一次成交距今的年份数",
				"买受人全部设备中处于保内状态的数量",
				"买受人全部设备中处于保外状态的数量",
				"买受人全部设备中处于未开机状态的数量",
				"买受人全部设备中设备使用状态为可用的设备数量",
				"买受人全部设备中设备使用状态为异常的设备数量",
				"买受人全部设备中设备使用状态为退市的设备数量",
				"买受人全部设备中设备异常状态为人机共失的设备数量",
				"买受人全部设备中设备异常状态为法务扣车的设备数量",
				"买受人全部设备中设备异常状态为国内转海外的设备数量",
				"买受人全部设备中设备异常状态为停用的设备数量",
				"买受人全部设备中设备异常状态为竞争对手回购的设备数量",
				"买受人全部设备中设备异常状态为退换机的设备数量",
				"买受人全部设备中设备异常状态为冻结_三停的设备数量",
				"买受人全部设备中设备异常状态为备案的设备数量",
				"买受人全部设备中设备主机解锁机状态为正常的设备数量",
				"买受人全部设备中设备主机解锁机状态为锁机的设备数量",
				"买受人全部设备中设备主机解锁机状态为异常的设备数量"
		};
		try {
			util.exportExcel("客户设备信息", columnNames, equipmentList, new FileOutputStream("D:/"+ fileName +".xls"), ExportExcelUtil.EXCEL_FILE_2003);
			System.out.println("equipmentList.size=" + equipmentList.size());
			System.out.println("time=" + (System.currentTimeMillis() - begin));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}