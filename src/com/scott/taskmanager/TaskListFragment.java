package com.scott.taskmanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class TaskListFragment extends Fragment {	
	long elapsedTime;
	Chronometer stopWatch;
	String name;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.taskitem_view, null);	
		
		elapsedTime = SystemClock.elapsedRealtime();
		stopWatch = (Chronometer) view.findViewById(R.id.task_time);
		
		Button.OnClickListener startListener = new Button.OnClickListener(){
			public void onClick(View v){
				//code courtesy of stackoverflow
				 int stoppedMilliseconds = 0;
		         String chronoText = stopWatch.getText().toString();
		         String array[] = chronoText.split(":");
		         if (array.length == 2) {
		           stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
		               + Integer.parseInt(array[1]) * 1000;
		         } else if (array.length == 3) {
		           stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 
		               + Integer.parseInt(array[1]) * 60 * 1000
		               + Integer.parseInt(array[2]) * 1000;
		         }
		        stopWatch.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
				stopWatch.start();
			}
		};
		
		final Activity activity = getActivity();
		final TaskListFragment thisTask = this;
		Button.OnClickListener deleteListener = new Button.OnClickListener(){
			public void onClick(View v){
				FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
				ft.remove(thisTask);
				ft.commit();
			}
		};
		
		Button.OnClickListener stopListener = new Button.OnClickListener(){
			public void onClick(View v){
				//elapsedTime = SystemClock.elapsedRealtime() - stopWatch.getBase();
				stopWatch.stop();
			}
		};
		
		Button.OnClickListener resetListener = new Button.OnClickListener(){
			public void onClick(View v){
				stopWatch.stop();
				stopWatch.setBase(SystemClock.elapsedRealtime());
			}
		};

		Button startButton = (Button) view.findViewById(R.id.start_button);
		startButton.setOnClickListener(startListener);

		Button pauseButton = (Button) view.findViewById(R.id.pause_button);
		pauseButton.setOnClickListener(stopListener);
		
		Button restartButton = (Button) view.findViewById(R.id.restart_button);
		restartButton.setOnClickListener(resetListener);
		
		Button deleteButton = (Button) view.findViewById(R.id.delete_button);
		deleteButton.setOnClickListener(deleteListener);
		
		return view;
	}
	
	public void onAttach(Activity activity){
		
		super.onAttach(activity);
		EditText taskName = (EditText) activity.findViewById(R.id.new_task_name);
		name = taskName.getText().toString();	
	}
	
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		Activity parent= getActivity();
		View fragView = this.getView();
		EditText taskName = (EditText) parent.findViewById(R.id.new_task_name);
		TextView taskTitle = (TextView) fragView.findViewById(R.id.taskName);		
		taskTitle.setText(name);
		taskName.setText("");
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
}
