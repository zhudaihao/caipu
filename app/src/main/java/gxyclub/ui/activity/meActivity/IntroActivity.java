package gxyclub.ui.activity.meActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import base.BaseActivity;
import butterknife.BindView;
import cn.gxyclub.R;

/**
 * 简介
 */

public class IntroActivity extends BaseActivity {
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_tab_num)
    TextView tvNum;

    @Override
    public int getResLayout() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("简介");
        rb_base_left.setText("取消");
        rb_base_right.setText("确定");

        //drawableLeft
        rb_base_left.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

        rb_base_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("确定");
            }
        });

    }


}
