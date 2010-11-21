package net.oliver.olframework.multithread.schedule;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		int delay = 5000; 
		Timer timer = new Timer(); 
		
		NewTask myTask = new NewTask(timer); 
		timer.schedule(myTask, delay); 
		
		/*myTask.cancel();
		
		Thread mythread = new Thread(new NewThread());
		mythread.start();*/
		
	}
}

class NewThread implements Runnable
{
	public void run() {
		System.out.println("printing!");
	}
	
}

class NewTask extends TimerTask {
	private Timer timer;
	
	public NewTask(Timer timer) {
		super();
		this.timer = timer;
	}


	public void run() {
		System.out.println("printing!");
	}
}
