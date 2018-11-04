package com.xyz.testengine.user.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.xyz.testengine.user.dto.RightDTO;
import com.xyz.testengine.user.dto.UserDTO;

public class DashBoardView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	
	public void fillDashBoard(UserDTO userDTO) {
		
		
		if(userDTO!=null) {
			UserInfolbl.setText("Welcome   "+userDTO.getName()+"  "+userDTO.getRoleName());
			for(RightDTO right : userDTO.getRights()) {
				JMenuItem menuItem = new JMenuItem(right.getRight());
				menuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int index = right.getScreenName().indexOf(".java");
						String className = right.getScreenName().substring(0, index);
		
						try {							
							Object object = Class.forName(className).newInstance();
							Method method = object.getClass().getMethod("setVisible", boolean.class);
							method.invoke(object, true);
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						
					}
				});
				mnFile.add(menuItem);
				
				
			}
			
//			if(userDTO.getRoleName().equals("ADMIN")){
//				JMenuItem AdminOption = new JMenuItem("Admin Options");
//				
//				AdminOption.addMouseMotionListener(new MouseMotionAdapter() {
//					
//					
//					public void mousePressed(MouseEvent e) {
//						// TODO Auto-generated method stub
//						JMenuItem bbb = new JMenuItem("bbbb");
//						AdminOption.add(bbb);
//					}
//					
//					public void mouseExited(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					public void mouseEntered(MouseEvent e) {
//						// TODO Auto-generated method stub
//						JMenuItem bbb = new JMenuItem("bbbb");
//						AdminOption.add(bbb);
//						
//					}
//					
//				
//				
//				}); 
//					
//				
//				
//				
//					
//			
//				AdminOption.addActionListener(new ActionListener() {
//					
//					
//					
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						
//						
//					}
//				});
//				
//				
//				
//				
//				mnFile.add(AdminOption);
//						
//				
//			}
			
			
			
		}
	
		
		JMenuItem changePassword = new JMenuItem("CHANGE PASSWORD");
		changePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChangePasswordView obj = new ChangePasswordView(userDTO.getName());	
				obj.setVisible(true);
			}
		});
		mnFile.add(changePassword);

		
		

		
	}
	
	private void logout() {
		LoginView loginView = new LoginView();
		loginView.setVisible(true);
		
		this.setVisible(false);
		this.dispose();
	
	}
	
	
	
	/**
	 * Create the frame.
	 */
	
	JLabel UserInfolbl = new JLabel("");
	
	JMenu mnFile = new JMenu("File");
	
	public DashBoardView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1365,760);
		
		JMenuBar menuBar = new JMenuBar();
		
		
		setJMenuBar(menuBar);
		
		
		menuBar.add(mnFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		UserInfolbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		UserInfolbl.setBounds(183, 46, 418, 53);
		contentPane.add(UserInfolbl);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogout.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogout.setBackground(Color.WHITE);
			}
		});
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x=JOptionPane.showConfirmDialog(null, "Do you really want to logout", "Logout",JOptionPane.YES_NO_CANCEL_OPTION);
				if(x==0) {
				logout();
				}
			}
		});
		btnLogout.setBounds(1189, 59, 101, 40);
		contentPane.add(btnLogout);
	}
}
