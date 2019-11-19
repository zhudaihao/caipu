package gxyclub.bean;

/**
 * Created by Administrator on 2018/2/1.
 */

public class UpDateBean {
    @Override
    public String toString() {
        return "UpdateInfoEntity{" +
                "version=" + version +
                '}';
    }

    private VersionBean version;

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public static class VersionBean {
        @Override
        public String toString() {
            return "VersionBean{" +
                    "createTime=" + createTime +
                    ", dataSource=" + dataSource +
                    ", desc='" + desc + '\'' +
                    ", id=" + id +
                    ", isUpdate=" + isUpdate +
                    ", isUsed=" + isUsed +
                    ", netUrl='" + netUrl + '\'' +
                    ", operationUser=" + operationUser +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    ", vcode=" + vcode +
                    ", vname='" + vname + '\'' +
                    '}';
        }

        private long createTime;
        private int dataSource;
        private String desc;
        private int id;
        private int isUpdate;
        private int isUsed;
        private String netUrl;
        private int operationUser;
        private int type;
        private String url;
        private int vcode;
        private String vname;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDataSource() {
            return dataSource;
        }

        public void setDataSource(int dataSource) {
            this.dataSource = dataSource;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
            this.isUsed = isUsed;
        }

        public String getNetUrl() {
            return netUrl;
        }

        public void setNetUrl(String netUrl) {
            this.netUrl = netUrl;
        }

        public int getOperationUser() {
            return operationUser;
        }

        public void setOperationUser(int operationUser) {
            this.operationUser = operationUser;
        }

        public int getType() {
            return type;
        }


        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVcode() {
            return vcode;
        }

        public void setVcode(int vcode) {
            this.vcode = vcode;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }
    }
}

