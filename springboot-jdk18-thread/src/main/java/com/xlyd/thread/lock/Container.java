package com.xlyd.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Container {

	private int num = 100;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = this.lock.newCondition();

	private List<Integer> unSafeList = new ArrayList<>();

	void setNumber() {
		this.lock.lock();

		try {
			while (this.unSafeList.size() > 10) {
				try {
					this.condition.await();
					System.out.println("waiting for consumer to consume ... ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.unSafeList.add(num);
			this.num ++;
			System.out.println(Thread.currentThread().getName() + ", Add number: " + num);
			this.condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}

	}

	Integer getNumber() {
		this.lock.lock();
		
		try {
			while (this.unSafeList.size() == 0) {
				try {
					this.condition.await();
					System.out.println("waiting for the producer to produce ...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Integer num = this.unSafeList.remove(0);
			System.out.println(Thread.currentThread().getName() + ", get number: " + num);
			this.condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}

		return num;
	}

}
