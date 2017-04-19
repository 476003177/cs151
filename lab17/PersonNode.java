import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Rooke_000 on 4/5/2017.
 */
public class PersonNode extends CircleNode {

    private String imageURL;
    private ImageIcon icon;

    /**
     * Construct a circle node with a given size and color.
     *
     */
    public PersonNode() {
        super(Color.WHITE);
        setSize(40);
        Number
    }

    public String getImageURL() {
        return imageURL;
    }

    public void draw(Graphics2D g2) {
        if (icon == null) return;
        Rectangle2D bounds = getBounds();
        icon.paintIcon(null, g2, (int) bounds.getX(), (int) bounds.getY());
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        try {
            icon = new ImageIcon(new URL(imageURL));
        } catch (IOException ex) {

        }
    }

}
