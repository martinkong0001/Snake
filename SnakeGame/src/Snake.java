import java.awt.event.ActionEvent;
import java.util.ArrayList;

class Snake implements Runnable
{
	
	int length;
	ArrayList<Integer> xs;
	ArrayList<Integer> ys;
	int direction;
	int eatingMode;
	boolean isAlive;
	
	public Snake()
	{
		length = 3;
		xs = new ArrayList<Integer>();
		xs.add(80);
		xs.add(60);
		xs.add(40);
		ys = new ArrayList<Integer>();
		ys.add(40);
		ys.add(40);
		ys.add(40);
		direction = 1;
		eatingMode = 0;
		isAlive = true;
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			if (eatingMode > 0)
			{
				xs.add(xs.get(length - 1));
				ys.add(ys.get(length - 1));
				length ++;
				eatingMode --;
			}
			
			for (int i = length - 1; i > 0; i--)
			{
				xs.set(i, xs.get(i - 1));
				ys.set(i, ys.get(i - 1));
			}
				
			switch (direction)
			{
				case 0:
					ys.set(0, ys.get(0) - 20);
					break;
				case 1:
					xs.set(0, xs.get(0) + 20);
					break;
				case 2:
					ys.set(0, ys.get(0) + 20);
					break;
				case 3:
					xs.set(0, xs.get(0) - 20);
					break;
			}
				
			if (isDead())
			{
				isAlive = false;
				break;
			}
		}
	}
	
	public boolean isDead()
	{
		if (xs.get(0) < 0 || xs.get(0) >= 600)
		{
			return true;
		}
		if (ys.get(0) < 0 || ys.get(0) >= 600)
		{
			return true;
		}
		
		int headX = xs.get(0);
		int headY = ys.get(0);
		for (int i = 1; i < length; i++)
		{
			if (headX == xs.get(i) && headY == ys.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
}
