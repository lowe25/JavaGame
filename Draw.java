import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
 
public class Draw extends JComponent
{
	private BufferedImage playerimage;
	private BufferedImage runnerimage1;
	private BufferedImage backgroundImage;
	public URL resource = getClass().getResource("IdleImages/idle01.png");
	public URL resource1 = getClass().getResource("runningImages/run1.png");

	// Char position
	//X = Horizontal
	//Y = Vertical
	public boolean walk = true;
	public boolean run = true;
	public int x = 5;
	public int y = 420;
	public int height;
	public int width;
    public int state = 0;

	// randomizer
	public Random randomizer;

//ENEMENY COMPONENTS ^^^^^
	public int enemyCount;
	Monster[] monsters = new Monster[10];

	public Draw()
	{
		randomizer = new Random();
		spawnEnemy();
		try{
			playerimage = ImageIO.read(resource);
			runnerimage1 = ImageIO.read(resource1);
			backgroundImage = ImageIO.read(getClass().getResource("bulkhead-wallsx3.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

        height = runnerimage1.getHeight();
        width = runnerimage1.getWidth();
		height = playerimage.getHeight();
		width =  playerimage.getWidth();

		startGame();
	}
	public void startGame()
{
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						for(int c = 0; c < monsters.length; c++){
							if(monsters[c]!=null){
								monsters[c].moveTo(x,y);
								repaint();
							}
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}
	public void spawnEnemy()
	{
		if(enemyCount < 10)
		{
			monsters[enemyCount] = new Monster(randomizer.nextInt(500), randomizer.nextInt(500), this);
			enemyCount++;
		}
	}
	//ATTACK
	public void attackAnimation()
	{
		Thread thread1 = new Thread(new Runnable()
		{
			public void run()
			{
				for(int ctr = 0; ctr < 6; ctr++)
				{
					try {
						if(ctr==5)
						{
							resource1 = getClass().getResource("IdleImages/idle01.png");
						}
						else
						{
							resource = getClass().getResource("attackImages/attack"+ctr+".png");
						}			
						try
						{
							playerimage = ImageIO.read(resource);
							runnerimage1 = ImageIO.read(resource1);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}	
				        repaint();
				        Thread.sleep(100);
					}
					 catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				for(int x=0; x<monsters.length; x++)
				{
					if(monsters[x]!=null)
					{
						if(monsters[x].contact)
						{
							monsters[x].life = monsters[x].life - 10;
						}
					}
				}
			}
		});
		thread1.start();
	}
	//WALKING
	public void walkAnimation()
	{
		Thread thread2 = new Thread(new Runnable()
		{
			public void run()
            {
              for(int ctr = 0; ctr < 8; ctr++)
              {
              	try
              	{
              		if(ctr==7)
              		{
              			resource1 = getClass().getResource("runningImages/run1.png");
              			y = 420;
              		}
              		else
              		{
              			resource1 = getClass().getResource("IdleImages/idle01.png");
              			y = 420;
              		}
              		try
              		{
              			playerimage = ImageIO.read(resource);
              			runnerimage1 = ImageIO.read(resource1);
              		}
              		catch(IOException e)
              		{
              			e.printStackTrace();
              		}
              		repaint();
              		Thread.sleep(100);
              	}
              	catch (InterruptedException e)
              	{
              		e.printStackTrace();
              	}
             }
          }
		});
		thread2.start();
	}
//=========================================================================================	
	public void walker()
	{
		walkAnimation();
	}
	public void attack()
	{
		attackAnimation();
	}
//CHAR SPEED
	public void moveUp(){
		y = y - 5;
		walkAnimation();
		repaint();
		checkCollision();
	}
	public void moveDown(){
		y = y + 5;
		walkAnimation();
		repaint();
		checkCollision();
	}
	public void moveLeft(){
		x = x - 5;
		walkAnimation();
		repaint();
		checkCollision();
	}
	public void moveRight(){
		x = x + 5;
		walkAnimation();
		repaint();
		checkCollision();
	}
	//ENEMY SHITS
public void checkCollision()
{
		int xChecker = x + width;
		int yChecker = y;

		for(int x=0; x<monsters.length; x++)
		{
			boolean collideX = false;
			boolean collideY = false;

			if(monsters[x]!=null)
			{
				monsters[x].contact = false;

				if(yChecker > monsters[x].yPos)
				{
					if(yChecker-monsters[x].yPos < monsters[x].height){
						collideY = true;
						System.out.println("collideY");
					}
				}
				else{
					if(monsters[x].yPos - yChecker < monsters[x].height){
						collideY = true;
						System.out.println("collideY");
					}
				}

				if(xChecker > monsters[x].xPos){
					if(xChecker-monsters[x].xPos < monsters[x].width){
						collideX = true;
						System.out.println("collideX");
					}
				}
				else{
					if(monsters[x].xPos - xChecker < 5){
						collideX = true;
						System.out.println("collideX");
					}
				}
			}

			if(collideX && collideY){
				System.out.println("collision!");
				monsters[x].contact = true;
			}
		}
	}
	//ENEMY SHITS LAST
        public void paintComponent(Graphics g)
        {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(playerimage, x, y, this);
		//ENEMY
		for(int c = 0; c < monsters.length; c++)
		{
			if(monsters[c]!=null){
				// character grid for monsters
				// g.setColor(Color.BLUE);
				// g.fillRect(monsters[c].xPos, monsters[c].yPos+5, monsters[c].width, monsters[c].height);
				g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 2);
			}	
		}
	}
	public void checkDeath(){
		for(int c = 0; c < monsters.length; c++){
			if(monsters[c]!=null){
				if(!monsters[c].alive){
					monsters[c] = null;
				}
			}			
		}
	}

}