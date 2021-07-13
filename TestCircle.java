import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestCircle extends JFrame{
    
    Circle circle =  new Circle(100, 100, 10.0);
	//Circle circle = new Circle(100,100,10.0);
    
	public static void main(String[] args) {
        new TestCircle().lauch();
	}
	public void lauch(){
		setLocation(200, 200);
		setSize(200,200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		addKeyListener(new KeyMonitor());
	}
	class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_UP:
                    circle.move(100, 101);
					System.out.println("位置为"+circle.getCenter());
					break;
				case KeyEvent.VK_DOWN:
                    circle.move(100, 99);
				    System.out.println("位置为"+circle.getCenter());
					break;
				 case KeyEvent.VK_LEFT:
                    circle.move(99, 100);
                    System.out.println("位置为"+circle.getCenter());
					 break;
				case KeyEvent.VK_RIGHT:
                    circle.move(101, 100);
				    System.out.println("位置为"+circle.getCenter());
					break;
				case KeyEvent.VK_PAGE_UP://radius加1
					circle.setRadius(circle.getRadius()+1);
					System.out.println("radius= " + circle.getRadius());
					break;
				case KeyEvent.VK_PAGE_DOWN://radius减1
					if(circle.getRadius()>1)
						circle.setRadius(circle.getRadius()-1);
					System.out.println("radius= " + circle.getRadius());
					break;
			}
		}
	} 
}
