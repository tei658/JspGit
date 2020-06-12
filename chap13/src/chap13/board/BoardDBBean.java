package chap13.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	
	private BoardDBBean(){}
	
	public String doA() {
		return "연결";
	}
	
	public static BoardDBBean getinstance() {
		return instance;
	}
	
	public void insertArticle(BoardDataBean dataBean) {
		System.out.println(dataBean);
		Connection conn = null; // DB 연결 객체
		PreparedStatement pstmt = null; // sql 연결에 insert select update delete
		// jar 파일 관리하는 회사 maven or gradle
		
		// jar 파일 연결되어 있는지 확인 Class.forName();
		// Connection 객체 연결 Drivermanager.getConnection();
		// prepareStatement 생성 conn.prepareStatement();
		// 실행 
		
		try {
			// mysql 3306
			// oracle 1521
			// sqlserver 1433
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.200:1433;database=te_20200611", "sa", "8765432!");
			System.out.println("DB 연결 성공");
			pstmt = conn.prepareStatement("INSERT INTO [dbo].[board]\r\n" + 
					"           ([num]\r\n" + 
					"           ,[writer]\r\n" + 
					"           ,[email]\r\n" + 
					"           ,[subject]\r\n" + 
					"           ,[passwd]\r\n" + 
					"		   ,[reg_date]\r\n" + 
					"           ,[readcount]\r\n" + 
					"           ,[ref]\r\n" + 
					"           ,[re_step]\r\n" + 
					"           ,[re_level]\r\n" + 
					"           ,[content]\r\n" + 
					"           ,[ip])\r\n" + 
					"     VALUES\r\n" + 
					"           ((select max(num)+1 from board)\r\n" + 
					"           ,? " + 
					"           ,? " + 
					"           ,? " + 
					"           ,? " + 
					"		   ,getdate()\r\n" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           ,? " + 
					"           ,'192.168.0.198')");
			pstmt.setString(1, dataBean.getWriter());
			pstmt.setString(2, dataBean.getEmail());
			pstmt.setString(3, dataBean.getSubject());
			pstmt.setString(4, dataBean.getPasswd());
			pstmt.setString(5, dataBean.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertDetailArticle(String subject, String name, String email, String content, String password) {
		System.out.println("subject = " + subject);
		System.out.println("name = " + name);
		System.out.println("email = " + email);
		System.out.println("content = " + content);
		System.out.println("password = " + password);
	}
}