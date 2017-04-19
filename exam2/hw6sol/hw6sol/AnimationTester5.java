import java.util.ArrayList;
import java.util.List;

/**
   This program implements an animation that moves
   a car shape.
*/
public class AnimationTester5
{
   public static void main(String[] args)
   {
      final int CAR_WIDTH = 100;
      final List<MoveableShape> shapes = new ArrayList<>(); 
      shapes.add(new CarShape(0, 0, CAR_WIDTH));
      shapes.add(new MoveableIcon("dog.png", 100, 0));
      shapes.add(new BoxedShape(new BoxedShape(new CarShape(200, 20, CAR_WIDTH / 2), 0), 5));
      Animation.show(shapes, 
            s -> {}, 
            400, 100);
   }
}
