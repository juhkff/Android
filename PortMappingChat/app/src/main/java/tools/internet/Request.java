package tools.internet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Request {
    private String addr = "http://123.207.13.112:8080/PortMappingChat/";
    private String requestURL;
    private Map<String, String> requestMap = new HashMap<String, String>();
    private Handler handler;

    public Request(String requestURL, Handler handler) {
        this.requestURL = requestURL;
        this.addr += requestURL;
        this.handler = handler;
        Log.i("requestGetAddr: ", this.addr);
    }

    public void get(Map<String, String> requestMap) throws IOException {
        this.requestMap = requestMap;
        String params = "";
        for (String key : requestMap.keySet()) {
            params += key + "=" + requestMap.get(key) + "&";
        }
        params = params.substring(0, params.length() - 1);  //去掉最后的&
        Log.i("requestParam", params);
        String getAddr = this.addr + "?" + params;
        Log.i("\tfinalGetAddr:", getAddr);
        HttpGet httpGet = new HttpGet(getAddr);
        HttpClient httpClient = new DefaultHttpClient();

        //发送请求
        HttpResponse response = httpClient.execute(httpGet);
        returnResponse(response);
    }

    public void post(Map<String, String> requestMap) throws IOException {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        for (String key : requestMap.keySet()) {
            pairList.add(new BasicNameValuePair(key, requestMap.get(key)));
        }
        Log.i("requestParam(Post)", String.valueOf(pairList));
        HttpEntity httpEntity = new UrlEncodedFormEntity(pairList);
        //URL不需要添加参数
        HttpPost httpPost = new HttpPost(addr);
        Log.i("\tfinalGetAddr(Post):", addr);
        httpPost.setEntity(httpEntity);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpPost);
        returnResponse(response);
    }

    private void returnResponse(HttpResponse response) throws IOException {
        HttpEntity httpEntity = response.getEntity();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
        String result = "";
        String line = "";
        try {
            while (null != (line = bufferedReader.readLine())) {
                result += line + "\n";
            }
            result = result.substring(0, result.length() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("ReceiveStr:", result);
        Message message = new Message();
        Bundle data = new Bundle();
        data.putString("resultStr", result);
        message.setData(data);
        handler.sendMessage(message);
    }
}
