package com.virtualis;

import java.io.File;
import android.os.Environment;

public interface Global {
	
	public String CONFURL = "http://www.cse.iitb.ac.in/~ashutosh14/conf.json"; // My conf file URL
	public String CONFFILE = "conf.json";
	
	File EXTDIR = Environment.getExternalStorageDirectory(); // base local storage dir
	
	public String PACKAGE = "com.virtualis";
	public String BASEDIR = EXTDIR.getAbsolutePath() 
			+ File.separator + "Android" 
			+ File.separator + "data" 
			+ File.separator + PACKAGE
			+ File.separator;
	
	
}
