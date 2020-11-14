import java.util.ArrayList;
import java.util.Scanner;

public class TestScanner {

	public static ArrayList<Graphe> graphes = new ArrayList<Graphe>();

	public static void main(String[] args) {

		for (int i = 1; i <= 13; i++) {
			String f = "g" + i + ".txt";
			Graphe g = new Graphe(f);
			graphes.add(g);
		}

		startXp();

	}

	private static void startXp() {
		System.out.println("which graph would you like to see ?");
		Scanner graphIdScan = new Scanner(System.in); // Create a Scanner object

		int graphId = Integer.parseInt(graphIdScan.nextLine()); // Read user input
		if (graphId < 1 || graphId > 13) {
			System.out.println("That graph doesnt exist\n");
		} else {
			System.out.println("Here is the graph: \n" + graphes.get(graphId - 1));
			System.out.println(graphes.get(graphId - 1).Floyd_Warshall());

			Graphe currG = graphes.get(graphId - 1);

			MyGraph testGraph = new MyGraph(currG.getNb_sommets());
			// ADDING EDGES
			for (int i = 0; i < currG.getNb_sommets(); i++) {
				for (int j = 0; j < currG.getNb_sommets(); j++) {

					if (currG.getMatrice()[i][j] != 0) {
						testGraph.addEdge(i, j);
					}
				}
			}

			if (testGraph.isCyclic())
				System.out.println("Graph contains cycle");
			else
				System.out.println("Graph doesn't " + "contain cycle");
		}

		System.out.println("\nwould you like to try another graph ? (yes/no)\n");
		Scanner anotherScan = new Scanner(System.in); // Create a Scanner object
		String another = graphIdScan.nextLine(); // Read user input
		System.out.println(another);

		if (another.equals("yes")) {
			startXp();
		} else {
			return;
		}

	}
}