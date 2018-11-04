package com.xyz.testengine.user.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.user.dao.RegisterDAO;
import com.xyz.testengine.user.dto.UserDTO;

public class TeacherRegister extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherRegister frame = new TeacherRegister();
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
	public TeacherRegister() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 503, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(73, 78, 71, 26);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 151, 71, 26);
		contentPane.add(lblPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doregister();
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegister.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegister.setBackground(Color.WHITE);
			}
		});
		btnRegister.setBounds(195, 220, 89, 23);
		contentPane.add(btnRegister);
		
		userName = new JTextField();
		userName.setBounds(221, 81, 133, 23);
		contentPane.add(userName);
		userName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(221, 154, 143, 23);
		contentPane.add(passwordField);
	}
	
	
	
	private void doregister() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(userName.getText());
		userDTO.setPassword(String.valueOf(passwordField.getPassword()));
		RegisterDAO register = new RegisterDAO();
		try {
			if(register.addTeacher(userDTO)) {
				int uid = register.getUid();
				boolean isRegister=register.userRoleMappingTeacher(uid);
				JOptionPane.showMessageDialog(contentPane, isRegister?"Register Successfully":"Can't register right now, Please try after some time");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
