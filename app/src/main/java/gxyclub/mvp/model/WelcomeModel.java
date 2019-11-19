package gxyclub.mvp.model;

import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.rest.Response;

import base.BaseModel;
import baseBean.ResponsePagesEntity;
import cn.gxyclub.R;
import gxyclub.bean.UpDateBean;

/**
 * 启动页和引导页
 */

public class WelcomeModel extends BaseModel {

    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
        UpDateBean upDateBean = JSONObject.parseObject(data.toString(), UpDateBean.class);
        onUpgradeLister.onUpgradeSuccess(upDateBean);


    }

    @Override
    public void onFailed(int what, Response<String> response) {
        super.onFailed(what, response);
        onUpgradeLister.onFailed(what);
    }

    @Override
    public void onFailure(String requestWhat, Object data) {
        super.onFailure(requestWhat, data);
        onUpgradeLister.onFailure(data);
    }

    //网络请求
    public void loadUpDate() {
        getNetClient().checkUpdate();
    }


    public void welcomeImage(OnWelcomeImageLister onWelcomeImageLister) {
        onWelcomeImageLister.onWelcomeImageSuccess(R.mipmap.welcome);

    }

    public void viewpagerImage(OnViewpagerImageLister onViewpagerImageLister) {
        int[] image = {R.mipmap.wc1, R.mipmap.wc2, R.mipmap.wc3, R.mipmap.wc4, R.mipmap.wc5};
        onViewpagerImageLister.onViewpagerImageSuccess(image);
    }


    private static OnUpgradeLister onUpgradeLister;

    public static void setOnUpgradeLister(OnUpgradeLister onUpgradeLister) {
        WelcomeModel.onUpgradeLister = onUpgradeLister;
    }

    public interface OnUpgradeLister {
        void onUpgradeSuccess(UpDateBean upDateBean);

        void onFailure(Object data);

        void onFailed(int what);
    }


    public interface OnWelcomeImageLister {
        void onWelcomeImageSuccess(Integer welcomeImage);

    }

    public interface OnViewpagerImageLister {
        void onViewpagerImageSuccess(int[] viewpagerImage);


    }
}
