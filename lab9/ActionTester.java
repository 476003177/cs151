import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FIELD_WIDTH = 20;
      final JTextField textField = new JTextField(FIELD_WIDTH);
      textField.setText("Click a button!");

      JButton helloButton = new JButton("Say Hello");

      JButton goodbyeButton = new JButton("Say Goodbye");
      goodbyeButton.setEnabled(false);

      int[] counter = { 0 };

      helloButton.addActionListener(event ->
         {
            helloButton.setEnabled(false);
            goodbyeButton.setEnabled(true);
            textField.setText("Hello, World! " + counter[0]++);
         });
      goodbyeButton.addActionListener(event ->
         {
            goodbyeButton.setEnabled(false);
            helloButton.setEnabled(true);
            textField.setText("Goodbye, World! " + counter[0]++);
         });

      frame.setLayout(new FlowLayout());

      frame.add(helloButton);
      frame.add(goodbyeButton);
      frame.add(textField);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
