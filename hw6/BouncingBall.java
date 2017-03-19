import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Rooke_000 on 3/1/2017.
 */
public class BouncingBall implements MoveableShape {
    private int x;
    private int y;

    private int ballWidth;

    private Rectangle bounds;

    private boolean goingUp;

    public BouncingBall(int maxY, int ballWidth) {
        this.x = 0;
        this.y = 0;

        this.ballWidth = ballWidth;

        this.bounds = new Rectangle(0, 0, ballWidth, maxY);

        this.goingUp = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        Color prevColor = g2.getColor();
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(x, y, ballWidth, ballWidth));
        g2.setColor(prevColor);
    }

    @Override
    public void move() {
        if (y <= 0)
            goingUp = false;
        else if (y >= bounds.getMaxY() - ballWidth)
            goingUp = true;

        y += (goingUp ? -1 : 1) * 4;

        y = Math.max(0, y);
        y = Math.min((int) bounds.getMaxY(), y);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
