package com.aakash.vlabs.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Xml.Encoding;
import android.webkit.WebView;

public class Summary extends Activity{

	MyAns[] AllAns = null;
	WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		
		Parcelable[] ps = getIntent().getParcelableArrayExtra("allAns");
		AllAns = new MyAns[ps.length];
		
		Log.d("Received",""+AllAns.length);
		System.arraycopy(ps, 0, AllAns, 0, ps.length);
		
		int total = AllAns.length,correct = 0,wrong = 0,partial = 0,notAttempted = 0;
		String tmpHTML = "";
		tmpHTML += genHTML(9,9,10,12);
		tmpHTML += endHtml(3);
		for(int i = 0;i<AllAns.length;i++){
			tmpHTML += getQnTitleHtml(AllAns[i].getQn(), (AllAns[i].getQnNo()+1), "correct", 100);
			tmpHTML += getAnsHtml("My True Ans", "My Submited Ans");
			/*if(AllAns[i].getQnType().equals("Multiple")){
				String cls = "wrong";
				float pts = 0;
				if(AllAns[i].getSubmulOptAns().equals(AllAns[i].getTruemulOptAns())){
					cls = "correct";pts = 100;
				}
				localHTML += getQnTitleHtml(AllAns[i].getQn(), AllAns[i].getQnNo(), cls, pts);
				
        	}
        	else if(AllAns[i].getQnType().equals("Multiple_many") || AllAns[i].getQnType().equals("Short_Answer")){
        		ArrayList<String> tmpStrAry = AllAns[i].getTruemulManyAns();
        		ArrayList<String> tmpFeedback = AllAns[i].getMulFeedback();
        		if(AllAns[i].getQnType().equals("Multiple_many")){
        			
        		}
        		
        	}
        	else if(AllAns[i].getQnType().equals("True_false")){
        		String[] tfArr = pAns.parseTrueFalse();
        		AllAns[i].setTrueAns(tfArr[0]);
        		AllAns[i].setFeedback(tfArr[1]);
        		AllAns[i].setSubAns("");
        	}
        	else if(AllAns[i].getQnType().equals("Numeric")){
        		ArrayList<float[]> tmpFloatAry = pAns.parseNumeric();
        		AllAns[i].setTrueNumeric(tmpFloatAry);
        	}
        	else if(AllAns[i].getQnType().equals("Matching")){
        		ArrayList<String[]> tmpStringAry = pAns.parseMatching();
        		ArrayList<String[]> tmpSubStrAry = new ArrayList<String[]>();
        		for(int i1 = 0;i1<tmpStringAry.size();i1++){
        			String[] tmpAr = new String[2];
        			tmpAr[0] = tmpStringAry.get(i1)[0];
        			tmpAr[1] = "Select a Match";
        			tmpSubStrAry.add(tmpAr);
        		}
        		AllAns[i].setSubMatch(tmpSubStrAry);
        		AllAns[i].setTrueMatch(tmpStringAry);
        	}*/
		}
		
		tmpHTML += endHtml(2);
		tmpHTML += endHtml(1);
		
		mWebView = (WebView) findViewById(R.id.summary_page);
		mWebView.loadData(tmpHTML, "text/html",null);
		//Log.d("Size Received ---", ""+ AllAns.length+ AllAns[0].getTruemulOptAns() + "--" + AllAns[0].getSubmulOptAns());
	}
	
	public String genHTML(int totQns, int crtQns, int wrngQns, int prtQns){
		String html = "<html><head><title>Summary of Quiz </title><style>body{font-family:'Times New Roman';}.summary td{padding:5px;}.correct {color:green;}.wrong {color:red;}.partial {color:orange;}.left{text-align:left; padding-left:5%;}.right{text-align:right; padding-right:2%;}.left-clear{text-align:left;}.center{text-align:center;} </style></head><body><br><h3 align='center' >Summary of Quiz Question</h3><table class='summary' align='center' width='90%' style='text-align:center;border:1px solid black;padding:20px;'><tr><th width='70%' class='left-clear'>Total Questions </th><td width='30%' >9</td></tr>";
		html += "<tr><th width='70%' class='left-clear'>Total Questions </th><td width='30%' >"+totQns+"</td></tr>";
		html += "<tr><th class='left-clear'>Correct Questions </th><td >"+crtQns+"</td></tr>";
		html += "<tr><th class='left-clear'>Wrong Questions </th><td >"+wrngQns+"</td> </tr>";
		html +=	"<tr><th class='left-clear'>Partial Correct Questions </th><td >"+prtQns+"</td></tr>";
		html += "<tr style='padding:10px;'><th style='text-align:left;border-top:1px solid black;'>Grand Total </th><td style='border-top:1px solid black;'>900 pt</td> </tr></table><br>";
		return html;
	}
	
	public String getQnTitleHtml(String QnTitle, int No, String cls, float pts){
		String html = "<tr ><th width='75%' class='left-clear'>Question "+No+" &raquo; <small class='"+cls+"'>"+cls+"</small></th><th width='25%' class='right'> "+pts+" pt </th></tr>";
		html += "<td colspan='2' style='border-top:1px solid black; padding-top:15px;'>"+QnTitle+"</td></tr>";
		return html;
	}
	
	public String getAnsHtml(String trueString, String subString){
		String html = "<tr><td>"+trueString+"</td><td class='right'>"+subString+"</td></tr>";
		return html;
	}
	
	public String endHtml(int type){
		String html = "";
		if(type == 1){
			html += "</body></html>";
		}else if(type == 2){
			html += "</table>";
		}else if(type == 3){
			html += "<h3 align='center' >Questions and Answers</h3><table class='summary' align='center' width='90%' style='border:1px solid black;padding:20px;'>";
		}
		return html;
	}
	
}
