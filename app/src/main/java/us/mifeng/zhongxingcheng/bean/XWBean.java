package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/11/3.
 */

public class XWBean {

    /**
     * success : true
     * msg : 0
     * data : {"msg":"0","pageCount":13,"page":0,"info":[{"id":"157","title":"【经济杂谈】供给侧结构性改革要知难而进","thumb":"http://m.taogt.cn/uploadfile/2017/0922/20170922051513177.jpg","hits":"22","commentNum":"0","praiseNum":"0","updateTime":"2017-09-22 17:15:16"},{"id":"156","title":"【经济杂谈】再谈抢抓\u201c一带一路\u201d建设机遇","thumb":"http://m.taogt.cn/uploadfile/2017/0922/20170922051035231.jpg","hits":"8","commentNum":"0","praiseNum":"0","updateTime":"2017-09-22 17:10:41"},{"id":"155","title":"【经济杂谈】从根本上杜绝\u201c数字造假\u201d","thumb":"http://m.taogt.cn/uploadfile/2017/0922/20170922050644795.jpg","hits":"8","commentNum":"0","praiseNum":"0","updateTime":"2017-09-22 17:06:48"},{"id":"154","title":"哈佛预测8年后印度经济将赶超中国！理由是\u2026\u2026","thumb":"http://xiangth.com/uploadfile/2017/0712/20170712114308590.jpeg","hits":"0","commentNum":"0","praiseNum":"0","updateTime":"2017-07-12 11:49:04"},{"id":"153","title":"多地发布养老金具体调整方案 这两类人可多涨点","thumb":"http://xiangth.com/uploadfile/2017/0712/20170712113654408.jpg","hits":"7","commentNum":"0","praiseNum":"0","updateTime":"2017-07-12 11:36:54"},{"id":"152","title":"保险双录时代到来：60周岁以上买长期人身险要录音录像","thumb":"http://xiangth.com/uploadfile/2017/0711/20170711045122888.jpg","hits":"0","commentNum":"0","praiseNum":"0","updateTime":"2017-07-11 16:51:25"},{"id":"151","title":"为了整治销售误导 保险业将于11月起实施双录","thumb":"https://www.touzi.com/Uploads/2017-07-11/1499738824653510.jpg","hits":"8","commentNum":"0","praiseNum":"0","updateTime":"2017-07-11 15:47:59"},{"id":"150","title":"中信银行正式开展家族信托业务","thumb":"https://www.touzi.com/Uploads/2015-06-09/1433838592407317.jpg","hits":"0","commentNum":"0","praiseNum":"0","updateTime":"2017-07-07 11:44:44"},{"id":"149","title":"中信银行正式开展家族信托业务","thumb":"https://www.touzi.com/Uploads/2015-06-09/1433838592407317.jpg","hits":"2","commentNum":"0","praiseNum":"0","updateTime":"2017-07-07 11:45:35"},{"id":"148","title":"中信银行民生信托等4机构卷入乐视资金风波","thumb":"http://xiangth.com/uploadfile/2017/0707/20170707103657915.jpg","hits":"1","commentNum":"0","praiseNum":"0","updateTime":"2017-07-07 11:44:51"}]}
     */

    private boolean success;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 0
         * pageCount : 13
         * page : 0
         * info : [{"id":"157","title":"【经济杂谈】供给侧结构性改革要知难而进","thumb":"http://m.taogt.cn/uploadfile/2017/0922/20170922051513177.jpg","hits":"22","commentNum":"0","praiseNum":"0","updateTime":"2017-09-22 17:15:16"},{"id":"156","title":"【经济杂谈】再谈抢抓\u201c一带一路\u201d建设机遇","thumb":"http://m.taogt.cn/uploadfile/2017/0922/20170922051035231.jpg","hits":"8","commentNum":"0","praiseNum":"0","updateTime":"2017-09-22 17:10:41"},{"id":"155","title":"【经济杂谈】从根本上杜绝\u201c数字造假\u201d","thumb":"http://m.taogt.cn/uploadfile/2017/0922/20170922050644795.jpg","hits":"8","commentNum":"0","praiseNum":"0","updateTime":"2017-09-22 17:06:48"},{"id":"154","title":"哈佛预测8年后印度经济将赶超中国！理由是\u2026\u2026","thumb":"http://xiangth.com/uploadfile/2017/0712/20170712114308590.jpeg","hits":"0","commentNum":"0","praiseNum":"0","updateTime":"2017-07-12 11:49:04"},{"id":"153","title":"多地发布养老金具体调整方案 这两类人可多涨点","thumb":"http://xiangth.com/uploadfile/2017/0712/20170712113654408.jpg","hits":"7","commentNum":"0","praiseNum":"0","updateTime":"2017-07-12 11:36:54"},{"id":"152","title":"保险双录时代到来：60周岁以上买长期人身险要录音录像","thumb":"http://xiangth.com/uploadfile/2017/0711/20170711045122888.jpg","hits":"0","commentNum":"0","praiseNum":"0","updateTime":"2017-07-11 16:51:25"},{"id":"151","title":"为了整治销售误导 保险业将于11月起实施双录","thumb":"https://www.touzi.com/Uploads/2017-07-11/1499738824653510.jpg","hits":"8","commentNum":"0","praiseNum":"0","updateTime":"2017-07-11 15:47:59"},{"id":"150","title":"中信银行正式开展家族信托业务","thumb":"https://www.touzi.com/Uploads/2015-06-09/1433838592407317.jpg","hits":"0","commentNum":"0","praiseNum":"0","updateTime":"2017-07-07 11:44:44"},{"id":"149","title":"中信银行正式开展家族信托业务","thumb":"https://www.touzi.com/Uploads/2015-06-09/1433838592407317.jpg","hits":"2","commentNum":"0","praiseNum":"0","updateTime":"2017-07-07 11:45:35"},{"id":"148","title":"中信银行民生信托等4机构卷入乐视资金风波","thumb":"http://xiangth.com/uploadfile/2017/0707/20170707103657915.jpg","hits":"1","commentNum":"0","praiseNum":"0","updateTime":"2017-07-07 11:44:51"}]
         */

        private String msg;
        private String pageCount;
        private String page;
        private List<InfoBean> info;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            /**
             * id : 157
             * title : 【经济杂谈】供给侧结构性改革要知难而进
             * thumb : http://m.taogt.cn/uploadfile/2017/0922/20170922051513177.jpg
             * hits : 22
             * commentNum : 0
             * praiseNum : 0
             * updateTime : 2017-09-22 17:15:16
             */
            private String url;
            private String id;
            private String title;
            private String thumb;
            private String hits;
            private String commentNum;
            private String praiseNum;
            private String updateTime;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getHits() {
                return hits;
            }

            public void setHits(String hits) {
                this.hits = hits;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
