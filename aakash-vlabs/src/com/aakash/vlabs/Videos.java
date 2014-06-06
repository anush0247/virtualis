package com.aakash.vlabs;

import java.util.ArrayList;

import com.aakash.vlabs.Videos.MyGestureDetector;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewFlipper;

public class Videos extends Activity {
	
	int cardRest;
	String userRest;
	String[] name,urls;
	
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	
	private ViewFlipper viewFlipper;
	private Button btn1,btn2;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos);
		
		ArrayList<VideoItem> VideoItem_data = new ArrayList<VideoItem>();
		// fill hard coded data VideoItem type in array list
		VideoItem_data.add(new VideoItem( "Ashish", "http://myurl/"));
		VideoItem_data.add(new VideoItem( "Avinash", "http://myurl/"));
		VideoItem_data.add(new VideoItem( "Ben", "http://myurl/"));
		VideoItem_data.add(new VideoItem( "Champ", "http://myurl/"));
		
		name = new String[VideoItem_data.size()];
		urls = new String[VideoItem_data.size()];
		for (int i = 0; i < VideoItem_data.size(); i++) {
		
			name[i] = VideoItem_data.get(i).title;
			urls[i] = VideoItem_data.get(i).url;
		}

		AlertDialog alertDialog = new AlertDialog.Builder(Videos.this).create();
		alertDialog.setTitle("Use swipes to get more Videos");
		alertDialog.setIcon(R.drawable.tick);
		alertDialog.show();
		
		viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
		
		for (int j = 0; j < VideoItem_data.size(); j++) {
			
			TextView label = new TextView(this);
			
			//WebView web = new WebView(this);
			VideoView video = new VideoView(this);
			Uri uri=Uri.parse(urls[j]);
		    video.setVideoURI(uri);
		    MediaController mc = new MediaController(this);
		    video.setMediaController(mc);
		    video.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT  , LayoutParams.WRAP_CONTENT ));
			
			label.setText(Html.fromHtml("<h3>Video " + (j+1) + ". "+ name[j]+"</h3>"));
			label.setPadding(10, 10, 10, 10);
			label.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 50));
			//web.loadUrl(urls[j]);
			//web.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT  , LayoutParams.WRAP_CONTENT ));
			
			
			LinearLayout mLinearLayout = new LinearLayout(this);
			mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 200));
			mLinearLayout.setOrientation(1);
			LinearLayout mLinearLayout2 = new LinearLayout(this);
			
			mLinearLayout2.setOrientation(0);
			mLinearLayout.addView(mLinearLayout2);
			LinearLayout mLinearLayout3 = new LinearLayout(this);
			
			mLinearLayout3.setOrientation(1);
			mLinearLayout2.addView(mLinearLayout3);
			mLinearLayout3.addView(label);
			mLinearLayout.addView(video);
			
			viewFlipper.addView(mLinearLayout);
			
			btn1 = (Button) findViewById(R.id.prev_btn);
			btn2 = (Button) findViewById(R.id.next_btn);
			
			btn1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					viewFlipper.showPrevious();
					
				}
			});
			
			btn2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					viewFlipper.showNext();					
				}
			});
			
			gestureDetector = new GestureDetector(getApplicationContext(),new MyGestureDetector());
			gestureListener = new View.OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
						if (gestureDetector.onTouchEvent(event)) {
							return true;
						}
					return false;
				}
			};
			
		}
}

class MyGestureDetector extends SimpleOnGestureListener {
	
	

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					viewFlipper.setInAnimation(Videos.this, R.animator.in_from_right);
                    viewFlipper.setOutAnimation(Videos.this, R.animator.out_to_left);
					viewFlipper.showNext();
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					viewFlipper.setInAnimation(Videos.this, R.animator.in_from_left);
                    viewFlipper.setOutAnimation(Videos.this, R.animator.out_to_right);
					viewFlipper.showPrevious();
				}
			} catch (Exception e) {}
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event))
			return true;
		else
			return false;
	}
}

class VideoItem {
	
    public String title, url;
    
    public VideoItem(){
        super();
    }
    
    public VideoItem( String title, String url) {
        super();
        this.title = title;
        this.url = url;
    }
}
