package org.example.threads;

import java.util.concurrent.*;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(new MyCallable());

		while (!future.isDone()){
			System.out.println("Waiting for an answer");
		}

		try {
			System.out.println("Callable result: "+ future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Print me after getting the result");

		executorService.shutdown();
	}
}

class MyCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("Callable initiating");
		Thread.sleep(5000);
		return "I did some calculations and the result is 3.14";
	}
}
