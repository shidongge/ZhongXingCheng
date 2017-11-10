package us.mifeng.zhongxingcheng.bean;

/**
 * Created by shido on 2017/11/7.
 */

public class WoDeBean {

    /**
     * success : true
     * msg : {"id":"2091","type":"0","openid":"9nbhn5mn3o7lj1g04ef1edc7vhfo8i1o","bindOpenid":"o7AJdwwMWefmtjDlznuvMDDvUGcA","firstType":"","balance":"2100.23","frozen":"-1000.23","available":"3100.46","shareholder":"0.00","cashMoney":"0.00","fictitiousBean":"0","shopBalance":"0.03","shopFrozen":"0.00","shopAvailable":"0.00","shopBindMoney":"0.03","userExp":"150","regTime":"2017-09-21 12:48:10","realName":"刘晓东","realStatus":"1","realFailReason":"55","mobile":"15210732085","pwd":"14e1b600b1fd579f47433b88e8d85291","payPwd":"acf56f1c329fd863212c81a5b19e4f7b","age":"23","birthDate":"2017-08-14","identityCard":"411522199406267236","identityFace":"55","identityBack":"55","gender":"1","nickName":"小东","province":"北京市","city":"北京市市辖区","job":"程序狗","income":"5000以下","hobby":"a\u2006a\u2006a","portrait":"portrait/20171106/150996872175.png","joinTime":null,"status":"2","loginStatus":"1","moneyStatus":"1","cashStatus":"1","vipLevel":"0","associatorLevel":"0","invitationCode":"msg5x","invitationId":"1960","groupBuild":"1","groupBuild2":"0","myTeam":"0","invitation":"2","validInvitation":"0","lastLoginTime":"2017-10-17 18:06:32","lastSignTime":"2017-11-06","lastSignNum":"1","signSeriesReward":"0","guideStatus":"1","vipUpgrade":"0"}
     * data : {"msg":{"id":"2091","type":"0","openid":"9nbhn5mn3o7lj1g04ef1edc7vhfo8i1o","bindOpenid":"o7AJdwwMWefmtjDlznuvMDDvUGcA","firstType":"","balance":"2100.23","frozen":"-1000.23","available":"3100.46","shareholder":"0.00","cashMoney":"0.00","fictitiousBean":"0","shopBalance":"0.03","shopFrozen":"0.00","shopAvailable":"0.00","shopBindMoney":"0.03","userExp":"150","regTime":"2017-09-21 12:48:10","realName":"刘晓东","realStatus":"1","realFailReason":"55","mobile":"15210732085","pwd":"14e1b600b1fd579f47433b88e8d85291","payPwd":"acf56f1c329fd863212c81a5b19e4f7b","age":"23","birthDate":"2017-08-14","identityCard":"411522199406267236","identityFace":"55","identityBack":"55","gender":"1","nickName":"小东","province":"北京市","city":"北京市市辖区","job":"程序狗","income":"5000以下","hobby":"a\u2006a\u2006a","portrait":"portrait/20171106/150996872175.png","joinTime":null,"status":"2","loginStatus":"1","moneyStatus":"1","cashStatus":"1","vipLevel":"0","associatorLevel":"0","invitationCode":"msg5x","invitationId":"1960","groupBuild":"1","groupBuild2":"0","myTeam":"0","invitation":"2","validInvitation":"0","lastLoginTime":"2017-10-17 18:06:32","lastSignTime":"2017-11-06","lastSignNum":"1","signSeriesReward":"0","guideStatus":"1","vipUpgrade":"0"}}
     */

    private boolean success;
    private MsgBean msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MsgBean {
        /**
         * id : 2091
         * type : 0
         * openid : 9nbhn5mn3o7lj1g04ef1edc7vhfo8i1o
         * bindOpenid : o7AJdwwMWefmtjDlznuvMDDvUGcA
         * firstType :
         * balance : 2100.23
         * frozen : -1000.23
         * available : 3100.46
         * shareholder : 0.00
         * cashMoney : 0.00
         * fictitiousBean : 0
         * shopBalance : 0.03
         * shopFrozen : 0.00
         * shopAvailable : 0.00
         * shopBindMoney : 0.03
         * userExp : 150
         * regTime : 2017-09-21 12:48:10
         * realName : 刘晓东
         * realStatus : 1
         * realFailReason : 55
         * mobile : 15210732085
         * pwd : 14e1b600b1fd579f47433b88e8d85291
         * payPwd : acf56f1c329fd863212c81a5b19e4f7b
         * age : 23
         * birthDate : 2017-08-14
         * identityCard : 411522199406267236
         * identityFace : 55
         * identityBack : 55
         * gender : 1
         * nickName : 小东
         * province : 北京市
         * city : 北京市市辖区
         * job : 程序狗
         * income : 5000以下
         * hobby : a a a
         * portrait : portrait/20171106/150996872175.png
         * joinTime : null
         * status : 2
         * loginStatus : 1
         * moneyStatus : 1
         * cashStatus : 1
         * vipLevel : 0
         * associatorLevel : 0
         * invitationCode : msg5x
         * invitationId : 1960
         * groupBuild : 1
         * groupBuild2 : 0
         * myTeam : 0
         * invitation : 2
         * validInvitation : 0
         * lastLoginTime : 2017-10-17 18:06:32
         * lastSignTime : 2017-11-06
         * lastSignNum : 1
         * signSeriesReward : 0
         * guideStatus : 1
         * vipUpgrade : 0
         */

        private String id;
        private String type;
        private String openid;
        private String bindOpenid;
        private String firstType;
        private String balance;
        private String frozen;
        private String available;
        private String shareholder;
        private String cashMoney;
        private String fictitiousBean;
        private String shopBalance;
        private String shopFrozen;
        private String shopAvailable;
        private String shopBindMoney;
        private String userExp;
        private String regTime;
        private String realName;
        private String realStatus;
        private String realFailReason;
        private String mobile;
        private String pwd;
        private String payPwd;
        private String age;
        private String birthDate;
        private String identityCard;
        private String identityFace;
        private String identityBack;
        private String gender;
        private String nickName;
        private String province;
        private String city;
        private String job;
        private String income;
        private String hobby;
        private String portrait;
        private Object joinTime;
        private String status;
        private String loginStatus;
        private String moneyStatus;
        private String cashStatus;
        private String vipLevel;
        private String associatorLevel;
        private String invitationCode;
        private String invitationId;
        private String groupBuild;
        private String groupBuild2;
        private String myTeam;
        private String invitation;
        private String validInvitation;
        private String lastLoginTime;
        private String lastSignTime;
        private String lastSignNum;
        private String signSeriesReward;
        private String guideStatus;
        private String vipUpgrade;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getBindOpenid() {
            return bindOpenid;
        }

        public void setBindOpenid(String bindOpenid) {
            this.bindOpenid = bindOpenid;
        }

        public String getFirstType() {
            return firstType;
        }

        public void setFirstType(String firstType) {
            this.firstType = firstType;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFrozen() {
            return frozen;
        }

        public void setFrozen(String frozen) {
            this.frozen = frozen;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public String getShareholder() {
            return shareholder;
        }

        public void setShareholder(String shareholder) {
            this.shareholder = shareholder;
        }

        public String getCashMoney() {
            return cashMoney;
        }

        public void setCashMoney(String cashMoney) {
            this.cashMoney = cashMoney;
        }

        public String getFictitiousBean() {
            return fictitiousBean;
        }

        public void setFictitiousBean(String fictitiousBean) {
            this.fictitiousBean = fictitiousBean;
        }

        public String getShopBalance() {
            return shopBalance;
        }

        public void setShopBalance(String shopBalance) {
            this.shopBalance = shopBalance;
        }

        public String getShopFrozen() {
            return shopFrozen;
        }

        public void setShopFrozen(String shopFrozen) {
            this.shopFrozen = shopFrozen;
        }

        public String getShopAvailable() {
            return shopAvailable;
        }

        public void setShopAvailable(String shopAvailable) {
            this.shopAvailable = shopAvailable;
        }

        public String getShopBindMoney() {
            return shopBindMoney;
        }

        public void setShopBindMoney(String shopBindMoney) {
            this.shopBindMoney = shopBindMoney;
        }

        public String getUserExp() {
            return userExp;
        }

        public void setUserExp(String userExp) {
            this.userExp = userExp;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealStatus() {
            return realStatus;
        }

        public void setRealStatus(String realStatus) {
            this.realStatus = realStatus;
        }

        public String getRealFailReason() {
            return realFailReason;
        }

        public void setRealFailReason(String realFailReason) {
            this.realFailReason = realFailReason;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getPayPwd() {
            return payPwd;
        }

        public void setPayPwd(String payPwd) {
            this.payPwd = payPwd;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public String getIdentityFace() {
            return identityFace;
        }

        public void setIdentityFace(String identityFace) {
            this.identityFace = identityFace;
        }

        public String getIdentityBack() {
            return identityBack;
        }

        public void setIdentityBack(String identityBack) {
            this.identityBack = identityBack;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public Object getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(Object joinTime) {
            this.joinTime = joinTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getMoneyStatus() {
            return moneyStatus;
        }

        public void setMoneyStatus(String moneyStatus) {
            this.moneyStatus = moneyStatus;
        }

        public String getCashStatus() {
            return cashStatus;
        }

        public void setCashStatus(String cashStatus) {
            this.cashStatus = cashStatus;
        }

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }

        public String getAssociatorLevel() {
            return associatorLevel;
        }

        public void setAssociatorLevel(String associatorLevel) {
            this.associatorLevel = associatorLevel;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public String getInvitationId() {
            return invitationId;
        }

        public void setInvitationId(String invitationId) {
            this.invitationId = invitationId;
        }

        public String getGroupBuild() {
            return groupBuild;
        }

        public void setGroupBuild(String groupBuild) {
            this.groupBuild = groupBuild;
        }

        public String getGroupBuild2() {
            return groupBuild2;
        }

        public void setGroupBuild2(String groupBuild2) {
            this.groupBuild2 = groupBuild2;
        }

        public String getMyTeam() {
            return myTeam;
        }

        public void setMyTeam(String myTeam) {
            this.myTeam = myTeam;
        }

        public String getInvitation() {
            return invitation;
        }

        public void setInvitation(String invitation) {
            this.invitation = invitation;
        }

        public String getValidInvitation() {
            return validInvitation;
        }

        public void setValidInvitation(String validInvitation) {
            this.validInvitation = validInvitation;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastSignTime() {
            return lastSignTime;
        }

        public void setLastSignTime(String lastSignTime) {
            this.lastSignTime = lastSignTime;
        }

        public String getLastSignNum() {
            return lastSignNum;
        }

        public void setLastSignNum(String lastSignNum) {
            this.lastSignNum = lastSignNum;
        }

        public String getSignSeriesReward() {
            return signSeriesReward;
        }

        public void setSignSeriesReward(String signSeriesReward) {
            this.signSeriesReward = signSeriesReward;
        }

        public String getGuideStatus() {
            return guideStatus;
        }

        public void setGuideStatus(String guideStatus) {
            this.guideStatus = guideStatus;
        }

        public String getVipUpgrade() {
            return vipUpgrade;
        }

        public void setVipUpgrade(String vipUpgrade) {
            this.vipUpgrade = vipUpgrade;
        }
    }

    public static class DataBean {
        /**
         * msg : {"id":"2091","type":"0","openid":"9nbhn5mn3o7lj1g04ef1edc7vhfo8i1o","bindOpenid":"o7AJdwwMWefmtjDlznuvMDDvUGcA","firstType":"","balance":"2100.23","frozen":"-1000.23","available":"3100.46","shareholder":"0.00","cashMoney":"0.00","fictitiousBean":"0","shopBalance":"0.03","shopFrozen":"0.00","shopAvailable":"0.00","shopBindMoney":"0.03","userExp":"150","regTime":"2017-09-21 12:48:10","realName":"刘晓东","realStatus":"1","realFailReason":"55","mobile":"15210732085","pwd":"14e1b600b1fd579f47433b88e8d85291","payPwd":"acf56f1c329fd863212c81a5b19e4f7b","age":"23","birthDate":"2017-08-14","identityCard":"411522199406267236","identityFace":"55","identityBack":"55","gender":"1","nickName":"小东","province":"北京市","city":"北京市市辖区","job":"程序狗","income":"5000以下","hobby":"a\u2006a\u2006a","portrait":"portrait/20171106/150996872175.png","joinTime":null,"status":"2","loginStatus":"1","moneyStatus":"1","cashStatus":"1","vipLevel":"0","associatorLevel":"0","invitationCode":"msg5x","invitationId":"1960","groupBuild":"1","groupBuild2":"0","myTeam":"0","invitation":"2","validInvitation":"0","lastLoginTime":"2017-10-17 18:06:32","lastSignTime":"2017-11-06","lastSignNum":"1","signSeriesReward":"0","guideStatus":"1","vipUpgrade":"0"}
         */

        private MsgBeanX msg;

        public MsgBeanX getMsg() {
            return msg;
        }

        public void setMsg(MsgBeanX msg) {
            this.msg = msg;
        }

        public static class MsgBeanX {
            /**
             * id : 2091
             * type : 0
             * openid : 9nbhn5mn3o7lj1g04ef1edc7vhfo8i1o
             * bindOpenid : o7AJdwwMWefmtjDlznuvMDDvUGcA
             * firstType :
             * balance : 2100.23
             * frozen : -1000.23
             * available : 3100.46
             * shareholder : 0.00
             * cashMoney : 0.00
             * fictitiousBean : 0
             * shopBalance : 0.03
             * shopFrozen : 0.00
             * shopAvailable : 0.00
             * shopBindMoney : 0.03
             * userExp : 150
             * regTime : 2017-09-21 12:48:10
             * realName : 刘晓东
             * realStatus : 1
             * realFailReason : 55
             * mobile : 15210732085
             * pwd : 14e1b600b1fd579f47433b88e8d85291
             * payPwd : acf56f1c329fd863212c81a5b19e4f7b
             * age : 23
             * birthDate : 2017-08-14
             * identityCard : 411522199406267236
             * identityFace : 55
             * identityBack : 55
             * gender : 1
             * nickName : 小东
             * province : 北京市
             * city : 北京市市辖区
             * job : 程序狗
             * income : 5000以下
             * hobby : a a a
             * portrait : portrait/20171106/150996872175.png
             * joinTime : null
             * status : 2
             * loginStatus : 1
             * moneyStatus : 1
             * cashStatus : 1
             * vipLevel : 0
             * associatorLevel : 0
             * invitationCode : msg5x
             * invitationId : 1960
             * groupBuild : 1
             * groupBuild2 : 0
             * myTeam : 0
             * invitation : 2
             * validInvitation : 0
             * lastLoginTime : 2017-10-17 18:06:32
             * lastSignTime : 2017-11-06
             * lastSignNum : 1
             * signSeriesReward : 0
             * guideStatus : 1
             * vipUpgrade : 0
             */

            private String id;
            private String type;
            private String openid;
            private String bindOpenid;
            private String firstType;
            private String balance;
            private String frozen;
            private String available;
            private String shareholder;
            private String cashMoney;
            private String fictitiousBean;
            private String shopBalance;
            private String shopFrozen;
            private String shopAvailable;
            private String shopBindMoney;
            private String userExp;
            private String regTime;
            private String realName;
            private String realStatus;
            private String realFailReason;
            private String mobile;
            private String pwd;
            private String payPwd;
            private String age;
            private String birthDate;
            private String identityCard;
            private String identityFace;
            private String identityBack;
            private String gender;
            private String nickName;
            private String province;
            private String city;
            private String job;
            private String income;
            private String hobby;
            private String portrait;
            private Object joinTime;
            private String status;
            private String loginStatus;
            private String moneyStatus;
            private String cashStatus;
            private String vipLevel;
            private String associatorLevel;
            private String invitationCode;
            private String invitationId;
            private String groupBuild;
            private String groupBuild2;
            private String myTeam;
            private String invitation;
            private String validInvitation;
            private String lastLoginTime;
            private String lastSignTime;
            private String lastSignNum;
            private String signSeriesReward;
            private String guideStatus;
            private String vipUpgrade;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getBindOpenid() {
                return bindOpenid;
            }

            public void setBindOpenid(String bindOpenid) {
                this.bindOpenid = bindOpenid;
            }

            public String getFirstType() {
                return firstType;
            }

            public void setFirstType(String firstType) {
                this.firstType = firstType;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getFrozen() {
                return frozen;
            }

            public void setFrozen(String frozen) {
                this.frozen = frozen;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getShareholder() {
                return shareholder;
            }

            public void setShareholder(String shareholder) {
                this.shareholder = shareholder;
            }

            public String getCashMoney() {
                return cashMoney;
            }

            public void setCashMoney(String cashMoney) {
                this.cashMoney = cashMoney;
            }

            public String getFictitiousBean() {
                return fictitiousBean;
            }

            public void setFictitiousBean(String fictitiousBean) {
                this.fictitiousBean = fictitiousBean;
            }

            public String getShopBalance() {
                return shopBalance;
            }

            public void setShopBalance(String shopBalance) {
                this.shopBalance = shopBalance;
            }

            public String getShopFrozen() {
                return shopFrozen;
            }

            public void setShopFrozen(String shopFrozen) {
                this.shopFrozen = shopFrozen;
            }

            public String getShopAvailable() {
                return shopAvailable;
            }

            public void setShopAvailable(String shopAvailable) {
                this.shopAvailable = shopAvailable;
            }

            public String getShopBindMoney() {
                return shopBindMoney;
            }

            public void setShopBindMoney(String shopBindMoney) {
                this.shopBindMoney = shopBindMoney;
            }

            public String getUserExp() {
                return userExp;
            }

            public void setUserExp(String userExp) {
                this.userExp = userExp;
            }

            public String getRegTime() {
                return regTime;
            }

            public void setRegTime(String regTime) {
                this.regTime = regTime;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getRealStatus() {
                return realStatus;
            }

            public void setRealStatus(String realStatus) {
                this.realStatus = realStatus;
            }

            public String getRealFailReason() {
                return realFailReason;
            }

            public void setRealFailReason(String realFailReason) {
                this.realFailReason = realFailReason;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getPayPwd() {
                return payPwd;
            }

            public void setPayPwd(String payPwd) {
                this.payPwd = payPwd;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(String birthDate) {
                this.birthDate = birthDate;
            }

            public String getIdentityCard() {
                return identityCard;
            }

            public void setIdentityCard(String identityCard) {
                this.identityCard = identityCard;
            }

            public String getIdentityFace() {
                return identityFace;
            }

            public void setIdentityFace(String identityFace) {
                this.identityFace = identityFace;
            }

            public String getIdentityBack() {
                return identityBack;
            }

            public void setIdentityBack(String identityBack) {
                this.identityBack = identityBack;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
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

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public String getHobby() {
                return hobby;
            }

            public void setHobby(String hobby) {
                this.hobby = hobby;
            }

            public String getPortrait() {
                return portrait;
            }

            public void setPortrait(String portrait) {
                this.portrait = portrait;
            }

            public Object getJoinTime() {
                return joinTime;
            }

            public void setJoinTime(Object joinTime) {
                this.joinTime = joinTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLoginStatus() {
                return loginStatus;
            }

            public void setLoginStatus(String loginStatus) {
                this.loginStatus = loginStatus;
            }

            public String getMoneyStatus() {
                return moneyStatus;
            }

            public void setMoneyStatus(String moneyStatus) {
                this.moneyStatus = moneyStatus;
            }

            public String getCashStatus() {
                return cashStatus;
            }

            public void setCashStatus(String cashStatus) {
                this.cashStatus = cashStatus;
            }

            public String getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(String vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getAssociatorLevel() {
                return associatorLevel;
            }

            public void setAssociatorLevel(String associatorLevel) {
                this.associatorLevel = associatorLevel;
            }

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }

            public String getInvitationId() {
                return invitationId;
            }

            public void setInvitationId(String invitationId) {
                this.invitationId = invitationId;
            }

            public String getGroupBuild() {
                return groupBuild;
            }

            public void setGroupBuild(String groupBuild) {
                this.groupBuild = groupBuild;
            }

            public String getGroupBuild2() {
                return groupBuild2;
            }

            public void setGroupBuild2(String groupBuild2) {
                this.groupBuild2 = groupBuild2;
            }

            public String getMyTeam() {
                return myTeam;
            }

            public void setMyTeam(String myTeam) {
                this.myTeam = myTeam;
            }

            public String getInvitation() {
                return invitation;
            }

            public void setInvitation(String invitation) {
                this.invitation = invitation;
            }

            public String getValidInvitation() {
                return validInvitation;
            }

            public void setValidInvitation(String validInvitation) {
                this.validInvitation = validInvitation;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLastSignTime() {
                return lastSignTime;
            }

            public void setLastSignTime(String lastSignTime) {
                this.lastSignTime = lastSignTime;
            }

            public String getLastSignNum() {
                return lastSignNum;
            }

            public void setLastSignNum(String lastSignNum) {
                this.lastSignNum = lastSignNum;
            }

            public String getSignSeriesReward() {
                return signSeriesReward;
            }

            public void setSignSeriesReward(String signSeriesReward) {
                this.signSeriesReward = signSeriesReward;
            }

            public String getGuideStatus() {
                return guideStatus;
            }

            public void setGuideStatus(String guideStatus) {
                this.guideStatus = guideStatus;
            }

            public String getVipUpgrade() {
                return vipUpgrade;
            }

            public void setVipUpgrade(String vipUpgrade) {
                this.vipUpgrade = vipUpgrade;
            }
        }
    }
}
