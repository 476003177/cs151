import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This program implements an animation that moves
 * a car shape.
 */
public class Animation {
    public static void show(List<MoveableShape> shapes, MoveStrategy strategy, int width, int height) {
        JFrame frame = new JFrame();

        Set<Drawing> drawings = new HashSet<>();

        for (MoveableShape s: shapes)
            drawings.add(new Drawing(s, width, height));

        frame.setLayout(new FlowLayout());
        for (Drawing s : drawings)
            frame.add(s.getLabel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(width, height));
        frame.setSize(new Dimension(width + 200, height + 100));
        frame.setVisible(true);

        final int DELAY = 100;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, event ->
        {
            strategy.process(shapes);
            for (Drawing d: drawings)
                d.update();
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

        public Drawing(MoveableShape shape, int iconWidth, int iconHeight) {
            this.shape = shape;
            icon = new ShapeIcon(shape, iconWidth + 1, iconHeight + 1);
            label = new JLabel(icon);
        }

        public void update() {
            label.repaint();
        }

        public JLabel getLabel() {
            return label;
        }

    }

}
