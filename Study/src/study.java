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
		System.out.println("�μ���ȣ �Է� : ");
		int deptno = sc.nextInt();
		System.out.println("�μ��̸� �Է� : ");
		String dname = sc.next();
		System.out.println("�����̸� �Է� : ");
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
			//3. Query�غ�
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,  deptno);
			pstm.setString(2,  dname);
			pstm.setString(3,  loc);
			//4. Query ���� �� ����
			int res = pstm.executeUpdate();
			if(res > 0) {
				System.out.println("�Է� ����");
			} else {
				System.out.println("�Է� ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. DB����
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

