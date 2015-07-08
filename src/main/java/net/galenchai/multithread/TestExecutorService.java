package net.galenchai.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestExecutorService {

	
	/**
	 * We can only see pool-1-thread-1 in console, this proves that there are only one thread activated each time
	 */
	//@Test
	public void testSingleThreadExecutor() {
		ExecutorService es = Executors.newSingleThreadExecutor();
		List<Callable<String>> callList = new ArrayList<Callable<String>>();
		for (int i = 0; i < 5; i ++) {
			Callable<String> call = new TestCallable();
			callList.add(call);
		}
		List<Future<String>> futureList = null;
		try {
			futureList = es.invokeAll(callList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------------------------");
		for (Future<String> fStr : futureList) {
			try {
				System.out.println(fStr.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * each time there are only 3 threads activated, so we can see the thread names are pool-1-thread-1
	 * and pool-1-thread-2 and pool-1-thread-3, no 4 and 5 
	 */
	//@Test
	public void testFixedThreadPool() {
		ExecutorService es = Executors.newFixedThreadPool(3);
		List<Callable<String>> callList = new ArrayList<Callable<String>>();
		for (int i = 0; i < 5; i ++) {
			Callable<String> call = new TestCallable();
			callList.add(call);
		}
		List<Future<String>> futureList = null;
		try {
			futureList = es.invokeAll(callList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------------------------");
		for (Future<String> fStr : futureList) {
			try {
				System.out.println(fStr.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * It will create as many threads as we need
	 */
	@Test
	public void testCachedThreadPool() {
		ExecutorService es = Executors.newCachedThreadPool();
		List<Callable<String>> callList = new ArrayList<Callable<String>>();
		for (int i = 0; i < 5; i ++) {
			Callable<String> call = new TestCallable();
			callList.add(call);
		}
		List<Future<String>> futureList = null;
		try {
			futureList = es.invokeAll(callList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------------------------");
		for (Future<String> fStr : futureList) {
			try {
				System.out.println(fStr.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
}



class TestCallable implements Callable<String> {

	public String call() throws Exception {
		for (int i = 0; i < 10; i ++) {
			System.out.println(Thread.currentThread().getName());
		}
		return Thread.currentThread().getName() + " is finished!";
	}
	
}
