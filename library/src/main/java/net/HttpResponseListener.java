package net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import baseBean.BaseResponseEntity;
import cn.zdh.library_dialog.DialogUtils;
import cn.zdh.library_dialog.ToastUtils;


/**
 * 网络请求
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {

    /**
     * 结果回调.
     */
    private HttpListener<T> callback;

    /**
     * 是否显示dialog.
     */

    private DialogUtils dialogUtils;
    private Context context;

    /**
     * @param context      context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Context context, Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.context = context;

        //数据加载对话框
        if (isLoading) {
            dialogUtils = DialogUtils.getInstance();
        }


        this.callback = httpCallback;

    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (dialogUtils != null) {
            DialogUtils.getInstance().shownLoadLoge(context,"加载中...");
        }

    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        dismissDialog();
    }


    /**
     * 返回成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        BaseResponseEntity back = null;
        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
        Log.e("cg","-------"+response.get().toString());
        try {
            back = JSON.parseObject(response.get().toString(), BaseResponseEntity.class);
            /**
             * 是否显示后台信息
             */
            if ("true".equals(back.getMsg().getIsVisible())) {
                ToastUtils.getInstance().shownCentreToast(context, back.getMsg().getInfo());
            }


            //判断登录是否过期
            //  CheckUtil cu = new CheckUtil(new CheckLoginInfo(context, back.getMsg().getType()));
            //   if(!MyApp.ISSHOWDIALOG) {
            //  new CheckLoginInfo(context, back.getMsg().getType()).check();
            //  }


            /**
             * 0、2成功回调。其他失败回调
             */
            switch (back.getMsg().getType()) {
                case 0:
                case 2:
                    if (callback != null) {
                        callback.onSuccessful(back.getCommon().getRequestWhat(), back.getData(), back.getPages());
                    }
                    break;
                case 4:
                    //重新登录

                    break;
                default:
                    if (callback != null) {
                        //返回失败回调
                        callback.onFailure(back.getMsg().getInfo(), back.getData());

                        ToastUtils.getInstance().shownCentreToast(context, back.getMsg().getInfo());

                    }


            }


        } catch (Exception e) {
            /**
             * 防止后台格式错误
             */


        }
    }


    /**
     * 返回错误
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            ToastUtils.getInstance().shownCentreToast(context, "网络不好");
        } else if (exception instanceof TimeoutError) {// 请求超时
            ToastUtils.getInstance().shownCentreToast(context, "请求超时");
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            ToastUtils.getInstance().shownCentreToast(context, "找不到服务器");
        } else if (exception instanceof URLError) {// URL是错的
            ToastUtils.getInstance().shownCentreToast(context, "URL是错的");
        }

        if (callback != null) {
            callback.onFailed(what, response);
        }

    }



    /**
     * 关闭加载框
     */
    protected void dismissDialog() {
        if (dialogUtils != null) {
            dialogUtils.dismissDialog();
        }
    }


}
