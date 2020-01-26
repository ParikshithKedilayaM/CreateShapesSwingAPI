

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Parikshith Kedilaya Mallar
 *
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String title = "Team 1";
	private static List<Shape> shapes = new ArrayList<>();
	private static Map<Shape, ShapesEnum> shapesMap = new HashMap<>();
	private int x, y;
	boolean isRepaint = false;
	ShapeFactory shapeFactory;
	
	private Frame() {
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setTitle(title);
		setVisible(true);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Create left panel to the frame
	 * paintComponent is used to create shapes
	 */
	private void createLeftPanel() {
		try {
			JPanel leftPanel = new JPanel() {
				private static final long serialVersionUID = 1L;
				
				/**
				 *  Creates shapes using Graphics 
				 */
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					shapeFactory = new ShapeFactory();
					shapeFactory.setGraphics(g);
					shapeFactory.createShape(ShapesEnum.TRIANGLE, 100, 100);
					shapeFactory.createShape(ShapesEnum.SQUARE, 100, 200);
					shapeFactory.createShape(ShapesEnum.CIRCLE, 100, 400);
					
				}
				
			};
			leftPanel.setBounds(0, 0, this.getWidth() / 4, this.getHeight());
			leftPanel.setVisible(true);
			this.add(leftPanel);
			leftPanel.addMouseListener(new LeftPanelMouseListener());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Create right panel to the frame
	 * paintComponent creates new shapes upon click
	 */
	private void createRightpanel() {
		try {
			JPanel rightPanel = new JPanel() {
				private static final long serialVersionUID = 1L;
				
				/**
				 * Uses Graphics to create shapes
				 */
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					shapeFactory.setGraphics(g);
					if (isRepaint) {
						for (Shape shape: shapes) {
							if (shape.getBounds().getX() != 100) {
								Graphics2D g2 = (Graphics2D) g;
								g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
								g2.draw(shape);
							}
						}
						shapeFactory.createShape(shapeFactory.getSelectedShape(), x, y);
					} 
				}
			};
			rightPanel.setBackground(Color.WHITE);
			rightPanel.setBounds(this.getWidth() / 4, 0, 3 * this.getWidth() / 4, this.getHeight());
			rightPanel.setVisible(true);
			this.add(rightPanel);
			
			rightPanel.addMouseListener(new MouseAdapter() {
				/**
				 * Overridden method to add mouse click event handler.
				 * Will call paintComponent(Graphics) method using repaint().
				 * 
				 */
				@Override
				public void mouseClicked(MouseEvent e) {
					if (shapeFactory.getSelectedShape() != null) {
						x = e.getX();
						y = e.getY();
						isRepaint = true;
						repaint();
					}
				}
				
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method to update shapes and shapesMap lists.
	 * Used to track the shapes created and kind of shape created.
	 * @param shape
	 * @param shapesEnum
	 */
	public static void addShape(Shape shape, ShapesEnum shapesEnum) {
		if (!shapes.contains(shape)) {
			shapes.add(shape);
			shapesMap.put(shape, shapesEnum);
		}
	}
	/**
	 * @author Parikshith Kedilaya Mallar
	 *
	 */
	private class LeftPanelMouseListener extends MouseAdapter {
        /**
         * Overridden method to add mouse click event handler.
		 * Used to track which shape has been clicked on the left panel,
		 * so that only that shape can be created on the right panel.
		 * 
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
				for (int i = shapes.size() - 1; i >= 0; i--) {
				    if (shapes.get(i).contains(e.getPoint())) {
				        shapeFactory.markIsClickedTrue(shapesMap.get(shapes.get(i)));
				    	return;
				    }
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
	

	/**
	 * @param args
	 * This method schedules a job for the event-dispatching thread. This method is responsible
	 * for creating and showing application's GUI.
	 */
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Frame frame = new Frame();
				frame.createLeftPanel();
				frame.createRightpanel();
            }
        });
	}
}
