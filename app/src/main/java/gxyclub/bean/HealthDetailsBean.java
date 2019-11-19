package gxyclub.bean;

/**
 * Created by Administrator on 2018/10/9.
 */

public class HealthDetailsBean {
    String image;
    boolean isShowOne;

    public boolean isShowOne() {
        return isShowOne;
    }

    public void setShowOne(boolean showOne) {
        isShowOne = showOne;
    }

    public HealthDetailsBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
