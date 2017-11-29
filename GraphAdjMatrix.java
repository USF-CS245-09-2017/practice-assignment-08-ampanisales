package cs245PA08;

/**
 * @author Anthony Panisales
 */

public class GraphAdjMatrix implements Graph {
	
	int[][] edges;
	
	public GraphAdjMatrix(int vertices) {
		edges = new int[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				edges[i][j] = -1;
			}
		}
	}

	public void addEdge(int v1, int v2) {
		edges[v1][v2] = v2;
	}
	
	public int indegree(int vertex) {
		int indegrees = 0;
		for (int i = 0; i < edges.length; i++) {
			if (edges[i][vertex] != -1)
				indegrees++;
		}
		return indegrees;
	}
	
	public int findZero(int[] incident) {
		for (int i = 0; i < incident.length; i++) {
			if (incident[i] == 0)
				return i;
		}
		return -1;
	}
	
	public void topologicalSort() {
		System.out.print("Topological Sort: ");
		int[] incident = new int[edges.length];
		for (int i = 0; i < edges.length; i++)
			incident[i] = indegree(i);
		ArrayQueue sequence = new ArrayQueue();
		for (int i = 0; i < incident.length; i++) {
			int vertex = findZero(incident);
			if (vertex != -1) {
				sequence.enqueue(vertex);
				for (int j = 0; j < edges.length; j++) {
					if (edges[vertex][j] != -1)
						incident[j]--;
				}
				incident[vertex] = -1;
			} else {
				System.out.println("Topological sort not possible because of a cycle");
				System.out.print("Partial solution: ");
				break;
			}
		}
		while(!sequence.empty())
			System.out.print(sequence.dequeue() + " ");
		System.out.println("");
	}
	
	public int[] neighbors(int vertex) {
		int[] nbors = new int[edges.length-1];
		for (int i = 0, j = 0; i < edges.length; i++) {
			if (edges[vertex][i] != -1)
				nbors[j++] = edges[vertex][i];
		}
		return nbors;
	}
}
