package com.xyz.testengine.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xyz.testengine.user.dto.RightDTO;
import com.xyz.testengine.user.dto.UserDTO;
import com.xyz.testengine.util.CommonDAO;
import com.xyz.testengine.util.constants.QueryConstants;

public class UserDAO {
	
	public UserDTO doLogin(String name,String password) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO userDTO = null;
		ArrayList<RightDTO> rights = null;
		try {
			connection=CommonDAO.setConnection();
			pstmt = connection.prepareStatement(QueryConstants.LOGIN_SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(userDTO==null) {
					userDTO=new UserDTO();
					userDTO.setName(rs.getString("userid"));
					userDTO.setRoleName(rs.getString("rolename"));
					rights= new ArrayList<>();
					userDTO.setRights(rights);
				}
				RightDTO right = new RightDTO(rs.getString("rightname"), rs.getString("screenname"));
				rights.add(right);
				
			}
			
			return userDTO;
			
		}
		
		finally {
			if(rs!= null) {
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
