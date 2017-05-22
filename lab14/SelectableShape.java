import java.awt.*;
import java.awt.geom.*;

/**
   A shape that manages its selection state.
*/
public abstract class SelectableShape implements SceneShape
{
   public void setSelected(boolean b)
   {
      selected = b;
   }

   public boolean isSelected()
   {
      return selected;
   }
   private void fillTinySquare(Graphics2D g2, double x, double y){
      
   }
   public void drawSelection(Graphics2D g2){
      Rectangle r = getBounds();
      
      fillTinySquare(g2,r.getX(),r.getY());
      fillTinySquare(g2,r.getMaxX(),r.getY());
      fillTinySquare(g2,r.getX(),r.getMaxY());
      fillTinySquare(g2,r.getMaxX(),r.getMaxY());
   }
   private boolean selected;
}
