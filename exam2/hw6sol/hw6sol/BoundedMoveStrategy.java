import java.awt.Rectangle;
import java.util.List;

public class BoundedMoveStrategy implements MoveStrategy
{
   public BoundedMoveStrategy(Rectangle bounds)
   {
      this.bounds = bounds;
   }
   
   @Override
   public void process(List<MoveableShape> shapes)
   {
      for (MoveableShape shape : shapes)
         if (bounds.contains(shape.getBounds())) 
            shape.move();
   }
   
   private Rectangle bounds;
}
