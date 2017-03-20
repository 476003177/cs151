import java.awt.*;
import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 * Stops moving when the shape is no longer contained in the BoundingBox
 */
public class BoundedMoveStrategy implements MoveStrategy {

    /**
     * Bounds to keep shapes in
     */
    private Rectangle bounds;

    /**
     * Initializes bounds
     * @param bounds the rect to bound the shapes
     */
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
