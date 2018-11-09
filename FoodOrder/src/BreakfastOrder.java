import java.util.Scanner;

public class BreakfastOrder extends Order {
	private final static double cost1 = 1.75;
	private final static String item1 = "Coffee";
	
	private final static double cost2 = 5.49;
	private final static String item2 = "Soggy Waffle";
	
	private double subTotal;

	public BreakfastOrder(int tipRate, int taxRate) {
		super(tipRate, taxRate);
		subTotal = 0;
	}

	public void promptUser() {
		// TODO Auto-generated method stub
		
	}

	public double getSubTotal() {
		return subTotal;
	}
	
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getMenu() {
		return item1+": "+cost1 +"/n"+item2+": "+cost2;
	}

	public double prompUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("How many "+item1+"s? ..");
		int num1 = input.nextInt();
		
		System.out.println("How many "+item2+"s? ..");
		int num2 = input.nextInt();
		
	}
	
}
