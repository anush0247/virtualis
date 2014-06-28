package com.virtualis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Splash extends Activity implements Global{

	GetConf gf;
	Button settings,save_btn,cancel_btn,about_btn,ok_btn;
	Dialog settingsDialog,getStarted;
	Spinner spinner;
	RadioGroup rg;
	ProgressDialog pDialog;
	Activity spl;
	File ConfFile;
	CheckBox cb_dont;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        
        spl = Splash.this;
        
        ConfFile = new File(BASEDIR  + CONFFILE);
        if(!ConfFile.exists()){
        	pDialog = new ProgressDialog(this);
    		pDialog.setMessage("Downloading Confing file Please wait...");
    		pDialog.setIndeterminate(false);
    		pDialog.setMax(100);
    		pDialog.setCancelable(true);
    		
        	new HttpAsyncTask().execute();
        	Log.d("File Downloading","..........");
        }
        
		try {
			gf = new GetConf();

			if(gf.getGetStart().equals("0")){
				getStarted = new Dialog(this);
				getStarted.setContentView(R.layout.get_start);
				getStarted.setTitle("Get Started");
				
				cb_dont = (CheckBox) getStarted.findViewById(R.id.check_dont);
				ok_btn = (Button) getStarted.findViewById(R.id.ok_btn);
				ok_btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						getStarted.dismiss();
						if(cb_dont.isChecked()){
							gf.setGetStart("1");
							gf.SetConf();
						}
					}
				});
				getStarted.show();
			}
			
			if(gf.getTerms().equals("0")) {
				AlertDialog.Builder licence = new AlertDialog.Builder(Splash.this);
				licence.setTitle("Licence "+ gf.getAppName());
				licence.setCancelable(false);
				licence.setMessage(Html.fromHtml("<center><p>This is a open Source Project at IITB</p></center>"));  
				licence.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						gf.setTerms("1");
						gf.SetConf();
					}
				});
				licence.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						spl.finish();
					}
				});
				licence.show(); 
			}
			
			
			
			settings = (Button) findViewById(R.id.settings);
			settings.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					settingsDialog = new Dialog(v.getContext());
					settingsDialog.setContentView(R.layout.settings);
					settingsDialog.setTitle("Settings");
					
					int index = 0;
					ArrayList<String> listClass = new ArrayList<String>();
					for(int i = 6;i<=10;i++){
						listClass.add(i+"");
						int tmp = Integer.parseInt(gf.getClsNo());
						if(tmp == i) index = tmp-6;
					}
					Log.d("clsno",""+index);
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(),
							android.R.layout.simple_list_item_1, listClass);
					adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
					
					spinner = (Spinner) settingsDialog.findViewById(R.id.student_class);
					spinner.setAdapter(adapter);
					spinner.setSelection(index);
					settingsDialog.show();
					
					rg = (RadioGroup) settingsDialog.findViewById(R.id.getstart);
					
					RadioButton yes = (RadioButton) settingsDialog.findViewById(R.id.getstart_yes),
					no = (RadioButton) settingsDialog.findViewById(R.id.getstart_no);
					
					if(gf.getGetStart().equals("0")){
						no.setChecked(true);
					}
					else{
						yes.setChecked(true);
					}
					save_btn = (Button) settingsDialog.findViewById(R.id.settings_save);
					save_btn.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							gf.setClsNo(""+(spinner.getSelectedItemPosition()+6));
							switch (rg.getCheckedRadioButtonId()) {
							case R.id.getstart_yes:
								gf.setGetStart("1");
								break;
							case R.id.getstart_no:
								gf.setGetStart("0");
								break;
							default:
								break;
							}
							gf.SetConf();
							settingsDialog.dismiss();
							Toast.makeText(v.getContext(), "Settings Saved", Toast.LENGTH_LONG).show();
						}
					});
					cancel_btn = (Button) settingsDialog.findViewById(R.id.settings_cancel);
					cancel_btn.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							settingsDialog.dismiss();
						}
					});
					
				}
			});
			
			about_btn = (Button) findViewById(R.id.about_us);
			
			about_btn.setOnClickListener(new OnClickListener() {  
				@Override  
				public void onClick(View v) {
					// TODO Auto-generated method stub  
					AlertDialog.Builder builder1=new AlertDialog.Builder(Splash.this);
					builder1.setTitle("About "+ gf.getAppName());
					builder1.setMessage(Html.fromHtml("<center><p>"+gf.getAppName()
									+"<small> "+gf.getVersion()+"</small> &nbsp;<small>"
									+gf.getTag()+"</small></p><p>"
									+gf.getAuthors()+" <br><br> "
									+gf.getSupport()+" &emsp;  "
									+gf.getDownlods()+"</p><center>"));  
					builder1.setNeutralButton("OK",new DialogInterface.OnClickListener() {  
						@Override  
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub  
					 	}  
					});  
					builder1.show();  
				}  
			});
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Not Found","Config File Not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... urls) {
			new Functions().DownloadFile(BASEDIR,CONFURL);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			pDialog.dismiss();
			spl.recreate();
		}
	}
}
