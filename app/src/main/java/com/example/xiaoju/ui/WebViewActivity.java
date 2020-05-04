package com.example.xiaoju.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.example.xiaoju.R;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private WebView webView;
    private String url;

    private boolean stateFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent =  getIntent();
        url =  intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        progressBar = findViewById(R.id.progressbar);
        webView = findViewById(R.id.webview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null == title){
            toolbar.setTitle(R.string.app_name);
        }else {
            toolbar.setTitle(title);
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initWebView();
    }

    private static final String TAG = "WebViewActivity";
    private void initWebView() {
        WebSettings settings = webView.getSettings();           //和系统webview一样
        settings.setJavaScriptEnabled(true);                    //支持Javascript 与js交互
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setAllowFileAccess(true);                      //设置可以访问文件
        settings.setSupportZoom(true);                          //支持缩放
        settings.setBuiltInZoomControls(true);                  //设置内置的缩放控件
        settings.setUseWideViewPort(true);                      //自适应屏幕
        settings.setSupportMultipleWindows(true);               //多窗口
        settings.setDefaultTextEncodingName("utf-8");            //设置编码格式
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);       //缓存模式
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                stateFinish = false;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                stateFinish = true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();//忽略SSL证书错误
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
            }

            @Override
            public void onProgressChanged(WebView webView, int progress) {
                super.onProgressChanged(webView, progress);
               progressBar.setProgress(progress);
               if (progress ==  100) {
                   progressBar.setVisibility(View.GONE);
               }else {
                   progressBar.setVisibility(View.VISIBLE);
               }
            }
        });


        if (null != url){
            webView.loadUrl(url);
        }else {
            webView.loadUrl("http://www.baidu.com");
        }

        }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

