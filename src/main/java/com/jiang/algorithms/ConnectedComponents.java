package com.jiang.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * 寻找图中连通组件
 * @author cl04
 * @date   2018年5月30日
 */

public class ConnectedComponents {
	private int ccNo;
	
	public void run(Graph graph) {
		List<Vertex> vertexes = graph.getVertexes();
		Map<Integer, List<Edge>> adj = graph.getAdj();
		Map<Integer, Boolean> visited = new HashMap<>();
		Map<Integer, Integer> vertexAndCCNo = new HashMap<>();
		
		for(Vertex vertex : vertexes) {
			if (visited.containsKey(vertex.getId()))
				continue;
			dfs(vertex.getId(), visited, adj, vertexAndCCNo);
			ccNo++;
		}
		
		for(Map.Entry<Integer, Integer> entry : vertexAndCCNo.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
	}
	/**
	 * @param id
	 * @param visited
	 * @param adj
	 * @param vertexAndCCNo
	 */
	private void dfs(int id, Map<Integer, Boolean> visited, Map<Integer, List<Edge>> adj,
			Map<Integer, Integer> vertexAndCCNo) {
		if (!visited.containsKey(id)) {
			vertexAndCCNo.put(id, ccNo);
			visited.put(id, true);
			
			List<Edge> vertexAdj = adj.get(id);
			
			if (vertexAdj == null)
				return;
			for(Edge edge : vertexAdj) {
				int toVertex = edge.getToVertexId();
				dfs(toVertex, visited, adj, vertexAndCCNo);
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(8, false);
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
		
		graph.addEdge(6, 7, 1);
		graph.addEdge(7, 6, 1);
		
		ConnectedComponents obj = new ConnectedComponents();
		obj.run(graph);
		
	}
}
