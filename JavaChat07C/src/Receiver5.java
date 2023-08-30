import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver5 extends Thread
{
	Socket socket;
	BufferedReader in = null;
	
	public Receiver5(Socket socket)
	{
		this.socket = socket;
		
		try
		{
			in = new BufferedReader(new InputStreamReader(
										this.socket.getInputStream() ));
		} catch(Exception e)
		{
			System.out.println("����:" + e);
		}	
	}	
	
	@Override
	public void run()
	{
		while (in!=null)
		{
			try
			{
				System.out.println("Thread Receive : " + in.readLine());
			} catch (java.net.SocketException ne)
			{
				break;
			} catch (Exception e)
			{
				System.out.println("����:" + e);
			}
		}
		
		try
		{
			in.close();
		} catch (Exception e)
		{
			System.out.println("����3 :" + e);
		}
	}	
}