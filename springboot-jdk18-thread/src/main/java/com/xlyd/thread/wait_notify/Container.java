package com.xlyd.thread.wait_notify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Container {

	private char c;
	private volatile boolean writeable = false;
	private int num = 100;

	private List<Integer> unSafeList = new ArrayList<>();
	private List<Integer> safeList = new CopyOnWriteArrayList<>();

	synchronized void setChar(char c) {
		while (!writeable) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.c = c;
		this.writeable = false;
		this.notifyAll();
	}

	synchronized char getChar() {
		while (writeable) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.writeable = true;
		this.notifyAll();
		return this.c;
	}

	synchronized void setNumber() {
		while (this.unSafeList.size() > 10) {
			try {
				this.wait();
				System.out.println("waiting for consumer to consume ... ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.unSafeList.add(num);
		System.out.println(Thread.currentThread().getName() + ", Add number: " + num);
		num ++;
		this.notifyAll();

	}

	synchronized Integer getNumber() {
		while (this.unSafeList.size() == 0) {
			try {
				this.wait();
				System.out.println("waiting for the producer to produce ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Integer num = this.unSafeList.remove(0);
		System.out.println(Thread.currentThread().getName() + ", get number: " + num);
		this.notifyAll();
		return num;
	}

	synchronized void setNum() {
		this.safeList.add(num);
		System.out.println(Thread.currentThread().getName() + ", Add number: " + num);
		num ++;
	}

	Integer getNum() {
		Integer num = this.safeList.remove(0);
		System.out.println(Thread.currentThread().getName() + ", get number: " + num);
		return num;
	}

}
