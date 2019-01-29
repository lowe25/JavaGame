import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame2 extends JFrame implements KeyListener{

	Draw drawing;

	public MyFrame2(){
		this.drawing = new Draw();
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			drawing.moveUp();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			drawing.moveRight();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			drawing.moveDown();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			drawing.moveLeft();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_V){
			drawing.attack();
			System.out.println("transform attack");
		}
	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){
		
	}

	public static void main(String args[]){
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(600,300);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.getContentPane().add(gameFrame.drawing);
		gameFrame.addKeyListener(gameFrame);
		System.out.println("practical programming");
	}
}