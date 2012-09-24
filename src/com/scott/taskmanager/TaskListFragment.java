package com.scott.taskmanager;

import java.util.Date;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

public class TaskListFragment extends Fragment {	
	long elapsedTime;
	Chronometer stopWatch;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.taskitem_view, null);	
		
		elapsedTime = SystemClock.elapsedRealtime();
		stopWatch = (Chronometer) view.findViewById(R.id.task_time);
		
		Button.OnClickListener startListener = new Button.OnClickListener(){
			public void onClick(View v){
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
		
		Button.OnClickListener stopListener = new Button.OnClickListener(){
			public void onClick(View v){
				//elapsedTime = SystemClock.elapsedRealtime() - stopWatch.getBase();
				stopWatch.stop();
			}
		};
		
		Button.OnClickListener resetListener = new Button.OnClickListener(){
			public void onClick(View v){
				stopWatch.setBase(SystemClock.elapsedRealtime());
			}
		};

		Button startButton = (Button) view.findViewById(R.id.start_button);
		startButton.setOnClickListener(startListener);

		Button pauseButton = (Button) view.findViewById(R.id.pause_button);
		pauseButton.setOnClickListener(stopListener);
		
		Button restartButton = (Button) view.findViewById(R.id.restart_button);
		restartButton.setOnClickListener(resetListener);		
		
		return view;
	}
	
	/*
	public interface OnTaskItemSelectedListener{
		public void onTaskItemSelected(String id);
	}
	
	private OnTaskItemSelectedListener onTaskItemSelected;
	
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			onTaskItemSelected = (OnTaskItemSelectedListener) activity;
		}catch(ClassCastException e){
			
		}				
	}
	
	
	*/
}
