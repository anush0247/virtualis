package com.aakash.vlabs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Theory extends Activity {

	String TheoryUrl = "", ExpDesc = "";
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theory);
		TheoryUrl = getIntent().getExtras().getString("theroy_url");
		ExpDesc = getIntent().getExtras().getString("exp_desc");
		
		TextView mydesc = (TextView) findViewById(R.id.mydesc);
		mydesc.setText("Description : " + ExpDesc);
		
		WebView mWebView = (WebView) findViewById(R.id.webview);
		final ProgressDialog pd = ProgressDialog.show(this, "", "Theory is Loading...",true);        
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("aasdaksldjflkasdjklfj")) {
                  view.loadUrl(url);
                } else {
                  Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                  startActivity(i);
                }
                return true;
              }
        });
		mWebView.loadUrl(TheoryUrl);
       // mWebView.loadUrl("http://askdjfalk");
	}
}