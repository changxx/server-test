package com.changxx.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.changxx.util.RequestUtil;

public class Request {

    private String              requestType;
    private String              requestUrl;
    private InputStream         inputStream;
    @SuppressWarnings("unused")
    private InetAddress         inetAddress;
    @SuppressWarnings("unused")
    private Map<String, Object> paramters;

    public Request(InputStream inputStream, InetAddress inetAddress) {
        this.inputStream = inputStream;
        this.inetAddress = inetAddress;
        this.paramters = new HashMap<String, Object>();
        parseRequest();
    }

    private void parseRequest() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String msg;
        try {
            msg = bufferedReader.readLine();
            while (msg == null) {
                return;
            }
            // 获得请求类型
            String requestType = RequestUtil.getRequestType(msg);
            //if (requestType.equals("GET")) {
                this.requestType = "GET";
            //} else {
            //    this.requestType = "POST";
            //}

            // 设置请求的资源
            this.requestUrl = RequestUtil.getRequestFileName(msg);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
