package gxyclub.bean;

import java.io.Serializable;

/**
 * 上传 图片
 */

public class WorkBean implements Serializable{
    public WorkBean(int imageId) {
        this.imageId = imageId;
    }

    public WorkBean(String image) {
        this.image = image;
    }

    private  int imageId=0;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private String image="";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private int tag=0;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
