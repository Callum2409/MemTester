import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemTesterDesktopHelp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// INITIALISE ALL OBJECTS

	public MemTesterDesktopHelp() throws IOException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 258);
		setTitle("Help");
		setLocation(MemTesterDesktop.getPointy());

		setIconImage(ImageIO.read(MemTesterDesktopMenu.class
				.getResource("mt.png")));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Help");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 0, 304, 40);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 28));
		contentPane.add(lblTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 52, 304, 126);
		contentPane.add(scrollPane);

		JTextArea lblHelpText = new JTextArea(
				"Easy: Press the buttons or type the numbers in the order they light up.\n\n"
						+ "Medium: Type the letters in the order they appear - not case sensitive.\n\n"
						+ "Hard: Type the characters in the order they appear - case sensitive.\n\n"
						+ "The sequence will get progressively longer as you get the sequence correct.\n\n"
						+ "If you get the sequence wrong, you lose a life and the sequence is repeated. "
						+ "Get it wrong 3 times and it's game over!\n\n"
						+ "You also have 3 chances of repeating the sequence if you forget, but you will have to enter the whole sequence again.");
		lblHelpText.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHelpText.setLineWrap(true);
		lblHelpText.setWrapStyleWord(true);
		lblHelpText.setEditable(false);
		scrollPane.setViewportView(lblHelpText);

		JLabel lblGoodLuck = new JLabel("Good luck!");
		lblGoodLuck.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoodLuck.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGoodLuck.setBounds(12, 190, 304, 20);
		contentPane.add(lblGoodLuck);

	}// END CONSTRUCTOR
}// END CLASS