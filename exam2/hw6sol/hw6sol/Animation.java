import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Animation
{
   public static void show(List<MoveableShape> shapes, MoveStrategy strategy, int width, int height)
   {
      JFrame frame = new JFrame();
   
      ShapeIcon icon = new ShapeIcon(shapes, width, height);
   
      final JLabel label = new JLabel(icon);
      frame.setLayout(new FlowLayout());
      frame.add(label);
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   
      final int DELAY = 10;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, event ->
         {
            strategy.process(shapes);
            label.repaint();
         });
      t.start();
   }
}