import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class study
{
	
	public static void main(String[] args) {
		insertForPrepared();
	}
	
	public static void insertForPrepared() {
		Scanner sc = new Scanner(System.in);
		System.out.println("부서번호 입력 : ");
		int deptno = sc.nextInt();
		System.out.println("부서이름 입력 : ");
		String dname = sc.next();
		System.out.println("지역이름 입력 : ");
		String loc = sc.next();
		
		//1.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "cine";
		String password = "cine";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3.
		String sql = " INSERT INTO DEPT "
				   + " VALUES (?, ?, ?) ";
		
		PreparedStatement pstm = null;
		
		try {
			//3. Query준비
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,  deptno);
			pstm.setString(2,  dname);
			pstm.setString(3,  loc);
			//4. Query 실행 및 리턴
			int res = pstm.executeUpdate();
			if(res > 0) {
				System.out.println("입력 성공");
			} else {
				System.out.println("입력 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. DB종료
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

