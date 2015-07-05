import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class MemTesterDesktopHighscores extends JFrame implements
		ActionListener {

	MemTesterDesktopMenu menu = new MemTesterDesktopMenu();

	String[] columnNames = { "", "Score", "Name" };
	Object[][] data = new Object[10][3];

	JButton btnReset = new JButton("Reset Scores");
	JButton btnBack = new JButton("Back");

	final String splitter = "\\?\\*_<\\^>_\\*\\?";
	final String adder = "?*_<^>_*?";
	
	String name = ".memTesterHighscores";
	String fullName = "";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	int gameNo;

	// INITIALISE ALL OBJECTS

	public MemTesterDesktopHighscores(int game) throws IOException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 313);
		setLocation(MemTesterDesktop.getPointy());

		setTitle("memTester");
		setIconImage(ImageIO.read(MemTesterDesktopMenu.class
				.getResource("mt.png")));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				menu.setVisible(true);
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = null;
		if (game == 0) {
			lblTitle = new JLabel("Highscores Easy");
			fullName = name + ".mt";
		} else if (game == 1) {
			lblTitle = new JLabel("Highscores Medium");
			fullName = name + "1.mt";
		} else if (game == 2) {
			lblTitle = new JLabel("Highscores Hard");
			fullName = name + "2.mt";
		}
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 0, 304, 40);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 27));
		contentPane.add(lblTitle);

		table = new JTable(data, columnNames);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 52, 304, 178);
		contentPane.add(scrollPane);

		btnReset.setBounds(12, 242, 146, 25);
		contentPane.add(btnReset);
		btnReset.addActionListener(this);

		btnBack.setBounds(170, 242, 146, 25);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);

		DefaultTableCellRenderer centerer = new DefaultTableCellRenderer();
		centerer.setHorizontalAlignment(JLabel.CENTER);

		for (int x = 0; x < 3; x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerer);
			table.getColumnModel().getColumn(x).setResizable(false);
		}

		table.getColumnModel().getColumn(0).setMaxWidth(20);
		table.getColumnModel().getColumn(1).setMaxWidth(45);

		gameNo = game;
		this.showData();
	}// END CONSTRUCTOR

	public void showData() {
		// READS THE DATA
		String[] savedInfo = new String[10];
		String[] savedSplit = new String[2];

		try {
			BufferedReader in = new BufferedReader(new FileReader(fullName));

			for (int x = 0; x < 10; x++) {
				try {
					savedInfo[x] = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					savedInfo[x] = "";
				}
			}// END FOR READ EACH LINE
			in.close();
		} catch (FileNotFoundException e) {
			for (int x = 0; x < 10; x++)
				savedInfo[x] = "";
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < 10; x++) {
			table.getModel().setValueAt((x + 1), x, 0);// THIS IS THE NUMBERS

			if (savedInfo[x] == null) {
				savedSplit[0] = "";
				savedSplit[1] = "";
			} else {
				if (savedInfo[x].contains(adder) == true) {
					savedSplit = savedInfo[x].split(splitter, 2);
				} else {
					savedSplit[0] = "";
					savedSplit[1] = "";
				}
				table.getModel().setValueAt(savedSplit[0], x, 1);
				table.getModel().setValueAt(savedSplit[1], x, 2);
			}
		}
	}

	public void clearData() {
		try {

			File file = new File(fullName);

			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				while (file.isHidden() == true) {
					Runtime.getRuntime().exec(// UNHIDE FILE IN WINDOWS
							"attrib -H " + fullName);
				}// USE WHILE SO WONT CONTINUE
			}

			PrintWriter out = new PrintWriter(new FileWriter(fullName));

			for (int x = 0; x < 10; x++) {
				out.println("");
			}

			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				while (file.isHidden() == false) {
					Runtime.getRuntime().exec(// HIDE FILE IN WINDOWS
							"attrib +H " + fullName);
				}// USE WHILE SO WILL NOT CONTINUE UNTIL HIDDEN
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.showData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReset) {
			this.clearData();
		}
		if (e.getSource() == btnBack) {
			MemTesterDesktop.setPointy(getLocation());
			this.dispose();
			menu.setVisible(true);
			menu.setLocation(MemTesterDesktop.getPointy());
		}
	}// END ACTIONLISTENER
}// END CLASS