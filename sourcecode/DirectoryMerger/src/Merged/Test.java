package Merged;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JTextPane;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.JScrollBar;

public class Test extends JPanel implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	final static JTextPane textPane = new JTextPane();
	private JTextField textField_1;
	private JButton btnMerge;
	private static File dest;
	private static File input;
	static JSpinner spinner = new JSpinner();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSelectInput = new JButton("Select Input folder");
		btnSelectInput.setBounds(6, 45, 152, 26);
		frame.getContentPane().add(btnSelectInput);

		JButton btnSelectOutput = new JButton("Select Output folder");
		btnSelectOutput.setBounds(6, 74, 152, 29);
		frame.getContentPane().add(btnSelectOutput);

		textField = new JTextField();
		textField.setBounds(157, 44, 287, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(157, 74, 287, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		btnMerge = new JButton("Merge");
		btnMerge.setBounds(327, 142, 117, 29);
		frame.getContentPane().add(btnMerge);

		textPane.setBounds(6, 183, 438, 361);
		frame.getContentPane().add(textPane);

		JButton btnExportLogFile = new JButton("Export log file");
		btnExportLogFile.setBounds(0, 543, 117, 29);
		frame.getContentPane().add(btnExportLogFile);
		
		JLabel lblDirectoryMergerTool = new JLabel("Directory Merger tool");
		lblDirectoryMergerTool.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblDirectoryMergerTool.setBounds(135, 6, 223, 26);
		frame.getContentPane().add(lblDirectoryMergerTool);
		
		JLabel lblDevelopedByRoyal = new JLabel("Developed by Royal Surrey County Hospital | Scientific Computing");
		lblDevelopedByRoyal.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDevelopedByRoyal.setBounds(135, 556, 309, 16);
		frame.getContentPane().add(lblDevelopedByRoyal);
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(6, 165, 61, 16);
		frame.getContentPane().add(lblLog);
		
		JLabel lblNumberOfDirectories = new JLabel("Number of Directories to go back:");
		lblNumberOfDirectories.setBounds(6, 137, 223, 16);
		frame.getContentPane().add(lblNumberOfDirectories);
		
		
		spinner.setBounds(229, 132, 51, 26);
		frame.getContentPane().add(spinner);

		btnSelectInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textField.setText("" + chooser.getSelectedFile());
					textPane.setText(textPane.getText()
							+ "\nADDED INPUT DIRECTORY: "
							+ chooser.getSelectedFile());
					input = chooser.getSelectedFile();
				} else {
					textPane.setText(textPane.getText()
							+ "\nNO INPUT DIRECTORY WAS SELECTED");
				}
			}
		});

		btnSelectOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					textField_1.setText("" + chooser.getSelectedFile());
					textPane.setText(textPane.getText()
							+ "\nADDED OUTPUT DIRECTORY: "
							+ chooser.getSelectedFile());
					dest = chooser.getSelectedFile();
				} else {
					textPane.setText(textPane.getText()
							+ "\nNO OUTPUT DIRECTORY WAS SELECTED");
				}
			}
		});

		btnExportLogFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					writeLog();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				displayDirectoryContents(input);

			}
		});
	}

	protected void writeLog() throws IOException {
		FileWriter pw = new FileWriter("log.txt");
		pw.write(textPane.getText());
		textPane.write(pw);

		
	}

	public static void displayDirectoryContents(File dir) {
		int num23 = (int) spinner.getValue();
		System.out.println(num23);
		Path tot = Paths.get(dest.getPath());
		String name = "";

		
		try {
			Path x = Paths.get(dir.getPath());
			String bob = x.getFileName().toString();

			int num = x.getNameCount();
			System.out.println("Number of Directories to Root: " + num);
			for(int i = 1; i<=num23;i++){
				int va1 = num-i;
				name = x.getName(va1).toString()+"-"+name;
			}

			name = name + "-";
			System.out.println(name);
			
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					
					textPane.setText(textPane.getText() + "\nDiretory Found: "
							+ file.getCanonicalPath());
					displayDirectoryContents(file);

				} else {
					
					textPane.setText(textPane.getText() + "\nFile Found: "
							+ file.getName());					

					textPane.setText(textPane.getText() + "\nLast "+num23+ " directories found are: "+name);
					
					textPane.setText(textPane.getText() + "Changing name to: "+name+ file.getName() );
			
						System.out.println(name);
					file.renameTo(new File(dest, name + file.getName()));
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
