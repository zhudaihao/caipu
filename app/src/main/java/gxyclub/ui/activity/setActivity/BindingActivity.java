package gxyclub.ui.activity.setActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;

/**
 * Created by Administrator on 2018/10/17.
 */

public class BindingActivity extends BaseActivity {
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.ed_verify)
    EditText edVerify;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    public int getResLayout() {
        return R.layout.activity_binding;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("绑定手机号码");
    }



    @OnClick({R.id.tv_get_code, R.id.bt_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                break;
            case R.id.bt_out:
                break;
        }
    }
}
