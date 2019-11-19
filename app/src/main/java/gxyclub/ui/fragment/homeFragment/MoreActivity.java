package gxyclub.ui.fragment.homeFragment;

import android.content.Intent;
import android.view.View;

import base.BaseActivity;
import butterknife.OnClick;
import cn.gxyclub.R;

/**
 * 公告
 */

public class MoreActivity extends BaseActivity {
    @Override
    public int getResLayout() {
        return R.layout.activity_more;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("消息");

    }

    @OnClick({R.id.rl_layout_1, R.id.rl_layout_2, R.id.rl_layout_3, R.id.rl_layout_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //系统消息
            case R.id.rl_layout_1:
                startToActivity(new Intent(this,MoreSystemActivity.class),false);
                break;
            //收藏
            case R.id.rl_layout_2:
                startToActivity(new Intent(this,MoreCollectActivity.class),false);
                break;
            //点赞
            case R.id.rl_layout_3:
                startToActivity(new Intent(this,MoreLikeActivity.class),false);
                break;
            //评论
            case R.id.rl_layout_4:
                startToActivity(new Intent(this,MoreRemarkActivity.class),false);
                break;
        }
    }
}
