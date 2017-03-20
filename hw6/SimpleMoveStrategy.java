import java.util.List;

/**
 * Created by Tyler on 3/19/2017.
 */
public class SimpleMoveStrategy implements MoveStrategy {
    @Override
    public void process(List<MoveableShape> shapes) {
        for (MoveableShape s: shapes)
            s.move();
    }
}
