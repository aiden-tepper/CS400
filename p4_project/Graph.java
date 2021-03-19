import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Hashtable;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    Aiden Tepper
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {


	private ArrayList<GraphNode> vertexList;
	private ArrayList edgeList;


	/*
	 * Default no-argument constructor
	 */ 
	public Graph() {
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
	}


	private class GraphNode {

		public String name;
		public ArrayList<GraphNode> dependents;

		public GraphNode(String name) {
			this.name = name;
			dependents = new ArrayList<>();
		}

	}


	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists,
	 * method ends without adding a vertex or 
	 * throwing an exception.
	 * 
	 * Valid argument conditions:
	 * 1. vertex is non-null
	 * 2. vertex is not already in the graph 
	 * 
	 * @param vertex the vertex to be added
	 */
	public void addVertex(String vertex) {	

		GraphNode node = new GraphNode(vertex);

		if(vertex == null)
			return;

		if(getGraphNode(vertex) != null) 
			return;

		vertexList.add(node);

	}


	/**
	 * Remove a vertex and all associated 
	 * edges from the graph.
	 * 
	 * If vertex is null or does not exist,
	 * method ends without removing a vertex, edges, 
	 * or throwing an exception.
	 * 
	 * Valid argument conditions:
	 * 1. vertex is non-null
	 * 2. vertex is not already in the graph 
	 *  
	 * @param vertex the vertex to be removed
	 */
	public void removeVertex(String vertex) {
		
		if(vertex == null)
			return;

		if(getGraphNode(vertex) == null) 
			return;

		vertexList.remove(getGraphNode(vertex));

	}


	/**
	 * Add the edge from vertex1 to vertex2
	 * to this graph.  (edge is directed and unweighted)
	 * 
	 * If either vertex does not exist,
	 * VERTEX IS ADDED and then edge is created.
	 * No exception is thrown.
	 *
	 * If the edge exists in the graph,
	 * no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions:
	 * 1. neither vertex is null
	 * 2. both vertices are in the graph 
	 * 3. the edge is not in the graph
	 *  
	 * @param vertex1 the first vertex (src)
	 * @param vertex2 the second vertex (dst)
	 */
	public void addEdge(String vertex1, String vertex2) {

		if(getGraphNode(vertex1) == null)
			addVertex(vertex1);

		if(getGraphNode(vertex2) == null)
			addVertex(vertex2);

		GraphNode node1 = getGraphNode(vertex1);
		GraphNode node2 = getGraphNode(vertex2);
		node1.dependents.add(node2);

	}


	/**
	 * Remove the edge from vertex1 to vertex2
	 * from this graph.  (edge is directed and unweighted)
	 * If either vertex does not exist,
	 * or if an edge from vertex1 to vertex2 does not exist,
	 * no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions:
	 * 1. neither vertex is null
	 * 2. both vertices are in the graph 
	 * 3. the edge from vertex1 to vertex2 is in the graph
	 *  
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 */
	public void removeEdge(String vertex1, String vertex2) {

		if(getGraphNode(vertex1) == null || getGraphNode(vertex2) == null)
			return;

		if(vertex1 == null || vertex2 == null)
			return;

		GraphNode node1 = getGraphNode(vertex1);
		GraphNode node2 = getGraphNode(vertex2);
		node1.dependents.remove(node2);
		
	}


	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return a Set<String> which contains all the vertices in the graph
	 */
	public Set<String> getAllVertices() {
		
		Set<String> set = new HashSet<>();
		
		for(GraphNode node : vertexList)
			set.add(node.name);
		
		return(set);
		
	}


	/**
	 * Get all the neighbor (adjacent-dependencies) of a vertex
	 * 
	 * For the example graph, A->[B, C], D->[A, B] 
	 *     getAdjacentVerticesOf(A) should return [B, C]. 
	 * 
	 * In terms of packages, this list contains the immediate 
	 * dependencies of A and depending on your graph structure, 
	 * this could be either the predecessors or successors of A.
	 * 
	 * @param vertex the specified vertex
	 * @return an List<String> of all the adjacent vertices for specified vertex
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {

		List<String> adjacents = new ArrayList<>();
		
		GraphNode node = getGraphNode(vertex);
		
		for(GraphNode dependent : node.dependents)
			adjacents.add(dependent.name);
		
		return adjacents;
		
	}


	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return number of edges in the graph.
	 */
	public int size() {
		
		int size = 0;
		
		for(GraphNode node : vertexList)
			size += node.dependents.size();
		
		return size;

	}


	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return number of vertices in graph.
	 */
	public int order() {
		
		return vertexList.size();

	}

	/**
	 * Private helper method that returns the GraphNode for a given vertex string.
	 * 
	 * @param vertex of GraphNode to find
	 * @return corresponding GraphNode, or null if doesn't exist
	 */
	private GraphNode getGraphNode(String vertex) {

		for(GraphNode node : vertexList) {
			if(node.name.equals(vertex)) {
				return node;
			}
		}

		return null;
	}

}
