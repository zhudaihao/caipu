package gxyclub.ui.activity.loginActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import utils.Util;

import net.RequestWhat;


/**
 * 忘记密码
 */

public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.image_delete_1)
    ImageView imageDelete1;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_delete_2)
    ImageView ivDelete2;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.iv_shown_3)
    ImageView ivShown3;
    @BindView(R.id.iv_delete_3)
    ImageView ivDelete3;
    @BindView(R.id.et_Password)
    EditText etPassword;
    @BindView(R.id.iv_shown_4)
    ImageView ivShown4;
    @BindView(R.id.iv_delete_4)
    ImageView ivDelete4;
    @BindView(R.id.et_Confirm_Password)
    EditText etConfirmPassword;

    @BindView(R.id.tv_code)
    TextView tvCode;

    @Override
    public int getResLayout() {
        return R.layout.activity_forgetpwd;
    }


    protected void initView() {
        tv_base_title.setText("忘记密码");
        Util.setEditText(etPhone, imageDelete1);
        Util.setEditText(etCode, ivDelete2);
        Util.setEditText(etPassword, ivDelete3);
        Util.setEditText(etConfirmPassword, ivDelete4);
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
            case RequestWhat.FORGETPWD:
                showToast("修改成功");
                finishToActivity();
                break;
        }
    }

    private boolean shown3 = true, shown4 = true;

    @OnClick({ R.id.tv_code, R.id.image_delete_1, R.id.iv_delete_2,
            R.id.iv_shown_3, R.id.iv_delete_3, R.id.iv_shown_4, R.id.iv_delete_4, R.id.bt_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //获取验证码
            case R.id.tv_code:
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showToast("手机号码不能为空");
                    return;
                }
                //短信验证码
                getNetClient().getCode(etPhone.getText().toString(), 2);

              /*  if (etPhone.getText().toString().replace(" ", "").matches("[1][34578][\\d]{9}")) {

                } else {
                    showToast("手机号码格式不正确");
                }*/

                break;
            case R.id.image_delete_1:
                etPhone.setText("");
                break;
            case R.id.iv_delete_2:
                etCode.setText("");
                break;
            //点击显示密码
            case R.id.iv_shown_3:
                if (shown3) {
                    //显示密码
                    etPassword.setInputType(0x90);
                    ivShown3.setSelected(true);
                    //设置光标位置的
                    etPassword.setSelection(etPassword.getText().length());
                } else {
                    //隐藏密码
                    etPassword.setInputType(0x81);
                    ivShown3.setSelected(false);
                    //设置光标位置的
                    etPassword.setSelection(etPassword.getText().length());
                }

                shown3 = !shown3;
                break;
            case R.id.iv_delete_3:
                etPassword.setText("");
                break;
            case R.id.iv_shown_4:
                if (shown4) {
                    //显示密码
                    etConfirmPassword.setInputType(0x90);
                    ivShown4.setSelected(true);
                    //设置光标位置的
                    etConfirmPassword.setSelection(etConfirmPassword.getText().length());
                } else {
                    //隐藏密码
                    etConfirmPassword.setInputType(0x81);
                    ivShown4.setSelected(false);
                    //设置光标位置的
                    etConfirmPassword.setSelection(etConfirmPassword.getText().length());
                }

                shown4 = !shown4;
                break;
            case R.id.iv_delete_4:
                etConfirmPassword.setText("");
                break;
            //确定
            case R.id.bt_yes:
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号码不能为空");
                    return;
                }

                String code = etCode.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    showToast("验证码不能为空");
                    return;
                }

                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    showToast("新登录密码不能为空");
                    return;
                }

                String ConfirmPassword = etConfirmPassword.getText().toString();
                if (TextUtils.isEmpty(ConfirmPassword)) {
                    showToast("确让新密码不能为空");
                    return;
                }

                getNetClient().forgetPwd(phone, code, ConfirmPassword);

                break;
        }
    }
}
