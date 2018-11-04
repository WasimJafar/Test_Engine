package com.xyz.testengine.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.regexp.internal.recompile;
import com.xyz.testengine.user.dto.UserDTO;
import com.xyz.testengine.util.CommonDAO;
import com.xyz.testengine.util.constants.QueryConstants;

public class RegisterDAO {
	
	public String checkOldPass(String userid) throws ClassNotFoundException, SQLException {
		
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String password=null;
		 try {
			 connection=CommonDAO.setConnection();
			 pstmt=connection.prepareStatement(QueryConstants.CHECK_OLD_PASSWORD_SQL);
			 pstmt.setString(1, userid);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 password = rs.getString("password");
				}
			 if(password!=null) {
				 return password;
			 }
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
		
		 return null;
		
	}
	
	
	public boolean changePassword(String password,String userid) throws SQLException, ClassNotFoundException {
		
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 int result=0;
		 try {
			 connection=CommonDAO.setConnection();
			 pstmt=connection.prepareStatement(QueryConstants.CHANGE_PASSWORD_SQL);
			 pstmt.setString(1, password);
			 pstmt.setString(2, userid);
			 result=pstmt.executeUpdate();
			 if(result!=0) {
				 return true;
			 }
		 }
		 finally {
			 if(pstmt!=null) {
				pstmt.close();
				}
			 if(connection!=null) {
				connection.close();
				}
		 }
		 
		 return false;
	}
	
	
	public boolean checkUserid(String userid) throws SQLException, ClassNotFoundException {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 int uid=0;
		 try {
			 connection=CommonDAO.setConnection();
			 pstmt=connection.prepareStatement(QueryConstants.CHECK_USERID_SQL);
			 pstmt.setString(1, userid);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 uid = rs.getInt("uid");
				}
			 if(uid==0) {
				 return true;
			 }
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
		
		 return false;
		 
	 }
	 
	
	
	public boolean addTeacher(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		 Connection connection = null;
			PreparedStatement pstmt =null;
				try {
				connection=CommonDAO.setConnection();
				pstmt=connection.prepareStatement(QueryConstants.ADD_TEACHER_SQL);
				pstmt.setString(1, userDTO.getName());
				pstmt.setString(2, userDTO.getPassword());
				
				int result=pstmt.executeUpdate();

				if(result>0) {

					return true;
				}
				
				}
				finally {
					if(pstmt!=null) {
						pstmt.close();
					}
					if(connection!=null) {
						connection.close();
					}
					
				}
				return false;
		 
		}

	
	
	
 public boolean doRegister(UserDTO userDTO) throws ClassNotFoundException, SQLException {
	 Connection connection = null;
		PreparedStatement pstmt =null;
			try {
			connection=CommonDAO.setConnection();
			pstmt=connection.prepareStatement(QueryConstants.REGISTER_SQL);
			pstmt.setString(1, userDTO.getName());
			pstmt.setString(2, userDTO.getPassword());
			pstmt.setString(3, userDTO.getGender());
			pstmt.setString(4, userDTO.getDateOfbirth());
			pstmt.setString(5, userDTO.getAddress());
			pstmt.setString(6, userDTO.getCity());
			int result=pstmt.executeUpdate();

			if(result>0) {
//			return "Register Successfully";
				return true;
			}
			
			}
			finally {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(connection!=null) {
					connection.close();
				}
				
			}
			return false;
	 
	}

 
 public int getUid() throws SQLException, ClassNotFoundException {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 int uid=0;
	 try {
		 connection=CommonDAO.setConnection();
		 pstmt=connection.prepareStatement(QueryConstants.GET_UID_SQL);
		 rs = pstmt.executeQuery();
		 while(rs.next()) {
			 uid = rs.getInt("last_value");
			
			 }
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
	
	 return uid;
	 
 }
 
 
 

 
 
 public boolean userRoleMappingTeacher(int uid) throws SQLException, ClassNotFoundException {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 try {
		 if(uid!=0) {
		 connection = CommonDAO.setConnection();
		 pstmt=connection.prepareStatement(QueryConstants.USER_ROLE_MAPP_TEACHER_SQL);
		 pstmt.setInt(1, uid);
		 pstmt.setInt(2, 2);
		 int rs= pstmt.executeUpdate();
		 if(rs!=0) {
				return true;
				}
				else {
					connection.rollback();
				}
	 }
	 }
	 finally {
		 if(pstmt!=null) {
				pstmt.close();
			}
		 if(connection!=null) {
				connection.close();
			}
	 }
	return false;
 }
	 
	 
 
 
 public boolean userRoleMapping(int uid) throws SQLException, ClassNotFoundException {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 try {
		 if(uid!=0) {
		 connection = CommonDAO.setConnection();
		 pstmt=connection.prepareStatement(QueryConstants.USER_ROLE_MAP_SQL);
		 pstmt.setInt(1, uid);
		 pstmt.setInt(2, 3);
		 int rs= pstmt.executeUpdate();
		 if(rs!=0) {
				return true;
				}
				else {
					connection.rollback();
				}
	 }
	 }
	 finally {
		 if(pstmt!=null) {
				pstmt.close();
			}
		 if(connection!=null) {
				connection.close();
			}
	 }
	return false;
	 
	 
 }


}
