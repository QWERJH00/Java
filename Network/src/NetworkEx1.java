import java.net.*;
import java.util.Arrays;
public class NetworkEx1
{

	public static void main(String[] args)
	{
		InetAddress ip = null;
		InetAddress[] ipArr = null;
		
		try
		{
			ip = InetAddress.getByName("www.naver.com");
			// getHostName(): ȣ��Ʈ �̸��� ���ڿ��� ��ȯ.
			System.out.println("getHostName() : " + ip.getHostName());
			// getHostAddress(): ip�ּҸ� ��ȯ.
			System.out.println("getHostAdderss() : " + ip.getHostAddress());
			System.out.println("oString() : " + ip.toString());
			
			// getAddress(): InetAddress ��ü�� ip�ּҸ� ��ȯ
			byte[] ipAddr = ip.getAddress();
			System.out.println("getAddress() : " + Arrays.toString(ipAddr));
			
			String result = "";
			for(int i=0; i < ipAddr.length; i++)
			{
				result += (ipAddr[i] < 0) ? ipAddr[i] + 256 : ipAddr[i];
				result += ".";
			}
			System.out.println("getAddress()+256 :"+result);
			System.out.println();
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			// ���� ȣ��Ʈ ���Ӱ� IP�ּ� ���
			ip = InetAddress.getLocalHost();
			System.out.println("getHostName() : " + ip.getHostName());
			System.out.println("getHostAdderss() : " + ip.getHostAddress());
			System.out.println();
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			// ��� ip�ּ� ��������
			ipArr = InetAddress.getAllByName("www.naver.com");
			
			for(int i=0; i < ipArr.length; i++)
			{
				System.out.println("ipArr["+i+"] :" + ipArr[i]);
			}
		}	catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		

	}

}
