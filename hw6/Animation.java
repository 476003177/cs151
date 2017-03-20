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

    /**
     * Draws the provided shapes, and moves the shapes according to the provided move strategy with a icon as a
     * container of size width and height
     * @param shapes all of the shapes to be drawn
     * @param strategy the way the shapes are to be moved
     * @param width the width of the shapes container
     * @param height the height of the shapes container
     */
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

    /**
     * A class that wraps a shape in a ShapeIcon, and then wraps that ShapeIcon in a JLabel
     */
    private static class Drawing {
        MoveableShape shape;
        ShapeIcon icon;
        JLabel label;

        /**
         * Wraps a shape in a ShapeIcon, and then wraps that ShapeIcon in a JLabel
         * @param shape the shape to wrap
         */
        public Drawing(MoveableShape shape) {
            this.shape = shape;
            icon = new ShapeIcon(shape, (int) shape.getBounds().getWidth(), (int) shape.getBounds().getHeight());
            label = new JLabel(icon);
        }

        /**
         * Wraps a shape in a ShapeIcon, and then wraps that ShapeIcon in a JLabel. Makes that ShapeIcon
         * the specified size.
         * @param shape the shape to wrap
         * @param iconWidth the width of the icon
         * @param iconHeight the height of the icon
         */
        public Drawing(MoveableShape shape, int iconWidth, int iconHeight) {
            this.shape = shape;
            icon = new ShapeIcon(shape, iconWidth, iconHeight);
            label = new JLabel(icon);
        }

        /**
         * Repaints the label
         */
        public void update() {
            label.repaint();
        }

        /**
         * Returns JLabel associated with this class
         * @return JLabel
         */
        public JLabel getLabel() {
            return label;
        }

    }

}
