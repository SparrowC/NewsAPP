package com.vonnie.mynewsapp.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vonnie on 2016/3/5.
 */
public class ChatInfo {
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"code":100000,"ret_code":0,"text":"我叫图灵机器人，聪明又可爱的图灵机器人"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * code : 100000
     * ret_code : 0
     * text : 我叫图灵机器人，聪明又可爱的图灵机器人
     */

    private ShowapiResBodyEntity showapi_res_body;

    public static ChatInfo objectFromData(String str) {

        return new Gson().fromJson(str, ChatInfo.class);
    }

    public static ChatInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ChatInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ChatInfo> arrayChatDetailsFromData(String str) {

        Type listType = new TypeToken<ArrayList<ChatInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ChatInfo> arrayChatDetailsFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ChatInfo>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public static class ShowapiResBodyEntity {
        private int code;
        private int ret_code;
        private String text;

        public static ShowapiResBodyEntity objectFromData(String str) {

            return new Gson().fromJson(str, ShowapiResBodyEntity.class);
        }

        public static ShowapiResBodyEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ShowapiResBodyEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ShowapiResBodyEntity> arrayShowapiResBodyEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<ShowapiResBodyEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ShowapiResBodyEntity> arrayShowapiResBodyEntityFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ShowapiResBodyEntity>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public int getRet_code() {
            return ret_code;
        }

        public String getText() {
            return text;
        }
    }
}
