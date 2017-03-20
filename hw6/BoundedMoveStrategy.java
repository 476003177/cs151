import java.awt.*;
import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 */
public class BoundedMoveStrategy implements MoveStrategy {

    private Rectangle bounds;

    public BoundedMoveStrategy(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public void process(List<MoveableShape> shapes) {
        for (MoveableShape s: shapes) {
            if (bounds.contains(s.getBounds()))
                s.move();
        }
    }
}
