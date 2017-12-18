package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/12/18.
 */

public class RXPHBean {

    /**
     * info : 成功
     * status : 0
     * data : [{"id":33,"shopId":7,"goodsName":"梧桐堡·雷恩干红葡萄酒2009 法国原瓶进口 75CL","shortDesc":"雷恩干红葡萄酒2009 法国原瓶进口 75CL","retailPrice":"246.00","goodsMoney":"295.20","goodsMoney1":"282.90","sellCount":4,"imgCart":284,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170814/150269079999.jpg"},{"id":41,"shopId":7,"goodsName":"梧桐堡·杜佩干红葡萄酒2007 法国原瓶进口 75CL","shortDesc":"杜佩干红葡萄酒2007 法国原瓶进口 75CL","retailPrice":"393.60","goodsMoney":"472.32","goodsMoney1":"452.64","sellCount":2,"imgCart":281,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170814/150269077140.jpg"},{"id":69,"shopId":9,"goodsName":"甘肃特产陇上农庄 食用油5L包邮 生榨亚麻籽油家庭食用胡麻油5升","shortDesc":"甘肃特产 胡麻油","retailPrice":"0.00","goodsMoney":"237.60","goodsMoney1":"226.80","sellCount":2,"imgCart":408,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170814/150269920547.jpg"},{"id":135,"shopId":12,"goodsName":"【椹都】古桑霜桑叶茶-散装-古桑茶叶","shortDesc":"古桑霜桑叶茶-散装-古桑茶叶","retailPrice":"0.00","goodsMoney":"105.60","goodsMoney1":"94.80","sellCount":0,"imgCart":598,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170821/150328565492.jpg"},{"id":136,"shopId":12,"goodsName":"【椹都】霜叶茶-茶礼","shortDesc":"霜叶茶-茶礼","retailPrice":"0.00","goodsMoney":"117.60","goodsMoney1":"106.80","sellCount":0,"imgCart":601,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170821/150328576979.jpg"},{"id":134,"shopId":12,"goodsName":"【椹都】精品桑黄切片-名贵礼品-礼盒装","shortDesc":"精品桑黄切片-名贵礼品-礼盒装","retailPrice":"0.00","goodsMoney":"463.20","goodsMoney1":"439.20","sellCount":0,"imgCart":595,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170821/150328499734.jpg"},{"id":133,"shopId":12,"goodsName":"【椹都】精品古桑霜桑叶茶-名师手工制茶-豪华礼盒装","shortDesc":"精品古桑霜桑叶茶-名师手工制茶-豪华礼盒装","retailPrice":"0.00","goodsMoney":"177.60","goodsMoney1":"153.60","sellCount":0,"imgCart":592,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170821/150328485590.jpg"},{"id":137,"shopId":8,"goodsName":"红酒原瓶进口澳洲瑞森银袋鼠(黄标)干白葡萄酒 750ML 1瓶","shortDesc":"进口澳洲瑞森银袋鼠(黄标)干白葡萄酒 750ML 1瓶","retailPrice":"0.00","goodsMoney":"189.60","goodsMoney1":"177.60","sellCount":0,"imgCart":604,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170824/150354369770.jpg"},{"id":138,"shopId":15,"goodsName":"康满乐-奔科空气净化器-负离子空气净化器","shortDesc":"空气净化器-负离子空气净化器","retailPrice":"9000.00","goodsMoney":"10800.00","goodsMoney1":"10350.00","sellCount":0,"imgCart":609,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170824/150354785113.jpg"},{"id":139,"shopId":15,"goodsName":"康满乐-欧卡姿青春定格液-oucoz 120ml 缩紧毛孔-美肤精华","shortDesc":"欧卡姿青春定格液-120ml 缩紧毛孔-美肤精华","retailPrice":"720.00","goodsMoney":"864.00","goodsMoney1":"828.00","sellCount":0,"imgCart":612,"freight":0,"imgUrl":"http://47.94.144.186:8080/uploads/goodsThumb/20170824/150354814917.jpg"}]
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
         * id : 33
         * shopId : 7
         * goodsName : 梧桐堡·雷恩干红葡萄酒2009 法国原瓶进口 75CL
         * shortDesc : 雷恩干红葡萄酒2009 法国原瓶进口 75CL
         * retailPrice : 246.00
         * goodsMoney : 295.20
         * goodsMoney1 : 282.90
         * sellCount : 4
         * imgCart : 284
         * freight : 0
         * imgUrl : http://47.94.144.186:8080/uploads/goodsThumb/20170814/150269079999.jpg
         */

        private String id;
        private String shopId;
        private String goodsName;
        private String shortDesc;
        private String retailPrice;
        private String goodsMoney;
        private String goodsMoney1;
        private String sellCount;
        private String imgCart;
        private String freight;
        private String imgUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(String retailPrice) {
            this.retailPrice = retailPrice;
        }

        public String getGoodsMoney() {
            return goodsMoney;
        }

        public void setGoodsMoney(String goodsMoney) {
            this.goodsMoney = goodsMoney;
        }

        public String getGoodsMoney1() {
            return goodsMoney1;
        }

        public void setGoodsMoney1(String goodsMoney1) {
            this.goodsMoney1 = goodsMoney1;
        }

        public String getSellCount() {
            return sellCount;
        }

        public void setSellCount(String sellCount) {
            this.sellCount = sellCount;
        }

        public String getImgCart() {
            return imgCart;
        }

        public void setImgCart(String imgCart) {
            this.imgCart = imgCart;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
