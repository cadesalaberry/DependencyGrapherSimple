#Dependency Grapher
=============

## COMP251

### Winter 2013

#### Homework 3


#### Question 1 ~ __/20

A *dependency graph* is a DAG that describes necessary precedence between individual tasks: each task is represented by a node, and if *t1* must be completed before *t2* can be started, then there is a directed
edge from the node representing *t1* to the node representing *t2*.
Assume you have *n* tasks to perform, arranged as such as a dependency DAG *G = (V,E)*. For simplicity assume each task takes the same amount of time to perform. You have *p* people available to perform tasks, each of whom can do at most one task at a time. The goal is to give your workers tasks so as to minimize the total time taken to complete all tasks, while respecting the dependencies given by the dependency DAG.


(a) **4** Is there any lower bound constraint(s) on the solution (minimum time to do all tasks) for an arbitrary *n*, *p*, and dependence DAG? Explain. 
(Note that this question is asking about the solution itself, not the running time of the algorithm generating the solution!) 

>Yes there is a lower-bound, when every workers work all the time (i.e execute task all the time). If 1 is the time that the workers take to execute one task, with n tasks and p workers, it would take *n/p* time to finish all the tasks in this case. This is the best scenario case.


(b) **6** Describe a _greedy_ solution to perform all tasks in as little total time as possible. Your solution does not need to be optimal, but it should be as good as you can make it. 
Note that the running time of your algorithm is not a great concern, but it should be efficient enough in practice to scale up to at least thousands of nodes in the dependency DAG and or workers.
Give a clear pseudo-code description of your proposed algorithm. Is your solution optimal? Justify your answer. 
	
	
	List<Node> roots
	List<Node> destinations
	List<Node> workers

	int findOptimalTaskOrder(Tasks[n] tasks)
		
		updateRootsAndDestinations()
		int hours
		
		while (workers is not empty)

			// Checks that the root has no predecessor (it will be our next task)
			for each node of roots
				if root does not exist in destinations
					
					add root to workers
					delete root from roots
					delete root from destinations
					print root,
			
					// Limit to the number of workers
					if size of workers >= p
						empty workers
						hours++
						print \n

			empty workers
			hours++
			print \n

		return hours

		
	updateRootsAndDestinations()
		
		// Get possible roots
		for each node of tasks
			add node to root with no duplicates

			// Get possible destinations
			for each node of node.destinations
				add node to destinations with no duplicates

> The algorithm definitely is not optimal, (it's greedy) it fits for every nodes, but does not optimizes itself for special cases. To get an optimal solution we would have to look at the big picture, and then deduce what would be the shortest path. Just like we do when we try to schedule a team.

(c) **10** You do not need to actually implement the above algorithm (although it should be clearly implementable). However, testing such an algorithm would require some input dependency DAGs.
Design and implement an algorithm that accepts 1 or 2 command-line parameters: *n*, and an optional seed to a random number generator. A template is provided in *MyCourses*.
Your program should emit the graph to standard output in an adjacency list format. For each vertex emit a single line starting with the vertex number followed by the list of vertices to which there are outgoing edges, all separated by spaces. Do not include anything else in the output. For example
(*n* = 3):

		1 3 2
		2 3

Your function must be capable of generating any possible DAG on *n* nodes.


#### Question 2 ~ __/20


Consider a rectangle in the positive quadrant of R^2 , with bottom-left corner at the origin. Inside are *n − 4* points, randomly located (ie random coordinates, but strictly within the rectangle). The goal is to add as many edges as possible between points, using straight lines only, and without allowing any edges to intersect.

		Helpful:
		Theorem 1. If v ≥ 3 then e ≤ 3v − 6;
		Theorem 2. If v ≥ 3 and there are no cycles of length 3, then e ≤ 2v − 4.


		Euler's formula states that if a finite, connected, planar graph is drawn in the plane without any edge intersections, and v is the number of vertices, e is the number of edges and f is the number of faces (regions bounded by edges, including the outer, infinitely large region), then
		v − e + f = 2.

(a) **10** Implement this simulation.
You may use any approach to placing edges you want, as long as its complexity is in *O(n^k)* for some *k*, and it achieves the goal of ensuring no further edge can be added without introducing an intersection.
Your program should take three or four command-line parameters: *n−4*, the width and height of the rectange, and an optional random seed value (for reproducibility). You may assume (*n≥4*).
Using a random number generator (initialized to the seed value if one is provided), choose random coordinates for the *n−4* points such that they lie (strictly) within your rectangle.
Connect as many nodes as possible without crossing edges. Emit the total number of edges in the graph.
Note: rendering the results visually is not required. However, for debugging it is helpful, so a library
to help you draw graphs is provided in *MyCourses*. nb: You do **NOT** need to include this library in your submission.

> See Edger.java

(b) **5** What is the worst-case asymptotic complexity of your edge-addition process? Give a brief high-level description of your approach, a suitable (tight) complexity class, and an argument explaining how/why you derived that complexity.

> If every line drawn intersect with the last edge recorded, the program will have to go through all the vertices O(n), do it again to try to link them O(n), and go through all the edges already recorded O(n). Therefore the complexity will be O(n^3) 


(c) **5** Plot the number of edges added as a function of *n*. Comment on the results—what do you observe?

> we get a trend of ~ y = 2.9845754634 x -10.9435856845
Therefore a graph with x points will roughtly have 3 x edges that will not intersect.



#### Question 3 ~ __/10


Drawing a line on the plane bisects the plane into two pieces. Suppose you then draw another line, not parallel to the first. It will further divide the two halves of the plane. You can keep on doing this, each time choosing a new line of arbitrary slope, but not parallel to any other line (also assume no more than 2 lines meet at any point).
An adjacency graph can be constructed to represent this. Each node represents an undivided portion of the plane, and edges exist between portions that share a boundary edge (as created by your lines). An example is shown below.
Use induction to prove that the adjacency graph is always a bipartite graph for any such arrangement of lines.

> Base case: 0 cut, leads to one region therefore one color. It is bipartite by definition.
Induction:
For each cuts added the region will bit split into two smaller regions which will be bipartite as well by definition

