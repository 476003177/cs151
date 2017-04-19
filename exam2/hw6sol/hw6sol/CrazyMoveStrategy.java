import java.util.List;

public class CrazyMoveStrategy implements MoveStrategy
{
   @Override
   public void process(List<MoveableShape> shapes)
   {
      for (int i = 0; i < shapes.size(); i++)
      {
         for (int j = 0; j <= i; j++)
            shapes.get(i).move();
      }   
   }
}
