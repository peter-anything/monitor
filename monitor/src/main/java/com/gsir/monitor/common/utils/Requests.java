package com.gsir.monitor.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Requests {
    public static String post(String url, Map<String, String> mapParams, Map<String, String> headers) {
        return request(url, mapParams, "POST", headers);
    }

    public static String get(String url, Map<String, String> mapParams, Map<String, String> headers) {
        return request(url, mapParams, "GET", headers);
    }
    
    public static Object getJson(String url, Map<String, String> mapParams, Map<String, String> headers) {
        String result = get(url, mapParams, headers);
        try {
            JSONArray jsonObj = JSONObject.parseArray(result);
            return jsonObj;
        } catch (Exception e) {
            JSONObject jsonObj = JSONObject.parseObject(result);
            return jsonObj;
        }
    }

    private static String request(String url, Map<String, String> mapParams, String requestMethod, Map<String, String> headers) {
        StringBuffer resultStrBuffer = new StringBuffer();
        BufferedReader in = null;
        StringBuffer stringBuffer = new StringBuffer();
        String params = "";
        try {
            String contactURL = "";
            if (mapParams == null) {
                contactURL = url;
            } else {
                if (mapParams.size() == 1) {
                    for (Map.Entry<String, String> entry : mapParams.entrySet()) {
                        stringBuffer.append(entry.getKey()).append("=").append(entry.getValue() == null ? null
                                : java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));
                        params = stringBuffer.toString();
                    }
                } else {
                    for (Map.Entry<String, String> entry : mapParams.entrySet()) {
                        stringBuffer.append(entry.getKey()).append("=").append(entry.getValue() == null ? null
                                : java.net.URLEncoder.encode(entry.getValue(), "UTF-8")).append("&"); // 对参数进行编码格式化以及拼接
                    }
                    params = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
                }
                contactURL = url + "?" + params;
            }

            // 创建URL对象
            java.net.URL connURL = new java.net.URL(contactURL);
            // 打开url连接
            java.net.HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            httpConn.setRequestProperty("Accept-Charset", "utf-8");
            httpConn.setRequestProperty("connection", "keep-alive");
            httpConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

//            if (headers != null && headers.size() > 0) {
//                for (Map.Entry<String, String> entry : headers.entrySet()) {
//                    httpConn.setRequestProperty(entry.getKey(), entry.getValue());
//                }
//            }
            httpConn.setRequestMethod(requestMethod);
            httpConn.setDoOutput(false);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(true);
            httpConn.setConnectTimeout(30000);
            httpConn.setReadTimeout(30000);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.connect();

            int code = httpConn.getResponseCode();
            if (code == 200) {
                in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    resultStrBuffer.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return resultStrBuffer.toString();
    }

}
