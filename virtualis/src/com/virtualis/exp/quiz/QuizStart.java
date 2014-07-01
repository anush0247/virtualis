package com.virtualis.exp.quiz;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.virtualis.R;
import com.virtualis.all.Splash;
import com.virtualis.exp.quiz.ParseAnswer.McqOpts;
import com.virtualis.exp.quiz.Question.OnAnswered;

public class QuizStart extends Activity implements OnAnswered{

	Button prev,next;
	int currnt_qn = 1,next_qn = 2,prev_qn = 1,total_qn=10;
	int correct = 0,wrong = 0,grand = 0;
	
	QuizStart tmpQuizStart;
	FragmentManager fragmentManager ;
	FragmentTransaction fragmentTransaction;
	
	TextView qn_status;
	
	MyAns[] AllAns = null;
	ArrayList<String> Qns = new ArrayList<String>();
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.one_quiz, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	Intent quiz_summary= new Intent(getApplicationContext(),
				Summary.class);
		Bundle b = new Bundle();
		b.putParcelableArray("allAns", AllAns);
		quiz_summary.putExtras(b);
		startActivity(quiz_summary);
		return super.onOptionsItemSelected(item);
	}


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_quiz_quiz_start);
        
        tmpQuizStart = this;
        Qns = getIntent().getStringArrayListExtra("qn_array");
        
        total_qn = Qns.size();
        wrong = total_qn;
        AllAns = new MyAns[total_qn];
        for(int i=0;i<total_qn;i++){
        	ParseQuestion pQuestion = new ParseQuestion(Qns.get(i));
        	AllAns[i] = new MyAns(i);
        	AllAns[i].setQn(pQuestion.parts[1]);
        	AllAns[i].setQnType(pQuestion.parts[3]);
        	AllAns[i].setIsCorrect(0);
    		AllAns[i].setIsPartial(0);
    		AllAns[i].setIsAnswred(0);
    		AllAns[i].setSubString("Not Answered");
    		AllAns[i].setScoredWeight(0);
        	
        	ParseAnswer pAns = new ParseAnswer(pQuestion.parts[2]);
        	if(AllAns[i].getQnType().equals("Multiple_many") || AllAns[i].getQnType().equals("Short_Answer") || AllAns[i].getQnType().equals("Multiple")){
        		ArrayList<String> tmpStrAry = new ArrayList<String>();
        		ArrayList<String> tmpFeedback = new ArrayList<String>();
        		ArrayList<float[]> tmpWeigh = new ArrayList<float[]>();
        		String trueStr = "<span class='correct'><br>";
        		ArrayList<McqOpts> mcq = pAns.parseMCQ();int num = 0;
        		for(int i1 = 0;i1<mcq.size();i1++){
        			tmpWeigh.add(new float[]{mcq.get(i1).weight});
        			tmpFeedback.add(mcq.get(i1).feedback);
        			
        			if(AllAns[i].getQnType().equals("Multiple_many")){
    					if(mcq.get(i1).weight > 0) trueStr += (++num)+". "+mcq.get(i1).value+"<br>";
    					tmpStrAry.add(mcq.get(i1).value);
        			}
					
        			if(mcq.get(i1).isAns){
        				if(AllAns[i].getQnType().equals("Multiple")){
        					AllAns[i].setTruemulOptAns(mcq.get(i1).value);
        					AllAns[i].setTrueString("<span class='correct'>"+mcq.get(i1).value+"</span>");
        				}
        				else {
        					trueStr += mcq.get(i1).value+"<br>";
        					tmpStrAry.add(mcq.get(i1).value);
        				}
        			}
        		}
        		
        		trueStr += "</span>";
        		
        		if(AllAns[i].getQnType().equals("Multiple")){
        			AllAns[i].setSubmulOptAns("");
        		}
        		else if(AllAns[i].getQnType().equals("Short_Answer")){
        			AllAns[i].setTrueString(trueStr);
        			AllAns[i].setSubShortAns("");
        			AllAns[i].setTruemulManyAns(tmpStrAry);
        		}else {
        			AllAns[i].setTrueString(trueStr);
        			ArrayList<String> tmpSubAry = new ArrayList<String>();
            		AllAns[i].setSubmulManyAns(tmpSubAry);
            		AllAns[i].setTruemulManyAns(tmpStrAry);
        		}
        		
        		AllAns[i].setTrueMulWeight(tmpWeigh);
        		AllAns[i].setMulFeedback(tmpFeedback);
        	}
        	else if(AllAns[i].getQnType().equals("True_false")){
        		String[] tfArr = pAns.parseTrueFalse();
        		AllAns[i].setTrueAns(tfArr[0]);
        		AllAns[i].setFeedback(tfArr[1]);
        		AllAns[i].setSubAns("");
        		AllAns[i].setTrueString("<span class='correct'>"+tfArr[0] + "</span>");
        	}
        	else if(AllAns[i].getQnType().equals("Numeric")){
        		ArrayList<float[]> tmpFloatAry = pAns.parseNumeric();
        		String numStr = "<span class='correct'> <br>";
        		for(int i1 = 0; i1< tmpFloatAry.size();i1++){
        			numStr += tmpFloatAry.get(i1)[0] + " +/- " +  tmpFloatAry.get(i1)[1] +"<br>"; 
        		}
        		numStr += "</span>";
        		AllAns[i].setTrueString(numStr);
        		AllAns[i].setTrueNumeric(tmpFloatAry);
        	}
        	else if(AllAns[i].getQnType().equals("Matching")){
        		ArrayList<String[]> tmpStringAry = pAns.parseMatching();
        		ArrayList<String[]> tmpSubStrAry = new ArrayList<String[]>();
        		String matStr = "<span class='correct'> <br>";//subStr = "Your Answer <br> ";
        		for(int i1 = 0;i1<tmpStringAry.size();i1++){
        			String[] tmpAr = new String[2];
        			tmpAr[0] = tmpStringAry.get(i1)[0];
        			tmpAr[1] = "Select a Match";
        			matStr += tmpAr[0] +" &rarr; " + tmpStringAry.get(i1)[1] + "<br>" ;
        			tmpSubStrAry.add(tmpAr);
        		}
        		AllAns[i].setIsCorrect(0);
        		AllAns[i].setIsPartial(0);
        		AllAns[i].setScoredWeight(0);
        		AllAns[i].setTrueString(matStr);
        		AllAns[i].setSubString("Not Answered");
        		AllAns[i].setSubMatch(tmpSubStrAry);
        		AllAns[i].setTrueMatch(tmpStringAry);
        	}
        	else {
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
		
		AlertDialog.Builder goBack = new AlertDialog.Builder(QuizStart.this);
		goBack.setTitle("Confirmation");
		goBack.setCancelable(false);
		goBack.setMessage(Html.fromHtml("<center><p>Are you sure, do you want to close Quiz now ? </p></center>"));  
		goBack.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tmpQuizStart.finish();
				tmpQuizStart.onBackPressed();
			}
		});
		goBack.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface my, int arg1) {
				// TODO Auto-generated method stub
				my.dismiss();
			}
		});
		goBack.show(); 
		
		
	}

	@Override
	public void updateAns(int QnNo, MyAns ans) {
		// TODO Auto-generated method stub
		AllAns[QnNo] = ans;
		AllAns[QnNo].setIsAnswred(1);
		if(AllAns[QnNo].getQnType().equals("True_false")){
			String msg = "Your Answer is ";
			if(AllAns[QnNo].getTrueAns().equals(AllAns[QnNo].getSubAns())){
				msg += "Correct";
				AllAns[QnNo].setIsCorrect(1);
				AllAns[QnNo].setCssCls("correct");
				AllAns[QnNo].setScoredWeight(100);
				AllAns[QnNo].setSubString("Your Ans : <span class='correct'>"+AllAns[QnNo].getTrueAns()+"<br>#"+AllAns[QnNo].getFeedback()+"</span>");
			}
			else {
				AllAns[QnNo].setIsCorrect(0);
				AllAns[QnNo].setCssCls("wrong");
				AllAns[QnNo].setScoredWeight(0);
				msg += "Wrong";
				AllAns[QnNo].setSubString("Your Ans : <span class='wrong'>"+AllAns[QnNo].getSubAns()+"</span>");
			}
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
		else if(AllAns[QnNo].getQnType().equals("Multiple")){
			String msg = "Your Answer is ";
			AllAns[QnNo].setScoredWeight(AllAns[QnNo].getTrueMulWeight().get(AllAns[QnNo].getMulOptindex())[0]);
			if(AllAns[QnNo].getTruemulOptAns().equals(AllAns[QnNo].getSubmulOptAns())){
				msg += "Correct";
				AllAns[QnNo].setIsCorrect(1);
				AllAns[QnNo].setCssCls("correct");
				AllAns[QnNo].setSubString("Your Ans : <span class='correct'>"+AllAns[QnNo].getTruemulOptAns()+"<br>#"+AllAns[QnNo].getMulFeedback().get(AllAns[QnNo].getMulOptindex())+"</span>");
			}
			else {
				AllAns[QnNo].setIsCorrect(0);
				AllAns[QnNo].setCssCls("wrong");
				
				String cls = "wrong";
				if(AllAns[QnNo].getScoredWeight()>0){
					cls = "partial";
					msg += "Partially Correct";
					AllAns[QnNo].setIsPartial(1);
					AllAns[QnNo].setCssCls("partial");
				}
				else msg += "Wrong";
				AllAns[QnNo].setSubString("Your Ans : <span class='"+cls+"'>"+AllAns[QnNo].getSubmulOptAns()+"<br>#"+AllAns[QnNo].getMulFeedback().get(AllAns[QnNo].getMulOptindex())+"</span>");
			}
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
		else if(AllAns[QnNo].getQnType().equals("Multiple_many")){
			float num = 0;
			String tmp = "";
			for(int i = 0;i<AllAns[QnNo].getSubmulManyAns().size();i++){
				for(int j=0;j<AllAns[QnNo].getTruemulManyAns().size();j++){
					if(AllAns[QnNo].getTruemulManyAns().get(j).equals(AllAns[QnNo].getSubmulManyAns().get(i)) && AllAns[QnNo].getTrueMulWeight().get(j)[0] > 0 ){
						num += AllAns[QnNo].getTrueMulWeight().get(j)[0];
						tmp += "<span class='correct'>"+AllAns[QnNo].getSubmulManyAns().get(i)+"</span><br>";
					}
					else if(AllAns[QnNo].getTruemulManyAns().get(j).equals(AllAns[QnNo].getSubmulManyAns().get(i))) {
						tmp += "<span class='wrong'>"+AllAns[QnNo].getSubmulManyAns().get(i)+"</span><br>";
					}
				}
			}
			
			if(num > 0 ){
				if(num == 100){
					AllAns[QnNo].setIsCorrect(1);
					AllAns[QnNo].setCssCls("correct");
				}
				else {
					AllAns[QnNo].setIsPartial(1);
					AllAns[QnNo].setCssCls("partial");
				}
			}
			else {
				AllAns[QnNo].setIsCorrect(0);
				AllAns[QnNo].setCssCls("wrong");
			}
			
			AllAns[QnNo].setScoredWeight(num);
			AllAns[QnNo].setSubString("Your Ans : <br>"+tmp);
			Toast.makeText(getApplicationContext(), num + " Answers Correct", Toast.LENGTH_SHORT).show();
		}
		else if(AllAns[QnNo].getQnType().equals("Short_Answer")){
			String msg = "Your Answer is ";
			AllAns[QnNo].setCssCls("wrong");
			AllAns[QnNo].setSubString(msg+"<span class='wrong'>"+AllAns[QnNo].getSubShortAns()+"</span>");
			for(int i = 0; i< AllAns[QnNo].getTruemulManyAns().size(); i++){
				if(AllAns[QnNo].getTruemulManyAns().get(i).contains(AllAns[QnNo].getSubShortAns())){
					AllAns[QnNo].setCssCls("correct");
					AllAns[QnNo].setIsCorrect(1);
					AllAns[QnNo].setScoredWeight(AllAns[QnNo].getTrueMulWeight().get(i)[0]);
					AllAns[QnNo].setSubString(msg+"<span class='correct'>"+AllAns[QnNo].getSubShortAns()+"</span>");
				}
			}
		}
		else if(AllAns[QnNo].getQnType().equals("Numeric")){
			
			String msg = "Your Answer is ";
			AllAns[QnNo].setCssCls("wrong");
			AllAns[QnNo].setIsCorrect(0);
			AllAns[QnNo].setScoredWeight(0);
			AllAns[QnNo].setSubString(msg+"<span class='wrong'>"+AllAns[QnNo].getSubNumeric()+"</span>");
			for(int i = 0; i< AllAns[QnNo].getTrueNumeric().size(); i++){
				float tmp1 = AllAns[QnNo].getTrueNumeric().get(i)[0] + AllAns[QnNo].getTrueNumeric().get(i)[1]; 
				float tmp2 = AllAns[QnNo].getTrueNumeric().get(i)[0] - AllAns[QnNo].getTrueNumeric().get(i)[1]; 
				float tmp3 = AllAns[QnNo].getSubNumeric();
				if(tmp3 <= tmp1 && tmp3 >= tmp2){
					AllAns[QnNo].setCssCls("correct");
					AllAns[QnNo].setIsCorrect(1);
					AllAns[QnNo].setScoredWeight(AllAns[QnNo].getTrueNumeric().get(i)[2]);
					AllAns[QnNo].setSubString(msg+"<span class='correct'>"+AllAns[QnNo].getSubNumeric()+"</span> ");
				}
			}
		}
		else if(AllAns[QnNo].getQnType().equals("Matching")){
			String ab = "Your Answers : <br>";
			float num = 0;
			for(int i = 0;i<AllAns[QnNo].getSubMatch().size();i++){
				if(AllAns[QnNo].getTrueMatch().get(i)[1].equals(AllAns[QnNo].getSubMatch().get(i)[1])){
					num++;
					ab += "<span class='correct'> &rarr; "+AllAns[QnNo].getSubMatch().get(i)[1] + "</span><br>";
				}
				else {
					ab += "<span class='wrong'> &rarr; "+AllAns[QnNo].getSubMatch().get(i)[1] + "</span><br>";
				}
				
			}
			if(num > 0 ){
				if(num == AllAns[QnNo].getSubMatch().size()){
					AllAns[QnNo].setIsCorrect(1);
					AllAns[QnNo].setCssCls("correct");
				}
				else {
					AllAns[QnNo].setIsPartial(1);
					AllAns[QnNo].setCssCls("partial");
				}
			}
			else {
				AllAns[QnNo].setIsCorrect(0);
				AllAns[QnNo].setCssCls("wrong");
			}
			AllAns[QnNo].setScoredWeight(((num)*(100/AllAns[QnNo].getSubMatch().size())));
			AllAns[QnNo].setSubString(ab);
		}
	}
}