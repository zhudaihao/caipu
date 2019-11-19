package base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import net.HttpListener;
import net.NetClient;

import baseBean.ResponsePagesEntity;
import butterknife.ButterKnife;
import cn.zdh.library_dialog.ToastUtils;
import gxy.library.R;


/**
 * fragment基类
 */

public abstract class BaseFragment extends Fragment implements HttpListener<String> {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取布局
        View layout = inflater.inflate(getLayoutId(), null);
        //小刀绑定
        ButterKnife.bind(layout);
        return layout;
    }

    public abstract int getLayoutId();

    public void showToast(String msg) {
        ToastUtils.getInstance().shownCentreToast(getActivity(), msg);
    }


    protected BaseActivity activity;
    protected RequestQueue requestQueue;
    protected NetClient netClient;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        activity = (BaseActivity) this.getActivity();
    }


    protected NetClient getNetClient() {
        requestQueue = NoHttp.newRequestQueue();
        if (netClient == null) {
            netClient = new NetClient(requestQueue, activity, this);
        }
        return netClient;
    }


    /**
     * 页面跳转/关闭页面
     */
    public void finishToActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(0, R.anim.anim_right);
    }


    public void startToActivity(Activity activity, Class<?> toClass, boolean isFinish) {
        Intent intent = new Intent(activity, toClass);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_left, 0);

        //是否关闭页面
        if (isFinish) {
            activity.finish();
        }
    }


    public void startToActivity(Intent intent, boolean isFinish) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_left, 0);

        //是否关闭页面
        if (isFinish) {
            activity.finish();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (requestQueue != null) {
            requestQueue.cancelAll(); // 退出页面时时取消所有请求。
            requestQueue.stop(); // 退出时销毁队列，回收资源。
        }
    }

    //网络请求回调
    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {

    }

    @Override
    public void onFailure(String requestWhat, Object data) {

    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }


    //隐藏软键盘
    protected void hintKbOne(EditText etFind, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(etFind.getWindowToken(), 0);
    }


    //优化glide内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //内存低时清理Glide缓存
        Glide.get(getContext()).clearMemory();
    }


}
