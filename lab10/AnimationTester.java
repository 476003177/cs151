import java.awt.*;
import java.util.HashSet;
import javax.swing.*;

/**
   This program implements an animation that moves
   a car shape.
*/
public class AnimationTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      HashSet<Car> cars = new HashSet<>();
      HashSet<Ball> balls = new HashSet<>();

      for (int n = 0; n < 0; n++)
         cars.add(new Car());
      for (int n = 0; n < 1; n++)
         balls.add(new Ball());

      frame.setLayout(new FlowLayout());
      for (Car c: cars)
         frame.add(c.getLabel());
      for (Ball b: balls)
         frame.add(b.getLabel());

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setMinimumSize(new Dimension(300, 600));
      frame.setVisible(true);

      final int DELAY = 100;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, event ->
         {
            for (Car c: cars)
               c.update();
            for (Ball b: balls)
               b.update();
         });
      t.start();
   }

   private static class Car {
      MoveableShape shape;
      ShapeIcon icon;
      JLabel label;

      public Car() {
         shape = new CarShape(0, 0, CAR_WIDTH);
         icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);
         label = new JLabel(icon);
      }

      public void update() {
            shape.move();
            label.repaint();
      }

      public JLabel getLabel() {
         return label;
      }

      private static final int ICON_WIDTH = 400;
      private static final int ICON_HEIGHT = 100;
      private static final int CAR_WIDTH = 100;

   }

   private static class Ball {
      MoveableShape shape;
      ShapeIcon icon;
      JLabel label;

      public Ball() {
         shape = new BouncingBall_Ignore(BALL_WIDTH);
         icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);
         label = new JLabel(icon);
      }

      public void update() {
         shape.move();
         label.repaint();
      }

      public JLabel getLabel() {
         return label;
      }

      private static final int ICON_WIDTH = 100;
      private static final int ICON_HEIGHT = 400;
      private static final int BALL_WIDTH = 20;
   }

}
