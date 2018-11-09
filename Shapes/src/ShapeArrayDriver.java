import javax.swing.JFrame;

public class ShapeArrayDriver {
	public static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame("What is the Point");
		frame.setSize(ShapePanel.HEIGHT, ShapePanel.WIDTH);
		frame.setLocation(50, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ShapePanel());
		frame.setVisible(true);
	}
}
