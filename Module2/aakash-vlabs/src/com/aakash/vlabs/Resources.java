package com.aakash.vlabs;
import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class Resources extends Activity {

	boolean loadUrlExternally = true;
	String ResourceUrl;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resources);
		ResourceUrl = getIntent().getExtras().getString("resource_url");
		WebView mWebView = (WebView) findViewById(R.id.webview_resources);
		final ProgressDialog pd = ProgressDialog.show(this, "", "Resources is Loading...",true);        
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);  
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setScrollbarFadingEnabled(true);
		mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if(pd.isShowing()&&pd!=null)
                {
                    pd.dismiss();
                }
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                /*if(url.contains("aasdaksldjflkasdjklfj")) {
                  view.loadUrl(url);
                } else {
                  Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                  startActivity(i);
                }*/
            	 if (url.contains(".pdf")) {
                     Uri path = Uri.parse(url); 
                     Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                     pdfIntent.setDataAndType(path, "application/pdf");
                     pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                     try
                     {
                         startActivity(pdfIntent);
                     }
                     catch(ActivityNotFoundException e)
                     {
                         Toast.makeText(getApplicationContext(), "No PDF application found", Toast.LENGTH_SHORT).show();
                     }
                     catch(Exception otherException)
                     {
                         Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_SHORT).show();
                     }

                 }

                 return true;
              }
        });
       mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + ResourceUrl);
        //mWebView.loadUrl("http://askdjfalk");
        
        
	}
	
	@Override
	public File getCacheDir(){
		// NOTE: this method is used in Android 2.1
		
		return getApplicationContext().getCacheDir();
	}
	
}