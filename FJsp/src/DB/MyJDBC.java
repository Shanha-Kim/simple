package DB;

/*
	이 클래스는 데이터베이스 작업에 필요한
	드라이버 로딩, Connection, Statement, PreparedStatement 를 만들어줄 클래스이다.
	
	이 클래스를 인스턴스로 만드는 순간
	드라이버 로딩이 완료되는 클래스로 제작을 하자.
 */

import java.sql.*;

public class MyJDBC {
	
	// 이 클래스가 new 되는 상황은 
	// 오라클 데이터베이스를 사용하는 상황이므로
	// 먼저 이 클래스가 인스턴스가 될때 드라이버 로딩이 되게 해주자.
	public MyJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("### 드라이버 로딩 실패 ###");
		}
	}
	
	// Connection 얻어오는 함수
	public Connection getCON() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pw = "tiger";
		try {
			con = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			System.out.println("### 커넥션 얻기 실패 ###");
		}
		
		return con;
	}
	
	// Statement 얻어내주는 함수
	public Statement getSTMT(Connection con) {
		Statement stmt = null;
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			/*
				
				con.createStatement(ResultSet Type, Concurrency type)
				
				ResultSet Type
					1. TYPE_FORWARD_ONLY : scroll 이 불가능한 forward only 형
					2. TYPE_SCROLL_INSENSITIVE : scroll은 가능하나, 변경된 사항은 적용되지 않음
					3. TYPE_SCROLL_SENSITIVE : scroll도 가능하고, 변경된 사항도 적용됨
					
				Concurrency Type
					1. CONCUR_READ_ONLY		: ResultSet Object 를 변경이 불가능
					2. CONCUR_UPDATABLE 	: ResultSet Object 를 변경이 가능
		
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stmt;
	}
	
	// PreparedStatement 얻어주는 함수
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {}
		
		return pstmt;
	}
	
	// 누군가 필요가 없어서 Connection, Statement, PreparedStatement, ResultSet 을 닫고 싶은경우
	// 닫을 수 있는 함수를 만들어 주자.
	public void close(Object o) {
		try {
			if(o instanceof Connection) {
				((Connection) o).close();
			} else if(o instanceof Statement) {
				((Statement) o).close();
			} else if(o instanceof PreparedStatement) {
				((PreparedStatement) o).close();
			} else if(o instanceof ResultSet) {
				((ResultSet) o).close();
			}
		} catch(Exception e) {}
	}
	
}
