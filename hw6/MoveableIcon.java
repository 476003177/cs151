import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tyler on 3/19/2017.
 */
public class MoveableIcon extends ImageIcon implements MoveableShape {

    private Rectangle bounds;

    public MoveableIcon(String filename, int x, int y)
    {
        super(filename);

        this.bounds = new Rectangle(x, y, this.getIconWidth(), this.getIconHeight());
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(this.getImage(), (int) bounds.getX(), (int) bounds.getY(), this.getImageObserver());
    }

    @Override
    public void move() {
        if (Math.random() < 0.5)
            bounds.setRect(bounds.getX() + 1, bounds.getY(), bounds.getWidth(), bounds.getHeight());
        else
            bounds.setRect(bounds.getX() - 1, bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
