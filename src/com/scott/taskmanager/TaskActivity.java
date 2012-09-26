package com.scott.taskmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TaskActivity extends Activity{	
	//private ArrayList<String> taskNames;	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_activity);
		//taskNames = new ArrayList<String>();
		
		Button btnNewTask = (Button) findViewById(R.id.new_task);
		final EditText taskName = (EditText) findViewById(R.id.new_task_name);
		
		OnClickListener listener = new OnClickListener(){
			public void onClick(View v){
				/*
				if(taskNames.contains(taskName.getText().toString())){
					Context context = getApplicationContext();
					CharSequence txt = "A task of that name already exits. Please choose another";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, txt, duration);
					toast.show();
					taskName.setText("");
				}else{*/
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					TaskListFragment task = new TaskListFragment();
					fragmentTransaction.add(R.id.fragment_container, task, taskName.getText().toString());
					fragmentTransaction.commit();
					//taskNames.add(taskName.getText().toString());
				//}
			}
		};		
		btnNewTask.setOnClickListener(listener);
	}
	
	public void onDestroy(){
		super.onDestroy();		
	}
	
}
