package com.xlyd.thread.timer_timertask;

import java.util.Timer;
import java.util.TimerTask;

public class Demo {

	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
		
				System.out.println("alarm going off");
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 0, 1000);
//		timer.cancel();
	}
}
