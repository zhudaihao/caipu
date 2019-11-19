package gxyclub.mvp.presenter;

import gxyclub.bean.UpDateBean;
import gxyclub.config.MyApp;
import gxyclub.mvp.model.WelcomeModel;
import gxyclub.mvp.view.WelcomeView;
import gxyclub.ui.activity.welcomeActivity.WelcomeActivity;
import utils.SPUtils;
import utils.Util;
import utils.VersionUtil;

/**
 * 启动 引导页
 */

public class WelcomePresenter {

    //构造方法 创建WelcomeModel
    public WelcomeModel welcomeModel;

    public WelcomePresenter() {
        welcomeModel = new WelcomeModel();
    }

    //多态获取view子类
    private WelcomeView welcomeView;

    public void setWelcomeView(WelcomeActivity welcomeView) {
        this.welcomeView = welcomeView;
    }


    //处理逻辑部分
    public void welcome() {
        //设置启动页还是引导页
        final boolean one = (boolean) SPUtils.get(MyApp.getInstance(), "one", true);

        //回调获取网络请求结果
        welcomeModel.setOnUpgradeLister(new WelcomeModel.OnUpgradeLister() {
            @Override
            public void onUpgradeSuccess(UpDateBean upDateBean) {
                UpDateBean.VersionBean version = upDateBean.getVersion();
                if (version != null) {
                    //更新地址
                    String url = version.getUrl();
                    //后台获取最新版本
                    String newVersion = version.getVname();
                    //是否必须更新
                    int update = version.getIsUpdate();
                    boolean isUpdate;
                    //当前版本
                    String oldVersion = Util.getVersionName(MyApp.getInstance());
                    //有新版本
                    if (VersionUtil.compareVersion(newVersion, oldVersion) > 0) {
                        //1强制更新
                        if (update == 1) {
                            isUpdate = true;
                        } else {
                            isUpdate = false;
                        }
                        welcomeView.onSuccess(url, isUpdate);
                    } else {
                        //没有版本更新
                        //是否启动手势密码
                        boolean isGesture = (boolean) SPUtils.get(MyApp.getInstance(), "isGesture", false);
                        welcomeView.unUpgrade(isGesture);

                    }


                }
            }

            @Override
            public void onFailure(Object data) {
                welcomeView.onFailure();
            }

            @Override
            public void onFailed(int what) {
                welcomeView.onFailed();
            }


        });


        //实现model方法获取回调数据,在回调方法里面实现view方法
        welcomeModel.welcomeImage(new WelcomeModel.OnWelcomeImageLister() {
            @Override
            public void onWelcomeImageSuccess(Integer welcomeImage) {
                welcomeView.welcomeImage(welcomeImage);

            }
        });
        welcomeModel.viewpagerImage(new WelcomeModel.OnViewpagerImageLister() {
            @Override
            public void onViewpagerImageSuccess(int[] viewpagerImage) {
                welcomeView.isOneViewpagerImage(one, viewpagerImage);
            }
        });


    }


}
