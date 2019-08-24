package com.xlyd.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) {
		Container cont = new Container();
		
		Producer p1 = new Producer(cont);
		Producer p2 = new Producer(cont);
		Consumer c1 = new Consumer(cont);
		Consumer c2 = new Consumer(cont);
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		service.execute(p1);
		service.execute(p2);
		service.execute(c1);
		service.execute(c2);

		try {
			service.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();
	}
}
