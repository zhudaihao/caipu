package gxyclub.config;


import com.mob.MobSDK;
import com.szysky.customize.siv.util.LogUtil;
import com.umeng.commonsdk.UMConfigure;

import config.BaseApp;
import gxyclub.bean.LoginBean;


/**
 * 负者初始化第三方SDK和数据共享（不要忘记在mainfest注册）
 */
public class MyApp extends BaseApp {
    //使用单利模式
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;

    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base); MultiDex.install(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //打印日子开启
        LogUtil.GlobalLogPrint = true;

        //mob分享
        MobSDK.init(this);

        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true);
        /*
        注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口
        （如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
      */
        UMConfigure.init(this, "5b84b033f29d986618000042", "wswff", UMConfigure.DEVICE_TYPE_PHONE, null);
    }

    //登录成功后用户数据
    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }


    /**
     * 保存是否登录状态
     */
    private boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }


}
