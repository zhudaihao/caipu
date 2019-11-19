package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class NewsBean implements Serializable {
    String image;

    public NewsBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

