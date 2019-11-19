package gxyclub.ui.activity.setActivity;

import android.view.View;

import base.BaseActivity;
import cn.gxyclub.R;

/**
 * Created by Administrator on 2018/10/17.
 */

public class IdeaActivity extends BaseActivity {
    @Override
    public int getResLayout() {
        return R.layout.activity_idea;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("意见反馈");
        rb_base_right.setText("提交");
        rb_base_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("提交");
            }
        });
    }
}
