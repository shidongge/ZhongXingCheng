package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/12/14.
 */

public class FPGLBean {

    /**
     * info : 成功
     * status : 0
     * data : [{"id":1,"user_id":69484,"oid":0,"title":"哈哈","id_code":"哈哈哈唉","money":"0.00","is_default":0},{"id":2,"user_id":69484,"oid":0,"title":"哈哈","id_code":"哈哈","money":"0.00","is_default":0},{"id":4,"user_id":69484,"oid":0,"title":"123456","id_code":"兴民","money":"0.00","is_default":0}]
     * user_token : 4j8jqin91f26ci8edg811i1df1
     * total : 3
     */

    private String info;
    private int status;
    private String user_token;
    private int total;
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

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
         * user_id : 69484
         * oid : 0
         * title : 哈哈
         * id_code : 哈哈哈唉
         * money : 0.00
         * is_default : 0
         */

        private String id;
        private String user_id;
        private String oid;
        private String title;
        private String id_code;
        private String money;
        private String is_default;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId_code() {
            return id_code;
        }

        public void setId_code(String id_code) {
            this.id_code = id_code;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}
