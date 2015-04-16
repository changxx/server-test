package com.changxx.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.changxx.domain.Request;
import com.changxx.domain.Response;
import com.changxx.util.Constant;

public class HandlerRunnable implements Runnable {

    private Request  request;
    private Response response;

    public HandlerRunnable(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void run() {
        try {
            requestHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestHandler() throws IOException {

        String requestType = request.getRequestType();
        if (requestType.equals("GET")) {
            String fileName = request.getRequestUrl();
            String filePath = Constant.WEB_ROOT + fileName;
            System.out.println(filePath);

            // GET the OutputSteam object
            OutputStream outputStream = response.getOutputStream();
            // 进行文件的读取
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bufferByte = new byte[1024];
                int read = fileInputStream.read(bufferByte);
                while (read != -1) {
                    outputStream.write(bufferByte, 0, read);
                    read = fileInputStream.read(bufferByte, 0, 1024);
                }
                fileInputStream.close();
            } else {
                outputStream.write("404!!  can not find the file".getBytes());
            }

            outputStream.flush();
            outputStream.close();
            System.out.println("over!!");
        }

    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
