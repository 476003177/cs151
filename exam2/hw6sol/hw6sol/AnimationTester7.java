import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;

/**
   This program implements an animation that moves
   a car shape.
*/
public class AnimationTester7
{
   /**
      Runs the program.
      @param args unused
    */
   public static void main(String[] args) throws Exception
   {
      final int CAR_WIDTH = 100;
      final List<MoveableShape> shapes = new ArrayList<>(); 
      shapes.add(new BoxedShape(new CompoundShape(new CarShape(200, 20, CAR_WIDTH),
            new MoveableIcon("dog.png", 100, 10),
            new MoveableIcon("dog.png", 150, 100)), 0));
      Animation.show(shapes, 
            new BoundedMoveStrategy(new Rectangle(0, 0, 500, 200)), 
            600, 200);

      GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice screen = environment.getDefaultScreenDevice();
      Robot robot = new Robot(screen);
      robot.delay(1000);
      BufferedImage screencapture = new Robot().createScreenCapture(
         new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
      File file = new File("screencapture.jpg");
      ImageIO.write(screencapture, "jpg", file);
      System.exit(0);
   }
}
