package com.example.anusha.github.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.anusha.github.R;


public class SecondActivity extends Activity {

    private String url ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webView = findViewById(R.id.webView);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
            url = extras.getString("webUrl");

        webView.loadUrl(url);




    }
}
