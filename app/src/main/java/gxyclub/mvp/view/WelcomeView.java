package gxyclub.mvp.view;

/**
 * 定义用户输入的数据 方法，和界面需要的数据 方法
 */

public interface WelcomeView {

    void isOneViewpagerImage(boolean one, int[] viewpagerImage);

    void onSuccess(String path, boolean isUpdate);

    void unUpgrade(boolean isGesture);

    void onFailed();

    void onFailure();

    void welcomeImage(Integer welcomeImage);


}
