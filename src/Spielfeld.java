import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Spielfeld extends JFrame {

	public void malen(int i) {

		switch (i) {
		case 1: // male Hulk

		case 2: // male Weg

		case 3: // male Mauer

		case 4: // male Block

		}
	}

	public Spielfeld() {

		super("test");
		setVisible(true);

		final Image imageHulk = Toolkit.getDefaultToolkit()
				.getImage("Hulk.jpg");
		final Image imageBlock = Toolkit.getDefaultToolkit().getImage(
				"Block.jpg");
		final Image imageMauer = Toolkit.getDefaultToolkit().getImage(
				"Mauer.jpg");
		final Image imageWeg = Toolkit.getDefaultToolkit().getImage("Weg.jpg");

	}

	public static void main(String args[]) {
		JFrame jf = new JFrame();
		jf.getContentPane().setLayout(new GridLayout(16, 16));

		for (int i = 0; i < 16; i++) {
			JLabel label = new JLabel();
			ImageIcon icon = new ImageIcon("../Block.jpg");
			label.setIcon(icon);
			jf.getContentPane().add(label);
		}

		jf.setSize(new Dimension(300, 300));
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
