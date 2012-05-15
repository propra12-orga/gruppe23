import java.awt.Color;
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

public class Place extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Place frame = new Place();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Place() {
		int n = 16;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1, 1, 1, 1, 0 };
		gbl_contentPane.rowHeights = new int[] { 14, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		setResizable(false);

		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		contentPane.add(label_1, gbc_label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 0;
		contentPane.add(label_2, gbc_label_2);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Place.class.getResource("/Pics/Hulk.jpg")));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 2;
		gbc_label_3.gridy = 0;
		contentPane.add(label_3, gbc_label_3);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Mauer.jpg")));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.gridx = 3;
		gbc_label_4.gridy = 0;
		contentPane.add(label_4, gbc_label_4);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 1;
		contentPane.add(label_5, gbc_label_5);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Block.jpg")));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 1;
		contentPane.add(label_6, gbc_label_6);

		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 2;
		gbc_label_7.gridy = 1;
		contentPane.add(label_7, gbc_label_7);

		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Mauer.jpg")));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 5, 0);
		gbc_label_8.gridx = 3;
		gbc_label_8.gridy = 1;
		contentPane.add(label_8, gbc_label_8);

		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 2;
		contentPane.add(label_9, gbc_label_9);

		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 1;
		gbc_label_10.gridy = 2;
		contentPane.add(label_10, gbc_label_10);

		JLabel label_11 = new JLabel("");
		label_11.setBackground(Color.RED);
		label_11.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Mauer.jpg")));
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 2;
		gbc_label_11.gridy = 2;
		contentPane.add(label_11, gbc_label_11);

		JLabel label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Block.jpg")));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.insets = new Insets(0, 0, 5, 0);
		gbc_label_12.gridx = 3;
		gbc_label_12.gridy = 2;
		contentPane.add(label_12, gbc_label_12);

		JLabel label_13 = new JLabel("");
		label_13.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.insets = new Insets(0, 0, 0, 5);
		gbc_label_13.gridx = 0;
		gbc_label_13.gridy = 3;
		contentPane.add(label_13, gbc_label_13);

		JLabel label_14 = new JLabel("");
		label_14.setIcon(new ImageIcon(Place.class.getResource("/Pics/Weg.jpg")));
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.insets = new Insets(0, 0, 0, 5);
		gbc_label_14.gridx = 1;
		gbc_label_14.gridy = 3;
		contentPane.add(label_14, gbc_label_14);

		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Mauer.jpg")));
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.insets = new Insets(0, 0, 5, 0);
		gbc_label_15.gridx = 2;
		gbc_label_15.gridy = 3;
		contentPane.add(label_15, gbc_label_15);

		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon(Place.class
				.getResource("/Pics/Mauer.jpg")));
		GridBagConstraints gbc_label_16 = new GridBagConstraints();
		gbc_label_16.insets = new Insets(0, 0, 5, 0);
		gbc_label_16.gridx = 3;
		gbc_label_16.gridy = 3;
		contentPane.add(label_16, gbc_label_16);

		pack();

	}
}
