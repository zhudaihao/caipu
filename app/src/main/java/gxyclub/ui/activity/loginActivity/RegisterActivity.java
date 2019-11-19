package gxyclub.ui.activity.loginActivity;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.RequestWhat;

import java.util.HashMap;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import utils.Util;


/**
 * 注册
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_login_pw)
    EditText etLoginPw;
    @BindView(R.id.iv_shown)
    ImageView ivShown;
    @BindView(R.id.iv_delete_1)
    ImageView ivDelete1;
    @BindView(R.id.iv_delete_2)
    ImageView ivDelete2;
    @BindView(R.id.iv_delete_3)
    ImageView ivDelete3;


    @Override
    public int getResLayout() {
        return R.layout.activitiy_register;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("注册");
        Util.setEditText(etPhone, ivDelete1);
        Util.setEditText(etCode, ivDelete2);
        Util.setEditText(etLoginPw, ivDelete3);
        setAnimType(2);//设置页面切换动画类型
    }


    @Override
    public void onSuccessful(String requestWhat, Object data, baseBean.ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
        switch (requestWhat) {
            //短信验证码
            case RequestWhat.CODE:
                showToast("验证码已发送");
                getRunTimer(this, tvCode);
                break;
            case RequestWhat.REGISTER:
                //注册成功
                //请求登录 接口
                HashMap param = new HashMap();
                param.put("username", etPhone.getText().toString());
                param.put("password", etLoginPw.getText().toString());
                getNetClient().login(param);
                break;

            //登录成功
            case RequestWhat.LOGIN:
                setResult(RESULT_OK);
                finishToActivity();
                break;
        }
    }


    private boolean shown = true;

    @OnClick({R.id.rb_base_left, R.id.tv_code, R.id.iv_delete_1, R.id.iv_delete_2, R.id.iv_delete_3,
            R.id.iv_shown, R.id.bt_yes, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            ///注册协议
            case R.id.tv_register:

                break;
            //点击返回键
            case R.id.rb_base_left:
                //设置动画
                setActivityAnimation(this, view);
                break;
            case R.id.tv_code:
                //获取验证码
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showToast("手机号码不能为空");
                    return;
                }

                //短信验证码
                getNetClient().getCode(etPhone.getText().toString(), 0);

                break;
            case R.id.iv_delete_1:
                etPhone.setText("");
                break;
            case R.id.iv_delete_2:
                etCode.setText("");
                break;
            case R.id.iv_delete_3:
                etLoginPw.setText("");
                break;

            case R.id.iv_shown:
                if (shown) {
                    //显示密码
                    etLoginPw.setInputType(0x90);
                    ivShown.setSelected(true);
                    //设置光标位置的
                    etLoginPw.setSelection(etLoginPw.getText().length());
                } else {
                    //隐藏密码
                    etLoginPw.setInputType(0x81);
                    ivShown.setSelected(false);
                    //设置光标位置的
                    etLoginPw.setSelection(etLoginPw.getText().length());
                }

                shown = !shown;
                break;

            case R.id.bt_yes:
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showToast("手机号码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(etCode.getText().toString())) {
                    showToast("验证码不能为空");
                    return;
                }


                if (TextUtils.isEmpty(etLoginPw.getText().toString())) {
                    showToast("登录密码不能为空");
                    return;
                }


                HashMap<String, String> param = new HashMap<>();
                param.put("phone", etPhone.getText().toString());
                param.put("captcha", etCode.getText().toString());
                param.put("password", etLoginPw.getText().toString());

                getNetClient().register(param);
                break;

        }
    }


}
