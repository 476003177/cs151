import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tyler on 3/19/2017.
 */
public class MoveableIcon extends ImageIcon implements MoveableShape
{
    private int x;
    private int y;

    private Rectangle bounds;

    public MoveableIcon(String filename, int x, int y)
    {
        super(filename);
        this.x = x;
        this.y = y;

        this.bounds = new Rectangle(x, y, this.getIconWidth(), this.getIconHeight());
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(this.getImage(), x, y, this.getImageObserver());
    }

    @Override
    public void move() {
        if (Math.random() < 0.5)
            x++;
        else
            x--;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
