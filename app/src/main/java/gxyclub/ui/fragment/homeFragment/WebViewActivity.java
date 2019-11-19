package gxyclub.ui.fragment.homeFragment;

import base.BaseWebViewActivity;


/**
 * Created by we on 2017/6/9.
 */

public class WebViewActivity extends BaseWebViewActivity {

    @Override
    public void setDate() {
        //文字轮播
//        if (isText) {
//            HomeBean.ArticleListBean articleListBean = (HomeBean.ArticleListBean) getIntent().getSerializableExtra("articleListBean");
//            String html = "<html><head>" +
//                    "<script type='text/javascript'>" +
//                    "window.onload = function(){" +
//                    "var $img = document.getElementsByTagName('img');" +
//                    "for(var p add  $img){\n" +
//                    "$img[p].style.width = '90%';" + "$img[p].style.height ='auto';" + "}" + "}" + "</script>"
//                    + "<meta name='viewport' content='width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'/>"
//                    + "</head><body>" + "<br/><h3><center><font color='#333333'>"
//                    + articleListBean.getTitle() + "</font></center></h3>" + "<p align='left'>" + "<font color='#999999' size='3rem'>" + "发件人："
//                    + articleListBean.getOperationUser() + "</font></p>" + "<p align='left'>" + "<font color='#999999' size='3rem'>" + "发件时间："
//                    + Util.paseDate(articleListBean.getCreateTime(), true) + "</font></p>" + "<div class='gxyContent'>"
//                    + articleListBean.getContent() + "</div>" + "<br/><h3><center><font color='#333333'>" + "</font></center></h3>" + "</body></html>";
//
//            tv_base_title.setText("公告");
//            agentWebView.loadDataWithBaseURL(NetRequest.SERVER + "/attached/beanList/2016/20160602/", html, "text/html", "UTF-8", null);
//            //图片轮播
//        } else {
//            String videoUrl = getIntent().getStringExtra("url");
//            agentWebView.loadUrl(videoUrl);
//
//        }
    }
}
