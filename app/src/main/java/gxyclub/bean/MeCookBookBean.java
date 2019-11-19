package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MeCookBookBean implements Serializable {
    String image;

    public MeCookBookBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

