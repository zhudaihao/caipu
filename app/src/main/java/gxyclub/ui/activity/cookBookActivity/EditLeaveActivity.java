package gxyclub.ui.activity.cookBookActivity;

import android.view.View;
import android.widget.EditText;

import base.BaseActivity;
import butterknife.BindView;
import cn.gxyclub.R;

/**
 * Created by Administrator on 2018/10/18.
 */

public class EditLeaveActivity extends BaseActivity {

    @BindView(R.id.et_edit)
    EditText etEdit;

    @Override
    public int getResLayout() {
        return R.layout.activity_edit_leave;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("留言");
        rb_base_left.setText("取消");
        rb_base_left.setBackground(null);
        rb_base_right.setText("确定");
        rb_base_left.setCompoundDrawables(null,null,null,null);

        rb_base_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击完成");
            }
        });
    }
}
