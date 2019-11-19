package gxyclub.ui.activity.setActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.zdh.library_dialog.DialogUtils;
import cn.zdh.library_dialog.ToastUtils;
import cn.zdh.picturelibrary.PopupShow;
import gxyclub.utils.DataClearUtil;

/**
 * Created by Administrator on 2018/10/16.
 */

public class SetActivity extends BaseActivity {
    @BindView(R.id.tv_set)
    TextView tvSet;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.tv_idea)
    TextView tvIdea;
    @BindView(R.id.tv_cache)
    TextView tvCache;//清除缓存
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_help)
    TextView tvHelp;

    @Override
    public int getResLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("设置");
        //获取缓存
        tvCache.setText(DataClearUtil.getTotalCacheSize(this));
    }

    private PopupShow popupShow;

    @OnClick({R.id.rl_set, R.id.rl_password, R.id.rl_idea, R.id.rl_cache, R.id.rl_share, R.id.rl_help, R.id.bt_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //绑定手机号
            case R.id.rl_set:

                Intent intent = new Intent(this, BindingActivity.class);
                startToActivity(intent, false);

                break;
            //密码
            case R.id.rl_password:
                //判断是否绑定号码
                if (false) {
                    DialogUtils instance = DialogUtils.getInstance();
                    instance.shownBindingDialog(this);
                    instance.setOnclickBinding(new DialogUtils.OnclickBinding() {
                        @Override
                        public void onClickBinding() {
                            Intent intent = new Intent(SetActivity.this, BindingActivity.class);
                            startToActivity(intent, false);
                        }
                    });
                } else {
                    startToActivity(new Intent(SetActivity.this, SetPassWordActivity.class), false);
                }

                break;
            //意见反馈
            case R.id.rl_idea:
                startToActivity(new Intent(SetActivity.this, IdeaActivity.class), false);
                break;
            //清除缓存
            case R.id.rl_cache:
                //显示选择视图
                setClear();
                break;
            case R.id.rl_share:
                //分享
                share("https://www.baidu.com/");
                break;
            case R.id.rl_help:
                break;
            case R.id.bt_out:
                setOut();
                break;
        }
    }

    /**
     * 退出登录
     */
    private void setOut() {
        popupShow = PopupShow.getInstance();
        popupShow.showPopWindowIcon(this);
        popupShow.rb_one.setTextColor(Color.parseColor("#7A7A7A"));
        popupShow.rb_one.setTextSize(12);
        popupShow.rb_one.setText("你真的确定要退出账号吗?");

        popupShow.rb_two.setTextColor(Color.parseColor("#EB5454"));
        popupShow.rb_two.setText("退出");
        popupShow.setOnTwoClick(new PopupShow.OnTwoClick() {
            @Override
            public void setOnTwoClick() {

                showToast("退出登录");
            }
        });
    }

    /**
     * 清除缓存
     */
    private void setClear() {
        popupShow = PopupShow.getInstance();
        popupShow.showPopWindowIcon(this);
        popupShow.rb_one.setTextColor(Color.parseColor("#7A7A7A"));
        popupShow.rb_one.setTextSize(12);
        popupShow.rb_one.setText("你确定要清除缓存吗?");

        popupShow.rb_two.setTextColor(Color.parseColor("#EB5454"));
        popupShow.rb_two.setText("清理");
        popupShow.setOnTwoClick(new PopupShow.OnTwoClick() {
            @Override
            public void setOnTwoClick() {
                //清除缓存
                DataClearUtil.cleanAllCache(SetActivity.this);

                ToastUtils instance = ToastUtils.getInstance();
                instance.showToastShape(SetActivity.this, "");
                instance.tv_num.setBackgroundResource(R.mipmap.clear_finsh);
                instance.tv_content.setText("清理完毕");

                tvCache.setText(DataClearUtil.getTotalCacheSize(SetActivity.this));
            }
        });
    }


    /**
     * 分享 网址
     */
    private void share(final String uri) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        /**
         * 真正分享出去的内容实际上是由下面的这些参数决定的，根据平台不同分别配置
         */
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                paramsToShare.setText("分享内容");
                paramsToShare.setTitle("分享标题");

                if (Wechat.NAME.equals(platform.getName()) || SinaWeibo.NAME.equals(platform.getName())) {
                    //微信  //新浪
                    paramsToShare.setUrl(uri);
                }

                if (QQ.NAME.equals(platform.getName())) {
                    //QQ
                    paramsToShare.setTitleUrl(uri);

                }
            }
        });


        //回调监听
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //成功
                showToast("分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //失败
                showToast("分享失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //取消
                showToast("取消分享");
            }
        });

        // 启动分享GUI
        oks.show(this);
    }


}
