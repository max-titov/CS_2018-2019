import javax.swing.JFrame;

public class DriverBlackjack {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Man Hanging");
		frame.setSize(1200, 800);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new PanelBlackjack());
		frame.setVisible(true);
	}
}
