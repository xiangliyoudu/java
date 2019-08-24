package com.xlyd.thread.wait_notify;

public class Producer implements Runnable {

	private final Container container;

	public Producer(Container container) {
		super();
		this.container = container;
	}

	@Override
	public void run() {
//		for (char ch = 'A'; ch <= 'Z'; ch++) {
//			this.container.setChar(ch);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(ch + " is produced by " + Thread.currentThread().getName());
//		}
		
		while(true){

			try {
				this.container.setNumber();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
