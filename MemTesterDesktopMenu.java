import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class MemTesterDesktopMenu extends JFrame implements ActionListener {
	
	JButton btnStart = new JButton("Start!");

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenuItem mntmSettings = new JMenuItem("Settings");
	private final JMenuItem mntmHelp = new JMenuItem("Help");
	private final JMenuItem mntmAbout = new JMenuItem("About");

	private final JButton[] btnHighscores = new JButton[3];

	// INITIALISE ALL OBJECTS

	public MemTesterDesktopMenu() throws IOException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 280);
		setTitle("memTester");
		
		if(MemTesterDesktop.getPointy() == null){
			setLocation(100,100);
		}else{
			setLocation(MemTesterDesktop.getPointy());
		}

		setIconImage(ImageIO.read(MemTesterDesktopMenu.class
				.getResource("mt.png")));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setJMenuBar(menuBar);

		mntmSettings.addActionListener(this);
		// menuBar.add(mntmSettings);

		mntmHelp.addActionListener(this);
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuBar.add(mntmHelp);

		mntmAbout.addActionListener(this);
		menuBar.add(mntmAbout);

		btnStart.setFont(new Font("Dialog", Font.BOLD, 32));
		btnStart.setBounds(12, 12, 304, 60);
		contentPane.add(btnStart);
		btnStart.addActionListener(this);

		for (int x = 0; x < 3; x++) {
			btnHighscores[x] = new JButton();
			btnHighscores[x].setBounds(12, 84 + (x * 47), 304, 35);

			if (x == 0)
				btnHighscores[x].setText("Highscores Easy");
			else if (x == 1)
				btnHighscores[x].setText("Highscores Medium");
			else if (x == 2)
				btnHighscores[x].setText("Highscores Hard");

			contentPane.add(btnHighscores[x]);
			btnHighscores[x].addActionListener(this);
		}

	}// END CONSTRUCTOR

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {

			setVisible(false);
			
			MemTesterDesktop.setPointy(getLocation());

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopGameSelect frame = new MemTesterDesktopGameSelect();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}// END START PRESSED
		for (int x = 0; x < 3; x++) {
			if (e.getSource() == btnHighscores[x]) {
				final int y = x;

				setVisible(false);

				MemTesterDesktop.setPointy(getLocation());
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MemTesterDesktopHighscores frame = new MemTesterDesktopHighscores(
									y);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}// END HIGHSCORES ACTTIONLISTENER
		}// END FOR
		if (e.getSource() == mntmHelp) {
			MemTesterDesktop.setPointy(getLocation());
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopHelp frame = new MemTesterDesktopHelp();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}// OPEN HELP
		if (e.getSource() == mntmAbout) {
			MemTesterDesktop.setPointy(getLocation());
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopAbout frame = new MemTesterDesktopAbout();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}// OPEN ABOUT
	}// END ACTIONLISTENER

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemTesterDesktopMenu frame = new MemTesterDesktopMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// END MAIN
}// END CLASS