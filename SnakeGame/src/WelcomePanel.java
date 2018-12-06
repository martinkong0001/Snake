import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

class WelcomePanel extends JPanel implements Runnable
{
	
	boolean isAlive = true;
	
	public WelcomePanel()
	{
		this.setPreferredSize(new Dimension(600, 600));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if (isAlive == true)
		{
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
			g.setColor(Color.BLACK);
			g.drawString("Welcome!", 175, 280);
		}
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			isAlive = false;
			this.repaint();
		
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			isAlive = true;
			this.repaint();
		}
	}
	
}
