import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 * Moves shapes
 */
public interface MoveStrategy {
    /**
     * Moves the shapes according to this method
     * @param shapes shapes to be moved
     */
    void process(List<MoveableShape> shapes);
}
