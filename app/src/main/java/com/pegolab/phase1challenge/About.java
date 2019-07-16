package com.pegolab.phase1challenge;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About ALC");
        WebView myWebView = findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                // for SSLErrorHandler
                new AlertDialog.Builder(About.this).
                setTitle(R.string.notification_error_ssl_cert_invalid).
                setMessage(error.toString()).
                setPositiveButton("continue", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                }).
                setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                }).
                show();
            }
        });
        myWebView.loadUrl("https://andela.com/alc/");
    }
}
