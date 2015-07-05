import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemTesterDesktopGameE extends JFrame implements ActionListener,
		KeyListener {

	Timer timer = new Timer();

	MemTesterDesktopMenu menu = new MemTesterDesktopMenu();

	MemTesterDesktop desktop = new MemTesterDesktop();
	int lives = desktop.getLives();
	int round = desktop.getRound();
	ArrayList<Integer> generated = desktop.getGen();
	ArrayList<Integer> input = desktop.getIn();

	JButton[] btnRepeat = new JButton[3];
	JButton[] btn = new JButton[9];

	JLabel lblLives = new JLabel();
	JLabel lblRound = new JLabel();
	JLabel lblRemaining = new JLabel();
	JLabel lblStatus = new JLabel();

	String lvs = "Lives: ";
	String rnd = "Round: ";
	String rmn = "Left to enter: ";

	String comp = "Correct!";
	String fail = "Incorrect!";
	String over = "Game Over!";

	boolean[] repeat = new boolean[3];
	boolean enterDisable = false;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	long timeA, timeB;
	int keyA, keyB;

	// INITIALISE ALL OBJECTS

	public MemTesterDesktopGameE() throws IOException {

		menu.setVisible(false);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 238);
		setLocation(MemTesterDesktop.getPointy());

		setTitle("memTester");
		setIconImage(ImageIO.read(MemTesterDesktopMenu.class
				.getResource("mt.png")));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (int x = 0; x < 3; x++) {
			btnRepeat[x] = new JButton("Repeat");
			btnRepeat[x].setBounds(12, (x * 37) + 12, 85, 25);
			btnRepeat[x].addActionListener(this);
			contentPane.add(btnRepeat[x]);
		}// CREATE REPEAT BUTTONS

		for (int x = 0; x < 9; x++) {
			btn[x] = new JButton("" + (x + 1));

			if (x < 3) {
				btn[x].setBounds((x * 57) + 160, 12, 45, 45);
			} else if (x < 6) {
				btn[x].setBounds(((x - 3) * 57) + 160, 69, 45, 45);
			} else if (x < 9) {
				btn[x].setBounds(((x - 6) * 57) + 160, 123, 45, 45);
			}
			btn[x].addActionListener(this);
			contentPane.add(btn[x]);
			btn[x].addKeyListener(this);
		}// CREATE NUMBER BUTTONS

		lblLives.setText(lvs + desktop.getLives());
		lblLives.setBounds(12, 123, 70, 15);
		contentPane.add(lblLives);

		lblRound.setText(rnd + desktop.getRound());
		lblRound.setBounds(12, 150, 100, 15);
		contentPane.add(lblRound);

		lblRemaining.setText(rmn + (round - input.size()));
		lblRemaining.setBounds(12, 177, 140, 15);
		contentPane.add(lblRemaining);

		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(150, 177, 166, 15);
		contentPane.add(lblStatus);

		generated.clear();
		input.clear();// CLEAR ARRAYLISTS READY TO START
		desktop.random(0, 8);// CREATE FIRST RANDOM
		timer.schedule(new flash(), 500); // STARTS FLASH AFTER WAITING
		// START GAME

	}// END CONSTRUCTOR

	class flash extends TimerTask {
		public void run() {

			input.clear();
			lblStatus.setText("");

			for (int x = 0; x < 9; x++) {
				btn[x].setEnabled(false);
			}// DISABLE NUMBER BUTTONS
			for (int x = 0; x < 3; x++) {
				btnRepeat[x].setEnabled(false);
			}// DISABLE REPEAT BUTTONS

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}// PAUSE FOR 500 BEFORE FLASH

			for (int x = 0; x < generated.size(); x++) {
				btn[generated.get(x)].setOpaque(true);
				btn[generated.get(x)].setBackground(Color.red);// FLASH IS
																// CHANGING
																// BACKGROUND OF
																// BUTTON

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}// PAUSE 1000 TO SHOW FLASH

				btn[generated.get(x)].setBackground(null);// SET BACKGROUND TO
															// NORMAL
				btn[generated.get(x)].setOpaque(false);

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}// PAUSE BETWEEN BUTTON FLASH

			}// END FOR
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}// FINAL PAUSE

			for (int x = 0; x < 9; x++) {
				btn[x].setEnabled(true);
			}// ENABLE ALL NUMBER BUTTONS
			for (int x = 0; x < 3; x++) {
				if (repeat[x] == false) {
					btnRepeat[x].setEnabled(true);
				}// ENABLE REPEAT BUTTONS THAT HAVE NOT BEEN PRESSED AND CHANGED
					// BOOLEAN TO TRUE
			}
			lblRemaining.setText(rmn + round);// SET TEXT OF REMAINING
			btn[0].requestFocusInWindow();

			enterDisable = false;
		}
	}// END FLASH

	class reset extends TimerTask {
		public void run() {

			MemTesterDesktop.setPointy(getLocation());
			
			dispose();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopGameOver frame = new MemTesterDesktopGameOver(
								0, round - 1);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}// USED TO CHANGE DISPLAY TO SHOW START AND QUIT BUTTONS

	public void compOut() {
		boolean complete = desktop.getComp();

		if (complete == true) {// NEXT ROUND IF SEQUENCE CORRECT
			lblStatus.setText(comp);
			lblStatus.setForeground(Color.green);
			this.nextRound();
		} else {
			if (lives > 1) {// IF INCORRECT, BUT HAVE LIVES, DECREASE LIVES AND
							// REPEAT
				lives = lives - 1;
				lblStatus.setText(fail);
				lblStatus.setForeground(Color.red);
				lblLives.setText(lvs + lives);
				timer.schedule(new flash(), 500); // STARTS FLASH AFTER WAITING
			} else {// IF INCORRECT, WITH NO LIVES, GAME OVER
				lives = lives - 1;
				lblLives.setText(lvs + lives);
				lblStatus.setText(over);
				lblStatus.setForeground(Color.red);

				for (int x = 0; x < 9; x++) {
					btn[x].setEnabled(false);
				}// DISABLE NUMBER BUTTONS SO CANT CONTINUE

				for (int x = 0; x < 3; x++) {
					btnRepeat[x].setEnabled(false);
				}// DISABLE REPEAT BUTTONS SO CANT CONTINUE
				timer.schedule(new reset(), 2000); // STARTS RESET AFTER WAITING
			}
		}
	}// END OUTPUT FOR COMPLETE SEQUENCE

	public void nextRound() {
		round = round + 1;
		desktop.random(0, 8);
		lblRound.setText(rnd + round);
		timer.schedule(new flash(), 500); // STARTS FLASH AFTER WAITING
	}// END INCREASE ROUND

	public void checkKey(int key) {
		if (48 < key && key < 58) {
			key = key - 49;
			btn[key].doClick();
		} else {
			if (96 < key && key < 106) {
				key = key - 97;
				btn[key].doClick();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		for (int x = 0; x < 9; x++) {// IF NUMBER PRESSED
			if (ae.getSource() == btn[x]) {
				if (enterDisable == false) {
					input.add(x);// ADD NUMBER TO ARRAYLIST
					if (round >= input.size())
						lblRemaining.setText(rmn + (round - input.size()));// DECREASE
																			// REMAINING
					int size = input.size();

					if (round == size) {
						desktop.check(x, size, round);// SEND INFO TO NON GUI TO
														// CHECK
						this.compOut();// IF COMPLETE SEQUENCE, GO TO OUTPUT
						enterDisable = true;
					}
				}
			}
		}// END IF NUMBER PRESSED

		for (int x = 0; x < 3; x++) {// IF REPEAT PRESSED
			if (ae.getSource() == btnRepeat[x]) {
				btnRepeat[x].setEnabled(false);// DISABLE BUTTON SO CANT BE
												// RE-USED
				repeat[x] = true;// SET BOOLEAN SO DOESNT ENABLE AFTER FLASH
				timer.schedule(new flash(), 500); // STARTS FLASH AFTER WAITING
			}
		}// END IF REPEAT PRESSED
	}// END ACTIONLISTENER

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if (btn[0].isEnabled() == true) {
			keyB = ke.getKeyCode();
			timeB = ke.getWhen();

			if (keyB == keyA) {// only autorepeat check if buttons same
				if ((timeB - timeA) > 74)// check for autorepeat
					this.checkKey(keyB);
			} else
				this.checkKey(keyB);

			keyA = keyB;
			timeA = timeB;
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}// END CLASS