package com.aakash.vlabs;

import java.util.ArrayList;
import java.util.List;

public class JSONdata {

    /*static String[] Subjects = {
        "Physics",
        "Chemistry",
        "Mathematics",
        "Biology"
    };*/
	
	static int subjectNo = -1;
	static int ExperimentNo = -1;
	static String StudentClass;
	
	static ArrayList<String> Subjects = new ArrayList<String>();
	//static String[][] Experiments = new String[10][20];
	//ArrayList<String>[] exp = new ArrayList<String>()[10];
	static ArrayList<ArrayList<String>> ExperimentsHead = new ArrayList<ArrayList<String>>();
	static ArrayList<String> SubExpHead = new ArrayList<String>();
	
	static ArrayList<ArrayList<String>> ExperimentsDesc = new ArrayList<ArrayList<String>>();
	static ArrayList<String> SubExpDesc = new ArrayList<String>();
	
	static ArrayList<ArrayList<String>> ExperimentsNum = new ArrayList<ArrayList<String>>();
	static ArrayList<String> SubExpNum = new ArrayList<String>();
	
	public static void setSubject(String sub) {
		
		Subjects.add(sub);
		subjectNo++;
		//SubExp.add("Begin_List");
		SubExpHead = new ArrayList<String>();
		ExperimentsHead.add(SubExpHead);
		
		SubExpDesc = new ArrayList<String>();
		ExperimentsDesc.add(SubExpDesc);
		
		SubExpNum = new ArrayList<String>();
		ExperimentsNum.add(SubExpNum);
		//SubExp.clear();
	}
	
	public static void setExperimentHead(int index, String str) {
		ExperimentsHead.get(index).add(str);
		ExperimentNo++;
	}
	
	public static void setExperimentDesc(int index, String str) {
		ExperimentsDesc.get(index).add(str);
	}
	
	public static void setStudentClass(String string) {
		// TODO Auto-generated method stub
		StudentClass = string;
	}

	public static void setExperimentNum(int index, String str) {
		// TODO Auto-generated method stub
		ExperimentsNum.get(index).add(str);
	}
    
}