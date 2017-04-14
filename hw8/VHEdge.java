import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by Tyler on 4/14/2017.
 */
public class VHEdge extends LineEdge {

    @Override
    public void draw(Graphics2D g2) {
        Line2D verticalLine = new Line2D.Double(getConnectionPoints().getX1(), getConnectionPoints().getY1(), getConnectionPoints().getX1(), getConnectionPoints().getY2());
        Line2D horizontalLine = new Line2D.Double(getConnectionPoints().getX1(), getConnectionPoints().getY2(), getConnectionPoints().getX2(), getConnectionPoints().getY2());

        // Save the stroke of the graphics context so we can re-establish later
        Stroke savedStroke = g2.getStroke();

        g2.setStroke(getLineStyle().getStroke());
        g2.draw(verticalLine);
        g2.draw(horizontalLine);

        g2.setStroke(savedStroke);
    }

    @Override
    public boolean contains(Point2D aPoint) {
        Line2D verticalLine = new Line2D.Double(getConnectionPoints().getX1(), getConnectionPoints().getY1(), getConnectionPoints().getX1(), getConnectionPoints().getY2());
        Line2D horizontalLine = new Line2D.Double(getConnectionPoints().getX1(), getConnectionPoints().getY2(), getConnectionPoints().getX2(), getConnectionPoints().getY2());

        final double MAX_DIST = 2;
        return verticalLine.ptSegDist(aPoint) < MAX_DIST
            || horizontalLine.ptSegDist(aPoint) < MAX_DIST;
    }

}
