

import java.awt.Graphics;

/**
 * @author Parikshith Kedilaya Mallar
 *
 */
public class ShapeFactory {
	Graphics g;
	
	/**
	 * @param shape
	 * @param x
	 * @param y
	 * 
	 * Instantiates or uses the created instance of different shapes' class 
	 * and creates the shape at the given co-ordinates x and y.
	 */
	public void createShape(ShapesEnum shape, double x, double y) {
		try {
			if (ShapesEnum.SQUARE == shape) {
				Square.getInstance().createShape(g, x, y);
			}
			if (ShapesEnum.TRIANGLE == shape) {
				Triangle.getInstance().createShape(g, x, y);
			}
			if (ShapesEnum.CIRCLE == shape) {
				Circle.getInstance().createShape(g, x, y);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param shape
	 * 
	 * Instantiates or uses the created instance of different shapes' class
	 * and sets isClicked value as true for the shape which was clicked.
	 */
	public void markIsClickedTrue(ShapesEnum shape) {
		try {
			if (ShapesEnum.SQUARE == shape) {
				Square.getInstance().setClicked(true);
				Triangle.getInstance().setClicked(false);
				Circle.getInstance().setClicked(false);
			}
			if (ShapesEnum.TRIANGLE == shape) {
				Triangle.getInstance().setClicked(true);
				Square.getInstance().setClicked(false);
				Circle.getInstance().setClicked(false);
			}
			if (ShapesEnum.CIRCLE == shape) {
				Circle.getInstance().setClicked(true);
				Triangle.getInstance().setClicked(false);
				Square.getInstance().setClicked(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * returns which shape was clicked.
	 */
	public ShapesEnum getSelectedShape() {
		if (Square.getInstance().isClicked()) {
			return ShapesEnum.SQUARE;
		} else if (Triangle.getInstance().isClicked()) {
			return ShapesEnum.TRIANGLE;
		} else if (Circle.getInstance().isClicked()) {
			return ShapesEnum.CIRCLE;
		} else {
			return null;
		}
		
	}
	public void setGraphics(Graphics graphics) {
		g = graphics;
	}
}
