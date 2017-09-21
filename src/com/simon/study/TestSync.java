package com.simon.study;
/**
 * 线程同步
 * @author: Simon
 * @date: 2017年7月29日 下午9:15:39
 */
public class TestSync implements  Runnable{
	Timer timer=new Timer();
	public static void main(String[] args){
		TestSync testSync=new TestSync();
		Thread r1=new Thread(testSync);
		Thread r2=new Thread(testSync);
		r1.setName("r1");
		r2.setName("r2");
		r1.start();
		r2.start();
	}
	public void run() {
		timer.add(Thread.currentThread().getName());
	}

}

class Timer{
	int num=0;
	public  void add(String name){
		num++;
		synchronized (this) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}	
		}
		System.out.println(name+"你是第"+num+"个访问Timer的线程！");
	}
}