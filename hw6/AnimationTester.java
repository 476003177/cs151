import java.awt.*;

import java.util.Set;
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

      Set<Drawing> drawings = new HashSet<>();

//      // Add cars
//      for (int n = 0; n < 5; n++)
//         drawings.add(new Drawing(new BoxedShape(new BoxedShape(new CarShape(0, 0, 40), 0), 30)));
//
//      // Add balls
//      for (int n = 0; n < 5; n++)
//         drawings.add(new Drawing(new BouncingBall(100, 40)));
//
//      // Add dogs
//      for (int n = 0; n < 5; n++)
//         drawings.add(new Drawing(new BoxedShape(new MoveableIcon("hw6/dog.png", 0, 0), 20)));

      // Compound Shape
      drawings.add(new Drawing(new CompoundShape(new BoxedShape(new CarShape(0, 0, 40), 20),
              new BoxedShape(new BouncingBall(100, 40), 0),
              new BoxedShape(new MoveableIcon("hw6/dog.png", 0, 0), 0))));

      frame.setLayout(new FlowLayout());
      for (Drawing s: drawings)
         frame.add(s.getLabel());

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setMinimumSize(new Dimension(300, 600));
      frame.setVisible(true);

      final int DELAY = 100;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, event ->
         {
            for (Drawing s: drawings)
               s.update();
         });
      t.start();
   }

   private static class Drawing {
      MoveableShape shape;
      ShapeIcon icon;
      JLabel label;

      public Drawing(MoveableShape shape) {
         this.shape = shape;
         icon = new ShapeIcon(shape, (int) shape.getBounds().getWidth() + 1, (int) shape.getBounds().getHeight() + 1);
         label = new JLabel(icon);
      }

       public Drawing(MoveableShape shape, int extraSpaceRight, int extraSpaceDown) {
           this.shape = shape;
           icon = new ShapeIcon(shape, (int) shape.getBounds().getWidth() + extraSpaceRight + 1, (int) shape.getBounds().getHeight() + extraSpaceDown + 1);
           label = new JLabel(icon);
       }

      public void update() {
            shape.move();
            label.repaint();
      }

      public JLabel getLabel() {
         return label;
      }

   }

}
