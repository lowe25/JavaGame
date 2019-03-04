import java.awt.*;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
class Homepage
{
	//public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);

public void render(Graphics g)
{
	Font fnt0 = new Font ("arial", Font.BOLD, 50);
	g.setFont(fnt0);
	g.setColor(Color.white);
	//g.drawString("Zombie Wars", Game.WIDTH / 2, 100);
}	
	public static void main(String[]args)
	{
		JFrame frame = new JFrame();
		//Frame components
		frame.setVisible(true);
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Zombie Wars");
		frame.setResizable(false);
	}

	}
