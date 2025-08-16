package main;

import javax.swing.JFrame;

public class maintrexa {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Trexa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        gamePanel panel = new gamePanel();
        
        frame.requestFocus();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
	}

}
