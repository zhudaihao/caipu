package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreSystemBean implements Serializable {
    String image;

    public MoreSystemBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

