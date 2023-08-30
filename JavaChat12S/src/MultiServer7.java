import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer7
{
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	
	public MultiServer7()
	{
		clientMap = new HashMap<String, PrintWriter>();
		Collections.synchronizedMap(clientMap);
	}
	
	public void init()
	{
		try
		{
			serverSocket = new ServerSocket(9999);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while (true)
			{
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + ":" + socket.getPort());
			
			Thread mst = new MultiServerT(socket);
			mst.start();
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			try
			{
				serverSocket.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			} 
				
		}
	}
	
	public void list(PrintWriter out)
	{
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "����� ����Ʈ[";
		while (it.hasNext())
		{
			msg += (String)it.next() + ";";
		}
		msg = msg.substring(0, msg.length()-1) + "]";
		try
		{
			out.println(msg);
		} catch (Exception e)
		{
			
		}
	}
	
	public void sendAllMsg(String msg, String name)
	{
		Iterator<String> it = clientMap.keySet().iterator();
		
		while (it.hasNext())
		{
			try
			{
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (name.equals(""))
				
					it_out.println(msg);
				else
				
					it_out.println(name + " > " + msg);
				
			} catch (Exception e)
			{
				System.out.println("����:" + e);
			}
		}
	}
	
	
	
	public static void main(String[] args)
	{
		MultiServer7 ms = new MultiServer7();
		ms.init();
	}
// -------------------------------------------------------
	
	class MultiServerT extends Thread
	{
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;
		String name = "";
		 
		
	 public MultiServerT(Socket socket)
		{
			this.socket = socket;
			try
			{
				out = new PrintWriter(socket.getOutputStream(), true);
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
	 		String s = "";
	 		
	 		try
	 		{
	 			name = in.readLine();
	 			System.out.println("[" + name + "]���� ��ȭ�濡 �����ϼ̽��ϴ�.");
	 			
	 			sendAllMsg(name, "���� �����ϼ̽��ϴ�.");
	 			
	 			clientMap.put(name, out);
	 			
	 			System.out.println("���� ������ ���� " + clientMap.size() + "�� �Դϴ�.");
	 			while (in!=null)
	 			{
	 				s = in.readLine();
	 				
	 				
	 				if( s.equals("q") || s.equals("Q"))
	 					break;
	 				
	 				System.out.println(name + " > " + s);
	 				if (s.equals("/list"))
	 					list(out);
	 				else
	 					sendAllMsg(s, name);
	 				
	 			}
	 			System.out.println("������ ����");
	 		} catch(Exception e)
	 		{
	 			System.out.println("���� : " + e);
	 		}finally
	 		{
	 			clientMap.remove(name);
	 			sendAllMsg(name + "���� �����ϼ̽��ϴ�.", "");
	 			System.out.println("���� ������ ���� " + clientMap.size() + "�� �Դϴ�.");
	 			try
	 			{
	 				in.close();
	 				out.close();
	 				
	 				socket.close();
	 			}catch(Exception e)
	 			{
	 				e.printStackTrace();
	 			}
	 		}
	 	}

		
	 	
	 	
	}
	
}	

			
		
	
	
	
