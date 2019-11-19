package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreRemarkBean implements Serializable {
    String image;

    public MoreRemarkBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

