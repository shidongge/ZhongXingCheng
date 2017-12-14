package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/12/14.
 */

public class GWGGBean {


    /**
     * info : 成功
     * status : 0
     * data : [{"id":1,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":3,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":4,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":5,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":6,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":7,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":8,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":9,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":10,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"},{"id":11,"title":"欢迎登录享投汇，享投汇-中星商城正式上线！","publisher":"杨雅方","read":0,"thumbnail":"","content":"欢迎登录享投汇，享投汇-中星商城正式上线！","url":"http://192.168.1.120:8082/index/shop_index"}]
     * total : 11
     * page_count : 2
     * page : 1
     */

    private String info;
    private String status;
    private String total;
    private String page_count;
    private String page;
    private List<DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage_count() {
        return page_count;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 欢迎登录享投汇，享投汇-中星商城正式上线！
         * publisher : 杨雅方
         * read : 0
         * thumbnail :
         * content : 欢迎登录享投汇，享投汇-中星商城正式上线！
         * url : http://192.168.1.120:8082/index/shop_index
         */

        private String id;
        private String title;
        private String publisher;
        private String read;
        private String thumbnail;
        private String content;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
