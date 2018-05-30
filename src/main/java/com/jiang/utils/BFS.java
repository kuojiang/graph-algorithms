package com.jiang.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * @author cl04
 * @date   2018年5月30日
 */
public class BFS {
	public void run(Graph graph) {
		List<Vertex> list = graph.getVertexes();
		Map<Integer, List<Edge>> adj = graph.getAdj();
		Map<Integer, Boolean> visited = new HashMap<>();
		for(Vertex vertex : list) {
			if (visited.containsKey(vertex.getId()))
				continue;
			bfs(vertex.getId(), visited, adj);
			System.out.println();
		}
	}
	
	

	/**
	 * @param id
	 * @param visited
	 * @param adj
	 */
	private void bfs(int id, Map<Integer, Boolean> visited, Map<Integer, List<Edge>> adj) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(id);
		visited.put(id, true);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			System.out.print(curr + "->");
			
			
			List<Edge> vertexAdj = adj.get(curr);
			if (vertexAdj == null)
				return;
			for(Edge edge : vertexAdj) {
				if (!visited.containsKey(edge.getToVertexId())) {
					queue.add(edge.getToVertexId());
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
		
		BFS bfs = new BFS();
		bfs.run(graph);
	}


}
