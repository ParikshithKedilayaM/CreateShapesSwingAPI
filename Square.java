

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * @author Parikshith Kedilaya Mallar
 *
 */
public class Square implements Shapes {
	private boolean isClicked;
	private static Square instance = null;
	
	/**
	 * @return
	 * Singleton Class
	 */
	public static Square getInstance() {
		if (instance == null) {
			instance = new Square();
		}
		return instance;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	/**
	 *	Creates a shape using Rectangle2D class
	 */
	@Override
	public void createShape(Graphics g, double x, double y) {
		try {
			//g.drawRect(100, 200, 100, 100);
			Shape shape = new Rectangle2D.Double(x, y, 100, 100);
			Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.draw(shape);
	        Frame.addShape(shape, ShapesEnum.SQUARE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
