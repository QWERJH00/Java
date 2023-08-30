
public class ThreadSub extends Thread
{
	int nNum;
	
	public ThreadSub(int num)
	{
		this.nNum = num;
	}
	
	@Override
	public void run()
	{
		int i = 0;
		while (i < nNum)
		{
			try
			{
				Thread.sleep(1000);
				i = i +1;
				System.out.println("Thread : " + i);
			} catch(Exception e)
			{
				System.out.println("¿¹¿Ü:" + e);
			}
		}
	}
}
