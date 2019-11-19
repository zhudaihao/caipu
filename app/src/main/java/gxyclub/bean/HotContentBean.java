package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class HotContentBean implements Serializable {
    String image;

    public HotContentBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

