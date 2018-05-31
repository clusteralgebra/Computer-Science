import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameOfLife {
	public static void main(String[] args) {
        final Board c = new Board(400);
        JFrame frame = new JFrame();
        frame.getContentPane().add(c);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                c.repaint();
            }
        }).start();
    }
}
