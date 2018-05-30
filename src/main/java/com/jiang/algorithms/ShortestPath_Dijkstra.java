package com.jiang.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * 寻找单源所有的最短路径，使用dijkstra算法
 * @author cl04
 * @date   2018年5月30日
 */
public class ShortestPath_Dijkstra {
	public void run(Graph graph, int sourceVertex) {
		int totalVertexCount = graph.getVertexes().size();
		Map<Integer, Double> dist = new HashMap<Integer, Double>();
		Map<Integer, Path> paths = new HashMap<>();
		Map<Integer, Boolean> visited = new HashMap<>(); 
		init(dist, graph);  // 初始化源点到所有点的距离为正无穷
		
		dist.put(sourceVertex, 0.0);
		
		while(visited.size() < totalVertexCount) {
			int minDistVertex = getMinDistVertex(dist, visited);
			visited.put(minDistVertex, true);
			
			List<Edge> vertexEdge = graph.getAdj().get(minDistVertex);
			
			if (vertexEdge == null)
				continue;
			for(Edge edge : vertexEdge) {
				int otherVertex = edge.getToVertexId();
				double newDist = dist.get(minDistVertex) + edge.getWeight();
				if(newDist > dist.get(otherVertex))
					continue;
				
				double updateDist = Math.min(dist.get(otherVertex), newDist);
				dist.put(otherVertex, updateDist);
				
				Path path = paths.get(otherVertex);
				if (path != null) {
						if (path.getDist() == updateDist) {
							List<List<Integer>> newPath = getNewPath(paths, minDistVertex, otherVertex);
							path.getValues().addAll(newPath);
						} else if (path.dist > updateDist) {
							path.getValues().clear();
							List<List<Integer>> newPath = getNewPath(paths, minDistVertex, otherVertex);
							path.getValues().addAll(newPath);
							path.setDist(updateDist);
						}
				} else {
					path = new Path();
					List<List<Integer>> newPath = getNewPath(paths, minDistVertex, otherVertex);
					path.getValues().addAll(newPath);
					path.setDist(updateDist);
					paths.put(otherVertex, path);
				}
			}
			
		}
		
		for(Map.Entry<Integer, Path> entry : paths.entrySet()) {
			System.out.println(sourceVertex + " to " + entry.getKey() + " paths: " + entry.getValue());
		}
	}
	
	
	/**
	 * @param paths
	 * @param minDistVertex
	 * @return
	 */
	private List<List<Integer>> getNewPath(Map<Integer, Path> paths, int minDistVertex, int otherVertex) {
		List<List<Integer>> result = new ArrayList<>();
		if (paths.containsKey(minDistVertex)) {
			Path fatherVertexPath = paths.get(minDistVertex);
			for(List<Integer> value : fatherVertexPath.values) {
				List<Integer> list = new ArrayList<>();
				list.addAll(value);
				list.add(otherVertex);
				result.add(list);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(minDistVertex);
			list.add(otherVertex);
			result.add(list);
		}
		
		return result;
	}


	/**
	 * @param dist
	 * @param visited
	 * @return
	 */
	private int getMinDistVertex(Map<Integer, Double> dist, Map<Integer, Boolean> visited) {
		int vertex = -1;
		double minDist = Double.MAX_VALUE;
		for(Map.Entry<Integer, Double> entry : dist.entrySet()) {
			if (!visited.containsKey(entry.getKey())) {
				if (minDist > entry.getValue()) {
					vertex = entry.getKey();
					minDist = entry.getValue();
				}
			}
		}
		return vertex;
	}


	/**
	 * @param dist
	 */
	private void init(Map<Integer, Double> dist, Graph graph) {
		List<Vertex> vertexes = graph.getVertexes();
		for(Vertex vertex : vertexes) {
			dist.put(vertex.getId(), Double.MAX_VALUE);
		}
		
	}
	
	private static class Path {
		private double dist;
		private List<List<Integer>> values = new ArrayList<>();
		
		public double getDist() {
			return dist;
		}
		public void setDist(double dist) {
			this.dist = dist;
		}
		public List<List<Integer>> getValues() {
			return values;
		}
		@SuppressWarnings("unused")
		public void setValues(List<List<Integer>> values) {
			this.values = values;
		}
		@Override
		public String toString() {
			return "Path [dist=" + dist + ", values=" + values + "]";
		}
		
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(9, true);
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 2);
		graph.addEdge(0, 3, 4);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 4, 2);
		graph.addEdge(1, 5, 5);
		graph.addEdge(2, 4, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(4, 3, 1);
		graph.addEdge(4, 5, 2);
		graph.addEdge(5, 6, 6);
		graph.addEdge(6, 7, 1);
		graph.addEdge(7, 6, 2);
		graph.addEdge(6, 8, 1);
		graph.addEdge(7, 8, 3);
		
		ShortestPath_Dijkstra obj = new ShortestPath_Dijkstra();
		obj.run(graph, 0);
	}


}
