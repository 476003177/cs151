import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Rooke_000 on 3/1/2017.
 */
public class BouncingBall implements MoveableShape {

    private Ellipse2D ball;

    public BouncingBall(int ballWidth) {
        ball = new Ellipse2D.Double(0, 0, ballWidth, ballWidth);
    }

    @Override
    public void draw(Graphics2D g2) {
        Color prevColor = g2.getColor();
        g2.setColor(Color.RED);
        g2.fill(ball);
        g2.setColor(prevColor);
    }

    @Override
    public void move() {

    }
}
