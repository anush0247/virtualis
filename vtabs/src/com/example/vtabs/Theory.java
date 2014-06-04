package com.example.vtabs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Theory extends Activity {

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theory);
		WebView mWebView = (WebView) findViewById(R.id.webview);
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
		mWebView.loadUrl("http://www.cse.iitb.ac.in/~aneesh14/html/theory.html");
		//TextView mytext = (TextView) findViewById(R.id.theory_title);
		//mytitle.setText("Phy - Exp - Theory ");
		//mytitle.setBackground(getResources().getDrawable(R.drawable.border));
		//Linkify.addLinks(mytext, Linkify.ALL);
		//String my = "<b>This is Theory/procedure tab</b> <a href=\"abc.com\">welcome</a> <img src=http://www.w3schools.com/css/transforms.gif height=100px width=100px />";
		//mytext.setText(Html.fromHtml(my));
		
		//Spannable span = Spannable.Factory.getInstance().newSpannable(my);
		//mytext.setText("www.google.com");
	}
}