import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Tyler on 4/8/2017.
 */
public class DiamondNode implements Node {

    private static final int DEFAULT_SIZE = 20;

    private double x;
    private double y;
    private double size;

    public DiamondNode() {
        x = 0;
        y = 0;

        size = DEFAULT_SIZE;
    }

    @Override
    public void draw(Graphics2D g2) {
        // Make points for edges
        Point2D.Double leftEdge = new Point2D.Double(x - size, y);
        Point2D.Double topEdge = new Point2D.Double(x, y - size);
        Point2D.Double rightEdge = new Point2D.Double(x + size, y);
        Point2D.Double bottomEdge = new Point2D.Double(x, y + size);

        // Make lines from points
        Line2D.Double topLeftLine = new Line2D.Double(leftEdge, topEdge);
        Line2D.Double topRightLine = new Line2D.Double(topEdge, rightEdge);
        Line2D.Double bottomRightLine = new Line2D.Double(rightEdge, bottomEdge);
        Line2D.Double bottomLeftLine = new Line2D.Double(bottomEdge, leftEdge);

        // Draw lines
        g2.draw(topLeftLine);
        g2.draw(topRightLine);
        g2.draw(bottomRightLine);
        g2.draw(bottomLeftLine);
    }

    @Override
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean contains(Point2D aPoint) {
        return false;
    }

    @Override
    public Point2D getConnectionPoint(Point2D aPoint) {
        return null;
    }

    @Override
    public Rectangle2D getBounds() {
        return null;
    }

    @Override
    public Object clone() {
        return null;
    }

}
