package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreDidBean implements Serializable {
    String image;

    public MoreDidBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

