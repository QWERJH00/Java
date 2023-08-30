import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer
{

	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String s = "";
		
		try
		{
			serverSocket = new ServerSocket(9999);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			socket = serverSocket.accept();
			// ����� ���� ��巹���� ������ �����ش�.
			System.out.println(socket.getInetAddress() + ":" + socket.getPort());
			// ������ ���۵� ��Ʈ��ũ �������̽��� ��Ʈ�� �˷��ش�.
			System.out.println(socket.getLocalAddress() + ":" + socket.getPort());
			
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
										socket.getInputStream() ));
			
			s = in.readLine();
			System.out.println(s);
			out.println(s);
			
			System.out.println("������ ����Ǿ����ϴ�.");
		
		} catch(Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				in.close();
				out.close();
				
				socket.close();
				serverSocket.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		

	}

}
