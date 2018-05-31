package com.jiang.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;
import com.jiang.utils.Utils;

/**
 * 最小割算法：Stoer–Wagner algorithm
 * @author cl04
 * @date   2018年5月30日
 */
public class MinCut {
	private double minCut = Double.MAX_VALUE;
	private Map<Integer, String> mergeVertex = new HashMap<>();
	private Map<String, Set<Integer>> mergeVertexChilds = new HashMap<>();
	public void run(Graph graph) {
		List<Vertex> vertexes = graph.getVertexes();
		
		int start = Utils.getRandomVertex(graph);
		int totalVertexCount = vertexes.size();
		int shrinkVertexCount = totalVertexCount;
		while(shrinkVertexCount > 1) {
			MinimumCutPhase(graph, start, shrinkVertexCount);
			shrinkVertexCount--;
		}
		
		System.out.println(minCut);
		
		
	}

	/**
	 * @param graph
	 * @param start
	 */
	private void MinimumCutPhase(Graph graph, int start, int vertexCount) {
		Set<Integer> set = new HashSet<>();
		set.add(start);
		Set<Integer> penultVertex = null;
		Set<Integer> lastVertex = null;
		int count = 1;
		while(count < vertexCount) {
			Set<Integer> tightVertex = null;
			if (count + 1 == vertexCount) {
				tightVertex = getTightVertex(graph, set, true);
			} else {
				tightVertex = getTightVertex(graph, set, false);
			}
			
			set.addAll(tightVertex);
			
			// 记录倒数第二个添加的点，和倒数第一个添加的点
			if (lastVertex != null)
				penultVertex = lastVertex;
			lastVertex = tightVertex;
			
			count++;
		}
		shrinkGraph(penultVertex, lastVertex);
	}

	/**
	 * 压缩图，将倒数第二个点和倒数第一个点合并在一起，我通过的是将要合并的点映射成一个id的方式来合并的，同时
	 * 对映射的id和它的孩子做了一个映射
	 * @param graph
	 * @param penultVertex
	 * @param lastVertex
	 */
	private void shrinkGraph(Set<Integer> penultVertex, Set<Integer> lastVertex) {
		String mergeVertexId = UUID.randomUUID().toString();
		if (penultVertex != null) {
			for(Integer vertex : penultVertex) {
				mergeVertex.put(vertex, mergeVertexId);
			}
		}
		
		for(Integer vertex : lastVertex) {
			mergeVertex.put(vertex, mergeVertexId);
		}
		
		mergeVertexChilds.clear();
		for(Map.Entry<Integer, String> entry : mergeVertex.entrySet()) {
			if (mergeVertexChilds.containsKey(entry.getValue())) {
				mergeVertexChilds.get(entry.getValue()).add(entry.getKey());
			} else {
				Set<Integer> set = new HashSet<Integer>();
				set.add(entry.getKey());
				mergeVertexChilds.put(entry.getValue(), set);
			}
		}
		
	}

	/**
	 * @param graph
	 * @param set
	 * @return
	 */
	private Set<Integer> getTightVertex(Graph graph, Set<Integer> set, boolean isLast) {
		String result = null;
		double maxWeight = Double.MIN_VALUE;
		Map<String, Double> vertexAndEdgeWeight = new HashMap<>();
		for(Integer vertexId : set) {
			List<Edge> vertexEdge = graph.getAdj().get(vertexId);
			if (vertexEdge == null)
				continue;
			for(Edge edge : vertexEdge) {
				String otherVertex = null;
				int otherVertexInt = edge.getToVertexId();
				
				if (mergeVertex.containsKey(otherVertexInt)) {
					otherVertex = mergeVertex.get(otherVertexInt);
				} else {
					otherVertex = otherVertexInt + "";
				}
				
				if (set.contains(otherVertexInt))
					continue;
				if (vertexAndEdgeWeight.containsKey(otherVertex)) {
					vertexAndEdgeWeight.put(otherVertex, vertexAndEdgeWeight.get(otherVertex) + edge.getWeight());
				} else {
					vertexAndEdgeWeight.put(otherVertex, edge.getWeight());
				}
				
			}
		}
		
		for(Map.Entry<String, Double> entry : vertexAndEdgeWeight.entrySet()) {
			if (entry.getValue() > maxWeight) {
				maxWeight = entry.getValue();
				result = entry.getKey();
				if (isLast) {
					minCut = minCut < maxWeight ? minCut : maxWeight;
				}
			}
		}
		
		if (mergeVertexChilds.containsKey(result)) {
			return mergeVertexChilds.get(result);
		}
		// 待合并的点并不是一个复合点，所以将它自己返回
		Set<Integer> resultSet = new HashSet<>();
		resultSet.add(Integer.parseInt(result));
		return resultSet;
	}
	

	public static void main(String[] args) {
		Graph graph = new Graph(8, false);
		graph.addEdge(0, 1, 2);
		graph.addEdge(0, 4, 3);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 4, 2);
		graph.addEdge(1, 5, 2);
		graph.addEdge(2, 3, 4);
		graph.addEdge(2, 6, 2);
		graph.addEdge(3, 6, 2);
		graph.addEdge(3, 7, 2);
		graph.addEdge(4, 5, 3);
		graph.addEdge(5, 6, 1);
		graph.addEdge(6, 7, 3);
		
		MinCut obj = new MinCut();
		obj.run(graph);
	}

}
