import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class HVEdge extends LineEdge
{
   @Override
   public boolean contains(Point2D aPoint)
   {
      final double MAX_DIST = 2;
      Line2D connectionPoints = getConnectionPoints();
      Point2D p1 = connectionPoints.getP1();
      Point2D p2 = connectionPoints.getP2();
      Point2D p = new Point2D.Double(p2.getX(), p1.getY());
      return new Line2D.Double(p1, p).ptSegDist(aPoint) 
         < MAX_DIST || new Line2D.Double(p2, p).ptSegDist(aPoint) < MAX_DIST;
   }
   
   @Override
   public void draw(Graphics2D g2)
   {
      Stroke oldStroke = g2.getStroke();
      g2.setStroke(getLineStyle().getStroke());      
      Line2D connectionPoints = getConnectionPoints();
      Point2D p1 = connectionPoints.getP1();
      Point2D p2 = connectionPoints.getP2();
      Point2D p = new Point2D.Double(p2.getX(), p1.getY());
      g2.draw(new Line2D.Double(p1, p));
      g2.draw(new Line2D.Double(p2, p));
      g2.setStroke(oldStroke);
   }   
}
