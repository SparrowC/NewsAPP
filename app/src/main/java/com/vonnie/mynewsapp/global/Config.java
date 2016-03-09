package com.vonnie.mynewsapp.global;

/**
 * Created by Vonnie on 2016/2/26.
 */
public class Config {

    public static String timestamp;
    final public static String newsUrl="https://route.showapi.com/109-35?channelId=5572a108b3cdc86cf39001cd&channelName=郭磊焦点&needContent=0&needHtml=0&page=1&showapi_appid=2992&showapi_timestamp=20160226192956&title=新闻&showapi_sign=ccd3b9a741a94c8d9cf1e72332d311ca";

    final public static String newsDomainUrl="https://route.showapi.com/109-35?";
    final public static String channelId="channelId=";
    final public static String channelName="&channelName=";
    public static String otherConstantUrl="&needContent=0&needHtml=0&page=1&showapi_appid=2992&showapi_timestamp="+timestamp+"&title=新闻&showapi_sign=ccd3b9a741a94c8d9cf1e72332d311ca";

    public static String newsChannelUrl="https://route.showapi.com/109-34?showapi_appid=2992&showapi_timestamp="+timestamp+"&showapi_sign=ccd3b9a741a94c8d9cf1e72332d311ca";

    public static String myNewsURL="http://121.42.208.111/MyNews/";
<<<<<<< HEAD

    public static String getChannelNewsUrl(String ID, String name, String time) {
        return "https://route.showapi.com/109-35?channelId=" + ID + "&channelName=" + name + "&needContent=0&needHtml=0&page=1&showapi_appid=16363&showapi_timestamp=" + time + "&title=%E6%96%B0%E9%97%BB&showapi_sign=56f27f8086be4e989a25b7d231a1287b";

    }

    public static String getChatRobortUrl(String info, String time) {
        return "https://route.showapi.com/60-27?info=" + info + "&showapi_appid=2992&showapi_timestamp=" + time + "&userid=userid&showapi_sign=ccd3b9a741a94c8d9cf1e72332d311ca";
    }
=======
>>>>>>> origin/master
}
