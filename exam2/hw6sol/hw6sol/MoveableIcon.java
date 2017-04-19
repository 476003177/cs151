import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class MoveableIcon extends ImageIcon implements MoveableShape 
{
   public MoveableIcon(String filename, int x, int y)
   {
      super(filename);
      this.x = x;
      this.y = y;
   }
   
   @Override
   public void draw(Graphics2D g2)
   {
      paintIcon(null, g2, x, y);      
   }
   
   public void move()
   {
      x++;
   }
   
   public Rectangle getBounds()
   {
      return new Rectangle(x, y, getIconWidth(), getIconHeight());
   }
   
   private int x;
   private int y;
}
