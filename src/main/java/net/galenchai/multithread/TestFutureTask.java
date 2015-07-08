package net.galenchai.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * FutureTask is asynchronous taks. 
 * When we start it, the program will not wait for its return. Instead, the program will execute the following codes
 * But when invoking futureTask.get(), the thread will be blocked to ensure we get the result
 * @author caizhen
 */
public class TestFutureTask {
	
	public static void main(String args[]) {
		Callable<Integer> cal = new Callable<Integer>() {
			public Integer call() {
				System.out.println("I need some sleep!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("I am getting up!");
				return new Integer(8);
			}
		};
		FutureTask<Integer> futureTask = new FutureTask<Integer>(cal);
		Thread newThread = new Thread(futureTask);
		newThread.start();
		System.out.println("Am I sleeping?");
		Integer result = null;
		try {
			result = futureTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("Am I waking up?");
		System.out.println(result);
	}

}
