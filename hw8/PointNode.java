import java.awt.*;
import java.awt.geom.*;

/**
   An inivisible node that is used in the toolbar to draw an
   edge.
*/
public class PointNode implements Node
{
   /**
      Constructs a point node with coordinates (0, 0)
   */
   public PointNode()
   {
      point = new Point2D.Double();
   }

   @Override
   public void draw(Graphics2D g2)
   {
   }

   @Override
   public void translate(double dx, double dy)
   {
      point.setLocation(point.getX() + dx,
         point.getY() + dy);
   }

   @Override
   public boolean contains(Point2D p)
   {
      return false;
   }

   @Override
   public Rectangle2D getBounds()
   {
      return new Rectangle2D.Double(point.getX(), 
         point.getY(), 0, 0);
   }

   @Override
   public Point2D getConnectionPoint(Point2D other)
   {
      return point;
   }

   @Override
   public Object clone()
   {
      try
      {
         return super.clone();
      }
      catch (CloneNotSupportedException exception)
      {
         return null;
      }
   }

   private Point2D point;
}
