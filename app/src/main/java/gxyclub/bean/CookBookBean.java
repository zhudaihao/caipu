package gxyclub.bean;

/**
 * 菜谱精选 更多
 */

public class CookBookBean {
    public CookBookBean(String image, String title) {
        this.image = image;
        this.title = title;
    }

    private String image;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
