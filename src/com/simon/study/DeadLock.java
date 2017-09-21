package com.simon.study;
/**
 * 线程死锁
 * @author: Simon
 * @date: 2017年7月29日 下午9:48:43
 */
public class DeadLock implements Runnable{
  static Object o1=new Object(),o2=new Object();
  public int flag=1;
  
  
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("flag***********"+flag);
		if(flag==1){
			synchronized (o1) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				synchronized (o2) {
					System.out.println("这里线程1获取所有的线程权限");
				}
			}
			
			}
		if(flag==0){
			synchronized (o2) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				synchronized (o1) {
					System.out.println("这里线程2获取所有的线程权限");
				}
			}
			
		}
		}
	public static void main(String[] args){
		DeadLock deadLock1=new DeadLock();
		DeadLock deadLock2=new DeadLock();
		deadLock1.flag=1;
		deadLock2.flag=0;
		Thread r1=new Thread(deadLock1);
		Thread r2=new Thread(deadLock2);
		r1.start();
		r2.start();
	}
	}
  
