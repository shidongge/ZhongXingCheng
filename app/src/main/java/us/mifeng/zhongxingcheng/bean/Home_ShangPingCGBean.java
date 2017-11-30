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
     * msg : 0
     * data : {"msg":0,"goodsInfo":[{"id":"194","goodsMoney":"454.00","goodsMoney1":"2221.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171122/151134969435.png","shortDesc":"","goodsMoney_old":"454.00"},{"id":"198","goodsMoney":"123.00","goodsMoney1":"123.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171123/151141607776.png","shortDesc":"244422","goodsMoney_old":"123.00"},{"id":"197","goodsMoney":"455.00","goodsMoney1":"444.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171123/151140531683.png","shortDesc":"35432","goodsMoney_old":"455.00"},{"id":"214","goodsMoney":"540.00","goodsMoney1":"517.50","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171129/151192610699.png","shortDesc":"点点滴滴","goodsMoney_old":"540.00"},{"id":"202","goodsMoney":"22.00","goodsMoney1":"22.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171127/151174978562.png","shortDesc":"对对对","goodsMoney_old":"22.00"},{"id":"199","goodsMoney":"0.00","goodsMoney1":"0.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171129/151192577049.png","shortDesc":"好好","goodsMoney_old":"0.00"},{"id":"196","goodsMoney":"24.00","goodsMoney1":"22.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171122/151135041143.png","shortDesc":"","goodsMoney_old":"24.00"},{"id":"201","goodsMoney":"1.00","goodsMoney1":"3.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171124/151151644317.png","shortDesc":"柔柔弱弱若","goodsMoney_old":"1.00"},{"id":"195","goodsMoney":"12.00","goodsMoney1":"33.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171122/151135033124.png","shortDesc":"","goodsMoney_old":"12.00"}]}
     */

    private boolean success;
    private int msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 0
         * goodsInfo : [{"id":"194","goodsMoney":"454.00","goodsMoney1":"2221.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171122/151134969435.png","shortDesc":"","goodsMoney_old":"454.00"},{"id":"198","goodsMoney":"123.00","goodsMoney1":"123.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171123/151141607776.png","shortDesc":"244422","goodsMoney_old":"123.00"},{"id":"197","goodsMoney":"455.00","goodsMoney1":"444.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171123/151140531683.png","shortDesc":"35432","goodsMoney_old":"455.00"},{"id":"214","goodsMoney":"540.00","goodsMoney1":"517.50","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171129/151192610699.png","shortDesc":"点点滴滴","goodsMoney_old":"540.00"},{"id":"202","goodsMoney":"22.00","goodsMoney1":"22.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171127/151174978562.png","shortDesc":"对对对","goodsMoney_old":"22.00"},{"id":"199","goodsMoney":"0.00","goodsMoney1":"0.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171129/151192577049.png","shortDesc":"好好","goodsMoney_old":"0.00"},{"id":"196","goodsMoney":"24.00","goodsMoney1":"22.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171122/151135041143.png","shortDesc":"","goodsMoney_old":"24.00"},{"id":"201","goodsMoney":"1.00","goodsMoney1":"3.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171124/151151644317.png","shortDesc":"柔柔弱弱若","goodsMoney_old":"1.00"},{"id":"195","goodsMoney":"12.00","goodsMoney1":"33.00","imgCart":"http://47.94.144.186:8080/uploads/goodsThumb/20171122/151135033124.png","shortDesc":"","goodsMoney_old":"12.00"}]
         */

        private int msg;
        private List<GoodsInfoBean> goodsInfo;

        public int getMsg() {
            return msg;
        }

        public void setMsg(int msg) {
            this.msg = msg;
        }

        public List<GoodsInfoBean> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public static class GoodsInfoBean {
            /**
             * id : 194
             * goodsMoney : 454.00
             * goodsMoney1 : 2221.00
             * imgCart : http://47.94.144.186:8080/uploads/goodsThumb/20171122/151134969435.png
             * shortDesc :
             * goodsMoney_old : 454.00
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



}
