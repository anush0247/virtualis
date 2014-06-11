package com.aakash.vlabs;

import java.io.FileOutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
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
            	/*Picture picture = view.capturePicture();
                Bitmap  b = Bitmap.createBitmap( picture.getWidth(),picture.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas( b );
                picture.draw( c );
                FileOutputStream fos = null;
                try 
                {

                   fos = new FileOutputStream( "/sdcard/"  + "page.jpg" );

                   if ( fos != null )
                   {
                       b.compress(Bitmap.CompressFormat.JPEG, 90, fos );

                       fos.close();
                   }

               } 
               catch( Exception e )
               {
                     Log.d("Error","Unable to created image"+e.toString());
                     e.printStackTrace();
                     
               }
               */
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
		mWebView.buildDrawingCache();
	    ImageView imageView = (ImageView) findViewById(R.id.imageView);
	    Bitmap bmap = imageView.getDrawingCache();
	    imageView.setImageBitmap(bmap);
	    mWebView.setVisibility(View.GONE);
       // mWebView.loadUrl("http://askdjfalk");
	}
}