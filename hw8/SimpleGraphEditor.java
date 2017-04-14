import javax.swing.*;

/**
   A program for editing UML diagrams.
*/
public class SimpleGraphEditor
{
   /**
    * Opens a simple graph.
    * @param args main args
    */
   public static void main(String[] args)
   {
      JFrame frame = new GraphFrame(new SimpleGraph());
      frame.setVisible(true);
   }
}

