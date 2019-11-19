package gxyclub.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import gxyclub.config.MyApp;
import gxyclub.ui.activity.loginActivity.LoginActivity;
import gxyclub.ui.fragment.homeFragment.HomeFragment;
import gxyclub.ui.fragment.investFragment.InvestFragment;
import gxyclub.ui.fragment.meFragment.MeFragment;
import gxyclub.ui.fragment.newsFragment.NewsFragment;
import gxyclub.ui.fragment.publishFragment.PublishActivity;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rg_tab)
    public RadioGroup rgTab;
    @BindView(R.id.rb_add)
    RadioButton rbAdd;
    @BindView(R.id.rb_tag)
    RadioButton rbTag;

    @Override
    public int getResLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rl_base_title.setVisibility(View.GONE);//隐藏title
        //监听
        //设置初始选中的
        rgTab.check(R.id.rb_home);
        showFragment(0);
        rgTab.setOnCheckedChangeListener(this);

    }


    /**
     * 点击中间发布按钮
     */
    @OnClick(R.id.rb_add)
    public void onViewClicked() {
        toActivity();
    }


    public void toActivity() {
        Intent intent = new Intent(this, PublishActivity.class);
        startTopActivity(intent);
    }

    //监听
    private static final int REQUEST_LOGIN = 1910;
    //定义个常量保存点击那个按钮
    int checkId = -1;
    int tag = 0;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.findViewById(checkedId) == null) {
            return;
        }
        tag = Integer.parseInt(group.findViewById(checkedId).getTag().toString());
        initTiTle(tag);
        //判断是否登录逻辑处理
        if (tag == 3) {
            //判断是否登录
            if (MyApp.getInstance().isLogin()) {
                //显示fragment方法
                showFragment(tag);
            } else {
                /**
                 *  //通过group设置防止按钮错位；(通当值也变量)
                 */
                switch (index) {
                    case 0:
                        rgTab.check(R.id.rb_home);
                        break;
                    case 1:
                        rgTab.check(R.id.rb_shops);
                        break;
                    case 2:
                        rgTab.check(R.id.rb_guarantee);
                        break;

                    case 3:
                        rgTab.check(R.id.rb_guarantee);
                        break;
                }


                //没有登录，跳转到登录
                startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_LOGIN);
                overridePendingTransition(R.anim.anim_left, 0);
                //保存上次点击按钮
                this.checkId = checkedId;
            }
        } else {
            showFragment(tag);
        }
    }

    private void initTiTle(int tag) {
        switch (tag) {
            case 0:
                rl_base_title.setVisibility(View.GONE);
                break;
            case 1:
                rl_base_title.setVisibility(View.VISIBLE);
                break;
            case 2:
                rl_base_title.setVisibility(View.VISIBLE);
                rb_base_left.setVisibility(View.GONE);
                rb_base_right.setVisibility(View.GONE);
                tv_base_title.setText("茹素趣闻");
                break;
            case 3:
                rl_base_title.setVisibility(View.GONE);
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
        if (resultCode == RESULT_OK && requestCode == REQUEST_LOGIN) {
            if (checkId != 0) {
                rgTab.check(checkId);
            }

        }

    }


    //定义个常量为当前页
    private int index = -1;
    private Fragment[] fragments = new Fragment[4];

    public void showFragment(int tag) {
        if (index == tag) {
            return;
        }
        //获取事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (index != -1) {
            //隐藏fragment
            transaction.hide(fragments[index]);

        }
        if (fragments[tag] == null) {
            //使用反射创建
            createFragment2(tag);
            //添加fragment
            transaction.add(R.id.frameLayout, fragments[tag]);
        } else {
            transaction.show(fragments[tag]);
        }

        //提交事务
        transaction.commit();
        index = tag;


    }

    /**
     * ----------------------反射, Fragment-----------------
     */

    public static final String[] fragmentName = {
            HomeFragment.class.getName(),//首页
            InvestFragment.class.getName(),
            NewsFragment.class.getName(),
            MeFragment.class.getName()};

    //创建fragment
    public void createFragment2(int tag) {
        try {
            fragments[tag] = (Fragment) Class.forName(fragmentName[tag]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    //点击两次退出App
    // （注意要把父类方法去掉）
    private long current;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - current < 2000) {
            //遍历循环退出
            MyApp.getInstance().exit();
        } else {
            showToast("再按一次退出程序");
            current = System.currentTimeMillis();
            MyApp.getInstance().setLogin(false);
            MyApp.getInstance().setLoginBean(null);
        }

    }

}
