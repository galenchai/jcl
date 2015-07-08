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
		ExecutorService es = Executors.newCachedThreadPool();
		Hello e = new Hello();
		Hello e1 = new Hello();
		List<Hello> eList = new ArrayList<Hello>();
		eList.add(e);
		eList.add(e1);
		List<Future<String>> sFList = es.invokeAll(eList);
//		es.submit(e);
//		es.submit(e1);
		for (Future<String> fStr : sFList) {
		//	if (fStr.isDone()) {
				System.out.println(fStr.get());
		//	}
		}
		System.out.println("test");
		es.shutdown();
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
		for (int t = 0; t <= 100000; t ++) {
			System.out.println(Thread.currentThread().getName());
		}
		return "I finished";
	}
	
}