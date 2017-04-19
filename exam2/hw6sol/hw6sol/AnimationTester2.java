import java.util.ArrayList;
import java.util.List;

/**
   This program implements an animation that moves
   a car shape.
*/
public class AnimationTester2
{
   public static void main(String[] args)
   {
      final int CAR_WIDTH = 100;
      final List<MoveableShape> shapes = new ArrayList<>(); 
      shapes.add(new CarShape(0, 0, CAR_WIDTH));
      shapes.add(new MoveableIcon("dog.png", 100, 0));
      Animation.show(shapes, new SimpleMoveStrategy(), 400, 100);
   }
}
