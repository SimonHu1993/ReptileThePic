package com.simon.study;

public class TestThread {
     public static void main(String[] args){
//    	 Runnable1 r=new Runnable1();
//    	 Thread thread=new Thread(r);
//    	 thread.start();
//    	 for(int i=0;i<10;i++){
//	 			System.out.println("Main Thread **********"+i);
//	 		}
    	 new B();
     }
    
}

class  Runnable1 implements Runnable{
	 @Override
	 	public void run() {
	 		// TODO Auto-generated method stub
	 		for(int i=0;i<10;i++){
	 			System.out.println("Runnable1 **********"+i);
	 		}
	 	}
}
class A {
    public A(){
          System. out.println("I am A" );
   }
   {System. out.println("A");}
    static{System.out .println("static A");}
}

class B  extends  A{
    public B(){
          System. out.println("I am B" );
   }
   {System. out.println("B" );}
    static{System.out .println("static B");}
}