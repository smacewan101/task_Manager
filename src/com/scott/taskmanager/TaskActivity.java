package com.scott.taskmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TaskActivity extends Activity{		
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_activity);
		
		Button btnNewTask = (Button) findViewById(R.id.new_task);
		
		OnClickListener listener = new OnClickListener(){
			public void onClick(View v){
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				TaskListFragment task = new TaskListFragment();
				fragmentTransaction.add(R.id.fragment_container, task, "TASK");
				fragmentTransaction.commit();				
			}
		};		
		btnNewTask.setOnClickListener(listener);
	}
	
}
