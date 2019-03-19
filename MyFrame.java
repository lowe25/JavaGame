import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener
{
	Draw drawing;
     
	public MyFrame()
	{
		this.drawing = new Draw();
	}
	public void keyPressed(KeyEvent e)
	{

		/*if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			drawing.moveUp();
			System.out.println("Jump: " + drawing.x + ", " + drawing.y);
		}*/
	     if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			drawing.moveRight();
			System.out.println("Move Forward: " + drawing.x + ", " + drawing.y);
		}
		/*else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			drawing.moveDown();
			System.out.println("Slide: " + drawing.x + ", " + drawing.y);
		}*/
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			drawing.moveLeft();
			System.out.println("Move Back: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			drawing.attack();
			System.out.println("Attack");
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			drawing.spawnEnemy();
		}
}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{
		
	}
	public static void main(String args[])
	{
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(900,500);
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setTitle("Zombie");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().add(gameFrame.drawing);
		gameFrame.addKeyListener(gameFrame);
		System.out.println("Java 2D Start");
	}
}