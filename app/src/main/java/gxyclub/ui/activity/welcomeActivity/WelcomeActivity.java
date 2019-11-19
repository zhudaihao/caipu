package gxyclub.ui.activity.welcomeActivity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.maning.updatelibrary.UpdateUtils;

import base.BaseActivity;
import cn.gxyclub.R;
import gxyclub.mvp.presenter.WelcomePresenter;
import gxyclub.mvp.view.WelcomeView;
import gxyclub.ui.activity.MainActivity;
import gxyclub.utils.ViewpagerUtils;
import utils.SPUtils;

/**
 * 启动页 和引导页
 */

public class WelcomeActivity extends BaseActivity implements WelcomeView {
    private ViewPager viewPager;
    private LinearLayout llProgress;
    private RelativeLayout rl_layout;
    private Button bt_start;


    private WelcomePresenter welcomePresenter;

    @Override
    public int getResLayout() {
        return R.layout.activity_welcome;
    }

    protected void initView() {
        rl_base_title.setVisibility(View.GONE);//隐藏title
        //获取布局
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        llProgress = (LinearLayout) findViewById(R.id.ll_progress);
        rl_layout = (RelativeLayout) findViewById(R.id.rl_layout);
        bt_start = (Button) findViewById(R.id.bt_start);

        welcomePresenter = new WelcomePresenter();
        welcomePresenter.setWelcomeView(this);

        welcomePresenter.welcome();
        //点击体验按钮监听
        setListener();

        //监听回调
        UpdateUtils.getInstance().initCallBack(this);
    }

    /**
     * 点击监听
     */
    private void setListener() {
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.put(WelcomeActivity.this, "one", false);
                setFinish();
            }
        });
    }

    /**
     * 跳转页面
     */
    private void setFinish() {
        startToActivity(this, MainActivity.class, true);
    }


    /**
     * 回调方法
     */
    @Override
    public void isOneViewpagerImage(final boolean one, int[] viewpagerImage) {
        if (one) {
            //引导页
            viewPager.setVisibility(View.VISIBLE);
            // setViewpager(viewpagerImage,5);
            ViewpagerUtils.getInstance().setViewpager(this, viewpagerImage, 5, viewPager, llProgress, bt_start);
        } else {
            //启动页
            setWelcome(one);
        }

    }


    //请求成功有升级
    @Override
    public void onSuccess(String path, boolean isUpdate) {

        UpdateUtils instance = UpdateUtils.getInstance();
        instance.shownUpDialog(this, path, isUpdate);

        instance.setOnClickCancelListener(new UpdateUtils.OnClickCancelListener() {
            @Override
            public void onCancel() {
                startToActivity(WelcomeActivity.this, MainActivity.class, true);
            }
        });
    }


    //无升级
    @Override
    public void unUpgrade(boolean isGesture) {
        //没有新版本
        //是否启动手势密码
        setFinish();

    }


    //请求失败
    @Override
    public void onFailed() {
        setFinish();
    }

    //请求错误
    @Override
    public void onFailure() {
        setFinish();
    }


    @Override
    public void welcomeImage(Integer welcomeImage) {
        rl_layout.setBackgroundResource(welcomeImage);

    }


    /**
     * 启动页
     */

    private void setWelcome(final boolean one) {
        viewPager.setVisibility(View.GONE);
        //设置动画
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        anim.setFillAfter(true);
        //动画监听
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 两个参数分别表示进入的动画,退出的动画
//                if (!one) {
//                    //请求版本更新接口
//                    welcomePresenter.welcomeModel.loadUpDate();
//                }

                startToActivity(WelcomeActivity.this, MainActivity.class, true);
            }
        });
        rl_layout.setAnimation(anim);
    }


}
