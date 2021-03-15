package org.example.threads;

public class MySimpleThread implements Runnable{
	private String name;

	public MySimpleThread(String name) {
		this.name = name;
		System.out.println(name + " is created.");
	}

	@Override
	public void run() {
		System.out.println(name + " is starting.");

		for (int i = 0; i < 10; i++) {
			System.out.println(name + " iterates " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(name +" was interrupted");
				e.printStackTrace();
			}
		}
		System.out.println(name + " finished.");
	}
}

class MySimpleThreadMain{
	public static void main(String[] args) {
		Thread t1 = new Thread(new MySimpleThread("Thread 1"));
		Thread t2 = new Thread(new MySimpleThread("Thread 2"));
		t1.start();
		t2.start();
	}
}

class MySimpleThreadWithJoin{
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new MySimpleThread("Thread 1"));
		Thread t2 = new Thread(new MySimpleThread("Thread 2"));
		Thread t3 = new Thread(new MySimpleThread("Thread 3"));
		t1.start();
		System.out.println("Current thread:"+ Thread.currentThread().getName()+" will start waiting");
		t1.join();
		System.out.println("Join invoked by thread:"+ Thread.currentThread().getName());
		t2.start();
		System.out.println("Current thread:"+ Thread.currentThread().getName()+" will wait for 1 sec.");
		t2.join(1000);
		System.out.println("Join invoked by thread:"+ Thread.currentThread().getName()+" and 1 sec passed.");
		t3.start();
	}
}
