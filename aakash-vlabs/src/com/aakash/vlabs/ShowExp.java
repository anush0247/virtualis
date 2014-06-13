package com.aakash.vlabs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class ShowExp extends TabActivity {
	
	Button theory,procedure,videos,simulation,quiz,resources;
	TabHost tabHost;
	int mycolor = Color.BLACK;//Color.rgb(51, 204, 255);//33CCFF
	TextView mytitle;
	
	String class_no,subject,exp_name,exp_no,exp_icon, view_mode,saved_status;
	String TheoryUrl,ProcedureUrl,ResourceUrl,SimulatinUrl,QuizUrl,ExpDesc, VideoUrls;
	int no_vid = 0;
	
	boolean err = false;
	String err_msg = "";
	
	File extStorageAppBasePath,extStorageAppExpPath,myExpFilesDir;
	File externalStorageDir = Environment.getExternalStorageDirectory();

	
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private Button startBtn;
    private ProgressDialog mProgressDialog;
    
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            //WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.showexp);
		
		class_no = getIntent().getExtras().getString("class_no");
		subject = getIntent().getExtras().getString("subject");
		exp_name = getIntent().getExtras().getString("exp_name");
		exp_no = getIntent().getExtras().getString("exp_no");
		exp_icon = getIntent().getExtras().getString("exp_icon");
		ExpDesc = getIntent().getExtras().getString("exp_desc");
		
		view_mode = getIntent().getExtras().getString("view_mode");
		saved_status = getIntent().getExtras().getString("saved_status");
		if(view_mode.equals("offline")){
			Toast.makeText(getApplicationContext(), "You are now in offline mode", Toast.LENGTH_LONG).show();
		}
		
		TheoryUrl = getIntent().getExtras().getString("theory_url");
		ProcedureUrl = getIntent().getExtras().getString("procedure_url");
		ResourceUrl = getIntent().getExtras().getString("resource_url");
		SimulatinUrl = getIntent().getExtras().getString("simulation_url");
		QuizUrl = getIntent().getExtras().getString("quiz_url");
	
		VideoUrls = getIntent().getExtras().getString("video_urls");
		
		//mytitle = (TextView) findViewById(R.id.mytitle);
		this.setTitle(Html.fromHtml("<b> Class "+class_no+" - "+subject+" - "+exp_no+"."+exp_name+"</b>"));
		
		//Toast.makeText(getApplicationContext(), no_vid,Toast.LENGTH_LONG ).show();
		
		
		theory = (Button) findViewById(R.id.theory);
		procedure = (Button) findViewById(R.id.procedure);
		videos = (Button) findViewById(R.id.videos);
		simulation = (Button) findViewById(R.id.simulation);
		quiz = (Button) findViewById(R.id.quiz);
		resources = (Button) findViewById(R.id.resources);
		
		//procedure.setBackground(getResources().getDrawable(R.drawable.border_black));
		
		tabHost = getTabHost();
		
		// Tab for Tab1
		TabSpec tab1 = tabHost.newTabSpec("theory");
		// setting Title and Icon for the Tab
		tab1.setIndicator("theory");
		Intent intent1 = new Intent(this, Theory.class);
		intent1.putExtra("exp_desc",ExpDesc );
		intent1.putExtra("theroy_url",TheoryUrl );
		tab1.setContent(intent1);
		
		// Tab for Tab2
		TabSpec tab2 = tabHost.newTabSpec("procedure");
		tab2.setIndicator("procedure");
		Intent intent2 = new Intent(this, Procedure.class);
		intent2.putExtra("procedure_url",ProcedureUrl );
		tab2.setContent(intent2);

		// Tab for Tab3
		TabSpec tab3 = tabHost.newTabSpec("videos");
		tab3.setIndicator("videos");
		Intent intent3 = new Intent(this, Videos.class);
		intent3.putExtra("video_urls",VideoUrls );
		tab3.setContent(intent3);
		
		
		// Tab for tab4
		TabSpec tab4 = tabHost.newTabSpec("simulation");
		// setting Title and Icon for the Tab
		tab4.setIndicator("simulation");
		Intent intent4 = new Intent(this, Simulation.class);
		tab4.setContent(intent4);
		
		// Tab for tab5
		TabSpec tab5 = tabHost.newTabSpec("quiz");
		tab5.setIndicator("quiz");
		Intent intent5 = new Intent(this, Quiz.class);
		tab5.setContent(intent5);

		// Tab for tab6
		TabSpec tab6 = tabHost.newTabSpec("resources");
		tab6.setIndicator("resources");
		Intent intent6 = new Intent(this, Resources.class);
		intent6.putExtra("resource_url",ResourceUrl );
		tab6.setContent(intent6);
		
		
		// Adding all TabSpec to TabHost
		tabHost.addTab(tab1); // Adding tab1 tab
		tabHost.addTab(tab2); // Adding tab2 tab
		tabHost.addTab(tab3); // Adding tab3 tab
		tabHost.addTab(tab4); // Adding tab4 tab
		tabHost.addTab(tab5); // Adding tab5 tab
		tabHost.addTab(tab6); // Adding tab6 tab
		
		
	
		tabHost.setCurrentTab(0);
		makeTransperent();
		theory.setBackgroundColor(mycolor);
	}
	
	public void makeTransperent(){
		theory.setBackgroundColor(Color.TRANSPARENT);
		procedure.setBackgroundColor(Color.TRANSPARENT);
		videos.setBackgroundColor(Color.TRANSPARENT);
		simulation.setBackgroundColor(Color.TRANSPARENT);
		quiz.setBackgroundColor(Color.TRANSPARENT);
		resources.setBackgroundColor(Color.TRANSPARENT);
	}
	
	public void tabHandler(View target) {
		
		theory.setSelected(false);
		procedure.setSelected(false);
		videos.setSelected(false);
		simulation.setSelected(false);
		quiz.setSelected(false);
		resources.setSelected(false);

		makeTransperent();
		
		if (target.getId() == R.id.theory) {
			tabHost.setCurrentTab(0);
			theory.setSelected(true);
			theory.setBackgroundColor(mycolor);
		} else if (target.getId() == R.id.procedure) {
			tabHost.setCurrentTab(1);
			procedure.setSelected(true);
			procedure.setBackgroundColor(mycolor);
		} else if (target.getId() == R.id.videos) {
			tabHost.setCurrentTab(2);
			videos.setSelected(true);
			videos.setBackgroundColor(mycolor);
		} else if (target.getId() == R.id.simulation) {
			tabHost.setCurrentTab(3);
			simulation.setSelected(true);
			simulation.setBackgroundColor(mycolor);
		} else if (target.getId() == R.id.quiz) {
			tabHost.setCurrentTab(4);
			quiz.setSelected(true);
			quiz.setBackgroundColor(mycolor);
		} else if (target.getId() == R.id.resources) {
			tabHost.setCurrentTab(5);
			resources.setSelected(true);
			resources.setBackgroundColor(mycolor);
		}
	};
	
	 
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    // Inflate the menu items for use in the action bar
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.optionmeu, menu);
		    return super.onCreateOptionsMenu(menu);
		}
		
		
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			 switch (item.getItemId()) {
		        case R.id.saveExp:
		            return saveExp();
		            //return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }

		}



	
	public boolean saveExp(){
		
		extStorageAppBasePath = new File(externalStorageDir.getAbsolutePath() +
				File.separator + "Android" + File.separator + "data" +
				File.separator + getPackageName() + File.separator + "ExPdaTA");
		
		myExpFilesDir = new File(extStorageAppBasePath.getAbsolutePath()
				+ File.separator  + class_no + File.separator 
				+ File.separator  + subject + File.separator
				+ File.separator  + exp_no + File.separator);
		
		boolean cont = true;
		if(!myExpFilesDir.exists()){
			if(myExpFilesDir.mkdirs()){
				Log.d("Sub directories ---", "created");
				cont = true;
			}
			else {
				err = true;
				cont = false;
			}
		}
		
		if(cont){
			// create a expData.json
			try {
				FileOutputStream expJson = new FileOutputStream(myExpFilesDir.getAbsolutePath() + File.separator + "expData.json");
				
				String mydata= "[{  'class_no' : '"+class_no+"', 'subject' : '"+subject+"', 'exp_name' : '"+exp_name+"', 'exp_no' : '"+exp_no+"','exp_desc' : '"+ExpDesc+"', 'thumb' : '"+exp_icon+"', 'gift' : '"+QuizUrl+"',},]";
				byte[] buf = mydata.getBytes();
				expJson.write(buf);
				expJson.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread t = new Thread(){
        		public void run(){
        			String url = "http://www.rgukt.in/pdftnp/Advertisement_ONGC.pdf";
        			DownloadFile(url);
        			DownloadFile("http://aakashlabs.org/static/images/favicon.ico");
        		}
        	};
        	
        	t.start();
        	Toast.makeText(getApplicationContext(), "Experiment Saved", Toast.LENGTH_LONG).show();
			
		}
		return false;
	}
	


	public void DownloadFile(String path){
		try {
			URL url = new URL(path);
			
			String fileName = path.substring( path.lastIndexOf('/')+1, path.length() );
			String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			File SDCardRoot = Environment.getExternalStorageDirectory();
			File file = new File(myExpFilesDir,fileName);
			FileOutputStream fileOutput = new FileOutputStream(file);
			InputStream inputStream = urlConnection.getInputStream();
			int totalSize = urlConnection.getContentLength();
			int downloadedSize = 0;
			byte[] buffer = new byte[1024];
			int bufferLength = 0;
			
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
				fileOutput.write(buffer, 0, bufferLength);
				downloadedSize += bufferLength;
			}
			fileOutput.close();
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	



}
