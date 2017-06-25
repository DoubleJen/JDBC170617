package tw.jdbc;

public class JDBC170625A006 {

	public static void main(String[] args) {
		//Way1
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(mr);
		t1.start();
		
		//Way2
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10; i++){
					System.out.println(i);
					try{
						Thread.sleep(200);
					}catch(InterruptedException e){
					}
				}
			}
		};
		Thread t2 = new Thread(r1);
		t2.start();
		
		//Way3 Java8以上新功能 ->實做
		Runnable r2 = () -> {
			for(int i=0; i<10; i++){
				System.out.println(i);
				try{
					Thread.sleep(200);
				}catch(InterruptedException e){
				}
			}
		};
		Thread t3 = new Thread(r2);
		t3.start();
		
		//Way4
		new Thread(() -> System.out.println("OK")).start();
		
	}

}


class MyRunnable implements Runnable{
	public void run() {
		for(int i=0; i<10; i++){
			System.out.println(i);
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
			}
		}
	}
}