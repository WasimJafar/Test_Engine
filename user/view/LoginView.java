
package com.xyz.testengine.user.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.user.dao.UserDAO;
import com.xyz.testengine.user.dto.UserDTO;
import com.xyz.testengine.util.ExceptionMessage;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usertext;
	private JPasswordField passwordtext;
	private boolean showPassword = false;
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
					LoginView frame = new LoginView();
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
	
	public LoginView() {
		setType(Type.UTILITY);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLogin.setBounds(208, 47, 110, 45);
		contentPane.add(lblLogin);
		
		JLabel lblUserid = new JLabel("UserId");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserid.setBounds(103, 129, 79, 26);
		contentPane.add(lblUserid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(103, 207, 79, 27);
		contentPane.add(lblPassword);
		
		usertext = new JTextField();
		usertext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usertext.setToolTipText("Enter the userid");
		usertext.setBounds(259, 130, 181, 26);
		contentPane.add(usertext);
		usertext.setColumns(10);
		
		passwordtext = new JPasswordField();
		passwordtext.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordtext.setToolTipText("Enter the password");
		passwordtext.setBounds(257, 209, 153, 26);
		contentPane.add(passwordtext);
		
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
				checkLogin();
			}
		});
		Login.setBounds(84, 275, 110, 34);
		contentPane.add(Login);
		
		JButton Reset = new JButton("Reset");
		Reset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
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
		Reset.setBounds(422, 275, 101, 34);
		contentPane.add(Reset);
		
		JButton NewUser = new JButton("New user");
		NewUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadToRegister();
			}
		});
		NewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				NewUser.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				NewUser.setBackground(Color.WHITE);
			}
		});
		NewUser.setBounds(245, 275, 110, 34);
		contentPane.add(NewUser);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!showPassword) {
					passwordtext.setEchoChar((char) 0);
					showPassword=true;
				}	
				else if(showPassword) {
					passwordtext.setEchoChar('•');
					showPassword=false;
				}
			
			}
		});
		btnNewButton.setIcon(new ImageIcon(LoginView.class.getResource("/com/xyz/testengine/111.png")));
		btnNewButton.setBounds(409, 209, 31, 25);
		contentPane.add(btnNewButton);
	}
	
	private void checkLogin() {
		String userid = usertext.getText();
		String password = new String(passwordtext.getPassword());
		UserDAO userDAO = new UserDAO();
		try {
			UserDTO userDTO= userDAO.doLogin(userid, password);
			if(userDTO==null) {
				JOptionPane.showMessageDialog(this, "Invalid userid or password");
				reset();
			}
			else {
				DashBoardView dashBoardView = new DashBoardView();
				dashBoardView.setVisible(true);
				this.setVisible(false);
				this.dispose();
//				dashBoardView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dashBoardView.fillDashBoard(userDTO);
				reset();
			}
			
			}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.CLASS_NOT_FOUND);
			reset();
			e.printStackTrace();
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.SQL);
			reset();
			e.printStackTrace();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.OTHER_EXCEPTION);
			reset();
			e.printStackTrace();
			}
		}
	
	private void reset() {
		usertext.setText(null);
		passwordtext.setText(null);
	}
	
	private void loadToRegister() {
		this.setVisible(false);
		new RegisterView().setVisible(true);
	}
}
