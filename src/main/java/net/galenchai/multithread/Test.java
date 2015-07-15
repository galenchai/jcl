package net.galenchai.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

	public static void main(String args[])	{
		try {
		ExecutorService es = Executors.newFixedThreadPool(12);
		List<Hello> eList = new ArrayList<Hello>();
		for (int i = 0; i < 10; i ++) {
			eList.add(new Hello());
		}
		List<Future<String>> sFList = es.invokeAll(eList); 
		//es.shutdown(); //If we submitted
		//es.awaitTermination(60L, TimeUnit.HOURS);
		//es.submit(new Hello());
//		es.submit(e);
//		es.submit(e1);
		for (Future<String> fStr : sFList) {
			System.out.println(fStr.get());
		}
		System.out.println("test");
		//es.shutdown(); //If we submitted 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Hello implements Callable<String> {

	public String call() throws Exception {
		for (int t = 0; t < 1000000; t ++) {
			System.out.println(Thread.currentThread().getName());
		}
		return "I finished";
	}
	
}