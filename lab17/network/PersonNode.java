import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;


public class PersonNode extends CircleNode{
   private String imageURL;
   private ImageIcon icon;
   public PersonNode(){
      super(new Color(255,255,255));
      setSize(40);
   }
   public void setImageURL(String imageURL) {
      this.imageURL = imageURL;
      try
      {
         icon = new ImageIcon(new URL(imageURL));
      } 
      catch (IOException ex)
      {
         ex.printStackTrace();
      }
   }
   public void draw(Graphics2D g2){
      if (icon == null) return;
      icon.paintIcon(null, g2, (int)x, (int)y);
   }
   public String getImageURL() {
      return imageURL;
   }
}
