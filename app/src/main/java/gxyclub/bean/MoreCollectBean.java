package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreCollectBean implements Serializable {
    String image;

    public MoreCollectBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

