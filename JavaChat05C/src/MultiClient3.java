import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient3
{

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		System.out.println("�̸��� �Է��� �ּ���.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		PrintWriter out = null;
		
		try
		{
			String ServerIP = "localhost";
			if(args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("������ ������ �Ǿ����ϴ�....");
			
			Thread receiver = new Receiver3(socket);
			receiver.start();
			
			out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println(name);
			
			while (out!=null)
			{
				try
				{
					
					
					String s = sc.nextLine();
					out.println(s);
					
					if ( s.equals("q") || s.equals("Q"))
					{
						break;
					}
				} catch (Exception e)
				{
					System.out.println("����: " + e);
				}
				
			} 
			
		out.close();
		
		socket.close();
	} 	catch(Exception e)
		{
			System.out.println("����[MultiCilent calss]:" + e);
		}
	}
}
