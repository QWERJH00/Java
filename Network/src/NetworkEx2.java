import java.net.URL;
import java.net.URLConnection;

public class NetworkEx2
{

	public static void main(String[] args)
	{
		URL url = null;
		String address = "http://www.enjoypuzzle.com:80/" +
							"board/bbsview/nemonotice/260/1#1";
		
		try
		{
			url = new URL(address);
			URLConnection conn = url.openConnection();
			
			System.out.println("conn.toString():" + conn);
			// UserInteraction (����� ��ȣ�ۿ�)�� ��뿩�θ� ��ȯ
			System.out.println("getAllowUserInteraction():" +conn.getAllowUserInteraction());
			// ���� ����ð��� õ���� ���ʷ� ��ȯ
			System.out.println("getConnectTimeout():" + conn.getConnectTimeout());
			// content ��ü�� ��ȯ
			System.out.println("getContent" + conn.getContent());
			// content ��ü�� ���ڵ��� ��ȯ
			System.out.println("getContentEncoding()" + conn.getContentEncoding());
			// content�� ũ�⸦ ��ȯ
			System.out.println("getContentLength():" + conn.getContentLength());
			// content�� Ÿ���� ��ȯ
			System.out.println("getContentType():" + conn.getContentType());
			// �ش�(header)�� date�ʵ��� ���� ��ȯ. �и������� ����
			System.out.println("getDate():" + conn.getDate());
			// UseCache�� ����Ʈ���� ��´�.
			System.out.println("getDefaultUseCaches():" + conn.getDefaultUseCaches());
			// DoInput �ʵ尪�� ��´�
			System.out.println("getDoInput():" + conn.getDoInput());
			// DOOutput �ʵ尪�� ��´�.
			System.out.println("getDoOutput():" + conn.getDoOutput());
			// �ڿ��� �������ڸ� ��´�.
			System.out.println("getExpiration():" + conn.getExpiration());
			// ����� �ʴ� �о�´�.
			System.out.println("getHeaderFields():" + conn.getHeaderFields());
			//IfModifiedSince(���濩��) �ʵ��� ���� ��ȯ�Ѵ�.
			System.out.println("getIfModifiedSince():" + conn.getIfModifiedSince());
			// ���� ������ �ʵ��� ���� ��ȯ
			System.out.println("getLastModified():" + conn.getLastModified());
			//�б� ���ѽð��� ���� ��ȯ
			System.out.println("getReadTimeout():" + conn.getReadTimeout());
			// URLConnection�� URL�� ��ȯ
			System.out.println("getURL():" + conn.getURL());
			// ĳ���� ��뿩�θ� ��ȯ
			System.out.println("getUseCaches():" + conn.getUseCaches());
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
