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
        Point2D.Double leftCorner = new Point2D.Double(x, y + size/2);
        Point2D.Double topCorner = new Point2D.Double(x + size/2, y);
        Point2D.Double rightCorner = new Point2D.Double(x + size, y + size/2);
        Point2D.Double bottomCorner = new Point2D.Double(x + size/2, y + size);

        // Make lines from points
        Line2D.Double topLeftEdge = new Line2D.Double(leftCorner, topCorner);
        Line2D.Double topRightEdge = new Line2D.Double(topCorner, rightCorner);
        Line2D.Double bottomRightEdge = new Line2D.Double(rightCorner, bottomCorner);
        Line2D.Double bottomLeftEdge = new Line2D.Double(bottomCorner, leftCorner);

        // Draw lines
        g2.draw(topLeftEdge);
        g2.draw(topRightEdge);
        g2.draw(bottomRightEdge);
        g2.draw(bottomLeftEdge);
    }

    @Override
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean contains(Point2D aPoint) {
        boolean aboveTopLeftEdge = aPoint.getY() > aPoint.getX() + size/2;
        boolean aboveTopRightEdge = aPoint.getY() > -aPoint.getX() + size + size/2;
        boolean belowBottomRightEdge = aPoint.getY() < aPoint.getX() - 10;
        boolean belowBottomLeftEdge = aPoint.getY() < -aPoint.getX() + size/2;

        return !aboveTopLeftEdge && !aboveTopRightEdge && !belowBottomRightEdge && ! belowBottomLeftEdge;
    }

    @Override
    public Point2D getConnectionPoint(Point2D aPoint) {

        return null;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, size, size);
    }

    // Copied from Professor Horstmann's CircleNode
    @Override
    public Object clone() {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException exception)
        {
            return null;
        }
    }

}
