package com.bijaya_rai.Excel_XML_fileReader;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Bijaya Rai
 *
 */
public class FileChooserTest extends JFrame implements ActionListener {
	private JTextField excelFile = new JTextField(), xmlFile = new JTextField();
	private JLabel info = new JLabel(" ");
	public String xml;
	public String excel;

	private JButton XMLButton = new JButton("XML File"), ExcelFile = new JButton("Excel File"),
			okButton = new JButton("OK");

	public FileChooserTest() {
		this.setTitle("Text Translator");
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 1);

		XMLButton.addActionListener(this);
		ExcelFile.addActionListener(this);
		Container cp = getContentPane();

		xmlFile.setEditable(false);
		excelFile.setEditable(false);
		panel = new JPanel();
		panel.setLayout(gridLayout);
		panel.add(new JLabel("Select XML file:"));
		panel.add(xmlFile);
		
		panel.add(XMLButton);

		panel.add(new JLabel("Select Excel file:"));
		panel.add(excelFile);
		panel.add(ExcelFile);

		okButton.setText("Run");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String tempExcel = excel;
				ExcelDataReader edr = new ExcelDataReader();
				LinkedHashMap<String, String> dataFromExcel = edr.functionHandler(tempExcel);
					System.out.println(tempExcel);
				
				
				tempExcel = xml;
				XmlFileReader xfr = new XmlFileReader();
				LinkedHashMap<String, String> dataFromXML=xfr.functionHandler(tempExcel);
				System.out.println(tempExcel);
				

				XmlFileWriter xfw= new XmlFileWriter();
				xfw.createXML(dataFromExcel, dataFromXML);
				
				System.exit(0);
			}
		});
		panel.add(new JLabel());
		panel.add(okButton);
		cp.add(panel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();
		c.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = c.showOpenDialog(FileChooserTest.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (e.getSource() == XMLButton) {
				xml = c.getSelectedFile().getName();
				xmlFile.setText(c.getSelectedFile().getAbsolutePath());
			} else if (e.getSource() == ExcelFile) {
				excel = c.getSelectedFile().getName();
				excelFile.setText(c.getSelectedFile().getAbsolutePath());
			}

			// xmlFile.setText(c.getCurrentDirectory().toString());
		}
		if (returnVal == JFileChooser.CANCEL_OPTION) {
			excelFile.setText("You pressed cancel");
			xmlFile.setText("You pressed cancel");
		}
	}

	public void runGUI() {
		run(new FileChooserTest(), 500, 300);
	}

	public static void run(JFrame frame, int width, int height) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}