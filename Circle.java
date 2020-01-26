

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * @author Parikshith Kedilaya Mallar
 *
 */
public class Circle implements Shapes {
	private boolean isClicked;
	private static Circle instance = null;
	
	/**
	 * @return
	 * Singleton Class
	 */
	public static Circle getInstance() {
		if (instance == null) {
			instance = new Circle();
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
	 *	Creates a Circle using Ellipse2D class
	 */
	@Override
	public void createShape(Graphics g, double x, double y) {
		try {
			//g.drawOval(100, 400, 100, 100);
			Shape shape = new Ellipse2D.Double(x, y, 100, 100);
			Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.draw(shape);
	        Frame.addShape(shape, ShapesEnum.CIRCLE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
