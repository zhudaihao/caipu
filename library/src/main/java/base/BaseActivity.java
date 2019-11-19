package base;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.zhy.autolayout.AutoLayoutActivity;

import net.HttpListener;
import net.NetClient;

import java.util.Timer;
import java.util.TimerTask;

import baseBean.ResponsePagesEntity;
import butterknife.ButterKnife;
import cn.zdh.library_dialog.ToastUtils;
import config.BaseApp;
import encoder.AESCrypt;
import gxy.library.R;
import top.wefor.circularanim.CircularAnim;
import utils.Util;

/**
 * 自定义个基类;用于给mainActivity继承
 * BaseActivity抽象就不用注册
 */


public abstract class BaseActivity extends AutoLayoutActivity implements HttpListener<String> {
    {
        //构造代码块(调用了文件夹config的MyApp类的添加activity方法)
        BaseApp.getInstance().addActivity(this);
    }

    public AESCrypt aesCrypt;
    public BaseActivity baseActivity;
    public LinearLayout ll_base_layout;
    public RelativeLayout rl_base_title;
    public RadioButton rb_base_left, rb_base_right;
    public TextView tv_base_title;
    public ViewGroup ll_base_content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        //小刀注解
        ButterKnife.bind(this);
        baseActivity = this;
        initView();
        try {
            aesCrypt = new AESCrypt();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return;
        }

    }

    private void setLayout() {
        setContentView(R.layout.activity_base);
        ll_base_layout = (LinearLayout) findViewById(R.id.ll_base_layout);
        rl_base_title = (RelativeLayout) findViewById(R.id.rl_base_title);
        rb_base_left = (RadioButton) findViewById(R.id.rb_base_left);
        rb_base_right = (RadioButton) findViewById(R.id.rb_base_right);
        tv_base_title = (TextView) findViewById(R.id.tv_base_title);
        ll_base_content = (ViewGroup) findViewById(R.id.ll_base_content);
        View.inflate(this, getResLayout(), ll_base_content);
        rb_base_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishToActivity();
            }
        });

    }


    //创建个抽象的方法获取布局
    public abstract int getResLayout();

    //获取组件方法
    protected void initView() {
    }


    /**
     * 常用Toast
     */
    public void showToast(final String msg) {
        ToastUtils.getInstance().shownCentreToast(getActivity(), msg);
    }


    /**
     * 常用对象
     */
    //创建个构造方法得到上下文
    public Activity getActivity() {
        return this;
    }


    /**
     * 关闭队列
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestQueue != null) {
            requestQueue.cancelAll(); // 退出页面时时取消所有请求。
            requestQueue.stop(); // 退出时销毁队列，回收资源。
        }
        BaseApp.getInstance().removeActivity(this);


    }

    /**
     * 网络框架
     */
    protected NetClient netClient;
    protected RequestQueue requestQueue;

    public NetClient getNetClient() {
        requestQueue = NoHttp.newRequestQueue();
        if (netClient == null) {
            netClient = new NetClient(requestQueue, this, this);
        }
        return netClient;
    }

    //网络请求成功
    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {

    }

    //网络请求出错
    @Override
    public void onFailure(String requestWhat, Object data) {

    }

    //网络请求失败
    @Override
    public void onFailed(int what, Response<String> response) {

    }


    private boolean isDispatchTouchEvent = true;

    public void setDispatchTouchEvent(boolean dispatchTouchEvent) {
        isDispatchTouchEvent = dispatchTouchEvent;
    }

    public boolean isDispatchTouchEvent() {
        return isDispatchTouchEvent;
    }

    /**
     * 解决键盘适配问题，可以写在BaseActivity里面
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isDispatchTouchEvent()) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();

                //如果不是落在EditText区域，则需要关闭输入法
                if (isShouldHideKeyboard(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
            return super.dispatchTouchEvent(ev);
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    /**
     * 控制键盘
     */
    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            //获取现在拥有焦点的控件view的位置，即EditText
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();

            //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
            return !(event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }


    /**
     * 页面跳转动画效果
     */
    private int animType = 1;

    protected int getAnimType() {
        return animType;
    }

    protected void setAnimType(int animType) {
        this.animType = animType;
    }

    /**
     * 动画
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getAnimType() == 2) {
            if (item.getItemId() == android.R.id.home) {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 手机返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getAnimType() == 2) {
            // 添加返回过渡动画.
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else {
            finish();
            overridePendingTransition(0, R.anim.anim_right);
        }
    }

    protected void setActivityAnimation(Activity activity, View view) {
        if (getAnimType() == 2) {
            CircularAnim.fullActivity(activity, view)
                    .colorOrImageRes(R.color.activity_animation)  //注释掉，因为该颜色已经在App.class 里配置为默认色
                    .go(new CircularAnim.OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd() {
                            finish();
                        }
                    });
        } else {
            finishToActivity();
        }
    }


    /**
     * 跳转页面动画
     */

    protected void finishToActivity() {
        finish();
        overridePendingTransition(0, R.anim.anim_right);
    }


    public void startToActivity() {
        overridePendingTransition(R.anim.anim_left, 0);
        finish();
    }


    protected void startToActivity(Intent intent, boolean isFinish) {
        startActivity(intent);
        overridePendingTransition(R.anim.anim_left, 0);
        //是否关闭页面
        if (isFinish) {
            finish();
        }

    }

    protected void startToActivity(Activity activity, Class<?> toClass, boolean isFinish) {
        Intent intent = new Intent(activity, toClass);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_left, 0);
        //是否关闭页面
        if (isFinish) {
            finish();
        }

    }
    protected void startTopActivity(Intent  intent){
        startActivity(intent);
        overridePendingTransition(R.anim.anim_top, 0);
    }

    protected void finishTopActivity(){
        finish();
        overridePendingTransition(0, R.anim.anim_bottom);

    }


    /**
     * 封装 view 转场动画
     * <p>
     * pair对象 Pair.create((View) imageView, "iv")
     * <p>
     * imageView是动画View ,iv是 android:transitionName="iv"
     * <p>
     * 如果多个view同时需要动画
     * ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.create((View) imageView, "iv"), Pair.create((View) bt8, "bt"));
     */
    public void startActivityOptionsCompat(Activity activity, Intent intent, Pair<View, String>... pair) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
        startActivity(intent, optionsCompat.toBundle());
    }

    protected void finishActivityOptionsCompat() {
        supportFinishAfterTransition();//实现关闭动画
    }

    /**
     * 优化glide内存
     */

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //内存低时清理Glide缓存
        Glide.get(this).clearMemory();
    }


    /**
     * 短信倒计时
     */
    protected Timer timer;
    protected TimerTask task;
    protected int relent = Util.TIMER_TASK;

    protected void getRunTimer(final Activity context, final TextView textView) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relent--;
                        textView.setText(relent + "s" + "后重新发送");

                        //倒计时结束
                        if (relent <= 0) {
                            timer.cancel();
                            // timeTest.setVisibility(View.GONE);
                            textView.setEnabled(true);
                            textView.setText("重新发送");
                            task.cancel();
                            timer = null;
                            task = null;
                            relent = Util.TIMER_TASK;

                        }
                    }
                });

            }
        };
        timer.schedule(task, 1000, 1000);
    }


    /**
     * 键盘适配
     *
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     * scrollHeight滑动高度
     * 登录按钮的location坐标的y值，用来计算软键盘弹出后rootview向上滑动的高度
     */
    private int scrollHeight = 0;
    private int btnY = 0;

    //root最外成布局,scrollToView按钮布局
    protected void setKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                        //   Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        // Log.i("tag", "bottom的高度" + rect.bottom);
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);

                            //btnY的初始值为0，一旦赋过一次值就不再变化
                            if (btnY == 0) {
                                btnY = location[1];
                            }
                            if (scrollHeight == 0) {
                                // 计算root滚动高度，使scrollToView在可见区域的底部
                                scrollHeight = (btnY + scrollToView.getHeight()) - rect.bottom + 30;
//                                Log.e("agt", "------scrollHeight----" + scrollHeight);
                                //平移属性动画(目标控件，方向，平移的起点位置和终点位置 的差，后面动画值可以多个)
                                ObjectAnimator.ofFloat(root, "translationY", scrollHeight, 0).setDuration(300).start();
                                root.scrollTo(0, scrollHeight);
                            }


                        } else {

                            // 软键盘没有弹出来的时候
                            if (scrollHeight > 0) {
                                // 平移属性动画(目标控件，方向，平移的起点位置和终点位置 的差，后面动画值可以多个)
                                ObjectAnimator.ofFloat(root, "translationY", -BaseActivity.this.scrollHeight, 0).setDuration(300).start();
                                root.scrollTo(0, 0);
                                scrollHeight = 0;
                            } else {
                                root.scrollTo(0, 0);
                            }

                        }
                    }
                });


        //点击布局 关闭键盘
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();

            }
        });
    }


    //关闭软键盘
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
