package gxyclub.ui.activity.loginActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.szysky.customize.siv.util.LogUtil;
import com.yolanda.nohttp.rest.Response;

import java.util.HashMap;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import gxyclub.bean.LoginBean;
import gxyclub.config.MyApp;
import top.wefor.circularanim.CircularAnim;
import utils.SPUtils;
import utils.Util;


/**
 * 登录
 */

public class LoginActivity extends BaseActivity implements PlatformActionListener {
    private static final int REQUEST_REGISTER = 33;
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_submit)
    Button loginSubmit;

    @BindView(R.id.iv_delete_phone)
    ImageView ivDeletePhone;
    @BindView(R.id.iv_delete_pwd)
    ImageView ivDeletePwd;

    @BindView(R.id.hint_pwd)
    ImageView hintPwd;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;

    @Override
    public int getResLayout() {
        setDispatchTouchEvent(false);
        return R.layout.activity_login;

    }


    protected void initView() {
        tv_base_title.setText("登录");
        rb_base_right.setText("注册");
        setListener();
        setKeyboardLayout(rl_layout, loginSubmit);
    }

    /**
     * 监听
     */
    private void setListener() {
        Util.setEditText(loginName, ivDeletePhone);
        Util.setEditText(loginPwd, ivDeletePwd);

        String name = (String) SPUtils.get(this, "name", "");
        loginName.setText(name);
        loginName.setSelection(loginName.getText().length());

    }

    private boolean isCheckShown = true;

    @OnClick({R.id.rb_base_right, R.id.rb_base_left, R.id.iv_delete_phone, R.id.iv_delete_pwd, R.id.hint_pwd, R.id.tv_forget_password, R.id.login_submit,
            R.id.progressBar, R.id.iv_wei_xi, R.id.iv_qq, R.id.iv_wei_bo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_base_right:
                //点击注册
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), REQUEST_REGISTER);

                //设置动画
                overridePendingTransition(R.anim.anim_left, 0);
                break;

            case R.id.progressBar:
                progressBar.setVisibility(View.GONE);
                // 伸展按钮
                CircularAnim.show(loginSubmit).go();
                break;
            //返回键
            case R.id.rb_base_left:
                if (MyApp.getInstance().isLogin()) {
                    finishToActivity();
                } else {
                    finishToActivity();
                }

                break;
            //删除登录号码
            case R.id.iv_delete_phone:
                loginName.setText("");
                break;
            //删除登录密码
            case R.id.iv_delete_pwd:
                loginPwd.setText("");
                break;
            //点击显示密码
            case R.id.hint_pwd:
                if (isCheckShown) {
                    //显示密码
                    loginPwd.setInputType(0x90);
                    hintPwd.setSelected(true);
                    //设置光标位置的
                    loginPwd.setSelection(loginPwd.getText().length());
                } else {
                    //隐藏密码
                    loginPwd.setInputType(0x81);
                    hintPwd.setSelected(false);
                    //设置光标位置的
                    loginPwd.setSelection(loginPwd.getText().length());
                }

                isCheckShown = !isCheckShown;
                break;
            //忘记密码
            case R.id.tv_forget_password:
                startToActivity(LoginActivity.this, ForgetPwdActivity.class, false);
                break;
            //登录
            case R.id.login_submit:
                final String name = loginName.getText().toString();
                final String pwd = loginPwd.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    showToast("账户不能为空");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    showToast("密码不能为空");
                    return;
                }

                //动画完成
                loginSubmit.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                //请求登录接口
                HashMap param = new HashMap();
                param.put("showapi_appid", "81623");
                param.put("showapi_sign", "c3080a02641048f2a2eaec34833069ce");
                param.put("num", "5");
                getNetClient().login(param);


//                getNetClient().getCode("27bcadfd14ebc");

//                CircularAnim.hide(loginSubmit)
//                        .endRadius(progressBar.getHeight() / 2)
//                        .deployAnimator(new CircularAnim.OnAnimatorDeployListener() {
//                            @Override
//                            public void deployAnimator(Animator animator) {
//                                animator.setDuration(1200L);
//                                animator.setInterpolator(new AccelerateInterpolator());
//                            }
//                        })
//                        .go(new CircularAnim.OnAnimationEndListener() {
//                            @Override
//                            public void onAnimationEnd() {
//                                progressBar.setVisibility(View.VISIBLE);
//                                progressBar.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        CircularAnim.fullActivity(LoginActivity.this, progressBar)
//                                                .go(new CircularAnim.OnAnimationEndListener() {
//                                                    @Override
//                                                    public void onAnimationEnd() {
//
//                                                    }
//                                                });
//                                    }
//                                }, 500);
//                            }
//                        });


                break;

            //第三方登录
            case R.id.iv_wei_xi:
                Platform wachat = ShareSDK.getPlatform(Wechat.NAME);
                wachat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                wachat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                wachat.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                if (wachat.isClientValid()) {
                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
                }
                if (wachat.isAuthValid()) {
                    //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                    showToast("已经授权过了");
                    return;
                }
                wachat.authorize();    //要功能，不要数据
                //     plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面

                break;
            //
            case R.id.iv_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                qq.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                qq.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                if (qq.isClientValid()) {
                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
                }
                if (qq.isAuthValid()) {
                    //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                    showToast("已经授权过了");
                    return;
                }
                qq.authorize();    //要功能，不要数据
                //     plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面

                break;

            case R.id.iv_wei_bo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                weibo.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                weibo.setPlatformActionListener(this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                if (weibo.isClientValid()) {
                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
                }
                if (weibo.isAuthValid()) {
                    //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                    showToast("已经授权过了");
                    return;
                }
                weibo.authorize();    //要功能，不要数据
                //     plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面

                break;
        }


    }


    /**
     * 返回
     * 回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_REGISTER) {
            setData();
        }

    }

    private LoginBean loginBean;

    @Override
    public void onSuccessful(String requestWhat, Object data, baseBean.ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
//        loginBean = JSONObject.parseObject(data.toString(), LoginBean.class);
//
//        setData();
//        //保存用户名
//        String username = loginBean.getUser().getMobilePhone();
//        SPUtils.put(this, "userName", username);
        loginSubmit.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        showToast("成功");

        Logger.json(data.toString());
        LogUtil._e("agt", "---------" + data.toString());
        //动画完成
        setFinish();
    }

    @Override
    public void onFailure(String requestWhat, Object data) {
        super.onFailure(requestWhat, data);
        showToast(data.toString());
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        super.onFailed(what, response);
        showToast(response.toString());
    }

    //登录完成
    private void setFinish() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    private void setData() {
        showToast("登录成功");
        setResult(RESULT_OK);
        finish();
        overridePendingTransition(0, R.anim.anim_right);
        MyApp.getInstance().setLogin(true);
        MyApp.getInstance().setLoginBean(loginBean);
    }


    /**
     * 第三方登录回调
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        setFinish();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        showToast("登录失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        showToast("登录取消");
    }
}
