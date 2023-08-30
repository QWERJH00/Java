import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer3
{
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	static PrintWriter out = null;
	static BufferedReader in = null;
	static String s = "";
	static String name = "";
	
	public MultiServer3()
	{
		
	}
	
	public static void init()
	{
		try
		{
			serverSocket = new ServerSocket(9999);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + ":" + socket.getPort());
	
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
										socket.getInputStream() ));
			
			name = in.readLine();
			System.out.println("[" + name + "]���� ��ȭ�濡 �����ϼ̽��ϴ�.");
			out.println("[" + name + "] ��ȭ�� ����");
			
			while (in!=null)
			{
				s = in.readLine();
				
				if(s == null)
					break;
				if (s.equals("q") || s.equals("Q") )
					break;
				
				System.out.println(s);
//				out.println(name + " > " + s);
				sendAllMsg(s);
			}
			
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
	
	public static void sendAllMsg(String msg)
	{
		try
		{
			out.println(name + " > " + msg);
		} catch (Exception e)
		{
			System.out.println("����:" + e);
		}
	}
	public static void main(String[] args)
	{
		init();
	}

}
