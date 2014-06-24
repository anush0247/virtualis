package com.aakash.vlabs.quiz;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.aakash.vlabs.quiz.ParseAnswer.McqOpts;

public class Question extends Fragment  {
	
	OnAnswered mySavedAns;
	
	int currentId = 1;
	MyAns savedAns = null, tmpAns = null;
	
	String Gift_qn = "";
	String[] parts = {};
	
	//true or false 
	String trueAns,feedback,submitedAns = "";
	RadioGroup tfGroup;
	
	public interface OnAnswered{
		public void updateAns(int QnNo, MyAns ans);
	}
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.question, container, false);
		LinearLayout qun_layout = (LinearLayout) view.findViewById(R.id.myquestions_page);
		TextView mytext = new TextView(view.getContext());
		
		ParseQuestion pQuestion = new ParseQuestion(Gift_qn);
		parts = pQuestion.parts;
		
		mytext.setText("Question "+ currentId + ". "+parts[0] +"\n" 
				+ parts[1] + "\n"
				+ parts[2] + "\n"
				+ parts[3] );
		mytext.setPadding(10, 5, 10, 5);
		mytext.setTextSize(18);
		qun_layout.addView(mytext);
		
		ParseAnswer pAns = new ParseAnswer(parts[2]);
		
		if(parts[3].equals("Multiple") || parts[3].equals("Multiple_many") || parts[3].equals("Short_Answer")){
			Log.d("I found :", parts[2]);
			
			ArrayList<McqOpts> list = pAns.parseMCQ(); 
			for(int i = 0;i<list.size();i++){
				Log.d(""+i,list.get(i).toString());
			}
			// send this list to draw layout
		}
		else if(parts[3].equals("True_false")){
			
			tfGroup = new RadioGroup(view.getContext());
			tfGroup.setTag("RadioGroup"+currentId);
			
			RadioButton trueBtn = new RadioButton(view.getContext());
			trueBtn.setText("True");
			trueBtn.setTag("True");
			trueBtn.setId(0);
			if(savedAns.getSubAns().equals("True")){
				trueBtn.setChecked(true);
			}
			
			RadioButton falseBtn = new RadioButton(view.getContext());
			falseBtn.setText("False");
			falseBtn.setTag("False");
			falseBtn.setId(1);
			if(savedAns.getSubAns().equals("False")){
				falseBtn.setChecked(true);
			}
			
			tfGroup.addView(trueBtn);
			tfGroup.addView(falseBtn);
			tfGroup.setOrientation(0);
			qun_layout.addView(tfGroup);
			
			tfGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup rg, int index) {
					// TODO Auto-generated method stub
					
					submitedAns = (String) rg.getChildAt(index).getTag();
					tmpAns = savedAns;
					tmpAns.setSubAns(submitedAns);
					mySavedAns.updateAns(currentId-1, tmpAns);
				}
			});
			 
			// send this trueAns, feedback to draw layout or create here itself
		}
		else if(parts[3].equals("Numeric")){
			ArrayList<float[]> numList = pAns.parseNumeric();
			for(int i = 0;i<numList.size();i++){
				Log.d("Numeric : ", "Num : "+numList.get(i)[0]+" +/- " + numList.get(i)[1] + " Weight : " + numList.get(i)[2]);
			}
			// send this numList to draw layout
		}
		else if(parts[3].equals("Matching")){
			ArrayList<String[]> matchingList = pAns.parseMatching();
			for(int i = 0;i<matchingList.size();i++){
				Log.d("Matching : " + i, "Question : "+matchingList.get(i)[0]+" +---+ " + matchingList.get(i)[1] );
			}
			// send this matchingList to draw layout
		}
		
		return view;
	}
	
	
	// Setter & Getter 
	public void setId(int id){
		this.currentId = id;
	}
	
	public void setGift_qn(String str){
		this.Gift_qn = str;
	}
	
	public void setSavedAns(MyAns myans){
		this.savedAns = myans;
	}
	
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mySavedAns = (OnAnswered) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAnswered method");
        }
    }
}
