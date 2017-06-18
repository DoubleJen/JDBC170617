package tw.jdbc;

public class JDBC170618A001 {

	public static void main(String[] args) {
		//p1 obj = new JDBC170618A0011();
		//JDBC170618A0011 obj2 = (JDBC170618A0011)obj;
			//Âà«¬
		
		p1 obj1 = new JDBC170618A0011();
		p1 obj2 = new JDBC170618A0012();
		main2(obj1);
		main2(obj2);

	}
	
	static void main2(p1 anyobj){
		anyobj.m1();
		if(anyobj instanceof JDBC170618A0011){
			((JDBC170618A0011) anyobj).m3();
		}else if(anyobj instanceof JDBC170618A0012){
			((JDBC170618A0012) anyobj).m4();
		}
	}

}

interface p1{
	void m1();
}

class JDBC170618A0011 implements p1{
	public void m1(){System.out.println("A");};
	void m3(){System.out.println("A1");};
}

class JDBC170618A0012 implements p1{
	public void m1(){System.out.println("B");};
	void m4(){System.out.println("B1");};
}