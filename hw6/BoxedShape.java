import java.awt.*;

/**
 * Created by Tyler on 3/19/2017.
 */
public class BoxedShape implements MoveableShape {

    private MoveableShape shape;

    private int gap;

    public BoxedShape(MoveableShape shape, int gap) {
        this.shape = shape;

        this.gap = gap;
    }

    @Override
    public void draw(Graphics2D g2) {
        shape.draw(g2);

        g2.draw(getBounds());
    }

    @Override
    public void move() {
        shape.move();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)shape.getBounds().getX() - gap, (int) shape.getBounds().getY() - gap,
                (int) shape.getBounds().getWidth() + (gap * 2), (int) shape.getBounds().getHeight() + (gap * 2));
    }
}
