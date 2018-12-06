import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class GamePanel extends JPanel implements KeyListener, Runnable
{
	
	Snake snake;
	Food food;
	Image snakeImage;
	Image foodImage;
	
	public GamePanel()
	{
		snake = new Snake();
		food = new Food(snake);
		Thread thread = new Thread(snake);
		thread.start();
		try
		{
			snakeImage = ImageIO.read(new File("images/Snake.jpeg"));
			foodImage = ImageIO.read(new File("images/Food.jpeg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < snake.length; i++)
		{
			g.drawImage(snakeImage, snake.xs.get(i), snake.ys.get(i), 20, 20, this);
		}
		g.drawImage(foodImage, food.x, food.y, 20, 20, this);
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_UP && snake.direction != 2)
		{
			snake.direction = 0;
		}
		else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && snake.direction != 3)
		{
			snake.direction = 1;
		}
		else if (arg0.getKeyCode() == KeyEvent.VK_DOWN && snake.direction != 0)
		{
			snake.direction = 2;
		}
		else if (arg0.getKeyCode() == KeyEvent.VK_LEFT && snake.direction != 1)
		{
			snake.direction = 3;
		}
	}

	public void keyReleased(KeyEvent arg0)
	{
	}

	public void keyTyped(KeyEvent arg0)
	{
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(80);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			this.eatFood();
			this.repaint();
		}
	}
	
	public void eatFood()
	{
		if (snake.xs.get(0) == food.x && snake.ys.get(0) == food.y)
		{
			snake.eatingMode += 3;
			food = new Food(snake);
		}
	}
	
}
