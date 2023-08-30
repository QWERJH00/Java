package jdbcEx01;

import java.sql.*;

public class Main
{
	static 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) 
		{
			cnfe.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		try
		{	//오라클 데이터베이스 연결
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",	//아이디
					"TIGER");	//패스워드
			Statement stmt = con.createStatement();
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from emp"); // SQL문을 여기다 적음
			
			ResultSet rs = stmt.executeQuery(sb.toString());
			while(rs.next()) {
				System.out.print("eno : " + rs.getInt(1) + ", ");
				System.out.println("ename : " + rs.getString("ename"));
							
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException sqle)
		{
			System.out.println("Connection Error");
			sqle.printStackTrace();
		}

	}

}
