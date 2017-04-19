import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Rooke_000 on 4/19/2017.
 */
public class SquareNode implements Node {
    private static final int DEFAULT_SIZE = 20;

    private double x;
    private double y;
    private double size;

    /**
     * Initializes x, y, and size of the diamond node.
     * Size is defined as the distance from the center of the diamond to any of its corners.
     */
    public SquareNode() {
        x = 0;
        y = 0;

        size = DEFAULT_SIZE;
    }

    @Override
    public void draw(Graphics2D g2) {
        // Make corners for edges
        Point2D.Double topLeftCorner = new Point2D.Double(x, y);
        Point2D.Double topRightCorner = new Point2D.Double(x + size, y);
        Point2D.Double bottomRightCorner = new Point2D.Double(x + size, y + size);
        Point2D.Double bottomLeftCorner = new Point2D.Double(x, y + size);

        // Make edges from corners
        Line2D.Double leftEdge = new Line2D.Double(topLeftCorner, bottomLeftCorner);
        Line2D.Double topEdge = new Line2D.Double(topLeftCorner, topRightCorner);
        Line2D.Double rightEdge = new Line2D.Double(topRightCorner, bottomRightCorner);
        Line2D.Double bottomEdge = new Line2D.Double(bottomLeftCorner, bottomRightCorner);

        // Draw lines
        g2.draw(leftEdge);
        g2.draw(topEdge);
        g2.draw(rightEdge);
        g2.draw(bottomEdge);
    }

    @Override
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean contains(Point2D aPoint) {
        return getBounds().contains(aPoint);
    }

    @Override
    public Point2D getConnectionPoint(Point2D other) {
        Line2D connectingLine = new Line2D.Double(new Point2D.Double(x + size/2, y + size/2), other);
        // Make corners for edges
        Point2D.Double topLeftCorner = new Point2D.Double(x, y);
        Point2D.Double topRightCorner = new Point2D.Double(x + size, y);
        Point2D.Double bottomRightCorner = new Point2D.Double(x + size, y + size);
        Point2D.Double bottomLeftCorner = new Point2D.Double(x, y + size);

        // Make edges from corners
        Line2D.Double leftEdge = new Line2D.Double(topLeftCorner, bottomLeftCorner);
        Line2D.Double topEdge = new Line2D.Double(topLeftCorner, topRightCorner);
        Line2D.Double rightEdge = new Line2D.Double(topRightCorner, bottomRightCorner);
        Line2D.Double bottomEdge = new Line2D.Double(bottomLeftCorner, bottomRightCorner);
        Point2D[] connectionPoints = {
            Lines.getIntersection(connectingLine, leftEdge),
            Lines.getIntersection(connectingLine, topEdge),
            Lines.getIntersection(connectingLine, rightEdge),
            Lines.getIntersection(connectingLine, bottomEdge)
      };

        double closestDistance = Double.MAX_VALUE;
        Point2D closestPoint = new Point2D.Double(x, y);
        for (Point2D p : connectionPoints)
        {
            if (p != null && p.distance(other) < closestDistance)
            {
                closestPoint = p;
                closestDistance = p.distance(other);
            }
        }
        return closestPoint;
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
