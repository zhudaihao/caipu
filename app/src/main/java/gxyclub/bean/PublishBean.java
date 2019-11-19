package gxyclub.bean;

import java.io.File;
import java.io.Serializable;

/**
 * 推荐
 */

public class PublishBean  implements Serializable {
    public PublishBean(File image, String title) {
        this.image = image;
        this.title = title;
    }

    private File image;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
