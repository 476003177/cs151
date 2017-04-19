import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BoxedShape implements MoveableShape
{
   public BoxedShape(MoveableShape shape, int gap)
   {
      this.shape = shape;
      this.gap = gap;
   }

   @Override
   public void draw(Graphics2D g2)
   {
      shape.draw(g2);
      g2.draw(getBounds());
   }
   
   @Override
   public void move()
   {
      shape.move();
   }
   
   @Override
   public Rectangle getBounds()
   {
      Rectangle bounds = (Rectangle) shape.getBounds().clone();
      bounds.grow(gap, gap);
      return bounds;
   }
   
   private MoveableShape shape;
   private int gap;
}
