package com.dominic.illinitower;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebActivity extends BaseActivity {

    public static final String URL = "URL";
    public static final String TITLE = "TITLE";
    public static final String MAIL_TO_LINKS = "MAIL_TO_LINKS";
    public static final String DATA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(getIntent().getStringExtra(TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String mUrl = getIntent().getStringExtra(URL);
        String mData = getIntent().getStringExtra(DATA);

        final WebView mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(mData, "text/html", "UTF-8");
        mWebView.setBackgroundColor(Color.parseColor("#39434F"));

        if (getIntent().getExtras().getBoolean(MAIL_TO_LINKS)){
            mWebView.setWebViewClient(new WebViewClient()
            {
                @Override
                public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                    if (url.startsWith("mailto:")) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                        intent.setType("text/plain");
                        intent.setData(Uri.parse(url)); // or just "mailto:" for blank
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                        sendEmail(intent);
                    }
                    return true;
                }
            });
        } else {
            mWebView.setWebViewClient(new WebViewClient());
        }

        mWebView.setInitialScale(1); // Set the initial zoom scale
        mWebView.getSettings().setBuiltInZoomControls(true); // Initialize zoom controls for your WebView component
        mWebView.getSettings().setUseWideViewPort(true); // Initializes double-tap zoom control
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //pbMax.setVisibility(View.VISIBLE);
                if (progress == 100) {
                    //pbMax.setVisibility(View.GONE); // Make the bar disappear after URL is loaded
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                    mWebView.setVisibility(View.VISIBLE);
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // do something
            }
        });
        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                WebActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
