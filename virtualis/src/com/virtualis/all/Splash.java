package com.virtualis.all;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
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

import com.virtualis.Functions;
import com.virtualis.GetConf;
import com.virtualis.Global;
import com.virtualis.R;

@SuppressWarnings("unused")

public class Splash extends Activity implements Global{

	GetConf gf;
	Button settings,save_btn,cancel_btn,about_btn,ok_btn,offline_btn,online_btn;
	Dialog settingsDialog,getStarted;
	Spinner spinner;
	RadioGroup rg;
	ProgressDialog pDialog,expDownload;
	Activity spl;
	File ConfFile;
	CheckBox cb_dont;
	
	int error_flag = 0;
	String error_msg = "Error Msg";
	
	JSONArray json = null, json1 = null;
	JSONObject classSubList = null;
	
	int count = 0,subcount = 0;
	String url = "http://www.cse.iitb.ac.in/~ashutosh14/file2.json";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.all_splash);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        
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
			//url = gf.getExpList_Link();
			//Log.d("URL - given",url);
			
			if(gf.getGetStart().equals("0")){
				getStarted = new Dialog(this);
				getStarted.setContentView(R.layout.start_get_start);
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
					settingsDialog.setContentView(R.layout.start_settings);
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
					
					if(gf.getGetStart().equals("1")){
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
								gf.setGetStart("0");
								break;
							case R.id.getstart_no:
								gf.setGetStart("1");
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
			
			// code of ashutosh need to be add
			
			online_btn = (Button) findViewById(R.id.online);
			online_btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					expDownload = new ProgressDialog(spl);
		    		expDownload.setMessage("Downloading Experiments please wait..");
		    		expDownload.setIndeterminate(false);
		    		expDownload.setMax(100);
		    		expDownload.setCancelable(false);
		    		error_flag = 0;
		    		error_msg = "";
		        	new JsonDownload().execute("online");
		        	Log.d("waiting to downloading exp","..........");
					
				}
			});
			
			
			offline_btn = (Button) findViewById(R.id.offline);
			offline_btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					expDownload = new ProgressDialog(spl);
		    		expDownload.setMessage("Loading Experiments please wait..");
		    		expDownload.setIndeterminate(false);
		    		expDownload.setMax(100);
		    		expDownload.setCancelable(false);
		    		error_flag = 0;
		    		error_msg = "";
		        	new JsonDownload().execute("offline");
		        	Log.d("waiting to downloading exp","..........");
					//parseOffline();
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
    
    public void parseOnline() {
		
		JSONParser jParser = new JSONParser(); 
		
		// get JSON data from URL 
		json = jParser.getJSONFromUrl(url);
		//Log.d("jsong-received",json.toString());
		if(json == null) {
			Context context = spl;
			CharSequence text = "No Internet Connection";
			int duration = Toast.LENGTH_SHORT;

			//Toast.makeText(context, text, duration).show();
			error_flag = 1;
			error_msg = "No Internet Connection";
			
		} else {
		
		try {
			classSubList = json.getJSONObject(0);
			JSONdata.setStudentClass(classSubList.getString("class_no"));
			json1 = classSubList.getJSONArray("subject");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		JSONdata.Subjects.clear();
		JSONdata.ExperimentsHead.clear();
		JSONdata.ExperimentsDesc.clear();
		JSONdata.ExperimentsThumb.clear();
		JSONdata.SubExpThumb.clear();
		JSONdata.SubExpHead.clear();
		JSONdata.SubExpDesc.clear();
		JSONdata.ExperimentNo = -1;
		JSONdata.subjectNo = -1;
		JSONdata.fullOffline = 0;
		
		for (int i = 0; i < json1.length(); i++) { 
			
			try {
				JSONObject subList = json1.getJSONObject(i);
				JSONdata.setSubject(subList.getString("subject_name"));
				
				for(int j = 0; j < subList.getJSONArray("exps").length(); j++) {
					
					JSONObject subExp = subList.getJSONArray("exps").getJSONObject(j);
					JSONdata.setExperimentHead(i,subExp.getString("name"));
					JSONdata.setExperimentDesc(i,subExp.getString("description"));
					JSONdata.setExperimentNum(i,subExp.getString("exp_no"));
					JSONdata.setExperimentThumb(i,subExp.getString("thumb"));
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		moveOnline();
		}
		
	}
	
	public void parseOffline() {
		
		JSONdata.Subjects.clear();
		JSONdata.ExperimentsHead.clear();
		JSONdata.ExperimentsDesc.clear();
		JSONdata.ExperimentsThumb.clear();
		JSONdata.SubExpThumb.clear();
		JSONdata.SubExpHead.clear();
		JSONdata.SubExpDesc.clear();
		JSONdata.ExperimentNo = -1;
		JSONdata.subjectNo = -1;
		JSONdata.fullOffline = 0;
		
		JSONdata.fullOffline = 1;
		JSONdata.setStudentClass("9");
		String pathAppend = BASEDIR + "ExPdaTA/" + JSONdata.StudentClass + "/";
		String pathAppend1 = "";
		String pathAppend2 = "";
		
		count = 0;
		getFile(pathAppend,"global") ;
		Log.d("count no of files ",""+count);

		if(count == 0 ){
			//Toast.makeText(spl, "No offline Experiments Found", Toast.LENGTH_SHORT).show();
			error_flag = 1;
			error_msg = "No offline Exps found";
		}
		else {
			File extStore = Environment.getExternalStorageDirectory();
			File myFile1 = new File(pathAppend);
			
			File list1[] = myFile1.listFiles();
			int k = 0;
			for(int i=0; i<list1.length; i++) {
				
				subcount = 0;
				getFile(pathAppend + list1[i].getName()+File.separator, "subject");
				Log.d("subject",list1[i].getName()+File.separator);
				Log.d("subcount",""+subcount);
				
				if(subcount == 0){
					continue;
				}
				
				pathAppend1 = list1[i].getName() + "/";
				File myFile2 = new File(pathAppend + pathAppend1);
				File list2[] = myFile2.listFiles();
				JSONdata.setSubject(list1[i].getName());
				for(int j=0; j<list2.length; j++) {
					
					JSONdata.setExperimentNum(k,list2[j].getName());
					pathAppend2 = list2[j].getName() + "/expData.json";
					
					/*JSONParser jParser = new JSONParser(); 
					json = jParser.getJSONFromUrl(extStore.getAbsolutePath() + pathAppend + pathAppend1 + pathAppend2);
					
					JSONObject subExp;
					try {
						subExp = json.getJSONObject(j);
						JSONdata.setExperimentHead(i,subExp.getString("exp_name"));
						JSONdata.setExperimentDesc(i,subExp.getString("exp_desc"));
						JSONdata.setExperimentThumb(i,subExp.getString("thumb"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
					
					String exp_thumb = "";
					
					String exp_simulation = "";
					String exp_video = "";
					String exp_resource = "";
					String exp_theory = "";
					String exp_quiz = "";
					String exp_procedure = "";
					
					String exp_class = "";
					String exp_subject = "";
					String exp_name = "";
					String exp_no = "";
					String exp_desc = "";
					String jString = null;
					
					try {

			            //File dir = Environment.getExternalStorageDirectory();
			            File yourFile = new File(pathAppend + pathAppend1 + pathAppend2);
			            FileInputStream stream = new FileInputStream(yourFile);
			            
			            try {
			                FileChannel fc = stream.getChannel();
			                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			                
			                jString = Charset.defaultCharset().decode(bb).toString();
			              }
			              finally {
			                stream.close();
			              }

			        } catch (Exception e) {e.printStackTrace();}
					
					int var = 0;
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_class += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_subject += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_name += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_no += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_desc += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_thumb += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_simulation += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_resource += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_video += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_theory += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!=',';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_procedure += jString.charAt(var);
					var+=2;
					
					for(;jString.charAt(var)!='}';var++);var-=2;
					for(;jString.charAt(var)!='"';var--);var++;
					for(;jString.charAt(var)!='"';var++)
						exp_quiz += jString.charAt(var);
					
					JSONdata.setExperimentHead(k,exp_name);
					JSONdata.setExperimentDesc(k,exp_desc);
					JSONdata.setExperimentThumb(k,exp_thumb);
					
				}
				
				k++;
			}
			
			moveOffline();
		}
		
	}
	@SuppressWarnings("rawtypes")
	public void moveOnline() {
		
		Class ourClass;
		try {
			ourClass = Class.forName("com.virtualis.all.ChooseExperiment");
			Intent intent = new Intent(Splash.this,ourClass);
	        startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("rawtypes")
	public void moveOffline() {
		Class ourClass;
		try {
			ourClass = Class.forName("com.virtualis.all.ChooseExperiment");
			Intent intent = new Intent(Splash.this,ourClass);
	        startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getFile(String dirPath, String cas) 
	{
	    File f = new File(dirPath);
	    File[] files  = f.listFiles();

	    if(files != null)
	    for(int i=0; i < files.length; i++)
	    {
	    	File file = files[i];
	        
	        if(file.isDirectory())
	        {   
	             getFile(file.getAbsolutePath(),cas); 
	        }
	        else {
	        	if(cas.equals("global"))count++;
	        	else subcount++;
	        }
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
	
	private class JsonDownload extends AsyncTask<String, Void, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			expDownload.show();
		}
		
		@Override
		protected String doInBackground(String... urls) {
			if(urls[0].equals("online")) parseOnline();
			if(urls[0].equals("offline")) parseOffline();
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			expDownload.dismiss();
			if(error_flag == 1){
				Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
	
	
}
