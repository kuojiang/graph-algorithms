package com.jiang.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * @author cl04
 * @date   2018年5月30日
 */
public class DFS {
	public void run(Graph graph) {
		List<Vertex> list = graph.getVertexes();
		Map<Integer, List<Edge>> adj = graph.getAdj();
		Map<Integer, Boolean> visited = new HashMap<>();
		for(Vertex vertex : list) {
			if (visited.containsKey(vertex.getId()))
				continue;
			dfs(vertex.getId(), visited, adj);
			System.out.println();
		}
		
		System.out.println("-----------------------------------");
		
		visited.clear();
		for(Vertex vertex : list) {
			if (visited.containsKey(vertex.getId()))
				continue;
			dfsNoRecursive(vertex.getId(), visited, adj);
			System.out.println();
		}
	}

	/**
	 * @param vertex
	 * @param visited
	 */
	private void dfs(Integer vertexId, Map<Integer, Boolean> visited, Map<Integer, List<Edge>> adj) {
		if (!visited.containsKey(vertexId)) {
			System.out.print(vertexId + "->");
			visited.put(vertexId, true);
			
			List<Edge> vertexAdj = adj.get(vertexId);
			for(Edge edge : vertexAdj) {
				Integer otherVertex = edge.getToVertexId();
				dfs(otherVertex, visited, adj);
			}
		}
	}
	
	private void dfsNoRecursive(Integer vertexId, Map<Integer, Boolean> visited, Map<Integer, List<Edge>> adj) {
		Stack<Integer> stack = new Stack<>();
		stack.push(vertexId);
		visited.put(vertexId, true);
		
		while(!stack.isEmpty()) {
			int curr = stack.pop();
			System.out.print(curr + "->");
			
			List<Edge> vertexAdj = adj.get(curr);
			for(Edge edge : vertexAdj) {
				if (!visited.containsKey(edge.getToVertexId())) {
					stack.push(edge.getToVertexId());
					visited.put(edge.getToVertexId(), true);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(6, false);
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 0, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 0, 1);
		graph.addEdge(2, 4, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(3, 1, 1);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 1);
		graph.addEdge(4, 5, 1);
		graph.addEdge(5, 3, 1);
		graph.addEdge(5, 4, 1);
		
		DFS dfs = new DFS();
		dfs.run(graph);
		
	}

}
