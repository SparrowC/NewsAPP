package com.vonnie.mynewsapp.utils;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Vonnie on 2016/2/26.
 */
public class NetUtils {

    /**
     *
     * @param url
     * @return if null,获取数据失败
     */
    public static String LoadJsonDataFromServer(String url)
    {
        String result="";
        try {
            URL mUrl=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) mUrl.openConnection();

            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod("GET");
            connection.connect();

            int resultCode=connection.getResponseCode();
            if(resultCode==200)
            {
                InputStream is=connection.getInputStream();


                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String buffer=new String();

                while ((buffer=br.readLine())!=null)
                {
                    result+=buffer;
                }
                br.close();
                is.close();
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }
    /**
     *
     * @param url
     * @return if null,获取数据失败
     */
    public static String PostToServer(String url)
    {
        String result="";
        try {
            URL mUrl=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) mUrl.openConnection();

            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod("POST");
            connection.connect();

            int resultCode=connection.getResponseCode();
            if(resultCode==200)
            {
                InputStream is=connection.getInputStream();


                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String buffer=new String();

                while ((buffer=br.readLine())!=null)
                {
                    result+=buffer;
                }
                br.close();
                is.close();
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }
    public static void SyncLoad(final Activity activity, final String url, final NetWork netWork){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String result=LoadJsonDataFromServer(url);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(netWork!=null){
                            if(result.equals(""))
                            {
                                netWork.onFailed(result);
                            }else {
                                netWork.onSuccess(result);
                            }
                        }
                    }
                });
            }
        }).start();
    }
    public static void SyncPost(final Activity activity, final String url, final NetWork netWork){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String result=PostToServer(url);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(netWork!=null){
                            if(result.equals(""))
                            {
                                netWork.onFailed(result);
                            }else {
                                netWork.onSuccess(result);
                            }
                        }
                    }
                });
            }
        }).start();
    }
    public interface NetWork{
        void onSuccess(String result);
        void onFailed(String result);
    }
}
