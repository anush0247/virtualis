package com.aakash.vlabs.quiz;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuizStart extends Activity {

	Button prev,next;
	int currnt_qn = 0,next_qn = 1,prev_qn = 0;
	
	FragmentManager fragmentManager ;
	FragmentTransaction fragmentTransaction;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);
        
        fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		Question currentQuestion = new Question();
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
				nextQuestion.setId(prev_qn);
				next_qn = currnt_qn;
				currnt_qn = prev_qn;
				prev_qn--;
				
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frag_question, nextQuestion);
				fragmentTransaction.addToBackStack(null);
		        fragmentTransaction.commit();
			}
		});
        
        next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Question nextQuestion = new Question();
				nextQuestion.setId(next_qn);
				prev_qn = currnt_qn;
				currnt_qn = next_qn;
				next_qn++;
				
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.frag_question, nextQuestion);
				fragmentTransaction.addToBackStack(null);
		        fragmentTransaction.commit();
				
			}
		});
        
    }
}
