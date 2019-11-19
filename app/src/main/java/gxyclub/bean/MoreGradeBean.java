package gxyclub.bean;

import java.io.Serializable;

/**
 *
 */
public class MoreGradeBean implements Serializable {
    String image;

    public MoreGradeBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

