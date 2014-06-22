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

public class QuizStart extends Activity {

	Button prev,next;
	int currnt_qn = 1,next_qn = 2,prev_qn = 1,total_qn=10;
	
	FragmentManager fragmentManager ;
	FragmentTransaction fragmentTransaction;
	
	TextView qn_status;
	ArrayList<String> Qns = new ArrayList<String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);
        
        Qns = getIntent().getStringArrayListExtra("qn_array");
       //for(int i = 0; i<Qns.size();i++){
        //	Log.d("Received Question "+i, Qns.get(i));
        //}
        
        total_qn = Qns.size();
        qn_status = (TextView) findViewById(R.id.quiz_qno_total);
        qn_status.setText("Quiz Question : "+(currnt_qn)+" / "+total_qn);
        
        fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		Question currentQuestion = new Question();
		currentQuestion.setGift_qn(Qns.get(currnt_qn-1));
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
		//Toast.makeText(getApplicationContext(), "" + currnt_qn + "" + prev_qn + "" + next_qn, Toast.LENGTH_SHORT).show();
		this.finish();
		super.onBackPressed();
		
	}
    
    
}
