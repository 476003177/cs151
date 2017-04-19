import java.awt.geom.*;

// Adapted from http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect

public class Lines
{
    public static Point2D getIntersection(Line2D l1, Line2D l2)
    {
        double l1StartX = l1.getP1().getX();
        double l1startY = l1.getP1().getY();
        double l1EndX = l1.getP2().getX();
        double l1EndY = l1.getP2().getY();
        double l2StartX = l2.getP1().getX();
        double l2StartY = l2.getP1().getY();
        double l2EndX = l2.getP2().getX();
        double l2EndY = l2.getP2().getY();

        double l1dX = l1EndX - l1StartX;
        double l1dY = l1EndY - l1startY;
        double l2dX = l2EndX - l2StartX;
        double l2dY = l2EndY - l2StartY;

        double denom = l1dX * l2dY - l2dX * l1dY;
        if (denom == 0) return null; // Collinear
        boolean denomPositive = denom > 0;

        double startdX = l1StartX - l2StartX;
        double startdY = l1startY - l2StartY;
        double sNumer = l1dX * startdY - l1dY * startdX;
        double tNumer = l2dX * startdY - l2dY * startdX;
        double EPSILON = 1E-10;
        double s = sNumer / denom;
        double t = tNumer / denom;
        if (-EPSILON <= t && t <= 1 + EPSILON && -EPSILON <= s && s <= 1 + EPSILON)
            return new Point2D.Double(l1StartX + (t * l1dX), l1startY + (t * l1dY));
        else
            return null;
    }

    public static String toString(Line2D line)
    {
        return line.getClass().getName() + "[x1=" + line.getX1() + ",y1=" +
                line.getY1() + ",x2=" + line.getX2() + ",y2=" +
                line.getY2() + "]";
    }
}