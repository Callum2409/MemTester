import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemTesterDesktopAbout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	final String version = "Version: 1.4.0";
	final String buildDate = "Build Date: 02/12/11";

	// INITIALISE ALL OBJECTS

	public MemTesterDesktopAbout() throws IOException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 307);
		setTitle("About");
		setLocation(MemTesterDesktop.getPointy());

		setIconImage(ImageIO.read(MemTesterDesktopMenu.class
				.getResource("mt.png")));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("About");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 0, 304, 40);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 28));
		contentPane.add(lblTitle);
		
		Icon icon = new ImageIcon(getToolkit().getImage(MemTesterDesktopMenu.class.getResource("mt_ldpi.png")));
		
		JLabel lblIcon = new JLabel(icon);
		lblIcon.setBounds(12, 52, 112, 112);
	contentPane.add(lblIcon);
	
	JLabel lblVersion = new JLabel(version);
	lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
	lblVersion.setFont(new Font("Dialog", Font.PLAIN, 16));
	lblVersion.setBounds(136, 80, 180, 22);
	contentPane.add(lblVersion);
	
	JLabel lblBuildDate = new JLabel(buildDate);
	lblBuildDate.setFont(new Font("Dialog", Font.PLAIN, 16));
	lblBuildDate.setBounds(136, 114, 180, 22);
	contentPane.add(lblBuildDate);
	
	JLabel lblCredits = new JLabel("<html><p>Created by: Callum Pryer<br><br>Icon by: Alex Jackson<br><br>Tested by: Friends and Family</p></html>");
	lblCredits.setFont(new Font("Dialog", Font.PLAIN, 12));
	lblCredits.setBounds(12, 176, 304, 85);
	contentPane.add(lblCredits);

	}// END CONSTRUCTOR
}// END CLASS