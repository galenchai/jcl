package net.galenchai.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class TestCompletionService {

	/**
	 * CompletionService will wait for the next thread is done and pick it
	 */
	@Test
	public void testCompletionService() {
		ExecutorService es = Executors.newCachedThreadPool();
		CompletionService<String> cs = new ExecutorCompletionService<String>(es);
		for (int i = 0; i < 5; i ++) {
			cs.submit(new Callable<String>() {
				public String call() {
					for (int j = 0; j < 10000000; j ++) {
						System.out.println(Thread.currentThread().getName());
					}
					return Thread.currentThread().getName();
				}
			});
		}
		System.out.println("--------------------------------------------------------------------");
		for (int i = 0; i < 5; i ++) {
			try {
				System.out.println(cs.take().get() + " has finished!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("=======================================================================");
		System.out.println("I finished!");
	}
	
}
