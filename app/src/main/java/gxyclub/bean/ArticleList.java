package gxyclub.bean;

import java.io.Serializable;

/**
 * 公告（首页 更多）适配器
 */
public class ArticleList implements Serializable {
    private int id=0;
    private String content="";
    private String title="";
    private String operationUser="";
    private String showTime="";
    private String shotDes="";



    private long createTime=0;

    public String getShotDes() {
        return shotDes;
    }

    public void setShotDes(String shotDes) {
        this.shotDes = shotDes;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
