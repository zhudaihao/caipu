package gxyclub.ui.activity.loginActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import utils.Util;


/**
 * 修改密码
 */

public class UpdatePwdActivity extends BaseActivity {
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.iv_delete_1)
    ImageView ivDelete1;
    @BindView(R.id.iv_old_pas)
    ImageView ivOldPas;

    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.iv_delete_2)
    ImageView imageDelete2;
    @BindView(R.id.iv_new_pwd)
    ImageView ivNewPwd;

    @BindView(R.id.et_re_new_pwd)
    EditText etReNewPwd;
    @BindView(R.id.iv_delete_3)
    ImageView imageDelete3;
    @BindView(R.id.iv_new_pwd_two)
    ImageView ivNewPwdTwo;

    @Override
    public int getResLayout() {
        return R.layout.activity_updatepwd;
    }

    @Override
    protected void initView() {
        Util.setEditText(etOldPwd, ivDelete1);
        Util.setEditText(etNewPwd, imageDelete2);
        Util.setEditText(etReNewPwd, imageDelete3);

    }

    /**
     * 点击监听
     */
    private boolean shown1 = true, shown2 = true, shown3 = true;

    @OnClick({R.id.tv_back, R.id.iv_delete_1, R.id.iv_old_pas, R.id.iv_delete_2,
            R.id.iv_new_pwd, R.id.iv_delete_3, R.id.iv_new_pwd_two, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finishToActivity();
                break;
            //清除按钮1
            case R.id.iv_delete_1:
                etOldPwd.setText("");
                break;

            //点击显示旧密码
            case R.id.iv_old_pas:
                if (shown1) {
                    //显示密码
                    etOldPwd.setInputType(0x90);
                    ivOldPas.setSelected(true);
                    //设置光标位置的
                    etOldPwd.setSelection(etOldPwd.getText().length());
                } else {
                    //隐藏密码
                    etOldPwd.setInputType(0x81);
                    ivOldPas.setSelected(false);
                    //设置光标位置的
                    etOldPwd.setSelection(etOldPwd.getText().length());
                }

                shown1 = !shown1;
                break;
            //清除按钮2
            case R.id.iv_delete_2:
                etNewPwd.setText("");
                break;

            //显示新密码
            case R.id.iv_new_pwd:
                if (shown2) {
                    //显示密码
                    etNewPwd.setInputType(0x90);
                    ivNewPwd.setSelected(true);
                    //设置光标位置的
                    etNewPwd.setSelection(etNewPwd.getText().length());
                } else {
                    //隐藏密码
                    etNewPwd.setInputType(0x81);
                    ivNewPwd.setSelected(false);
                    //设置光标位置的
                    etNewPwd.setSelection(etNewPwd.getText().length());
                }

                shown2 = !shown2;
                break;
            //清除按钮3
            case R.id.iv_delete_3:
                etReNewPwd.setText("");
                break;

            //确让新密码
            case R.id.iv_new_pwd_two:
                if (shown3) {
                    //显示密码
                    etReNewPwd.setInputType(0x90);
                    ivNewPwdTwo.setSelected(true);
                    //设置光标位置的
                    etReNewPwd.setSelection(etReNewPwd.getText().length());
                } else {
                    //隐藏密码
                    etReNewPwd.setInputType(0x81);
                    ivNewPwdTwo.setSelected(false);
                    //设置光标位置的
                    etReNewPwd.setSelection(etReNewPwd.getText().length());
                }

                shown3 = !shown3;
                break;
            //点击
            case R.id.bt_submit:
                String oldPwd = etOldPwd.getText().toString();
                String newPwd = etNewPwd.getText().toString();
                String reNewPwd = etReNewPwd.getText().toString();

                if (TextUtils.isEmpty(oldPwd)) {
                    showToast("旧密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(newPwd)) {
                    showToast("新密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(reNewPwd)) {
                    showToast("新密码不能为空");
                    return;
                }

                if (!newPwd.equals(reNewPwd)) {
                    showToast("新密码不一致");
                    return;
                }

                HashMap<String, Object> param = new HashMap<>();
                param.put("userId", getUserId());
                param.put("oldPwd", oldPwd);
                param.put("newPwd", newPwd);
                getNetClient().changePwd(param);
                break;
        }
    }

       //获取用户ID
    public int getUserId() {
        int i = 0;
        String id = "";

        return id == null ? 0 : i;
    }


    /**
     * 请求接口
     */
    @Override
    public void onSuccessful(String requestWhat, Object data, baseBean.ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
        showToast("密码修改成功");
        finishToActivity();
    }
}
