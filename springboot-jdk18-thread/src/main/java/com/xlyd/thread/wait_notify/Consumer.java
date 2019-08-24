package com.xlyd.thread.wait_notify;

public class Consumer implements Runnable {

	private final Container container;

	public Consumer(Container container) {
		super();
		this.container = container;
	}

	@Override
	public void run() {
//		char c;
//		do {
//			c = this.container.getChar();
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(c + " is consumed by " + Thread.currentThread().getName());
//		} while (c != 'Z');
		
		while(true) {
			try {
				this.container.getNumber();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
