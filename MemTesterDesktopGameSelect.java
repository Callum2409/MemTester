import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MemTesterDesktopGameSelect extends JFrame implements
		ActionListener {

	JButton startE = new JButton();
	JButton startM = new JButton();
	JButton startH = new JButton();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// INITIALISE ALL OBJECTS

	public MemTesterDesktopGameSelect() throws IOException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 322);
		setTitle("memTester");
		setLocation(MemTesterDesktop.getPointy());

		setIconImage(ImageIO.read(MemTesterDesktopGameSelect.class
				.getResource("mt.png")));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		startE.setBounds(12, 12, 304, 80);
		startE.setText("<html><big><b>Easy</b></big><br>Press the number buttons in the order they light up</html>");
		contentPane.add(startE);
		startE.addActionListener(this);

		startM.setBounds(12, 104, 304, 80);
		startM.setText("<html><big><b>Medium</b></big><br>Enter the letters in the order they appear. Not case sensitive</html>");
		contentPane.add(startM);
		startM.addActionListener(this);
		
		startH.setBounds(12, 196, 304, 80);
		startH.setText("<html><big><b>Hard</b></big><br>Enter the characters in the order they appear. Case sensitive!</html>");
		contentPane.add(startH);
		startH.addActionListener(this);

	}// END CONSTRUCTOR

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startE) {
			MemTesterDesktop.setPointy(getLocation());
			dispose();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopGameE frame = new MemTesterDesktopGameE();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}//END START EASY
		
		if (e.getSource() == startM) {
			MemTesterDesktop.setPointy(getLocation());
			dispose();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopGameM frame = new MemTesterDesktopGameM();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}//END START MEDIUM
		
		if (e.getSource() == startH) {
			MemTesterDesktop.setPointy(getLocation());
			dispose();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MemTesterDesktopGameH frame = new MemTesterDesktopGameH();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}//END START HARD
	}// END ACTIONLISTENER
}// END CLASS