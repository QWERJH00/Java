package BookPackage;

import java.security.DrbgParameters.NextBytes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;



public class book
{
	private static final String LocalaDate = null;


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
	static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() 
	{
		System.out.println("[�޴� ����]");
		System.out.println("=======================================");
		System.out.println("1. å ���");
		System.out.println("2. å ��ȸ");
		System.out.println("3. å ��ü ��ȸ");
		System.out.println("4. å ���"); 
		System.out.println("=======================================");
		System.out.println("5. ���� �뿩");
		System.out.println("6. ���� �ݳ�");
		System.out.println("=======================================");
		System.out.println("7. �ű� ȸ�� ����");
		System.out.println("8. ����");
	}
	
	
	public static void addBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		System.out.print("å ������ �Է����ּ��� : ");
		String book_name = sc.nextLine();
		System.out.print("å �Ǽ� : ");
		String book_count = sc.nextLine();
//		System.out.print("å ��ȣ : ");
//		String book_number = sc.nextLine();
		
		try
		{
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"study",
				"1234");
		
			con.setAutoCommit(false);
	
			String sql = "insert into BOOKDB values(?, ?,'')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_name);
			pstmt.setString(2, book_count);
//			pstmt.setString(3, book_number);
			int result = pstmt.executeUpdate(); //�����ߴ��� Ȯ��
			
			if(result ==1)
				System.out.println("��Ͽ� �����Ͽ����ϴ�. ");
			else System.out.println("��Ͽ� �����Ͽ����ϴ�. ");
//			con.rollback();
		} catch (SQLException sqle)
			{
			sqle.printStackTrace();
			System.out.println("������ �Է¿� �����߽��ϴ�.");
	
			} finally
			{
				try
				{
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
				} catch(SQLException sqle) {}
			}
		

		
	}

	public static void selBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
		con = DriverManager.getConnection(
		
				"jdbc:oracle:thin:@localhost:1521:xe",
				"study",
				"1234");
		
		String sql = "select * from bookdb where book_name = ?";
		System.out.print("��ȸ�� å ������ �Է����ּ��� : ");
		String bookname = sc.nextLine();
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		rs = pstmt.executeQuery();
		int nResult = 0;
		
		while(rs.next()) {
			nResult++;
			System.out.println("å��ȣ : " + rs.getString("book_number"));
			System.out.println("��  �� : " + rs.getString("book_name"));
			System.out.println("��  �� : " + rs.getInt("book_count"));
			System.out.println("----------------------------------------");
		}
		if (nResult == 0)
		{
			System.out.println("��ȸ�� å�� �����ϴ�.");
			System.out.println("----------------------------------------");
		}
		
		}catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("������ �Է¿� �����߽��ϴ�.");
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(SQLException sqle) {}
		}
		
	}
		
	
	public static void allselBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"study",
				"1234");
		
		String sql = "select * from bookdb ";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
			while(rs.next()) {
			
			System.out.println("å��ȣ : " + rs.getString("book_number"));
			System.out.println("��  �� : " + rs.getString("book_name"));
			System.out.println("��  �� : " + rs.getInt("book_count"));
			System.out.println("----------------------------------------");
		}
		
		}catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("������ �Է¿� �����߽��ϴ�.");
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				if (rs != null) rs.close();
			} catch(SQLException sqle) {}
		}
	}
	
	
	public static void delBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"study",
				"1234");
		
		String sql = "delete from bookdb where book_name = ? ";
		System.out.println("������ å ������ �Է����ּ��� : ");
		String bookname = sc.nextLine();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		
		int result = pstmt.executeUpdate(); //�����ߴ��� Ȯ��
		
		if(result ==1)
			System.out.println("���� �Ǿ����ϴ�.");
		else System.out.println("���� ���� �Դϴ�.");
		
		} catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("������ �Է¿� �����߽��ϴ�.");
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(SQLException sqle) {}
		}
	}

	
	public static void rentBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		System.out.println("���� Ȯ���� ���� ��й�ȣ�� �Է����ּ��� : ");
		String member_pw = sc.nextLine();
	
		try
		{	
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			String sql = "select * from memberdb where member_pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			rs = pstmt.executeQuery();
			int result = 0;
			while(rs.next()) 
			{
				result++;
			
				String member_id = rs.getString("member_id");
				String member_number = rs.getString("member_number");
				LocalDate sysdate = LocalDate.now();
				LocalDate rent_date = sysdate;
				LocalDate return_date = sysdate.plusWeeks(1);

				System.out.print("�뿩�� ������ȣ�� �Է��ϼ��� : ");
				String book_number = sc.nextLine();
				
				sql = "select * from bookdb where book_count = ?";
				pstmt = con.prepareStatement(sql);
				String book_count = null;
				pstmt.setString(1, book_count);
				
				if ((Integer)Integer.parseInt(book_number) == 1)
				{
					
				}
				
				try
				{	
					sql = "insert into rentdb values(?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, member_id);
					pstmt.setString(2, book_number);
					pstmt.setDate(3,java.sql.Date.valueOf(rent_date));
					pstmt.setDate(4,java.sql.Date.valueOf(return_date));
					pstmt.setString(5, member_number);	
					System.out.println("�뿩�� �Ϸ�Ǿ����ϴ�.");
					System.out.println("");

					int updateCount = pstmt.executeUpdate();
					if(updateCount==1)
					{
						sql = "update bookdb set book_count = book_count -1 "
								+ "where book_number = ?";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, book_number);
						pstmt.executeUpdate();					
						return;
					} 

					System.out.println("�뿩�� ���������� �Ϸ�Ǿ����ϴ�");
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("�Է¿� �����߽��ϴ�.(#4)");
				}
				
			}
			if (result == 0){
				System.out.println("ȸ�� ������ ��ġ���� �ʽ��ϴ�.");
			}
		}
	catch(Exception e) {	
		System.out.println("�Է¿� �����߽��ϴ�.(#3)");
		e.printStackTrace();
	} finally
	{
		try
		{
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch(SQLException sqle) {}
	}
		
	}
	
	public static void returnBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("����Ȯ���� ���� ��й�ȣ�� �Է����ּ��� : ");
		String member_pw = sc.nextLine();
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			String sql = "select * from memberdb where member_pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			rs = pstmt.executeQuery();
 			int result = 0;
			while(rs.next())
			{
				result++;
				String member_id = rs.getString("member_id");
				System.out.print("�ݳ��� ������ȣ�� �Է��ϼ��� : ");
				String book_number = sc.nextLine();
				sql = "delete from rentdb where book_number = ? and member_id = ?";
				
				try
				{
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, book_number);
					pstmt.setString(2, member_id);
					System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�.");
					
					int updateCount = pstmt.executeUpdate();
					if(updateCount==1)
					{
						sql = "update bookdb set book_count = book_count + 1 "
								+ "where book_number = ?";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, book_number);
						pstmt.executeUpdate();
						return;
					}
					
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("�����߻�");
				if (result ==0)
					{
					System.out.println("ȸ�� ������ ��ġ���� �ʽ��ϴ�.");
					}
				}
			}
		} catch(Exception e)
		{
			System.out.println("�Է¿� �����Ͽ����ϴ�.");
			e.printStackTrace();
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(SQLException sqle) 
			{} finally
			{
				try
				{
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
				} catch(SQLException sqle) {}
			}
		}
		
	}
	
	public static void newMember()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("����� ���̵� �Է����ּ��� : ");
		String member_id = sc.nextLine(); 
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			String sql = "select * from memberdb where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			int result = 0;
			
			if(result ==0)
			while(rs.next()) 
			{
				result++;
				rs.getString("member_id");
				System.out.println(rs.getString("member_id")+"�� �̹� ������� ID�Դϴ�.");
				System.out.println("");
			}
			if (result == 0)
			{
				System.out.println(member_id +"�� ��� ������ ID�Դϴ�.");
				System.out.print("Password : ");
				String member_pw = sc.nextLine();
				System.out.print("���� : ");
				String member_name = sc.nextLine();
//				System.out.println("ȸ�� ��ȣ : ");
//				String member_number = sc.nextLine();
				
				try
				{
					String sql2 = "insert into memberdb values (?, ?, ?, '')";
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, member_id);
					pstmt.setString(2, member_pw);
					pstmt.setString(3, member_name);
//					pstmt.setString(4, member_number);
					int updateCount = pstmt.executeUpdate();
					if(updateCount == 1)
					{
						System.out.println("ȸ�������� ���������� ó�� �Ǿ����ϴ�.");
					} else
					{
						System.out.println("ȸ�����Կ� �����߽��ϴ�.(���Կ���)");
					}
				} catch(SQLException sqle)
				{
					sqle.printStackTrace();
					System.out.println("������ �Է¿� �����Ͽ����ϴ�.(������ ����)");
				} finally
				{
					try
					{
						if (pstmt != null) pstmt.close();
						if (con != null) con.close();
					} catch(SQLException sqle) {}
				}
				
			}

			
		
		}catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("������ �Է¿� �����߽��ϴ�.");
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(SQLException sqle) 
			{} finally
			{
				try
				{
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
				} catch(SQLException sqle) {}
			}
		} 
	}
	
	
	
	
	public static void main(String[] args)
	{
		
		int choice;
		while(true)
		{
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice)
			{
			case 1:
				addBook();
				break;
			case 2:
				selBook();
				break;
			case 3:
				allselBook();
				break;
			case 4:
				delBook();
				break;
			case 5:
				rentBook();
				break;
			case 6:
				returnBook();
				break;
			case 7:
				newMember();
				break;
			case 8:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}
	
	
}