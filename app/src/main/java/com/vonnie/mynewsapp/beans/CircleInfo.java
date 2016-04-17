package com.vonnie.mynewsapp.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vonnie on 2016/4/17.
 */
public class CircleInfo {
    /**
     * user : admin
     * news : 第一条测试消息
     */

    private List<CircleEntity> circle;

    public static CircleInfo objectFromData(String str) {

        return new Gson().fromJson(str, CircleInfo.class);
    }

    public static CircleInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CircleInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CircleInfo> arrayCircleInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<CircleInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CircleInfo> arrayCircleInfoFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CircleInfo>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setCircle(List<CircleEntity> circle) {
        this.circle = circle;
    }

    public List<CircleEntity> getCircle() {
        return circle;
    }

    public static class CircleEntity {
        private String user;
        private String news;

        public static CircleEntity objectFromData(String str) {

            return new Gson().fromJson(str, CircleEntity.class);
        }

        public static CircleEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), CircleEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<CircleEntity> arrayCircleEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<CircleEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<CircleEntity> arrayCircleEntityFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<CircleEntity>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getUser() {
            return user;
        }

        public String getNews() {
            return news;
        }
    }
}
