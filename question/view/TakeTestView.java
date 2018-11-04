package com.xyz.testengine.question.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import com.xyz.testengine.question.dao.QuestionDAO;
import com.xyz.testengine.question.dto.QuestionDTO;
import com.xyz.testengine.user.dto.UserDTO;
import com.xyz.testengine.util.ExceptionMessage;

public class TakeTestView extends JFrame {

	private JPanel contentPane;

	
	public TakeTestView(UserDTO userDTO) {
		this();
		this.userDTO=userDTO;
	}
	
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
					TakeTestView frame = new TakeTestView();
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

	private void loadQuestion() {
		try {
			questions=new QuestionDAO().getQuestion();
			numberOfQuestion=questions.size();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.CLASS_NOT_FOUND);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, ExceptionMessage.SQL);
			e.printStackTrace();
		}
	}
	
	private void printQuestion(int index) {
		if(index<questions.size()) {
			fetchAns(index);
			QuestionDTO currentQuestion = questions.get(index);
			qno.setText(String.valueOf(currentQuestion.getId()));
			qname.setText(currentQuestion.getName());
			radioans1.setText(currentQuestion.getAns1());
			radioans2.setText(currentQuestion.getAns2());
			radioans3.setText(currentQuestion.getAns3());
			radioans4.setText(currentQuestion.getAns4());
			setEnableDisable();
		}
		
	}
	
	private void setAns(int index) {
		
		if(radioans1.isSelected()) {
			yourAns="A";
		}
		else
			if(radioans2.isSelected()) {
				yourAns="B";
			}
		else
			 if(radioans3.isSelected()) {
					yourAns="C";
			}
		else
			if(radioans4.isSelected()) {
					yourAns="D";
			}
		else {
			yourAns=null;
			}
		questions.get(index).setYourAns(yourAns);
	
	}
	
	
	private void fetchAns(int index) {
		if(questions.get(index).getYourAns()!=null) {
		yourAns=questions.get(index).getYourAns();
		if(yourAns.equals("A")) {
			radioans1.setSelected(true);
		}
		else
			if(yourAns.equals("B")) {
				radioans2.setSelected(true);
		}
		else
			if(yourAns.equals("C")) {
				radioans3.setSelected(true);
			}
		else
			if(yourAns.equals("D")) {
				radioans4.setSelected(true);
			}
		}
	else {
		questions.get(index).setYourAns(null);
		bg.clearSelection();
	 }
		
	}
	
	private void finishTest() throws ClassNotFoundException, SQLException {
		setAns(index);
		ResultView resultView = new ResultView(questions,checkScore());
		resultView.setVisible(true);
		this.setVisible(false);
	}
	
	private int checkScore() {
		int score=0;
		
		for(QuestionDTO question : questions) {
			if(question.getYourAns()==null) {
				question.setScore(0);
			}
			
			else {
				
				if(question.getRans().equals(question.getYourAns())) {
					score = score+question.getScore();
				}
				else {
					question.setScore(0);
				}
			}
			
		}
		return score;
		
	}
	
	private void setEnableDisable() {
		if(questions.size()==1){
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(false);
				}
		else
		if(index==0){
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(true);
		}
		else
		if(index == questions.size()-1){
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(true);
		}
		else
		if(index>0 && index<questions.size()){
			btnPrevious.setEnabled(true);
			btnNext.setEnabled(true);
		}
	}
		
	
	private void buttons(int numberofQuestions) {
		
		int x=40;
		int z=0;
		buttonName.add(0,zero);
		buttonName.add(1,one);
		buttonName.add(2,two);
		buttonName.add(3,three);
		buttonName.add(4,four);
		buttonName.add(5,five);
		buttonName.add(6,six);
		buttonName.add(7,seven);
		buttonName.add(8,eight);
		buttonName.add(9,nine);
		buttonName.add(10,ten);
		
		for(y=1; y<=numberofQuestions;y++) {
			z=z+x;
			buttonName.get(y).setText(String.valueOf(y));	
			buttonName.get(y).setBounds(z, 32, 34, 33);
			buttonName.get(y).setFont(new Font("Tahoma", Font.PLAIN, 9));
			contentPane.add(buttonName.get(y));
		}
		
	}
	

	
	String yourAns=null; 
	int index=0;
	int y;
	int q=0;
	ArrayList<QuestionDTO> questions = new ArrayList<>();
	JRadioButton radioans1 = new JRadioButton("");
	JRadioButton radioans2 = new JRadioButton("");
	JRadioButton radioans3 = new JRadioButton("");
	JRadioButton radioans4 = new JRadioButton("");
	private final JButton btnPrevious = new JButton("Previous");
	private final JButton btnNext = new JButton("Next");
	ButtonGroup bg = new ButtonGroup();
	private final JButton btnFinish = new JButton("Finish");
	private final JLabel qname = new JLabel("");
	private final JLabel qno = new JLabel("");
	private int numberOfQuestion;
	
	JButton one = new JButton("");
	JButton two = new JButton("");
	JButton three = new JButton("");
	JButton four = new JButton("");
	JButton five = new JButton("");
	JButton six = new JButton("");
	JButton seven = new JButton("");
	JButton eight = new JButton("");
	JButton nine = new JButton("");
	JButton ten = new JButton("");
	JButton zero = new JButton("");
	
	ArrayList<JButton> buttonName = new ArrayList<>();
	
	private UserDTO userDTO=null;	
	public TakeTestView() {
		setBackground(Color.LIGHT_GRAY);
		setTitle("Test");
		bg.add(radioans1);
		bg.add(radioans2);
		bg.add(radioans3);
		bg.add(radioans4);
		loadQuestion();
		printQuestion(0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 110, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		radioans1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		

		
		radioans1.setBounds(90, 152, 409, 23);
		contentPane.add(radioans1);
		radioans2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		radioans2.setBounds(90, 215, 409, 23);
		contentPane.add(radioans2);
		radioans3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		radioans3.setBounds(90, 274, 409, 23);
		contentPane.add(radioans3);
		radioans4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		radioans4.setBounds(90, 327, 409, 23);
		contentPane.add(radioans4);
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAns(index);
				index=index -1;
				printQuestion(index);
			}
		});
		btnPrevious.setBounds(90, 393, 96, 35);
		
		contentPane.add(btnPrevious);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAns(index);
				index = index +1;
				printQuestion(index);
			}
		});
		btnNext.setBounds(229, 393, 96, 35);
		
		contentPane.add(btnNext);
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					finishTest();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFinish.setBounds(368, 393, 96, 35);
		
		contentPane.add(btnFinish);
		qname.setFont(new Font("Tahoma", Font.BOLD, 15));
		qname.setBounds(89, 77, 420, 47);
		
		contentPane.add(qname);
		qno.setFont(new Font("Tahoma", Font.BOLD, 15));
		qno.setBounds(33, 84, 46, 35);
		
		contentPane.add(qno);
		
		buttons(numberOfQuestion);
	}
}
