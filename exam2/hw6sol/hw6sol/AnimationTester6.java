import java.util.ArrayList;
import java.util.List;

/**
   This program implements an animation that moves
   a car shape.
*/
public class AnimationTester6
{
   public static void main(String[] args)
   {
      final int CAR_WIDTH = 100;
      final List<MoveableShape> shapes = new ArrayList<>(); 
      shapes.add(new BoxedShape(new CompoundShape(new CarShape(200, 20, CAR_WIDTH),
            new MoveableIcon("dog.png", 100, 10),
            new MoveableIcon("dog.png", 150, 100)), 0));
      Animation.show(shapes, 
            new SimpleMoveStrategy(), 
            400, 200);
   }
}