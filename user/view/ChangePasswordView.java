package com.xyz.testengine.user.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.user.dao.RegisterDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangePasswordView extends JFrame {

	private JPanel contentPane;
	private JTextField oldpassword;
	private JTextField newpassword;
	private JTextField confirmpassword;
	private String userid=null;
	
	
	public static void main(String[] args) {
		ChangePasswordView obj = new ChangePasswordView("AA");
		obj.setVisible(true);
	}
	
	
	private void passwordChange() {
		
		RegisterDAO obj = new RegisterDAO();
		try {
			String password=obj.checkOldPass(userid);
			if(password.equals(oldpassword.getText())) {
				if(obj.changePassword(newpassword.getText(), userid)) {
					JOptionPane.showMessageDialog(contentPane,"Password change successfully");
				}
			}
			else {
				JOptionPane.showMessageDialog(contentPane, "Check the old password");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	/**
	 * Create the frame.
	 * @param userid 
	 */
	public ChangePasswordView(String userid) {
		this.userid=userid;
		setTitle("Change password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 519, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOldPassword = new JLabel("Old password");
		lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOldPassword.setBounds(99, 70, 111, 29);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewPassword.setBounds(99, 132, 111, 29);
		contentPane.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(99, 201, 111, 29);
		contentPane.add(lblConfirmPassword);
		
		oldpassword = new JTextField();
		oldpassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		oldpassword.setBounds(271, 71, 152, 29);
		contentPane.add(oldpassword);
		oldpassword.setColumns(10);
		
		newpassword = new JTextField();
		newpassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		newpassword.setColumns(10);
		newpassword.setBounds(271, 133, 152, 29);
		contentPane.add(newpassword);
		
		confirmpassword = new JTextField();
		confirmpassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmpassword.setColumns(10);
		confirmpassword.setBounds(271, 202, 152, 29);
		contentPane.add(confirmpassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSubmit.setBackground(Color.WHITE);
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newpassword.getText().equals(confirmpassword.getText())) {
				passwordChange();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Password doesn't match");
					}
			}
		});
		btnSubmit.setBounds(203, 279, 105, 35);
		contentPane.add(btnSubmit);
	}
}
