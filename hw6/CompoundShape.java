import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 */
public class CompoundShape implements MoveableShape {

    private List<MoveableShape> shapes;

    public CompoundShape(MoveableShape... shapes) {
        this.shapes = new ArrayList<>();

        for (MoveableShape s: shapes)
            this.shapes.add(s);
    }

    @Override
    public void draw(Graphics2D g2) {
        for (MoveableShape s: shapes) {
            g2.translate(-10, -10);
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
        Rectangle ret = new Rectangle(0, 0, 0, 0);

        for (MoveableShape s: shapes)
            ret.setBounds(0, 0, (int) ret.getBounds().getWidth() + (int) s.getBounds().getWidth(),
                (int) Math.max(ret.getBounds().getHeight(), s.getBounds().getHeight()));

        return ret;
    }

}
