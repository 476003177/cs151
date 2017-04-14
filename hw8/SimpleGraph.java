import java.awt.*;
import java.util.*;

/**
   A simple graph with round nodes and straight edges.
*/
public class SimpleGraph extends Graph
{
    @Override
    public Node[] getNodePrototypes()
    {
      Node[] nodeTypes =
              {
                      new CircleNode(Color.BLACK),
                      new CircleNode(Color.WHITE),
                      new DiamondNode()
              };
      return nodeTypes;
    }

    @Override
    public Edge[] getEdgePrototypes()
    {
      Edge[] edgeTypes =
         {
             new LineEdge(),
             new HVEdge(),
             new VHEdge()
         };
      return edgeTypes;
    }
}





