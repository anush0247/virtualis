package com.aakash.vlabs;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.StatusLine; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.json.JSONArray; 
import org.json.JSONException; 
import android.util.Log; 

public class JSONParser { 
	
	static InputStream iStream = null; 
	static JSONArray jsonArray = null; 
	
	public JSONParser() { } 
	
	public JSONArray getJSONFromUrl(String url) { 
		
		StringBuilder builder = new StringBuilder(); 
		HttpClient client = new DefaultHttpClient(); 
		HttpGet httpGet = new HttpGet(url); 
		
		try { 
			
			HttpResponse response = client.execute(httpGet); 
			StatusLine statusLine = response.getStatusLine(); 
			int statusCode = statusLine.getStatusCode(); 
			if (statusCode == 200) { 
				HttpEntity entity = response.getEntity(); 
				InputStream content = entity.getContent(); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(content)); 
				String line; 
				while ((line = reader.readLine()) != null) { 
					builder.append(line); 
				} 
			} else { 
				Log.e("=>", "Failed to download file"); 
			}
		} catch (ClientProtocolException e) {
			Log.d("I am client protocol exception","hello");
			e.printStackTrace();
		} catch (IOException e) { 
			Log.d("I am IO exception","hello");
			e.printStackTrace(); 
		} catch(Exception e){
			Log.d("I am other exception","hello");
		}
		
		
		try { 
			jsonArray = new JSONArray( builder.toString()); 
		} catch (JSONException e) { 
			Log.e("JSON Parser", "Error " + e.toString()); 
		}
		
		return jsonArray;
	}
}
