package com.aygus.kroquiapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aygus.kroquiapp.R;

/**
 * Created by SANTIAGO from AIGUS on 18/02/2019.
 */
public class ListenerWebViewClient extends WebViewClient {
    private WebView webView;
    private ProgressBar progressBar;
    private Context context;
    private FrameLayout frameLayout;

    public ListenerWebViewClient(WebView webView, ProgressBar progressBar, Context context, FrameLayout frameLayout) {
        this.webView = webView;
        this.progressBar = progressBar;
        this.context = context;
        this.frameLayout = frameLayout;
    }

    @Override
    public void onPageStarted(WebView webView1, String url, Bitmap favicon) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView webView1, String url) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onReceivedError(WebView webView1, int error, String description, String failUrl) {
        webView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
}
