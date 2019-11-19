package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreLikeBean implements Serializable {
    String image;

    public MoreLikeBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

