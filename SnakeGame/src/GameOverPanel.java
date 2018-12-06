import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

class GameOverPanel extends JPanel
{
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 165, 280);
	}

}
