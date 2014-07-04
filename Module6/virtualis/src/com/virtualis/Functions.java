package com.virtualis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Functions {

	
	public boolean isConnected(Activity myActivity) {
		ConnectivityManager connMgr = (ConnectivityManager) myActivity.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}

	public String readFile(String path) throws Exception{
		String str = "";
		File mytext = new File(path);
		@SuppressWarnings("resource")
		FileInputStream input = new FileInputStream(mytext) ;
		StringBuffer buf = new StringBuffer();
		int i = 0;
		while((i = input.read()) != -1){
			buf.append((char)i);
		}
		str = new String(buf);
		return str;
	}
	
	public void DownloadFile(String Dir, String URL){
		try {
			URL url = new URL(URL);
			
			String fileName = URL.substring( URL.lastIndexOf('/')+1, URL.length() );

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			File file = new File(Dir,fileName);
			FileOutputStream fileOutput = new FileOutputStream(file);
			InputStream inputStream = urlConnection.getInputStream();
			@SuppressWarnings("unused")
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
