import java.awt.*;
import java.awt.geom.*;

/**
   A diamond-shaped node.
*/
public class DiamondNode implements Node
{
   /**
      Construct a circle node with a given size and color.
      @param aColor the fill color
   */
   public DiamondNode()
   {
      size = DEFAULT_SIZE;
      x = 0;
      y = 0;
   }

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

   public void draw(Graphics2D g2)
   {
      g2.draw(new Line2D.Double(x + size / 2, y, x + size, y + size / 2));
      g2.draw(new Line2D.Double(x + size / 2, y, x, y + size / 2));
      g2.draw(new Line2D.Double(x + size / 2, y + size, x + size, y + size / 2));
      g2.draw(new Line2D.Double(x + size / 2, y + size, x, y + size / 2));
   }

   public void translate(double dx, double dy)
   {
      x += dx;
      y += dy;
   }

   public Rectangle2D getBounds()
   {
      return new Rectangle2D.Double(
            x, y, size, size);
   }

   public Point2D getConnectionPoint(Point2D other)
   {
      double centerX = x + size / 2;
      double centerY = y + size / 2;
      double dx = other.getX() - centerX;
      double dy = other.getY() - centerY;
      if (dx >= dy && dx >= -dy) return new Point2D.Double(x + size, centerY);
      if (dx < dy && dx >= -dy) return new Point2D.Double(centerX, y + size);
      if (dx >= dy && dx < -dy) return new Point2D.Double(centerX, y);
      return new Point2D.Double(x, centerY);
   }   

   public boolean contains(Point2D aPoint)
   {
      return getBounds().contains(aPoint);
   }

   private double x;
   private double y;
   private double size;
   private Color color;  
   private static final int DEFAULT_SIZE = 20;
}
