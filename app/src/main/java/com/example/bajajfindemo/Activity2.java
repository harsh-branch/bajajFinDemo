package com.example.bajajfindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new BranchWebViewController("w889x.app.link", MainActivity.class, this)); //YOUR_DOMAIN example: appname.app.link
        webView.loadUrl("https://harsh-branch.github.io/bajajFinDemo/webview.html");

    }
}


class BranchWebViewController extends WebViewClient {

    private String myDomain_;
    private Class activityToLaunch_;
    private Context context;

    BranchWebViewController(@NonNull String myDomain, Class activityToLaunch, Context context) {
        myDomain_ = myDomain;
        activityToLaunch_ = activityToLaunch;
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();

        if (url.contains(myDomain_)) {
            Intent i = new Intent(view.getContext(), activityToLaunch_);
            i.putExtra("branch", url);
            i.putExtra("branch_force_new_session", true);
            context.startActivity(i);
        } else {
            view.loadUrl(url);
        }

        return true;

    }
}