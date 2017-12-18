package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/12/15.
 */

public class ADBean {

    /**
     * info : 成功
     * status : 0
     * data : [{"title":"活动1","url":"http://192.168.1.120:8082/index/shopSingleDay","img":"http://192.168.1.120:8082/images/shop/index/banner001.png","type":1},{"title":"活动2","url":"http://192.168.1.120:8082/index/shopSingleDay","img":"http://192.168.1.120:8082/images/shop/index/banner001.png","type":1},{"title":"商品1","url":"7","img":"http://47.94.144.186:8080/uploads/userShops/20170814/150271253368.png","type":2},{"title":"商品2","url":"7","img":"http://47.94.144.186:8080/uploads/userShops/20170814/150271253368.png","type":2}]
     * total :
     * page_count : 0
     * page : 0
     */

    private String info;
    private int status;
    private String total;
    private int page_count;
    private int page;
    private List<DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
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
         * title : 活动1
         * url : http://192.168.1.120:8082/index/shopSingleDay
         * img : http://192.168.1.120:8082/images/shop/index/banner001.png
         * type : 1
         */

        private String title;
        private String url;
        private String img;
        private String type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String  getType() {
            return type;
        }

        public void setType(String  type) {
            this.type = type;
        }
    }
}
