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
	
	static ArrayList<String> Subjects = new ArrayList<String>();
	//static String[][] Experiments = new String[10][20];
	//ArrayList<String>[] exp = new ArrayList<String>()[10];
	static ArrayList<ArrayList<String>> ExperimentsHead = new ArrayList<ArrayList<String>>();
	static ArrayList<String> SubExpHead = new ArrayList<String>();
	
	static ArrayList<ArrayList<String>> ExperimentsDesc = new ArrayList<ArrayList<String>>();
	static ArrayList<String> SubExpDesc = new ArrayList<String>();
	
	public static void setSubject(String sub) {
		
		Subjects.add(sub);
		subjectNo++;
		//SubExp.add("Begin_List");
		SubExpHead = new ArrayList<String>();
		ExperimentsHead.add(SubExpHead);
		
		SubExpDesc = new ArrayList<String>();
		ExperimentsDesc.add(SubExpDesc);
		//SubExp.clear();
	}
	
	public static void setExperimentHead(int index, String str) {
		ExperimentsHead.get(index).add(str);
		ExperimentNo++;
	}
	
	public static void setExperimentDesc(int index, String str) {
		ExperimentsDesc.get(index).add(str);
	}
	/*
    static String[][] Experiments = {
    	{"Experiment 1 - P",
         "Experiment 2 - P",
         "Experiment 3 - P",
         "Experiment 4 - P",
         "Experiment 5 - P",
         "Experiment 6 - P",
         "Experiment 7 - P",
         "Experiment 8 - P",
         "Experiment 9 - P",
         "Experiment 10 - P",
         "Experiment 11 - P",
         "Experiment 12 - P"},
     	
         {"Experiment 1 - C",
          "Experiment 2 - C",
          "Experiment 3 - C",
          "Experiment 4 - C",
          "Experiment 5 - C",
          "Experiment 6 - C",
          "Experiment 7 - C",
          "Experiment 8 - C",
          "Experiment 9 - C",
          "Experiment 10 - C",
          "Experiment 11 - C",
          "Experiment 12 - C"},
          
         {"Experiment 1 - M",
          "Experiment 2 - M",
          "Experiment 3 - M",
          "Experiment 4 - M",
          "Experiment 5 - M",
          "Experiment 6 - M",
          "Experiment 7 - M",
          "Experiment 8 - M",
          "Experiment 9 - M",
          "Experiment 10 - M",
          "Experiment 11 - M",
          "Experiment 12 - M"},
             	
         {"Experiment 1 - B",
          "Experiment 2 - B",
          "Experiment 3 - B",
          "Experiment 4 - B",
          "Experiment 5 - B",
          "Experiment 6 - B",
          "Experiment 7 - B",
          "Experiment 8 - B",
          "Experiment 9 - B",
          "Experiment 10 - B",
          "Experiment 11 - B",
          "Experiment 12 - B"},                 
    };*/
    
}