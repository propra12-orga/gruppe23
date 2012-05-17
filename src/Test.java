import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Test extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void malen(JLabel label, int i) {

		switch (i) {
		case 1:
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Hulk.png")));
			break;

		case 2:
			label.setIcon(new ImageIcon(Test.class.getResource("/Pics/Weg.png")));
			break;

		case 3:
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Block.png")));
			break;

		case 4:
			label.setIcon(new ImageIcon(Test.class
					.getResource("/Pics/Mauer.png")));
			break;

		}
	}

	public Test() {
		int n = 3;

		int[][] map = { { 1, 2, 4 }, { 2, 1, 4 }, { 4, 4, 4 } };

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1, 1, 1, 1, 0 };
		gbl_contentPane.rowHeights = new int[] { 1, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		setResizable(true);

		JLabel[][] label = new JLabel[n][n];
		GridBagConstraints[][] gbc_label = new GridBagConstraints[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				label[i][j] = new JLabel("");

				label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				malen(label[i][j], map[i][j]);

				gbc_label[i][j] = new GridBagConstraints();
				gbc_label[i][j].anchor = GridBagConstraints.CENTER;
				gbc_label[i][j].fill = GridBagConstraints.NONE;
				gbc_label[i][j].gridwidth = GridBagConstraints.NONE;
				gbc_label[i][j].gridheight = GridBagConstraints.RELATIVE;
				gbc_label[i][j].insets = new Insets(0, 0, 0, 0);
				gbc_label[i][j].gridx = i;
				gbc_label[i][j].gridy = j;
				contentPane.add(label[i][j], gbc_label[i][j]);
			}
		}
		pack();
	}
}
