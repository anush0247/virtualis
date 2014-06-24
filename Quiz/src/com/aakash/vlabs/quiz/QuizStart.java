package com.aakash.vlabs.quiz;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aakash.vlabs.quiz.ParseAnswer.McqOpts;
import com.aakash.vlabs.quiz.Question.OnAnswered;

public class QuizStart extends Activity implements OnAnswered{

	Button prev,next;
	int currnt_qn = 1,next_qn = 2,prev_qn = 1,total_qn=10;
	
	FragmentManager fragmentManager ;
	FragmentTransaction fragmentTransaction;
	
	TextView qn_status;
	
	MyAns[] AllAns = null;
	ArrayList<String> Qns = new ArrayList<String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);
        
        Qns = getIntent().getStringArrayListExtra("qn_array");
        
        total_qn = Qns.size();
        AllAns = new MyAns[total_qn];
        for(int i=0;i<total_qn;i++){
        	ParseQuestion pQuestion = new ParseQuestion(Qns.get(i));
        	AllAns[i] = new MyAns(i);
        	AllAns[i].setQn(pQuestion.parts[1]);
        	AllAns[i].setQnType(pQuestion.parts[3]);
        	
        	ParseAnswer pAns = new ParseAnswer(pQuestion.parts[2]);
        	if(AllAns[i].getQnType().equals("Multiple")){
        		AllAns[i].setSubmulOptAns("");
        		ArrayList<McqOpts> mcq = pAns.parseMCQ();
        		for(int i1 = 0;i1<mcq.size();i1++){
        			if(mcq.get(i1).isAns){
        				AllAns[i].setTruemulOptAns(mcq.get(i1).value);
        				AllAns[i].setFeedback(mcq.get(i1).feedback);
        			}
        		}
        	}
        	else if(AllAns[i].getQnType().equals("Multiple_many") || AllAns[i].getQnType().equals("Short_Answer")){
        		ArrayList<String> tmpStrAry = new ArrayList<String>();
        		ArrayList<String> tmpFeedback = new ArrayList<String>();
        		ArrayList<String> tmpSubAry = new ArrayList<String>();
        		ArrayList<McqOpts> mcq = pAns.parseMCQ();
        		for(int i1 = 0;i1<mcq.size();i1++){
        			if(mcq.get(i1).isAns){
        				tmpStrAry.add(mcq.get(i1).value);
        				tmpFeedback.add(mcq.get(i1).feedback);
        			}
        		}
        		AllAns[i].setSubmulManyAns(tmpSubAry);
        		AllAns[i].setTruemulManyAns(tmpStrAry);
        		AllAns[i].setMulFeedback(tmpFeedback);
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
        		AllAns[i].setTrueMatch(tmpStringAry);
        	}
        	else {
        		Log.d("Error #"+i,"Unable to identify type of Question");
        	}
        }
        
        qn_status = (TextView) findViewById(R.id.quiz_qno_total);
        qn_status.setText("Quiz Question : "+(currnt_qn)+" / "+total_qn);
        
        fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		Question currentQuestion = new Question();
		currentQuestion.setGift_qn(Qns.get(currnt_qn-1));
		currentQuestion.setSavedAns(AllAns[currnt_qn-1]);
		fragmentTransaction.add(R.id.frag_question, currentQuestion);
		fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        
        
        
        prev = (Button) findViewById(R.id.quiz_qn_prev);
        next = (Button) findViewById(R.id.quiz_qn_next);
        
        prev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Question nextQuestion = new Question();
				
				if(currnt_qn <= 1) {
					prev_qn = 1;
					next_qn = 2;
				}
				else {
					nextQuestion.setId(prev_qn);
					nextQuestion.setGift_qn(Qns.get(prev_qn-1));
					nextQuestion.setSavedAns(AllAns[prev_qn-1]);
					qn_status.setText("Quiz Question : "+(prev_qn)+" / "+total_qn);
					next_qn = currnt_qn;
					currnt_qn = prev_qn;
					prev_qn--;
					fragmentTransaction = fragmentManager.beginTransaction();
					fragmentTransaction.replace(R.id.frag_question, nextQuestion);
					fragmentTransaction.addToBackStack(null);
			        fragmentTransaction.commit();
				}
			}
		});
        
        next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Question nextQuestion = new Question();
				
				if(currnt_qn >= total_qn) {
					prev_qn = total_qn-1;
					next_qn = total_qn;
				}
				else {
					nextQuestion.setId(next_qn);
					nextQuestion.setGift_qn(Qns.get(next_qn-1));
					nextQuestion.setSavedAns(AllAns[next_qn-1]);
					qn_status.setText("Quiz Question : "+(next_qn)+" / "+total_qn);
					prev_qn = currnt_qn;
					currnt_qn = next_qn;
					next_qn++;
					
					fragmentTransaction = fragmentManager.beginTransaction();
					fragmentTransaction.replace(R.id.frag_question, nextQuestion);
					fragmentTransaction.addToBackStack(null);
			        fragmentTransaction.commit();
				}
				
				
			}
		});
        
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.finish();
		super.onBackPressed();
		
	}

	@Override
	public void updateAns(int QnNo, MyAns ans) {
		// TODO Auto-generated method stub
		AllAns[QnNo] = ans;
		
		if(AllAns[QnNo].getQnType().equals("True_false")){
			Log.d("Updating ...", ""+QnNo+""+AllAns[QnNo].getSubAns());
			String msg = "Your Answer is ";
			if(AllAns[QnNo].getTrueAns().equals(AllAns[QnNo].getSubAns()))msg += "Correct";
			else msg += "Wrong";
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
		else if(AllAns[QnNo].getQnType().equals("Multiple")){
			Log.d("Updating ...", ""+QnNo+""+AllAns[QnNo].getSubmulOptAns());
			String msg = "Your Answer is ";
			if(AllAns[QnNo].getTruemulOptAns().equals(AllAns[QnNo].getSubmulOptAns()))msg += "Correct";
			else msg += "Wrong";
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
		
		
	}

    
}
