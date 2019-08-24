package com.xlyd.thread.lock;

public class Producer implements Runnable {

	private final Container container;

	public Producer(Container container) {
		super();
		this.container = container;
	}

	@Override
	public void run() {
		
		while(true){

			try {
				this.container.setNumber();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
