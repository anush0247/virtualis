package com.aakash.vlabs.quiz;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParseAnswer {
	
	String AnsString = null;
	
	public ParseAnswer(String ansString){
		this.AnsString = ansString;
	}
	
	public ArrayList<McqOpts> parseMCQ(){
		String ans = this.AnsString;
		ArrayList<McqOpts> list = new ArrayList<McqOpts>(); 
		Pattern p = Pattern.compile("([~=][^~=]+)");
		Matcher m = p.matcher(ans);
		while(m.find()){

			boolean isAns = (m.group(0).contains("~"))?false:true;
			int weight = 100;
			String feedback = "";
			String value = "";
			
			String[] a = m.group(0).split("%",3);
			if(a.length == 1){
				String[] b = a[0].split("#");
				value = b[0].substring(1);
				if(b.length != 1)feedback = b[1];
				else feedback = "No Feedback Provided.";
				
				if(isAns) weight = 100;
				else weight = 0;
			}
			else {
				weight = Integer.parseInt(a[1]);
				String[] b = a[2].split("#");
				value = b[0].substring(1);
				if(b.length != 1){
					feedback = b[1];
				}
				else feedback = "No Feedback Provided.";
			}
			list.add(new McqOpts(weight, value, feedback, isAns));
		}
		return list;
	}

	class McqOpts {
		int weight;
		String value,feedback;
		boolean isAns;
		
		public McqOpts(int w, String v, String f, boolean ans){
			this.weight = w;
			this.value = v;
			this.feedback = f;
			this.isAns = ans;
		}
		
		public String toString(){
			return ("Option : "+ this.value + " Feedback : "+this.feedback + " Weight :"+this.weight);
		}
	}

	/* end of mcq */
	
	// parsing numeric in gift
	public ArrayList<float[]> parseNumeric(){
		String strnum = this.AnsString;
		float num = -1,offset = 0,weight = 100;
		ArrayList<float[]> numList = new ArrayList<float[]>();
		if(strnum.indexOf("=") != -1){
			String[] numArr = strnum.split("=");
			for(int i = 0 ; i<numArr.length;i++){
				if(numArr[i].contains("#")){
					continue;
				}
				if(numArr[i].indexOf("..") != -1){
					String[] numArr1 = numArr[i].split("\\.\\.");
					String[] numArr2 = numArr1[0].split("%");
					if(numArr2.length != 1){
						weight = Float.parseFloat(numArr2[1]);
						num = (Float.parseFloat(numArr2[2]) + Float.parseFloat(numArr1[1]))/2;
					}
					else{
						num = (Float.parseFloat(numArr2[0]) + Float.parseFloat(numArr1[1]))/2;
					}
					offset = Float.parseFloat(numArr1[1]) - num; 
					float[] floAr = new float[3];
					floAr[0] = num; floAr[1] = offset; floAr[2] = weight;
					numList.add(floAr);
				}
				else {
					String[] numArr1 = numArr[i].split(":");
					String[] numArr2 = numArr1[0].split("%");
					if(numArr2.length != 1){
						weight = Float.parseFloat(numArr2[1]);
						num = Float.parseFloat(numArr2[2]);
					}
					else {
						num = Float.parseFloat(numArr2[0]);
					}
					if(numArr1.length != 1) offset = Float.parseFloat(numArr1[1]);
					float[] floAr = new float[3];
					floAr[0] = num; floAr[1] = offset; floAr[2] = weight;
					numList.add(floAr);
				}
			}
		}
		else if(strnum.indexOf("..") != -1){
			String[] numArr = strnum.split("\\.\\.");
			String[] numArr2 = numArr[0].substring(1).split("%");
			if(numArr2.length != 1){
				weight = Float.parseFloat(numArr2[1]);
				num = (Float.parseFloat(numArr2[2]) + Float.parseFloat(numArr[1]))/2;
			}
			else {
				num = (Float.parseFloat(numArr[0].substring(1)) + Float.parseFloat(numArr[1]))/2;
			}
			offset = Float.parseFloat(numArr[1]) - num; 
			float[] floAr = new float[3];
			floAr[0] = num; floAr[1] = offset; floAr[2] = weight;
			numList.add(floAr);
		}
		else {
			String[] numArr = strnum.split(":");
			String[] numArr2 = numArr[0].substring(1).split("%");
			if(numArr2.length != 1){
				weight = Float.parseFloat(numArr2[1]);
				num = Float.parseFloat(numArr2[2]);
			}
			else {
				num = Float.parseFloat(numArr[0].substring(1));
			}
			if(numArr.length != 1) offset = Float.parseFloat(numArr[1]);
			float[] floAr = new float[3];
			floAr[0] = num; floAr[1] = offset; floAr[2] = weight;
			numList.add(floAr);
		}
		return numList;
	}

	/* matching ... parsing */
	ArrayList<String[]> parseMatching() {
		// TODO Auto-generated method stub
		String matchStr = this.AnsString;
		ArrayList<String[]> matchingList = new ArrayList<String[]>();
		Pattern p = Pattern.compile("[ ]*=([\\w ]+)[ ]*->[ ]*([\\w ]+)[ ]*");
		Matcher m = p.matcher(matchStr);
		while(m.find()){
			String[] subqns = new String[2];
			subqns[0] = m.group(1);
			subqns[1] = m.group(2);
			matchingList.add(subqns);
			//System.out.println("Found : "+ m.group(2));
		}
		return matchingList;
	}
	
	/* True or False */
	String[] parseTrueFalse(){
		String trueAns = "", feedback = "No Feedback Provided";
		if(this.AnsString.charAt(0) == 'T' || this.AnsString.charAt(0) == 't' ) trueAns = "True";
		else trueAns = "False";
		String[] ansArr = this.AnsString.split("#"), outArr = new String[2];
		if(ansArr.length != 1){
			feedback = ansArr[1];
		}
		outArr[0] = trueAns;
		outArr[1] = feedback;
		return outArr;
		
	}
	
}
