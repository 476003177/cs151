import javax.swing.*;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SliderIterator implements Iterator<Integer>
{
    public SliderIterator(JSlider slider)
    {
        slider.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            int value = source.getValue();
            queue.add(value);
        });
    }

    public boolean hasNext()
    {
        return true;
    }

    public Integer next()
    {
        return queue.peek();
//        try
//        {
//        }
//        catch (InterruptedException ex)
//        {
//            return null;
//        }
    }

    public void remove()
    {
        queue.remove();
    }

    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
}