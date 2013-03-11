import graphdrawer.Edge;
import graphdrawer.Graph;
import graphdrawer.ResultFrame;
import graphdrawer.Vertex;

import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.util.*;

// Framework for assignment 3, question 1c
public class Edger {
	public static Random r; // seeded or not

	// Starting point. Accepts 1 or 2 command-line arguments,
	// the number of tasks (n) and an optional random seed may
	// be passed in for repeatable results.
	public static void main(String[] args) {
		try {
			// Reads the arguments
			int n = Integer.parseInt(args[0]);
			int width = Integer.parseInt(args[1]);
			int height = Integer.parseInt(args[2]);
			Dimension dim = new Dimension(width, height);

			if (args.length > 3) {
				long seed = Long.parseLong(args[3]);
				r = new Random(seed);
			} else {
				r = new Random();
			}

			// Starts drawing
			ResultFrame resultFrame = new ResultFrame(dim);
			resultFrame.validate();
			resultFrame.setVisible(true);

			Dimension window = resultFrame.getWindowSize();
			// bound coordinates by the actual size of the drawing panel
			Graph g = graph(n, window);
			resultFrame.setGraph(g);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates random graph which vertices' coordinates are bounded by the
	 * viewing window size
	 * 
	 * @param window
	 *            size of the drawing panel
	 * 
	 * @return graph with random vertices and edges
	 */
	static Graph graph(int n, Dimension window) {

		// random vertices
		Vertex[] vertices = new Vertex[n];

		for (int i = 0; i < n; i++) {
			int x = r.nextInt(window.width);
			int y = r.nextInt(window.height);
			vertices[i] = new Vertex(x, y);
		}

		// Get the maximum valid edges
		Edge[] edges = getMaxEdgesNoIntersect(vertices);
		/*
		 * for (int i = 0; i < e; i++) { int ind1 = r.nextInt(n); int ind2 =
		 * r.nextInt(n); edges[i] = new Edge(vertices[ind1], vertices[ind2]); }
		 */

		Graph g = new Graph(vertices, edges);
		return g;
	}

	static Edge[] getMaxEdgesNoIntersect(Vertex[] vertices) {
		ArrayList<Edge> edges = new ArrayList<>();
		
		// Parse vertices one by one
		for (int i = 0; i < vertices.length; i++) {
			
			// Try to link them to every following vertices
			for (int j = i ; j < vertices.length; j++) {

				int x1 = vertices[i].getX();
				int y1 = vertices[i].getY();
				int x2 = vertices[j].getX();
				int y2 = vertices[j].getY();
				Line2D line = new Line2D.Double(x1, y1, x2, y2);
				
				// Checks if it intersects a previously recorded edge.
				boolean intersect = false;
				for (Edge e : edges) {
					
					int ex1 = e.getV1().getX();
					int ey1 = e.getV1().getY();
					int ex2 = e.getV2().getX();
					int ey2 = e.getV2().getY();
					Line2D line2 = new Line2D.Double(ex1, ey1, ex2, ey2);
					
					if (line.intersectsLine(line2)) {
						intersect = true;
						break;
					}
				}
				
				if (!intersect) {
					edges.add(new Edge(vertices[i], vertices[j]));
				}
			}
		}
		Edge[] array = new Edge[edges.size()];
		System.out.println("Size: " + edges.size());
		return edges.toArray(array);
	}
}
