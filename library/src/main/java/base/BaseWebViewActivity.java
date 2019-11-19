package base;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.just.library.AgentWebView;

import cn.zdh.progresslibrary.CoolIndicator;
import gxy.library.R;


/**
 * 封装 WebView
 */

public abstract class BaseWebViewActivity extends BaseActivity {

    protected AgentWebView agentWebView;
    protected CoolIndicator mCoolIndicator;

    @Override
    public int getResLayout() {
        return R.layout.activity_base_webview;
    }


    protected boolean isText;

    @Override
    protected void initView() {
        agentWebView = (AgentWebView) findViewById(R.id.agentWebView);
        mCoolIndicator = (CoolIndicator) this.findViewById(R.id.indicator);
        mCoolIndicator.setMax(100);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        WebSettings webSettings = agentWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        agentWebView.setWebViewClient(new WebViewClient());

        //适配手机大小
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);

        //监听加载
        agentWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                mCoolIndicator.start();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mCoolIndicator.complete();
            }
        });


        isText = getIntent().getBooleanExtra("isText", false);
        if (!isText) {
            //设置标题
            WebChromeClient webChromeClient = new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    tv_base_title.setText(title);
                }

            };
            // 设置setWebChromeClient对象
            agentWebView.setWebChromeClient(webChromeClient);
        }

        //设置数据
        setDate();


    }

    //设置数据 逻辑处理
    public abstract void setDate();

    @Override
    protected void onResume() {
        agentWebView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        agentWebView.onPause();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        agentWebView.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        agentWebView.destroy();
        super.onBackPressed();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (agentWebView != null) {
            if (keyCode == KeyEvent.KEYCODE_BACK && agentWebView.canGoBack()) {
                agentWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
