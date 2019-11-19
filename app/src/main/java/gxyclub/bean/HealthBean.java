package gxyclub.bean;

/**
 * 养生专辑 更多
 */

public class HealthBean {
    public HealthBean(String image, String title) {
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
