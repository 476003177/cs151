import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
   An icon that contains a moveable shape.
*/
public class ShapeIcon implements Icon
{

   /**
    * Initializes instance variables
    * @param shape the shape to be contained
    * @param width the width of the icon
    * @param height the height of the icon
    */
   public ShapeIcon(MoveableShape shape,
      int width, int height)
   {
      this.shape = shape;
      this.width = width;
      this.height = height;
   }

   /**
    * Gets icon width
    * @return the width of the icon
    */
   public int getIconWidth()
   {
      return width;
   }

   /**
    * Gets icon height
    * @return the height of the icon
    */
   public int getIconHeight()
   {
      return height;
   }

   /**
    * Paints icon
    * @param c the component
    * @param g the graphics
    * @param x the x pos
    * @param y the y pos
    */
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      shape.draw(g2);
   }

   private int width;
   private int height;
   private MoveableShape shape;
}


