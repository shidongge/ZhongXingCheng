package us.mifeng.zhongxingcheng.bean;

import java.util.List;

/**
 * Created by shido on 2017/12/19.
 */

public class FenLieBean {

    /**
     * info : 成功
     * status : 0
     * data : [{"id":7,"level":0,"name":"粮油素食","child_list":[{"id":31,"level":0,"name":"米面粮油"},{"id":35,"level":0,"name":"冲调保健"}]},{"id":8,"level":0,"name":"休闲食品","child_list":[{"id":39,"level":0,"name":"蜜饯/干果"}]},{"id":9,"level":0,"name":"酒水饮料","child_list":[{"id":41,"level":0,"name":"名品白酒"},{"id":43,"level":0,"name":"红酒/洋酒"},{"id":44,"level":0,"name":"名贵茶品"}]},{"id":10,"level":0,"name":"母婴用品","child_list":[{"id":46,"level":0,"name":"纸尿裤/湿巾"}]},{"id":11,"level":0,"name":"清洁洗护","child_list":[{"id":50,"level":0,"name":"面部护肤"},{"id":51,"level":0,"name":"个人洗护"},{"id":53,"level":0,"name":"精品生活用纸"},{"id":54,"level":0,"name":"衣物清理护理"},{"id":55,"level":0,"name":"厨卫/家居清洁"}]},{"id":12,"level":0,"name":"日用百货","child_list":[{"id":58,"level":0,"name":"厨房配件"}]},{"id":13,"level":0,"name":"3C数码","child_list":[{"id":63,"level":0,"name":"手机"}]},{"id":14,"level":0,"name":"家用电器","child_list":[{"id":132,"level":0,"name":"其他"},{"id":130,"level":0,"name":"小家电"}]},{"id":133,"level":0,"name":"保健品","child_list":[{"id":134,"level":0,"name":"葛根礼包"}]}]
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
         * id : 7
         * level : 0
         * name : 粮油素食
         * child_list : [{"id":31,"level":0,"name":"米面粮油"},{"id":35,"level":0,"name":"冲调保健"}]
         */

        private String id;
        private String level;
        private String name;
        private List<ChildListBean> child_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildListBean> getChild_list() {
            return child_list;
        }

        public void setChild_list(List<ChildListBean> child_list) {
            this.child_list = child_list;
        }

        public static class ChildListBean {
            /**
             * id : 31
             * level : 0
             * name : 米面粮油
             */

            private String id;
            private String level;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
