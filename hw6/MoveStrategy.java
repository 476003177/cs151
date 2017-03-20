import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 */
public interface MoveStrategy {
    void process(List<MoveableShape> shapes);
}
