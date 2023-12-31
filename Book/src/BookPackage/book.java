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
		System.out.println("[메뉴 선택]");
		System.out.println("=======================================");
		System.out.println("1. 책 등록");
		System.out.println("2. 책 조회");
		System.out.println("3. 책 전체 조회");
		System.out.println("4. 책 폐기"); 
		System.out.println("=======================================");
		System.out.println("5. 도서 대여");
		System.out.println("6. 도서 반납");
		System.out.println("=======================================");
		System.out.println("7. 신규 회원 가입");
		System.out.println("8. 종료");
	}
	
	
	public static void addBook()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		System.out.print("책 제목을 입력해주세요 : ");
		String book_name = sc.nextLine();
		System.out.print("책 권수 : ");
		String book_count = sc.nextLine();
//		System.out.print("책 번호 : ");
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
			int result = pstmt.executeUpdate(); //성공했는지 확인
			
			if(result ==1)
				System.out.println("등록에 성공하였습니다. ");
			else System.out.println("등록에 실패하였습니다. ");
//			con.rollback();
		} catch (SQLException sqle)
			{
			sqle.printStackTrace();
			System.out.println("데이터 입력에 실패했습니다.");
	
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
		System.out.print("조회할 책 제목을 입력해주세요 : ");
		String bookname = sc.nextLine();
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		rs = pstmt.executeQuery();
		int nResult = 0;
		
		while(rs.next()) {
			nResult++;
			System.out.println("책번호 : " + rs.getString("book_number"));
			System.out.println("제  목 : " + rs.getString("book_name"));
			System.out.println("수  량 : " + rs.getInt("book_count"));
			System.out.println("----------------------------------------");
		}
		if (nResult == 0)
		{
			System.out.println("조회할 책이 없습니다.");
			System.out.println("----------------------------------------");
		}
		
		}catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("데이터 입력에 실패했습니다.");
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
			
			System.out.println("책번호 : " + rs.getString("book_number"));
			System.out.println("제  목 : " + rs.getString("book_name"));
			System.out.println("수  량 : " + rs.getInt("book_count"));
			System.out.println("----------------------------------------");
		}
		
		}catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("데이터 입력에 실패했습니다.");
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
		System.out.println("삭제할 책 제목을 입력해주세요 : ");
		String bookname = sc.nextLine();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookname);
		
		int result = pstmt.executeUpdate(); //성공했는지 확인
		
		if(result ==1)
			System.out.println("삭제 되었습니다.");
		else System.out.println("없는 도서 입니다.");
		
		} catch (SQLException sqle)
		{
		sqle.printStackTrace();
		System.out.println("데이터 입력에 실패했습니다.");
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

		
		System.out.println("본인 확인을 위해 비밀번호를 입력해주세요 : ");
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

				System.out.print("대여할 도서번호를 입력하세요 : ");
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
					System.out.println("대여가 완료되었습니다.");
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

					System.out.println("대여가 정상적으로 완료되었습니다");
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("입력에 실패했습니다.(#4)");
				}
				
			}
			if (result == 0){
				System.out.println("회원 정보가 일치하지 않습니다.");
			}
		}
	catch(Exception e) {	
		System.out.println("입력에 실패했습니다.(#3)");
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
		
		System.out.println("본인확인을 위해 비밀번호를 입력해주세요 : ");
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
				System.out.print("반납할 도서번호를 입력하세요 : ");
				String book_number = sc.nextLine();
				sql = "delete from rentdb where book_number = ? and member_id = ?";
				
				try
				{
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, book_number);
					pstmt.setString(2, member_id);
					System.out.println("반납이 완료되었습니다.");
					
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
					System.out.println("에러발생");
				if (result ==0)
					{
					System.out.println("회원 정보가 일치하지 않습니다.");
					}
				}
			}
		} catch(Exception e)
		{
			System.out.println("입력에 실패하였습니다.");
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
		
		System.out.println("사용할 아이디를 입력해주세요 : ");
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
				System.out.println(rs.getString("member_id")+"는 이미 사용중인 ID입니다.");
				System.out.println("");
			}
			if (result == 0)
			{
				System.out.println(member_id +"는 사용 가능한 ID입니다.");
				System.out.print("Password : ");
				String member_pw = sc.nextLine();
				System.out.print("성함 : ");
				String member_name = sc.nextLine();
//				System.out.println("회원 번호 : ");
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
						System.out.println("회원가입이 정상적으로 처리 되었습니다.");
					} else
					{
						System.out.println("회원가입에 실패했습니다.(가입오류)");
					}
				} catch(SQLException sqle)
				{
					sqle.printStackTrace();
					System.out.println("데이터 입력에 실패하였습니다.(데이터 예외)");
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
		System.out.println("데이터 입력에 실패했습니다.");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}
		}
	}
	
	
}
