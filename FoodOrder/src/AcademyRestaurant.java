import java.util.Scanner;

public class AcademyRestaurant {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {

		while (true) {
			System.out.println("\n");
			System.out.println("Academy Restaurant");
			System.out.println("1 .... breakfast");
			System.out.println("2 .... lunch");
         System.out.println("3 .... exit");
			System.out.println("  ");;
			int choice = input.nextInt();
			OrderInterface order1 = null;
			if (choice == 1)
				order1 = new BreakfastOrder(15, 5);
			else if (choice ==2)
				order1 = new LunchOrder(17, 5);
         else if (choice == 3)
            System.exit(1);
			else { 
				System.out.println("Come on, pick the correct choice\n");
				continue;
			}
			
			String menu = order1.getMenu();
			System.out.println(menu);
			
			order1.promptUser();
			
			System.out.printf( "%20s:  %6.2f\n", "Subtotal", order1.getSubTotal());
			System.out.printf( "%20s:  %6.2f\n", "tip", order1.getTip());
			System.out.printf( "%20s:  %6.2f\n", "tax", order1.getTax());
			System.out.printf( "%20s:  %6.2f\n", "Cost of Order", order1.getTotal());
			
		}

	}

}
