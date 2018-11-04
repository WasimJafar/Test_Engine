package com.xyz.testengine.question.view;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.question.dao.QuestionDAO;
import com.xyz.testengine.question.dto.QuestionDTO;
import com.xyz.testengine.question.dto.ResultModelTable;
import com.xyz.testengine.user.dto.UserDTO;

public class ResultView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ResultView() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Result");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(111, 0, 150, 35);
		contentPane.add(lblName);
		
		
		
	}
	
	private void savetoDB(int finalScore) throws ClassNotFoundException, SQLException {
		new QuestionDAO().resultUpload(finalScore);
		
		}
	
	
	
	public ResultView(ArrayList<QuestionDTO> questions,int finalScore) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Result");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(111, 0, 150, 35);
		contentPane.add(lblName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 46, 396, 236);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new ResultModelTable(questions));
		scrollPane.setViewportView(table);
		
		JLabel lblScore = new JLabel("Total Score :"+finalScore);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblScore.setBounds(135, 293, 184, 22);
		contentPane.add(lblScore);
		savetoDB(finalScore);
	}
}
