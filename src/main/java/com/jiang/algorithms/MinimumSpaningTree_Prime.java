package com.jiang.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * Prime算法寻找最小生成树
 * @author cl04
 * @date   2018年5月30日
 */
public class MinimumSpaningTree_Prime {
	public void run(Graph graph) {
		Set<Edge> mstSet = new HashSet<>();
		Set<Integer> mstVertexSet = new HashSet<>();
		List<Vertex> vertexes = graph.getVertexes();
		Map<Integer, List<Edge>> adj = graph.getAdj();
		int start = getRandomVertex(vertexes);
		int totalVertexCount = vertexes.size();
		mstVertexSet.add(start);
		
		while(mstVertexSet.size() < totalVertexCount) {
			Map<Integer, Edge> mstVertexSetEdge = new HashMap<>();
			for(int vertexId : mstVertexSet) {
				List<Edge> vertexEdge = adj.get(vertexId);
				Edge edge = getMinWeightEdge(mstVertexSet, vertexEdge);
				if (edge != null)
					mstVertexSetEdge.put(vertexId, edge);
				
			}
			
			Edge edge = getMinWeightEdge(mstVertexSet, new ArrayList<Edge>(mstVertexSetEdge.values()));
			if (edge != null) {
				mstSet.add(edge);
				mstVertexSet.add(edge.getToVertexId());
			}
			
		}
		
		for(Edge edge : mstSet) {
			System.out.println(edge);
		}
	}

	
	/**
	 * @return
	 */
	private int getRandomVertex(List<Vertex> vertexes) {
		Random random = new Random();
		int index = random.nextInt(vertexes.size());
		return vertexes.get(index).getId();
	}
	
	private Edge getMinWeightEdge(Set<Integer> mstVertexSet, List<Edge> edges) {
		Collections.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return  new Double(o1.getWeight()).compareTo(new Double(o2.getWeight()));
			}
		});
		
		for(Edge edge : edges) {
			if (!mstVertexSet.contains(edge.getToVertexId()))
				return edge;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(9, false);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 7, 8);
		graph.addEdge(1, 2, 8);
		graph.addEdge(1, 7, 11);
		graph.addEdge(2, 3, 7);
		graph.addEdge(2, 5, 4);
		graph.addEdge(2, 8, 2);
		graph.addEdge(3, 4, 9);
		graph.addEdge(3, 5, 14);
		graph.addEdge(4, 5, 10);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 7, 1);
		graph.addEdge(6, 8, 6);
		graph.addEdge(7, 8, 7);
		
		MinimumSpaningTree_Prime obj = new MinimumSpaningTree_Prime();
		obj.run(graph);
		
	}

}
