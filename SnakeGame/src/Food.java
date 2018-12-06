
public class Food
{
	
	int x;
	int y;
	
	public Food(Snake snake)
	{
		while (true)
		{
			x = (int)(Math.random() * 600 + 1);
			x = x - x % 20;
			
			y = (int)(Math.random() * 600 + 1);
			y = y - y % 20;
			
			for (int i = 0; i < snake.length; i++)
			{
				if (x == snake.xs.get(i) && y == snake.ys.get(i))
				{
					continue;
				}
			}
			break;
		}
	}
	
}
