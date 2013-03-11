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

		Graph g = new Graph(vertices, edges);
		return g;
	}

	static Edge[] getMaxEdgesNoIntersect(Vertex[] vertices) {

		ArrayList<Edge> edges = new ArrayList<>();

		// Parse vertices one by one
		for (int i = 0; i < vertices.length; i++) {

			// Try to link them to every following vertices
			for (int j = i; j < vertices.length; j++) {

				Line2D line1 = vertices2line(vertices[i], vertices[j]);

				if (!lineIntersectsEdges(line1, edges)) {
					edges.add(new Edge(vertices[i], vertices[j]));
				}
			}
		}
		Edge[] array = new Edge[edges.size()];
		System.out.println("Size: " + edges.size());
		return edges.toArray(array);
	}

	/**
	 * Helper method to convert two vertices to a Line2D.
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	static Line2D vertices2line(Vertex v1, Vertex v2) {

		int x1 = v1.getX();
		int y1 = v1.getY();
		int x2 = v2.getX();
		int y2 = v2.getY();
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		return line;
	}

	/**
	 * Checks if the given line intersects a previously recorded edge.
	 * 
	 * @param l
	 * @param edges
	 * @return
	 */
	static boolean lineIntersectsEdges(Line2D l, ArrayList<Edge> edges) {

		boolean intersects = false;

		for (Edge e : edges) {

			Line2D line2 = vertices2line(e.getV1(), e.getV2());

			if (l.intersectsLine(line2)) {
				intersects = true;
				break;
			}
		}
		return intersects;
	}
}
