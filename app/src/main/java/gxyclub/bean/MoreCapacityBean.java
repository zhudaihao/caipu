package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreCapacityBean implements Serializable {
    String image;

    public MoreCapacityBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

