import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

/**
   An icon that contains moveable shapes.
*/
public class ShapeIcon implements Icon
{
   public ShapeIcon(List<MoveableShape> shapes,
      int width, int height)
   {
      this.shapes = shapes;
      this.width = width;
      this.height = height;
   }
   
   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      for (MoveableShape shape : shapes) shape.draw(g2);
   }

   private int width;
   private int height;
   private List<MoveableShape> shapes;
}


