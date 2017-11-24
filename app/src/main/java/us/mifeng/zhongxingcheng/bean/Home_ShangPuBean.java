package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/11/21.
 */

public class Home_ShangPuBean {

    /**
     * success : true
     * msg :
     * data : [{"id":"14","imgTop":""},{"id":"2","imgTop":"http://47.94.144.186:8080/uploads/userShops/20170814/150271248149.png"},{"id":"5","imgTop":"http://47.94.144.186:8080/uploads/userShops/20170814/150271249610.png"},{"id":"6","imgTop":"http://47.94.144.186:8080/uploads/userShops/20170814/150271251179.png"}]
     */

    private boolean success;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 14
         * imgTop :
         */

        private String id;
        private String imgTop;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgTop() {
            return imgTop;
        }

        public void setImgTop(String imgTop) {
            this.imgTop = imgTop;
        }
    }
}
