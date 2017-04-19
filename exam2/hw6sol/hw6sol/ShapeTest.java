import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
   Tests Homework 6.
*/
public class ShapeTest
{
   /**
      A shape with predictable behavior.
   */
   public class TestShape implements MoveableShape
   {
      /**
         Constructs a shape with given bounds and positions.
         @param bounds the bounds
         @param dx the x-offset
         @param dy the y-offset
      */
      public TestShape(Rectangle bounds, int dx, int dy)
      {
         this.bounds = bounds;
         this.dx = dx;
         this.dy = dy;
      }
      
      @Override
      public void draw(Graphics2D g2)
      {
         g2.draw(bounds);
      }
      
      @Override
      public Rectangle getBounds()
      {        
         return bounds;
      }
      
      @Override
      public void move()
      {
         bounds.translate(dx, dy);         
      }
      
      private Rectangle bounds;
      private int dx;
      private int dy;
   }

   /**
      Tests Car.getBounds.
   */
   @Test public void testCarBounds()
   {
      MoveableShape car = new CarShape(10, 10, 50);
      assertEquals(new Rectangle(10, 10, 50, 25), car.getBounds());
   }
   
   /**
      Tests MoveableIcon.getBounds.
    */
   @Test public void testDogBounds()
   {
      MoveableShape shape = new MoveableIcon("dog.png", 10, 50);
      assertEquals(new Rectangle(10, 50, 50, 50), shape.getBounds());
   }

   /**
      Tests BoxedShape.getBounds.
    */
   @Test public void testBoxedBounds()
   {
      MoveableShape shape = new BoxedShape(new TestShape(new Rectangle(10, 10, 20, 30), 0, 0), 0);
      assertEquals(new Rectangle(10, 10, 20, 30), shape.getBounds());
      shape = new BoxedShape(new TestShape(new Rectangle(10, 10, 20, 30), 0, 0), 5);
      assertEquals(new Rectangle(5, 5, 30, 40), shape.getBounds());         
   }

   /**
      Tests BoxedShape.move.
    */
   @Test public void testMovedBox()
   {
      TestShape test = new TestShape(new Rectangle(10, 10, 20, 30), 2, 2);
      MoveableShape shape = new BoxedShape(test, 0);
      shape.move();
      assertEquals(new Rectangle(12, 12, 20, 30), test.getBounds());
      assertEquals(new Rectangle(12, 12, 20, 30), shape.getBounds());
   }
   
   /**
      Tests BoxedShape.draw.
    */
   @Test public void testDrawnBox()
   {
      BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
      for (int x = 0; x < img.getWidth(); x++)
         for (int y = 0; y < img.getHeight(); y++)
            img.setRGB(x, y, 0xFFFFFFFF);
      MoveableShape shape = new BoxedShape(new TestShape(new Rectangle(10, 10, 20, 30), 0, 0), 5);
      Graphics2D g2 = img.createGraphics();
      g2.setColor(Color.BLACK);
      shape.draw(g2);
      assertEquals(0xFF000000, img.getRGB(10, 10));
      assertEquals(0xFF000000, img.getRGB(5, 5));
   }

   /**
      Tests CompoundShape.getBounds.
    */
   @Test public void testCompoundBounds()
   {
      MoveableShape shape = new CompoundShape(
            new TestShape(new Rectangle(10, 10, 20, 30), 0, 0),
            new TestShape(new Rectangle(100, 100, 20, 30), 0, 0));
      assertEquals(new Rectangle(10, 10, 110, 120), shape.getBounds());            
   }
   
   /**
      Tests CompoundShape.move.
    */
   @Test public void testMovedCompound()
   {
      TestShape test1 = new TestShape(new Rectangle(10, 10, 20, 30), 2, 2);
      TestShape test2 = new TestShape(new Rectangle(100, 100, 20, 30), -1, -1);
      MoveableShape shape = new CompoundShape(test1, test2);
      shape.move();
      assertEquals(new Rectangle(12, 12, 20, 30), test1.getBounds());
      assertEquals(new Rectangle(99, 99, 20, 30), test2.getBounds());      
   }
   
   /**
      Tests CompoundShape.draw.
    */
   @Test public void testDrawnCompound()
   {
      BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
      for (int x = 0; x < img.getWidth(); x++)
         for (int y = 0; y < img.getHeight(); y++)
            img.setRGB(x, y, 0xFFFFFFFF);
      
      MoveableShape shape = new CompoundShape(
            new TestShape(new Rectangle(10, 10, 20, 30), 0, 0),
            new TestShape(new Rectangle(100, 100, 20, 30), 0, 0));
      Graphics2D g2 = img.createGraphics();
      g2.setColor(Color.BLACK);
      shape.draw(g2);
      assertEquals(0xFF000000, img.getRGB(10, 10));
      assertEquals(0xFF000000, img.getRGB(100, 100));      
   }
   
   /**
      Tests SimpleMoveStrategy.process.
    */
   @Test public void testSimpleMoveStrategy()
   {
      MoveStrategy strategy = new SimpleMoveStrategy();
      List<MoveableShape> shapes = Arrays.asList(
            new TestShape(new Rectangle(10, 10, 20, 30), 2, 2),
            new TestShape(new Rectangle(100, 100, 20, 30), -1, -1));
      strategy.process(shapes);
      assertEquals(new Rectangle(12, 12, 20, 30), shapes.get(0).getBounds());
      assertEquals(new Rectangle(99, 99, 20, 30), shapes.get(1).getBounds());         
   }
   
   /**
      Tests BoundedMoveStrategy.process.
    */
   @Test public void testBoundedMoveStrategy()
   {
      MoveStrategy strategy = new BoundedMoveStrategy(new Rectangle(0, 0, 120, 200));
      List<MoveableShape> shapes = Arrays.asList(
            new TestShape(new Rectangle(10, 10, 20, 30), -2, -2),
            new TestShape(new Rectangle(100, 100, 20, 30), 1, 1));
      strategy.process(shapes);
      assertEquals(new Rectangle(8, 8, 20, 30), shapes.get(0).getBounds());
      assertEquals(new Rectangle(101, 101, 20, 30), shapes.get(1).getBounds());         
      strategy.process(shapes);
      assertEquals(new Rectangle(6, 6, 20, 30), shapes.get(0).getBounds());
      assertEquals(new Rectangle(101, 101, 20, 30), shapes.get(1).getBounds());         
   }
}
