import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JComponent;

public class Monster
{
	public int xPos = 880;
	public int yPos = 420;
	public int width;
	public int height;
	public int life = 20;
	public boolean idle = true;
	public boolean die = true;
	public boolean alive = true;
	public boolean contact = false;

	public BufferedImage image;
	public URL resource = getClass().getResource("Zombiewalk/walk0.png");

	public Monster(Draw comp){
		try{
			image = ImageIO.read(resource);		
		}
		catch(IOException e){
			e.printStackTrace();
		}
		animate(comp);
	}
	public Monster(int xPass, int yPass, Draw comp){
		xPos = xPass;
		yPos = yPass;

		try{
			image = ImageIO.read(resource);	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		height = image.getHeight();
		width = image.getWidth();

		animate(comp);
	}
	public void animate(Draw compPass){
		Thread monThread = new Thread(new Runnable(){
			public void run(){
				while(idle){
					for(int ctr = 0; ctr < 4; ctr++){
						try {
							if(ctr==4){
								resource = getClass().getResource("Zombiewalk/walk0.png");
							}
							else{
								resource = getClass().getResource("Zombiewalk/walk"+ctr+".png");
							}
							
							try{
								image = ImageIO.read(resource);
							
							}
							catch(IOException e){
								e.printStackTrace();
							}
					        compPass.repaint();
					        Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(life<=0){
						die(compPass);
					}
				}
			}
		});
		monThread.start();
	}
	public void moveTo(int toX, int toY){
		if(xPos<toX){
			xPos++;
		}
		else if(xPos>toX){
			xPos--;
		}

		if(yPos<toY){
			yPos++;
		}
		else if(yPos>toY){
			yPos--;
		}
	}
	public void die(Draw compPass)
	{
		die = false;
		if(alive){
			Thread monThread = new Thread(new Runnable()
			{
				public void run()
				{
					for(int ctr = 0; ctr < 6; ctr++)
					{
						try 
						{					
							resource = getClass().getResource("die0"+ctr+".png");	
							try
							{
								image = ImageIO.read(resource);
								
							}
							catch(IOException e)
							{
								e.printStackTrace();
							}
					        compPass.repaint();
					        Thread.sleep(100);
						}
						 catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}
			});
			monThread.start();
		}
		alive = false;
		compPass.checkDeath();
	}
}