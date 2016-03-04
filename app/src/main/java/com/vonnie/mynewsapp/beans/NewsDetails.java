package com.vonnie.mynewsapp.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vonnie on 2016/2/27.
 */
public class NewsDetails {
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"pagebean":{"allNum":2,"allPages":1,"contentlist":[{"channelId":"5572a108b3cdc86cf39001cd","channelName":"国内焦点","desc":"参与互动2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，即将投入使用。中新社记者张浩摄2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，...","imageurls":[],"link":"http://www.chinanews.com/gn/2016/02-27/7775690.shtml","pubDate":"2016-02-27 09:10:43","source":"中国新闻网","title":"2016年中国两会新闻中心正式启用"},{"channelId":"5572a108b3cdc86cf39001cd","channelName":"国内焦点","desc":"　　原标题：中国出台规定政府网站等应在显著位置宣传展示公益广告　　新华社北京2月25日电为规范公益广告管理，扩大公益广告影响力，中国国家工商总局25日发布《公益广告促进和管理暂行办法》，对媒体单位等发布公益广告作出规定。　　办法规定，政府网站、新闻网站、经营性网....","imageurls":[],"link":"http://news.sina.com.cn/c/nd/2016-02-26/doc-ifxpvzah8148499.shtml","pubDate":"2016-02-26 08:48:42","source":"新浪","title":"中国规定新闻网站需在显著位置展示公益广告"}],"currentPage":1,"maxResult":20},"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * pagebean : {"allNum":2,"allPages":1,"contentlist":[{"channelId":"5572a108b3cdc86cf39001cd","channelName":"国内焦点","desc":"参与互动2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，即将投入使用。中新社记者张浩摄2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，...","imageurls":[],"link":"http://www.chinanews.com/gn/2016/02-27/7775690.shtml","pubDate":"2016-02-27 09:10:43","source":"中国新闻网","title":"2016年中国两会新闻中心正式启用"},{"channelId":"5572a108b3cdc86cf39001cd","channelName":"国内焦点","desc":"　　原标题：中国出台规定政府网站等应在显著位置宣传展示公益广告　　新华社北京2月25日电为规范公益广告管理，扩大公益广告影响力，中国国家工商总局25日发布《公益广告促进和管理暂行办法》，对媒体单位等发布公益广告作出规定。　　办法规定，政府网站、新闻网站、经营性网....","imageurls":[],"link":"http://news.sina.com.cn/c/nd/2016-02-26/doc-ifxpvzah8148499.shtml","pubDate":"2016-02-26 08:48:42","source":"新浪","title":"中国规定新闻网站需在显著位置展示公益广告"}],"currentPage":1,"maxResult":20}
     * ret_code : 0
     */

    private ShowapiResBodyEntity showapi_res_body;

    public static NewsDetails objectFromData(String str) {

        return new Gson().fromJson(str, NewsDetails.class);
    }

    public static NewsDetails objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), NewsDetails.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<NewsDetails> arrayNewsDetailsFromData(String str) {

        Type listType = new TypeToken<ArrayList<NewsDetails>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<NewsDetails> arrayNewsDetailsFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<NewsDetails>>() {
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
        /**
         * allNum : 2
         * allPages : 1
         * contentlist : [{"channelId":"5572a108b3cdc86cf39001cd","channelName":"国内焦点","desc":"参与互动2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，即将投入使用。中新社记者张浩摄2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，...","imageurls":[],"link":"http://www.chinanews.com/gn/2016/02-27/7775690.shtml","pubDate":"2016-02-27 09:10:43","source":"中国新闻网","title":"2016年中国两会新闻中心正式启用"},{"channelId":"5572a108b3cdc86cf39001cd","channelName":"国内焦点","desc":"　　原标题：中国出台规定政府网站等应在显著位置宣传展示公益广告　　新华社北京2月25日电为规范公益广告管理，扩大公益广告影响力，中国国家工商总局25日发布《公益广告促进和管理暂行办法》，对媒体单位等发布公益广告作出规定。　　办法规定，政府网站、新闻网站、经营性网....","imageurls":[],"link":"http://news.sina.com.cn/c/nd/2016-02-26/doc-ifxpvzah8148499.shtml","pubDate":"2016-02-26 08:48:42","source":"新浪","title":"中国规定新闻网站需在显著位置展示公益广告"}]
         * currentPage : 1
         * maxResult : 20
         */

        private PagebeanEntity pagebean;
        private int ret_code;

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

        public void setPagebean(PagebeanEntity pagebean) {
            this.pagebean = pagebean;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanEntity getPagebean() {
            return pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public static class PagebeanEntity {
            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;
            /**
             * channelId : 5572a108b3cdc86cf39001cd
             * channelName : 国内焦点
             * desc : 参与互动2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，即将投入使用。中新社记者张浩摄2月27日，北京梅地亚中心，第十二届全国人民代表大会第四次会议和政协第十二届全国委员会第四次会议的新闻中心在此设立，...
             * imageurls : []
             * link : http://www.chinanews.com/gn/2016/02-27/7775690.shtml
             * pubDate : 2016-02-27 09:10:43
             * source : 中国新闻网
             * title : 2016年中国两会新闻中心正式启用
             */

            private List<ContentlistEntity> contentlist;

            public static PagebeanEntity objectFromData(String str) {

                return new Gson().fromJson(str, PagebeanEntity.class);
            }

            public static PagebeanEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), PagebeanEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<PagebeanEntity> arrayPagebeanEntityFromData(String str) {

                Type listType = new TypeToken<ArrayList<PagebeanEntity>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<PagebeanEntity> arrayPagebeanEntityFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<PagebeanEntity>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public void setContentlist(List<ContentlistEntity> contentlist) {
                this.contentlist = contentlist;
            }

            public int getAllNum() {
                return allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public List<ContentlistEntity> getContentlist() {
                return contentlist;
            }

            public static class ContentlistEntity {
                private String channelId;
                private String channelName;
                private String desc;
                private String link;
                private String pubDate;
                private String source;
                private String title;
                private List<?> imageurls;

                public static ContentlistEntity objectFromData(String str) {

                    return new Gson().fromJson(str, ContentlistEntity.class);
                }

                public static ContentlistEntity objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ContentlistEntity.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ContentlistEntity> arrayContentlistEntityFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ContentlistEntity>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ContentlistEntity> arrayContentlistEntityFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ContentlistEntity>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public void setChannelId(String channelId) {
                    this.channelId = channelId;
                }

                public void setChannelName(String channelName) {
                    this.channelName = channelName;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setPubDate(String pubDate) {
                    this.pubDate = pubDate;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setImageurls(List<?> imageurls) {
                    this.imageurls = imageurls;
                }

                public String getChannelId() {
                    return channelId;
                }

                public String getChannelName() {
                    return channelName;
                }

                public String getDesc() {
                    return desc;
                }

                public String getLink() {
                    return link;
                }

                public String getPubDate() {
                    return pubDate;
                }

                public String getSource() {
                    return source;
                }

                public String getTitle() {
                    return title;
                }

                public List<?> getImageurls() {
                    return imageurls;
                }
            }
        }
    }
}
