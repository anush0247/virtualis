package com.virtualis.all;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.virtualis.R;

public class ChooseExperiment extends FragmentActivity 
        implements SubjectList.OnHeadlineSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.all_subject_list);
    }

    public void onArticleSelected(int position) {
       
        ExperimentList experimentFrag = (ExperimentList)
                getSupportFragmentManager().findFragmentById(R.id.experiment_list);

        if (experimentFrag != null) {

            experimentFrag.updateArticleView(position);

        }
    }
}