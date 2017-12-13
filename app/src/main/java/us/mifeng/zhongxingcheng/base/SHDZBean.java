package us.mifeng.zhongxingcheng.base;

import java.util.List;

/**
 * Created by shido on 2017/12/13.
 */

public class SHDZBean {


    /**
     * info : 成功
     * status : 0
     * data : [{"id":5600,"userId":2091,"userName":"刘晓东","mobile":"15210732085","province":"北京市","city":"石景山区","district":"","address":"八角南路社区4号楼","zip_code":"","isDefault":1},{"id":8855,"userId":2091,"userName":"给我红","mobile":"18801105410","province":"北京市","city":"北京市","district":"东城区","address":"给我说一声呀","zip_code":"494949","isDefault":0}]
     * user_token : enbqkluk7gh8cgbm8snsjr30h0
     * total :
     */

    private String info;
    private int status;
    private String user_token;
    private String total;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
         * id : 5600
         * userId : 2091
         * userName : 刘晓东
         * mobile : 15210732085
         * province : 北京市
         * city : 石景山区
         * district :
         * address : 八角南路社区4号楼
         * zip_code :
         * isDefault : 1
         */

        private String id;
        private String userId;
        private String userName;
        private String mobile;
        private String province;
        private String city;
        private String district;
        private String address;
        private String zip_code;
        private String isDefault;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }
    }
}
