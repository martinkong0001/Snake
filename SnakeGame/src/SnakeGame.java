import java.awt.event.*;
import javax.swing.*;

public class SnakeGame extends JFrame implements ActionListener, Runnable
{
	
	JMenuBar menuBar;
	JMenu menu1, menu2;
	JMenuItem menuItem1, menuItem2;
	
	WelcomePanel welcomePanel;
	GamePanel gamePanel;
	GameOverPanel gameOverPanel;
	
	public SnakeGame()
	{
		menuBar = new JMenuBar();
		menu1 = new JMenu("Game");
		menu2 = new JMenu("Exit");
		
		menuItem1 = new JMenuItem("Start New Game");
		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("StartNewGame");
		
		menuItem2 = new JMenuItem("Exit Game");
		menuItem2.addActionListener(this);
		menuItem2.setActionCommand("ExitGame");
		
		this.setJMenuBar(menuBar);
		menuBar.add(menu1);
		menu1.add(menuItem1);
		menuBar.add(menu2);
		menu2.add(menuItem2);
		
		welcomePanel = new WelcomePanel();
		Thread stageThread = new Thread(welcomePanel);
		stageThread.start();
		this.add(welcomePanel);
		
		this.setTitle("Snake");
		this.setLocation(500, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		Thread mainThread = new Thread(this);
		mainThread.start();
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getActionCommand().equals("StartNewGame")) 
		{
			if (welcomePanel != null)
			{
				this.remove(welcomePanel);
				welcomePanel = null;
			}
			else if (gamePanel != null)
			{
				this.remove(gamePanel);
				gamePanel = null;
			}
			else if (gameOverPanel != null)
			{
				this.remove(gameOverPanel);
				gameOverPanel = null;
			}
			gamePanel = new GamePanel();
			Thread thread = new Thread(gamePanel);
			thread.start();
			this.add(gamePanel);
			this.addKeyListener(gamePanel);
			this.setVisible(true);
		}
		
		else if (arg0.getActionCommand().equals("ExitGame"))
		{
			System.exit(0);
		}
		
		else if (arg0.getActionCommand().equals("GameOver"))
		{
			this.remove(gamePanel);
			gamePanel = null;
			gameOverPanel = new GameOverPanel();
			this.add(gameOverPanel);
			this.setVisible(true);
		}
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
			
			if (gamePanel != null && !gamePanel.snake.isAlive)
			{
				ActionEvent gameOver = new ActionEvent
						(gamePanel.snake, ActionEvent.ACTION_PERFORMED, "GameOver");
				this.actionPerformed(gameOver);
			}
		}
	}

	public static void main(String[] args) 
	{
		new SnakeGame();
	}
	
}
