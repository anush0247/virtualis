package com.example.vtabs;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Resources extends Activity {

	boolean loadUrlExternally = true;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resources);
		WebView mWebView = (WebView) findViewById(R.id.webview_resources);
		final ProgressDialog pd = ProgressDialog.show(this, "", "Loading...",true);        
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);  
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if(pd.isShowing()&&pd!=null)
                {
                    pd.dismiss();
                }
            }
        });
        mWebView.loadUrl("http://www.cse.iitb.ac.in/~aneesh14/html/resources.html");
     
		

	}
}