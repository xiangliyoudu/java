package com.xlyd.thread.wait_notify;

public class Client {
	public static void main(String[] args) {
		Container c = new Container();
		
		Producer p = new Producer(c);
		Producer p2 = new Producer(c);
		Consumer con = new Consumer(c);
		Consumer con2 = new Consumer(c);
		
		new Thread(p).start();
		new Thread(p2).start();
		new Thread(con).start();
		new Thread(con2).start();
	}
}
