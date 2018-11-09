
public class Poly {

	public static void main(String[] args) {
		One var1 = new Two();
		One var2 = new Three();
		One var3 = new Four();
		Three var4 = new Four();
		Object var5 = new Three();
		Object var6 = new One();
		
		
		//((Four)var4).method1();
		//((Three)var5).method2();
		//((Two)var1).method3();

		((Three)var3).method1();
		

	}
	


}
