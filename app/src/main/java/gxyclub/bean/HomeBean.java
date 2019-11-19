package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class HomeBean implements Serializable {
    String image;

    public HomeBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

