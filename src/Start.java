import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JFrame
{
	/**
	 * Tobias Korfmacher
	 */
	private static final long serialVersionUID = 1L;
	private JLabel oben;
	private static Config configger;
	private JPanel panelButton;

    public Start()
    {
		super("Starter");
		//        setLocation(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		panelButton = new JPanel(new GridLayout(2, 2));


		// buttons erstellen
		JButton neu_button = new JButton("Neues Spiel starten");
		ActionListener neu = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelButton.setVisible(false);
				Menue.spiel_neustarten();
			}
		};

		JButton config_button = new JButton("Einstellungen");
		ActionListener config = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configger = new Config();
				configger.setVisible(true);
				getContentPane().add(configger);
				setVisible(false);
				// Config einstellungen
			}
		};

		JButton level_button = new JButton("Levelauswahl");
		ActionListener level = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Optionpaine abfrage über die anzahl der level und level auswahl
			}
		};
		JButton exit_button = new JButton("Beenden");
		ActionListener exit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};

		// Listener hinzu
		neu_button.addActionListener(neu);
		config_button.addActionListener(config);
		level_button.addActionListener(level);
		exit_button.addActionListener(exit);
		// buttons aufs panel
		panelButton.add(neu_button);
		panelButton.add(config_button);
		panelButton.add(level_button);
		panelButton.add(exit_button);


		oben = new JLabel("BomberHulkStarter");
		oben.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(BorderLayout.NORTH, oben);
        getContentPane().add(BorderLayout.WEST, panelButton);

        pack();
        setVisible(true);

    }

	@SuppressWarnings("unused")
	public static void main(String[] args)
    {
        Start g = new Start();
    }


}

