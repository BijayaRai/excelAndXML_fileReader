package com.bijaya_rai.Excel_XML_fileReader;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileChooserTest extends JFrame implements ActionListener {
	private JTextField excelFile = new JTextField(), xmlFile = new JTextField();
	private JLabel info = new JLabel(" ");
	public String xml;
	public String excel;

	private JButton XMLButton = new JButton("XML File"), ExcelFile = new JButton("Excel File");

	public FileChooserTest() {
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);

		XMLButton.addActionListener(this);
		ExcelFile.addActionListener(this);
		Container cp = getContentPane();

		xmlFile.setEditable(false);
		excelFile.setEditable(false);
		panel = new JPanel();
		panel.setLayout(gridLayout);
		panel.add(new JLabel("Select XML file:"));
		panel.add(xmlFile);
		panel.add(new JLabel());
		panel.add(XMLButton);

		panel.add(new JLabel("Select Excel file:"));
		panel.add(excelFile);
		panel.add(new JLabel());
		panel.add(ExcelFile);
		cp.add(panel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();

		int returnVal = c.showOpenDialog(FileChooserTest.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (e.getSource() == XMLButton) {
				xml=c.getSelectedFile().getAbsolutePath();
				xmlFile.setText(c.getSelectedFile().getName());
			} else if (e.getSource() == ExcelFile) {
				excel=c.getSelectedFile().getAbsolutePath();
				excelFile.setText(c.getSelectedFile().getName());
			}

			// xmlFile.setText(c.getCurrentDirectory().toString());
		}
		if (returnVal == JFileChooser.CANCEL_OPTION) {
			excelFile.setText("You pressed cancel");
			xmlFile.setText("");
		}
	}

	public void runGUI() {
		run(new FileChooserTest(), 300, 200);
	}

	public static void run(JFrame frame, int width, int height) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}