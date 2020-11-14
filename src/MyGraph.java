import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class MyGraph {

	private final int V;
	private final List<List<Integer>> adj;

	public MyGraph(int V) {
		this.V = V;
		adj = new ArrayList<>(V);

		for (int i = 0; i < V; i++)
			adj.add(new LinkedList<>());
	}

	// This function is a variation of DFSUtil() in
	// https://www.geeksforgeeks.org/archives/18212
	private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {

		boolean verif = false;

		// Mark the current node as visited and
		// part of recursion stack
		if (recStack[i])
			return true;

		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;
		List<Integer> children = adj.get(i);

		for (Integer c : children) {
			if (isCyclicUtil(c, visited, recStack)) {
				verif = true;
				System.out.print(i + "  " + c + "  ");

			}

		}
		if (verif)
			System.out.println("");

		recStack[i] = false;

		return verif;
	}

	public void addEdge(int source, int dest) {
		adj.get(source).add(dest);
	}

	// Returns true if the graph contains a
	// cycle, else false.
	// This function is a variation of DFS() in
	// https://www.geeksforgeeks.org/archives/18212
	public boolean isCyclic() {
		boolean verif = false;

		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		for (int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack))
				verif = true;

		return verif;
	}
}