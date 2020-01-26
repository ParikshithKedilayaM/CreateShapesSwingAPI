

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

/**
 * @author Parikshith Kedilaya Mallar
 *
 */
public class Triangle implements Shapes {
	private boolean isClicked;
	private static Triangle instance = null;
	
	/**
	 * @return
	 * 
	 * Singleton Class
	 */
	public static Triangle getInstance() {
		if (instance == null) {
			instance = new Triangle();
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
	 * Creates a triangle using Path2D class.
	 * Since no default class is available to draw triangle, path is used.
	 * 
	 */
	@Override
	public void createShape(Graphics g, double x, double y) {
		try {
			//g.drawPolygon(new int[] { 100, 150, 200 }, new int[] { 100, 50, 100 }, 3);
			Path2D path = new Path2D.Double();
			path.moveTo(x, y);
			path.lineTo(x + 50, y - 50);
			path.lineTo(x + 100, y);
			path.closePath();
			
			Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.draw(path);
	        Frame.addShape(path, ShapesEnum.TRIANGLE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
