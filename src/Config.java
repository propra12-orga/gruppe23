import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Config extends JPanel {
	/**
	 * Tobias Korfmacher
	 */
	private static final long serialVersionUID = 1L;
	private JLabel oben;

	public Config() {

		JPanel panelButton = new JPanel(new GridLayout(2, 2));

		// buttons erstellen


		JButton exit_button = new JButton("Einstellungen verlassen");
		ActionListener exit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		};

		// Listener hinzu

		exit_button.addActionListener(exit);
		// buttons aufs panel

		panelButton.add(exit_button);

		oben = new JLabel("Bomber Hulk Configer");
		oben.setHorizontalAlignment(JLabel.CENTER);
		add(BorderLayout.NORTH, oben);
		add(BorderLayout.WEST, panelButton);


		setVisible(true);

	}

	//	public static void main(String[] args) {
	//		Config g = new Config();
	//	}

}

