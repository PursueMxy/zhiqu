package com.icarexm.zhiquwang.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BitmapUtils {

    /**
     * 获取网络图片
     *
     * @param imageurl 图片网络地址
     * @return Bitmap 返回位图
     */
    public Bitmap GetImageInputStream(String imageurl) {
        final Bitmap[] bitmaps = {null};
        new Thread(new Runnable() {
            @Override public void run() {
                URL url = null;
                HttpURLConnection connection = null;
                Bitmap bitmap = null;
                    try {
                        url = new URL(imageurl);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setConnectTimeout(6000); //超时设置
                        connection.setDoInput(true);
                        connection.setUseCaches(false); //设置不使用缓存
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                        if (inputStream==null){
                            throw  new RuntimeException("stream is null");
                        }else {
                            try{
                                byte[] data=readStream(inputStream);
                                if (data!=null){
                                    bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            inputStream.close();
                            bitmaps[0] =bitmap;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }).start();
        return bitmaps[0];
    }

    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }
}
