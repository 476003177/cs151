import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 * A shape that contains other shapes
 */
public class CompoundShape implements MoveableShape {

    private Collection<MoveableShape> shapes;

    /**
     * Initializes the collection of shapes
     * @param shapes the collection of shapes
     */
    public CompoundShape(MoveableShape... shapes) {
        this.shapes = new ArrayList<>();

        for (MoveableShape s: shapes)
            this.shapes.add(s);
    }

    @Override
    public void draw(Graphics2D g2) {
        for (MoveableShape s: shapes) {
            s.draw(g2);
        }
    }

    @Override
    public void move() {
        for (MoveableShape s: shapes)
            s.move();
    }

    @Override
    public Rectangle getBounds() {

        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        for (MoveableShape s: shapes) {
            x = (int) Math.min(x, s.getBounds().getX());
            y = (int) Math.min(y, s.getBounds().getY());
            maxX = (int) Math.max(maxX, (s.getBounds().getX() + s.getBounds().getWidth()));
            maxY = (int) Math.max(maxY, s.getBounds().getY() + s.getBounds().getHeight());
        }

        return new Rectangle(x, y, Math.abs(maxX - x), Math.abs(maxY - y));
    }

}
