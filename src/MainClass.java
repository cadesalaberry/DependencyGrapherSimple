
import java.awt.Dimension;
import graphdrawer.*;
import java.util.Random;

/**
 * This class demonstrates how to use graphdrawer package
 *
 * @author Amjad Almahairi
 */

public class MainClass {

    /**
     * Application entry point
     *
     */
    public static void main(String[] args) {

        Dimension dim = new Dimension(800, 600);
        ResultFrame resultFrame = new ResultFrame(dim);
        resultFrame.validate();
        resultFrame.setVisible(true);

        Dimension window = resultFrame.getWindowSize();
        // bound coordinates by the actual size of the drawing panel
        Graph g = randomGraph(window);
        resultFrame.setGraph(g);
    }

    /**
     * Generates random graph which vertices' coordinates are bounded by the
     * viewing window size
     *
     * @param window size of the drawing panel
     *
     * @return graph with random vertices and edges
     */

    static Graph randomGraph(Dimension window){
        // random vertices
        int n = 50;
        Vertex[] vertices = new Vertex[n];
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(window.width);
            int y = rand.nextInt(window.height);
            vertices[i] = new Vertex(x,y);
        }

        // random edges
        int e = 50;
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            int ind1 = rand.nextInt(n);
            int ind2 = rand.nextInt(n);
            edges[i] = new Edge(vertices[ind1], vertices[ind2]);
        }
        
        Graph g = new Graph(vertices, edges);
        return g;
    }

}
