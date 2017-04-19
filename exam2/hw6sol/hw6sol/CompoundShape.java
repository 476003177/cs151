import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CompoundShape implements MoveableShape
{
   public CompoundShape(MoveableShape... shapes)
   {
      this.shapes = shapes;
   }
   
   @Override
   public void draw(Graphics2D g2)
   {
      for (MoveableShape shape : shapes) shape.draw(g2);       
   }
   
   @Override
   public void move()
   {
      for (MoveableShape shape : shapes) shape.move();      
   }
   
   @Override
   public Rectangle getBounds()
   {
      if (shapes.length == 0) return new Rectangle();
      Rectangle result = shapes[0].getBounds();
      for (int i = 1; i < shapes.length; i++)
         result = result.union(shapes[i].getBounds());
      return result;
   }
   
   private MoveableShape[] shapes;
}
