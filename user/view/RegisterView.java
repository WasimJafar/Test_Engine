package com.xyz.testengine.user.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.user.dao.RegisterDAO;
import com.xyz.testengine.user.dto.UserDTO;
import com.xyz.testengine.util.ExceptionMessage;

public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JTextField dateOfbirth;
	private JPasswordField password;
	private JPasswordField confirmPassword;
	private JTextArea address;
	
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
					RegisterView frame = new RegisterView();
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

	JRadioButton radioButtonMale = new JRadioButton("Male");
	JRadioButton radioButtonFemale = new JRadioButton("Female");
	JComboBox comboBox = new JComboBox();
	ButtonGroup bg = new ButtonGroup();
	
	
	
	public RegisterView() {
		bg.add(radioButtonMale);
		bg.add(radioButtonFemale);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 639, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateNewAccount = new JLabel("Create new account");
		lblCreateNewAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCreateNewAccount.setBounds(181, 11, 191, 43);
		contentPane.add(lblCreateNewAccount);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(110, 99, 89, 33);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(110, 164, 79, 33);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(110, 227, 113, 33);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGender.setBounds(110, 350, 73, 33);
		contentPane.add(lblGender);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(110, 292, 96, 33);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(110, 417, 73, 33);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCity.setBounds(110, 514, 73, 23);
		contentPane.add(lblCity);
		
		user = new JTextField();
		user.setBounds(287, 99, 165, 33);
		contentPane.add(user);
		user.setColumns(10);
		
		dateOfbirth = new JTextField();
		dateOfbirth.setColumns(10);
		dateOfbirth.setBounds(287, 293, 165, 33);
		contentPane.add(dateOfbirth);
		
		password = new JPasswordField();
		password.setBounds(287, 165, 165, 33);
		contentPane.add(password);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(287, 228, 165, 33);
		contentPane.add(confirmPassword);
		
		address = new JTextArea();
		address.setForeground(Color.BLACK);
		address.setBounds(287, 422, 165, 54);
		address.setLineWrap(true);
		contentPane.add(address);
		
		JButton Submit = new JButton("Submit");
		Submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Submit.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Submit.setBackground(Color.WHITE);
			}
		});
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(new String(password.getPassword()).equals(new String(confirmPassword.getPassword())) ){ 
					if(isNotBlank()) {
					checkRegister();
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Please fill all the fields");
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Password doesn't match");
				}
			}
		});
		Submit.setBounds(138, 574, 89, 33);
		contentPane.add(Submit);
		
		JButton Reset = new JButton("Reset");
		Reset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Reset.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Reset.setBackground(Color.WHITE);
			}
		});
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		Reset.setBounds(287, 574, 96, 33);
		contentPane.add(Reset);
		
		JButton Login = new JButton("Login");
		Login.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Login.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Login.setBackground(Color.WHITE);
			}
		});
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadToLogin();
			}
		});
		Login.setBounds(469, 574, 89, 33);
		contentPane.add(Login);
		
		
		radioButtonMale.setBounds(287, 351, 55, 33);
		contentPane.add(radioButtonMale);
	
		
		radioButtonFemale.setBounds(378, 351, 71, 33);
		contentPane.add(radioButtonFemale);
		
		comboBox.setBounds(287, 510, 138, 33);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select City", "Agra", "Chennai", "Delhi", "Kolkata", "Mumbai"}));
		contentPane.add(comboBox);
		
		
		
		
	}
	
	private void checkRegister() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(user.getText());
		userDTO.setPassword(new String(password.getPassword()));
		userDTO.setGender(setGender());
		userDTO.setDateOfbirth(dateOfbirth.getText());
		userDTO.setAddress(address.getText());
		userDTO.setCity((String) comboBox.getSelectedItem());
		try {
			RegisterDAO register = new RegisterDAO();
			if(register.checkUserid(userDTO.getName())) {
				if(register.doRegister(userDTO)) {
					int uid=register.getUid();
					boolean isRegister=register.userRoleMapping(uid);
					JOptionPane.showMessageDialog(this, isRegister?"Register Successfully":"Can't register");
					reset();
				}
			} 
			else {
				JOptionPane.showMessageDialog(this, "This userid is already registered,try with an another userid");
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.CLASS_NOT_FOUND);
			e.printStackTrace();
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.SQL);
			e.printStackTrace();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.OTHER_EXCEPTION);
			e.printStackTrace();
			}
		}
	
	private String setGender() {
		if(radioButtonMale.isSelected()) {
			return "male";
		}
		else 
			if(radioButtonFemale.isSelected()) {
				return "female";
			}
		return null;
	}
	
	
	
	
	
	
	private void reset() {
		user.setText(null);
		password.setText(null);
		confirmPassword.setText(null);
		bg.clearSelection();
		dateOfbirth.setText(null);
		address.setText(null);
		comboBox.setSelectedIndex(0);
	}
	
	
	private void loadToLogin() {
		this.setVisible(false);
		this.dispose();
		new LoginView().setVisible(true);
	}
	
	
	
	private boolean isNotBlank() {
		if(user.getText().trim().length()>0 && new String(password.getPassword()).trim().length()>0 && (radioButtonMale.isSelected() || radioButtonFemale.isSelected()) && dateOfbirth.getText().trim().length()>0 && address.getText().trim().length()>0) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
