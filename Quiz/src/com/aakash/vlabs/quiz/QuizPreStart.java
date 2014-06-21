package com.aakash.vlabs.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.namespace.QName;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuizPreStart extends Activity {

	Button quiz_btn;
	String url = "http://www.cse.iitb.ac.in/~aneesh14/GIFT-examples.txt";
	String[] Questions = {}; 
	String gift_content = "";

	ArrayList<String> gift_qns = new ArrayList<String>();
	
	private ProgressDialog pDialog;
	
	@Override
	protected Dialog onCreateDialog(int id) {
		
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Downloading GIFT file Please wait...");
		pDialog.setIndeterminate(false);
		pDialog.setMax(100);
		pDialog.setCancelable(false);
		pDialog.show();
		return pDialog;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_pre_start);
		
		new HttpAsyncTask().execute(url);
		
		quiz_btn = (Button) findViewById(R.id.star_quiz);
		quiz_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				parseGIFT();
				Intent quiz_start= new Intent(getApplicationContext(),
						QuizStart.class);
				quiz_start.putStringArrayListExtra("qn_array", gift_qns);
				startActivity(quiz_start);
				
			}
		});
	}
	
	public void parseGIFT(){
		
		// parse the file and return the segments of the questions 
		Questions = gift_content.split("\n\n");
		
		for(int i = 0;i<Questions.length;i++){
			
			if(Questions[i].length() != 0){
				gift_qns.add(Questions[i].replaceAll("\n", ""));
			}
		}
	}
	
	/*
	 * To downloading the file and return the gift String
	 */
	
	public static String GET(String url) {
		Log.d("Location", "GET ? " + url );
		InputStream inputStream = null;
		String result = "";
		try {
			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";
		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
			Log.d("Exception", e.toString());
			e.printStackTrace();
		}
		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("//")) {
			} else {
				line += "\n";
				result += line;
			}
		}
		inputStream.close();
		return result;
	}

	public boolean isConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}
	
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		
		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(1);
		}
		
		@Override
		protected String doInBackground(String... urls) {
			gift_content = GET(urls[0]);
			return gift_content;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String result) {
			// check(result);
			dismissDialog(1);
		}
	}
	
	/*
	 * End of Download file 
	 */
}
