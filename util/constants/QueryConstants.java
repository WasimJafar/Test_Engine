package com.xyz.testengine.util.constants;

public interface QueryConstants {
	
	String LOGIN_SQL="SELECT USER_MST.USERID, ROLE_MST.NAME AS ROLENAME, RIGHT_MST.NAME AS RIGHTNAME,RIGHT_MST.SCREENNAME "
			+ "FROM USER_MST, ROLE_MST,RIGHT_MST,USER_ROLE_MAPPING,ROLE_RIGHT_MAPPING WHERE USER_MST.UID=USER_ROLE_MAPPING.UID "
			+ "AND ROLE_MST.ROLEID=USER_ROLE_MAPPING.ROLEID AND ROLE_MST.ROLEID=ROLE_RIGHT_MAPPING.ROLEID AND"
			+ " RIGHT_MST.RIGHTID=ROLE_RIGHT_MAPPING.RIGHTID AND USER_MST.USERID=? and USER_MST.PASSWORD=?";
	String REGISTER_SQL="insert into user_mst (userid,password,gender,dateofbirth,address,city) values (?,?,?,?,?,?)";
	String UPLOAD_SQL ="insert into question (qid,name,ans1,ans2,ans3,ans4,rans,score) values (?,?,?,?,?,?,?,?)";
			
	String QUESTION_FETCH_SQL= "select qid,name,ans1,ans2,ans3,ans4,rans,score from question";
	String SAVE_RESULT_SQL = "insert into result(score) values (?)";
	String GET_UID_SQL = "select last_value from user_mst_uid_seq";
	String ADD_TEACHER_SQL = "insert into user_mst (userid,password) values (?,?)";
	String USER_ROLE_MAP_SQL = "insert into user_role_mapping (uid,roleid) values (?,?)";
	String USER_ROLE_MAPP_TEACHER_SQL = "insert into user_role_mapping (uid,roleid) values (?,?)";
	String CHECK_USERID_SQL = "select uid from user_mst where userid=?";
	
	String CHANGE_PASSWORD_SQL="update user_mst set password=? where userid=?";
	String CHECK_OLD_PASSWORD_SQL="select password from user_mst where userid=?";
	
	
}
