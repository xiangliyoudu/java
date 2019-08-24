package com.xlyd.thread.lock;

public class Consumer implements Runnable {

	private final Container container;

	public Consumer(Container container) {
		super();
		this.container = container;
	}

	@Override
	public void run() {
		
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
