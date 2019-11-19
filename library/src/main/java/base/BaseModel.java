package base;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import net.HttpListener;
import net.NetClient;

import baseBean.ResponsePagesEntity;
import cn.zdh.library_dialog.DialogUtils;
import cn.zdh.library_dialog.ToastUtils;
import config.BaseApp;

/**
 * 封装请求网络 用于MVP模式的model的基类
 */

public class BaseModel implements HttpListener<String> {

    /**
     * 网络框架
     */
    protected NetClient netClient;
    protected RequestQueue requestQueue;

    public NetClient getNetClient() {
        requestQueue = NoHttp.newRequestQueue();
        if (netClient == null) {
            netClient = new NetClient(requestQueue, BaseApp.getInstance(), this);
        }
        return netClient;
    }

    //网络请求成功
    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {
        DialogUtils.getInstance().dismissDialog();
    }

    //网络请求出错
    @Override
    public void onFailure(String requestWhat, Object data) {
        ToastUtils.getInstance().shownToast(BaseApp.getInstance(), requestWhat);
        DialogUtils.getInstance().dismissDialog();
    }

    //网络请求失败
    @Override
    public void onFailed(int what, Response<String> response) {
        DialogUtils.getInstance().dismissDialog();

        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            ToastUtils.getInstance().shownToast(BaseApp.getInstance(), "请求检测网络");
        } else if (exception instanceof TimeoutError) {// 请求超时
            ToastUtils.getInstance().shownToast(BaseApp.getInstance(), "网络超时...");
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            ToastUtils.getInstance().shownToast(BaseApp.getInstance(), "服务器维护中...");
        } else if (exception instanceof URLError) {// URL是错的
            ToastUtils.getInstance().shownToast(BaseApp.getInstance(), "URL错误");

        }
    }


}
