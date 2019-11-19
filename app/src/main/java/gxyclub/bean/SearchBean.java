package gxyclub.bean;

/**
 * Created by Administrator on 2018/10/26.
 */

public class SearchBean {
    public SearchBean(String content) {
        this.content = content;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
