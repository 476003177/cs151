import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 */
public class AnimationTester {
    public static void main(String[] args) {

//        List<MoveableShape> shapes = new ArrayList<>();
//
////      // Add cars
////      for (int n = 0; n < 5; n++)
////          shapes.add(new BoxedShape(new BoxedShape(new CarShape(0, 0, 40), 0), 30));
////
////      // Add balls
////      for (int n = 0; n < 5; n++)
////          shapes.add(new BouncingBall(100, 40));
////
////      // Add dogs
////      for (int n = 0; n < 5; n++)
////          shapes.add(new BoxedShape(new MoveableIcon("hw6/dog.png", 0, 0), 20));
//
//        // Compound Shape
//        shapes.add(new BoxedShape(new BoxedShape(new CompoundShape(
//                new BoxedShape(new CarShape(0, 0, 40), 20),
//                new BoxedShape(new BouncingBall(100, 40), 0),
//                new BoxedShape(new MoveableIcon("hw6/dog.png", 0, 0), 10)), 0), 50));
//
//        Animation.show(shapes, new BoundedMoveStrategy(new Rectangle(-100, -100, 300, 300)), 600, 200);


        final int CAR_WIDTH = 100;
        List<MoveableShape> shapes = new ArrayList<>();
        shapes.add(new BoxedShape(new CompoundShape(new CarShape(200, 20, CAR_WIDTH),
                new MoveableIcon("hw6/dog.png", 100, 10),
                new MoveableIcon("hw6/dog.png", 150, 100)), 0));
        Animation.show(shapes,
                new BoundedMoveStrategy(new Rectangle(0, 0, 500, 200)),
                600, 200);
    }
}
