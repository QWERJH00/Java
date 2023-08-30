import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient5
{

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		System.out.println("�̸��� �Է��� �ּ���.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		try
		{
			String ServerIP = "localhost";
			if(args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("������ ������ �Ǿ����ϴ�....");
			
			Thread receiver = new Receiver5(socket);
			receiver.start();
			
			Thread sender = new Sender5(socket, name);
			sender.start();
			
			
		} 	catch(Exception e)
		{
			System.out.println("����[MultiCilent calss]:" + e);
		}
	}
}
