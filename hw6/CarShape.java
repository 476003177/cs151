import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A car that can be moved around.
 */
public class CarShape implements MoveableShape {

    private Rectangle bounds;

    /**
     * Constructs a car item.
     *
     * @param x     the left of the bounding rectangle
     * @param y     the top of the bounding rectangle
     * @param width the width of the bounding rectangle
     */
    public CarShape(int x, int y, int width) {
        this.bounds = new Rectangle(x, y, width, width/2);
    }

    public void move() {
        bounds.setRect(bounds.getX() + 1, bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    public void draw(Graphics2D g2) {
        Rectangle2D.Double body
                = new Rectangle2D.Double(bounds.getX(), bounds.getY() + getBounds().getWidth() / 6,
                getBounds().getWidth() - 1, getBounds().getWidth() / 6);
        Ellipse2D.Double frontTire
                = new Ellipse2D.Double(bounds.getX() + getBounds().getWidth() / 6, bounds.getY() + getBounds().getWidth() / 3,
                getBounds().getWidth() / 6, getBounds().getWidth() / 6);
        Ellipse2D.Double rearTire
                = new Ellipse2D.Double(bounds.getX() + getBounds().getWidth() * 2 / 3, bounds.getY() + getBounds().getWidth() / 3,
                getBounds().getWidth() / 6, getBounds().getWidth() / 6);

        // The bottom of the front windshield
        Point2D.Double r1
                = new Point2D.Double(bounds.getX() + getBounds().getWidth() / 6, bounds.getY() + getBounds().getWidth() / 6);
        // The front of the roof
        Point2D.Double r2
                = new Point2D.Double(bounds.getX() + getBounds().getWidth() / 3, bounds.getY());
        // The rear of the roof
        Point2D.Double r3
                = new Point2D.Double(bounds.getX() + getBounds().getWidth() * 2 / 3, bounds.getY());
        // The bottom of the rear windshield
        Point2D.Double r4
                = new Point2D.Double(bounds.getX() + getBounds().getWidth() * 5 / 6, bounds.getY() + getBounds().getWidth() / 6);
        Line2D.Double frontWindshield
                = new Line2D.Double(r1, r2);
        Line2D.Double roofTop
                = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield
                = new Line2D.Double(r3, r4);

        g2.draw(body);
        g2.draw(frontTire);
        g2.draw(rearTire);
        g2.draw(frontWindshield);
        g2.draw(roofTop);
        g2.draw(rearWindshield);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
