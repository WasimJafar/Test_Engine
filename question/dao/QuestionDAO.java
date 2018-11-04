package com.xyz.testengine.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xyz.testengine.question.dto.QuestionDTO;
import com.xyz.testengine.user.dto.UserDTO;
import com.xyz.testengine.util.CommonDAO;
import com.xyz.testengine.util.constants.QueryConstants;

public class QuestionDAO {
	
	
	
	public void resultUpload(int finalScore) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
		
			connection=CommonDAO.setConnection();
			pstmt = connection.prepareStatement(QueryConstants.SAVE_RESULT_SQL);
//			pstmt.setString(1, userDTO.getName());
			pstmt.setInt(1, finalScore);
			int rs=pstmt.executeUpdate();
			
		
		}
		finally {
			if(pstmt!=null) {
				pstmt.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		
	}
	
	
	public ArrayList<QuestionDTO> getQuestion() throws ClassNotFoundException, SQLException{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			ArrayList<QuestionDTO> questions = new ArrayList<>();
			connection=CommonDAO.setConnection();
			pstmt = connection.prepareStatement(QueryConstants.QUESTION_FETCH_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QuestionDTO question = new QuestionDTO();
				question.setId(rs.getInt("qid"));
				question.setName(rs.getString("name"));
				question.setAns1(rs.getString("ans1"));
				question.setAns2(rs.getString("ans2"));
				question.setAns3(rs.getString("ans3"));
				question.setAns4(rs.getString("ans4"));
				question.setRans(rs.getString("rans"));
				question.setScore(rs.getInt("score"));
				questions.add(question);

			}
			return questions;
		}
		
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		
	}
	
	
	
	public boolean bulkUpload(ArrayList<QuestionDTO> questionList) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = CommonDAO.setConnection();
			pstmt= connection.prepareStatement(QueryConstants.UPLOAD_SQL);
			for(QuestionDTO question : questionList) {
				pstmt.setInt(1, question.getId());
				pstmt.setString(2, question.getName());
				pstmt.setString(3, question.getAns1());
				pstmt.setString(4, question.getAns2());
				pstmt.setString(5, question.getAns3());
				pstmt.setString(6, question.getAns4());
				pstmt.setString(7, question.getRans());
				pstmt.setInt(8, question.getScore());
				pstmt.addBatch();
			}
			int result[]=pstmt.executeBatch();
			return true;
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(connection!=null) {
				connection.close();
			}
			
		}
		
		
		
	}
	
}
