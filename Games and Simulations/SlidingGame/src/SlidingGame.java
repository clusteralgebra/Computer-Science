import javax.swing.JFrame;

public class SlidingGame {
	public static void main(String[] args) { 
		 JFrame window = new JFrame("Slide Puzzle");
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.setContentPane(new SlidingGameGUI());
	        window.pack();  // finalize layout
	        window.show();  // make window visible
	        window.setResizable(false);
	}
}
