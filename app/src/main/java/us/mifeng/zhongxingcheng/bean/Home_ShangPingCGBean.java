package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/11/22.
 */

public class Home_ShangPingCGBean {
    /**
     * 陈哥商品接口
     */
    /**
     * success : true
     * msg : [{"id":"187","goodsMoney":"335.16","goodsMoney1":"321.20","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416644366.jpg","shortDesc":"赋能珍萃活肤精华露","goodsMoney_old":"335.16"},{"id":"22","goodsMoney":"299.52","goodsMoney1":"287.04","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416644892.jpg","shortDesc":"750ml冷榨油茶籽油（有机）","goodsMoney_old":"299.52"},{"id":"21","goodsMoney":"586.08","goodsMoney1":"561.66","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416644874.jpg","shortDesc":"1.45L冷压榨油茶籽油（有机）","goodsMoney_old":"586.08"},{"id":"20","goodsMoney":"455.04","goodsMoney1":"436.08","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416657391.jpg","shortDesc":"500ml*2压榨一级山茶油礼盒装","goodsMoney_old":"455.04"},{"id":"19","goodsMoney":"228.96","goodsMoney1":"219.42","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416657861.jpg","shortDesc":"750ml压榨一级山茶油","goodsMoney_old":"228.96"},{"id":"18","goodsMoney":"496.80","goodsMoney1":"476.10","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416657823.jpg","shortDesc":"1.45L压榨一级油茶籽油","goodsMoney_old":"496.80"},{"id":"17","goodsMoney":"905.76","goodsMoney1":"868.02","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170904/150449318962.jpg","shortDesc":"5L压榨油 一级茶籽油盒装","goodsMoney_old":"905.76"},{"id":"16","goodsMoney":"96.00","goodsMoney1":"72.00","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416451490.jpg","shortDesc":"槐花蜜 480g ","goodsMoney_old":"96.00"},{"id":"15","goodsMoney":"96.00","goodsMoney1":"72.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416483880.jpg","shortDesc":"枣花蜜 480g ","goodsMoney_old":"96.00"}]
     * data : {"msg":[{"id":"187","goodsMoney":"335.16","goodsMoney1":"321.20","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416644366.jpg","shortDesc":"赋能珍萃活肤精华露","goodsMoney_old":"335.16"},{"id":"22","goodsMoney":"299.52","goodsMoney1":"287.04","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416644892.jpg","shortDesc":"750ml冷榨油茶籽油（有机）","goodsMoney_old":"299.52"},{"id":"21","goodsMoney":"586.08","goodsMoney1":"561.66","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416644874.jpg","shortDesc":"1.45L冷压榨油茶籽油（有机）","goodsMoney_old":"586.08"},{"id":"20","goodsMoney":"455.04","goodsMoney1":"436.08","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416657391.jpg","shortDesc":"500ml*2压榨一级山茶油礼盒装","goodsMoney_old":"455.04"},{"id":"19","goodsMoney":"228.96","goodsMoney1":"219.42","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416657861.jpg","shortDesc":"750ml压榨一级山茶油","goodsMoney_old":"228.96"},{"id":"18","goodsMoney":"496.80","goodsMoney1":"476.10","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416657823.jpg","shortDesc":"1.45L压榨一级油茶籽油","goodsMoney_old":"496.80"},{"id":"17","goodsMoney":"905.76","goodsMoney1":"868.02","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170904/150449318962.jpg","shortDesc":"5L压榨油 一级茶籽油盒装","goodsMoney_old":"905.76"},{"id":"16","goodsMoney":"96.00","goodsMoney1":"72.00","imgCart":"http://47.94.144.186:8080/uploads/shopGoods/20170831/150416451490.jpg","shortDesc":"槐花蜜 480g ","goodsMoney_old":"96.00"},{"id":"15","goodsMoney":"96.00","goodsMoney1":"72.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416483880.jpg","shortDesc":"枣花蜜 480g ","goodsMoney_old":"96.00"}]}
     */

    private boolean success;
    private DataBean data;
    private List<MsgBeanX> msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<MsgBeanX> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBeanX> msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<MsgBean> msg;

        public List<MsgBean> getMsg() {
            return msg;
        }

        public void setMsg(List<MsgBean> msg) {
            this.msg = msg;
        }

        public static class MsgBean {
            /**
             * id : 187
             * goodsMoney : 335.16
             * goodsMoney1 : 321.20
             * imgCart : http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416644366.jpg
             * shortDesc : 赋能珍萃活肤精华露
             * goodsMoney_old : 335.16
             */

            private String id;
            private String goodsMoney;
            private String goodsMoney1;
            private String imgCart;
            private String shortDesc;
            private String goodsMoney_old;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getImgCart() {
                return imgCart;
            }

            public void setImgCart(String imgCart) {
                this.imgCart = imgCart;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public String getGoodsMoney_old() {
                return goodsMoney_old;
            }

            public void setGoodsMoney_old(String goodsMoney_old) {
                this.goodsMoney_old = goodsMoney_old;
            }
        }
    }

    public static class MsgBeanX {
        /**
         * id : 187
         * goodsMoney : 335.16
         * goodsMoney1 : 321.20
         * imgCart : http://47.94.144.186:8080/uploads/goodsThumb/20170831/150416644366.jpg
         * shortDesc : 赋能珍萃活肤精华露
         * goodsMoney_old : 335.16
         */

        private String id;
        private String goodsMoney;
        private String goodsMoney1;
        private String imgCart;
        private String shortDesc;
        private String goodsMoney_old;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getImgCart() {
            return imgCart;
        }

        public void setImgCart(String imgCart) {
            this.imgCart = imgCart;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getGoodsMoney_old() {
            return goodsMoney_old;
        }

        public void setGoodsMoney_old(String goodsMoney_old) {
            this.goodsMoney_old = goodsMoney_old;
        }
    }


}
