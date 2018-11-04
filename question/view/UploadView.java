package com.xyz.testengine.question.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.question.helper.ParallelUpload;
import com.xyz.testengine.question.helper.QuestionUploadHelper;
import com.xyz.testengine.util.ExceptionMessage;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class UploadView extends JFrame {

	private JPanel contentPane;
	private JTextField fileName1=null;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JTextField fileName2=null;
	private JTextField fileName3=null;
	private JTextField fileName4=null;
	private int numberOfUploads = 0;
	private ArrayList<String> file=new ArrayList<>();
	
	
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadView frame = new UploadView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UploadView() {
		setForeground(Color.MAGENTA);
		setFont(new Font("Dialog", Font.PLAIN, 19));
		setTitle("Upload Questions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 110, 600, 500);

//		setBounds(100, 100, 643, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpload.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpload.setBackground(Color.WHITE);
			}
		});
		btnUpload.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((fileName1.getText()).equals("")) { 
					JOptionPane.showMessageDialog(contentPane, "Please choose atleast one file");
				}
				else if(!fileName1.getText().equals(null) || !fileName2.getText().equals(null) || !fileName3.getText().equals(null) || !fileName4.getText().equals(null)){
					Boolean x=uploadIt();
					JOptionPane.showMessageDialog(contentPane, x?"Uploaded Successfully":"Can't upload now");
					reset();
					
				}
			}
		});
		btnUpload.setBounds(227, 397, 109, 41);
		contentPane.add(btnUpload);
		
		fileName1 = new JTextField();
		fileName1.setBounds(257, 116, 196, 29);
		contentPane.add(fileName1);
		fileName1.setColumns(10);
		
		JButton btnChooseFile = new JButton("Choose file");
		btnChooseFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChooseFile.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnChooseFile.setBackground(Color.WHITE);
			}
		});
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fileName1.setText(fileChooser());
				if(!isFilexls(fileName1.getText())) {
				JOptionPane.showMessageDialog(contentPane,"Select the correct excel(.xls) file");
				}
				numberOfUploads++;
			}
		});
		btnChooseFile.setBounds(72, 116, 98, 29);
		contentPane.add(btnChooseFile);
		
		button = new JButton("Choose file");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(Color.WHITE);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName2.setText(fileChooser());
				if(!isFilexls(fileName2.getText())) {
					JOptionPane.showMessageDialog(contentPane,"Select the correct excel(.xls) file");
					}
				numberOfUploads++;
			}
		});
		button.setBounds(72, 190, 98, 29);
		contentPane.add(button);
		
		button_1 = new JButton("Choose file");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setBackground(Color.WHITE);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName3.setText(fileChooser());
				if(!isFilexls(fileName3.getText())) {
					JOptionPane.showMessageDialog(contentPane,"Select the correct excel(.xls) file");
					}
				numberOfUploads++;
			}
		});
		button_1.setBounds(72, 256, 98, 29);
		contentPane.add(button_1);
		
		button_2 = new JButton("Choose file");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button_2.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_2.setBackground(Color.WHITE);
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName4.setText(fileChooser());
				if(!isFilexls(fileName4.getText())) {
					JOptionPane.showMessageDialog(contentPane,"Select the correct excel(.xls) file");
					}
				numberOfUploads++;
			}
		});
		button_2.setBounds(72, 328, 98, 29);
		contentPane.add(button_2);
		
		fileName2 = new JTextField();
		fileName2.setColumns(10);
		fileName2.setBounds(257, 190, 196, 29);
		contentPane.add(fileName2);
		
		fileName3 = new JTextField();
		fileName3.setColumns(10);
		fileName3.setBounds(257, 256, 196, 29);
		contentPane.add(fileName3);
		
		fileName4 = new JTextField();
		fileName4.setColumns(10);
		fileName4.setBounds(257, 328, 196, 29);
		contentPane.add(fileName4);
		
		JLabel lblUploadQuestions = new JLabel("Upload Questions");
		lblUploadQuestions.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUploadQuestions.setBounds(159, 34, 196, 41);
		contentPane.add(lblUploadQuestions);
	}
	
	private boolean isFilexls(String file) {
		int index = file.lastIndexOf(".");
		String name = file.substring(index, file.length());
		if(name.equals(".xls")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	private void reset() {
		fileName1.setText(null);
		fileName2.setText(null);
		fileName3.setText(null);
		fileName4.setText(null);
	}
	

	
	private boolean uploadIt() {
		
		file.add(fileName1.getText());
		file.add(fileName2.getText());
		file.add(fileName3.getText());
		file.add(fileName4.getText());
		int i;
		ParallelUpload upload = new ParallelUpload(file);
		for(i=0;i<numberOfUploads;i++) {
			Thread worker = new Thread(upload,String.valueOf(i));
			worker.start();
		}
		return true;
	
	
	}
		
		
	
	private String fileChooser() {
		JFileChooser jFileChooser = new JFileChooser("C:\\Users\\wasim\\Desktop");
		jFileChooser.showOpenDialog(null);	
		if(jFileChooser.getSelectedFile()!=null) {
			return jFileChooser.getSelectedFile().getAbsolutePath();
		}
		else {
			return null;
		}
	}
}
