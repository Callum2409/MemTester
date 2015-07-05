import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemTesterDesktopGameOver extends JFrame implements ActionListener {

	JLabel lblScore = new JLabel("lblScore");
	JLabel lblCong = new JLabel("lblCong");
	JLabel lblTop10 = new JLabel("lblTop10");
	JLabel lblName = new JLabel("lblName");

	JTextField txtName = new JTextField();

	JButton btnEnter = new JButton("Enter");
	JButton btnHighscores = new JButton("Highscore table");
	JButton btnMenu = new JButton("Menu");

	String cong = "Congratulations!";
	String comm = "Unlucky!";
	String topM = "You may be in the top 10!";
	String topD = "You are in the top 10!";
	String topP = "You are currently in place: ";
	String below = "You did not make the top 10";
	String pleaseName = "Please enter your name:";
	String nextTime = "Better luck next time!";

	String[] savedScores = new String[11];
	String name;
	String[] savedInfo = new String[10];
	String[] savedSplit = new String[2];

	final String splitter = "\\?\\*_<\\^>_\\*\\?";
	final String adder = "?*_<^>_*?";

	String fileName = ".memTesterHighscores";
	String fullName = "";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	int gameNo, gameScore;

	// INITIALISE ALL OBJECTS

	public MemTesterDesktopGameOver(int game, int score) throws IOException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 330, 311);
		setTitle("Game Over");
		setLocation(MemTesterDesktop.getPointy());

		setIconImage(ImageIO.read(MemTesterDesktopMenu.class
				.getResource("mt.png")));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Game Over!");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 0, 304, 40);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 28));
		contentPane.add(lblTitle);

		lblScore.setBounds(12, 52, 304, 15);
		contentPane.add(lblScore);

		lblCong.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCong.setHorizontalAlignment(SwingConstants.CENTER);
		lblCong.setBounds(12, 79, 304, 21);
		contentPane.add(lblCong);

		lblTop10.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTop10.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop10.setBounds(12, 106, 304, 21);
		contentPane.add(lblTop10);

		lblName.setBounds(12, 145, 304, 15);
		contentPane.add(lblName);

		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setBounds(12, 172, 304, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);

		btnEnter.setBounds(12, 203, 304, 25);
		contentPane.add(btnEnter);
		btnEnter.addActionListener(this);

		btnHighscores.setBounds(12, 240, 146, 25);
		contentPane.add(btnHighscores);
		btnHighscores.addActionListener(this);

		btnMenu.setBounds(170, 240, 146, 25);
		contentPane.add(btnMenu);
		btnMenu.addActionListener(this);

		lblScore.setText("Score: " + score);

		gameNo = game;
		gameScore = score;
		
		if (game == 0)
			fullName = fileName + ".mt";
		else if (game == 1)
			fullName = fileName + "1.mt";
		else if (game == 2)
			fullName = fileName + "2.mt";

		this.scoreCheck();
	}

	public void scoreCheck() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					fullName));
		
			for (int x = 0; x < 10; x++) {
				try {
					savedInfo[x] = in.readLine();
					if (savedInfo[x] == null) {
						savedInfo[x] = "-1";
					}
				} catch (IOException e) {
					e.printStackTrace();
					savedInfo[x] = "-1";
				}
			}// END FOR READ EACH LINE
			in.close();
		} catch (FileNotFoundException e) {
			for (int x = 0; x < 10; x++)
				savedInfo[x] = "-1";
		} catch (IOException e) {
		}
		// NOW SPLIT THE LAST INFO INTO NUMBER AND NAME
		if (savedInfo[9].contains(adder) == true) {
			savedSplit = savedInfo[9].split(splitter, 2);
		} else {
			savedSplit[0] = "-1";
		}
		savedScores[0] = savedSplit[0];

		if (gameScore > Integer.parseInt(savedScores[0])) {
			lblCong.setText(cong);
			lblTop10.setText(topD);
			lblName.setText(pleaseName);

			btnEnter.setVisible(true);
			txtName.setVisible(true);
			lblName.setVisible(true);

			btnHighscores.setVisible(false);
			btnMenu.setVisible(false);
		} else {// ELSE CHECK SCORE EQUAL-MAY BE
			if (gameScore == Integer.parseInt(savedScores[0])) {
				lblCong.setText(cong);
				lblTop10.setText(topM);
				lblName.setText(pleaseName);

				btnEnter.setVisible(true);
				txtName.setVisible(true);
				lblName.setVisible(true);

				btnHighscores.setVisible(false);
				btnMenu.setVisible(false);
			} else {// ELSE BELOW NO HIGHSCORE
				lblCong.setText(comm);
				lblTop10.setText(below);
				lblName.setText(nextTime);

				btnEnter.setVisible(false);
				txtName.setVisible(false);
				lblName.setVisible(true);

				btnHighscores.setVisible(true);
				btnMenu.setVisible(true);
			}// END ELSE BELOW
		}// END ELSE ABOVE
	}// END SCORECHECK METHOD

	@SuppressWarnings("unchecked")
	public void scoreSave(String name) {

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					fullName));
		
			for (int x = 0; x < 10; x++) {
				try {
					savedInfo[x] = in.readLine();
					if (savedInfo[x] == null) {
						savedInfo[x] = "-1";// SET TO -1 FOR EASY SORT
					}
				} catch (IOException e) {
					savedInfo[x] = "-1";
				}
				if (savedInfo[x].contains(adder) == true)
					savedScores[x] = savedInfo[x];
				else
					savedScores[x] = "-1";
			}// END FOR READ EACH LINE
			in.close();
		} catch (FileNotFoundException e) {
			for (int x = 0; x < 10; x++)
				savedScores[x] = "-1";
		}// END TRY CATCH GET ALL SCORES
		catch (IOException e) {
		}

		String saveString = Integer.toString(gameScore) + adder + name;
		savedScores[10] = saveString;

		List<String> toSort = Arrays.asList(savedScores);

		Collections.sort(toSort, new AlphanumComparator());
		Collections.reverse(toSort);// REVERSE SO SHOWS TOP SCORES
		savedScores = toSort.toArray(new String[toSort.size()]);

		if (savedScores[10] == saveString) {// FIND PLACE AND OUTPUT
			lblCong.setText(comm);
			lblTop10.setText(below);
			lblName.setText(nextTime);

			btnEnter.setVisible(false);
			txtName.setVisible(false);
			lblName.setVisible(true);

			btnHighscores.setVisible(true);
			btnMenu.setVisible(true);
		} else {
			for (int x = 0; x < 10; x++) {
				if (savedScores[x] == saveString) {
					lblCong.setText(cong);
					lblTop10.setText(topP + (x + 1));
				}
			}
		}

		try {// NOW WRITE TO FILE

			File file = new File(fullName);
		
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				while (file.isHidden() == true) {
					Runtime.getRuntime().exec(// UNHIDE FILE IN WINDOWS
							"attrib -H "+fullName);				
				}// USE WHILE SO WONT CONTINUE
			}

			PrintWriter out = new PrintWriter(new FileWriter(fullName));

			// MAKE THE FILE HIDDEN IN WINDOWS - SHOULD BE HIDDEN IN LUNUX, OSX
			// BY "."

			for (int x = 0; x < 10; x++) {
				out.println(savedScores[x]);
			}
			out.close();

			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				while (file.isHidden() == false) {
					Runtime.getRuntime().exec(// HIDE FILE IN WINDOWS
							"attrib +H "+fullName);
					
				}// USE WHILE SO WILL NOT CONTINUE UNTIL HIDDEN
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}// END SCORE SAVE

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnter) {
			name = txtName.getText();
			if (name.length() > 0) {
				btnEnter.setVisible(false);
				txtName.setVisible(false);
				lblName.setVisible(false);

				btnHighscores.setVisible(true);
				btnMenu.setVisible(true);

				this.scoreSave(name);
			} else
				txtName.requestFocusInWindow();
		}// END IF ENTER PRESSED
		if (e.getSource() == btnHighscores) {
			MemTesterDesktop.setPointy(getLocation());

			this.dispose();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopHighscores frame = new MemTesterDesktopHighscores(
								gameNo);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}// END HIGHSCORES PRESSED
		if (e.getSource() == btnMenu) {
			MemTesterDesktop.setPointy(getLocation());

			this.dispose();

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
		}
	}// END ACTIONPERFORMED
}// END CLASS
