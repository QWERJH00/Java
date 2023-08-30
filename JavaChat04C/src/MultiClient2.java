import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient2
{

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		System.out.println("�̸��� �Է��� �ּ���.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		PrintWriter out = null;
		BufferedReader in = null;
		
		try
		{
			String ServerIP = "localhost";
			if(args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("������ ������ �Ǿ����ϴ�....");
			
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream() ));
			
			out.println(name);
			
			while (out!=null)
			{
				try
				{
					if (in!=null)
					{
						System.out.println("Receive : " + in.readLine());
					}
					
					String s = sc.nextLine();
					out.println(s);
					
					if ( s.equals("q") || s.equals("Q"))
					{
						break;
					}
				} catch (IOException e)
				{
					System.out.println("����: " + e);
				}
				
			} 
			
		in.close();
		out.close();
		
		socket.close();
	} 	catch(Exception e)
		{
			System.out.println("����[MultiCilent calss]:" + e);
		}
	}
}
