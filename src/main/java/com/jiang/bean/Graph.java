package com.jiang.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cl04
 * @date   2018年5月29日
 */
public class Graph {
	private List<Vertex> vertexes = new ArrayList<>();
	private Map<Integer, List<Edge>> adj = new HashMap<>();
	private boolean directed;

	public void addVertex(int vertexId) {
		vertexes.add(new Vertex(vertexId));
	}
	
	public Graph(int V, boolean directed) {
		for(int i = 0; i < V; i++) {
			vertexes.add(new Vertex(i));
		}
		
		this.directed = directed;
		
	}
	
	public void addEdge(int fromVertexId, int toVertexId, double weight) {
		if (adj.containsKey(fromVertexId)) {
			adj.get(fromVertexId).add(new Edge(fromVertexId, toVertexId, weight));
		} else {
			List<Edge> list = new ArrayList<>();
			list.add(new Edge(fromVertexId, toVertexId, weight));
			adj.put(fromVertexId, list);
		}
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}
	
	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	
	public Map<Integer, List<Edge>> getAdj() {
		return adj;
	}

	public void setAdj(Map<Integer, List<Edge>> adj) {
		this.adj = adj;
	}

	

}
