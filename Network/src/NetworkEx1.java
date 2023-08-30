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
			// getHostName(): 호스트 이름을 문자열로 반환.
			System.out.println("getHostName() : " + ip.getHostName());
			// getHostAddress(): ip주소를 반환.
			System.out.println("getHostAdderss() : " + ip.getHostAddress());
			System.out.println("oString() : " + ip.toString());
			
			// getAddress(): InetAddress 객체의 ip주소를 반환
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
			// 실제 호스트 네임과 IP주소 얻기
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
			// 모든 ip주소 가져오기
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
