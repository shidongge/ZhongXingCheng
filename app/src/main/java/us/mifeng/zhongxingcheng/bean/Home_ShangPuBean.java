package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/11/21.
 */

public class Home_ShangPuBean {


    /**
     * success : true
     * msg : 0
     * data : {"msg":0,"shopsInfo":[{"id":"15","shopName":"茅台京驾贡酒","imgTop":""},{"id":"2","shopName":"鄃蜂蜂蜜","imgTop":"userShops/20170814/150271248149.png"},{"id":"5","shopName":"手机哥俱乐部","imgTop":"userShops/20170814/150271249610.png"},{"id":"6","shopName":"本草天香茶花籽油","imgTop":"userShops/20170814/150271251179.png"}]}
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
         * shopsInfo : [{"id":"15","shopName":"茅台京驾贡酒","imgTop":""},{"id":"2","shopName":"鄃蜂蜂蜜","imgTop":"userShops/20170814/150271248149.png"},{"id":"5","shopName":"手机哥俱乐部","imgTop":"userShops/20170814/150271249610.png"},{"id":"6","shopName":"本草天香茶花籽油","imgTop":"userShops/20170814/150271251179.png"}]
         */

        private int msg;
        private List<ShopsInfoBean> shopsInfo;

        public int getMsg() {
            return msg;
        }

        public void setMsg(int msg) {
            this.msg = msg;
        }

        public List<ShopsInfoBean> getShopsInfo() {
            return shopsInfo;
        }

        public void setShopsInfo(List<ShopsInfoBean> shopsInfo) {
            this.shopsInfo = shopsInfo;
        }

        public static class ShopsInfoBean {
            /**
             * id : 15
             * shopName : 茅台京驾贡酒
             * imgTop :
             */

            private String id;
            private String shopName;
            private String imgTop;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getImgTop() {
                return imgTop;
            }

            public void setImgTop(String imgTop) {
                this.imgTop = imgTop;
            }
        }
    }
}
