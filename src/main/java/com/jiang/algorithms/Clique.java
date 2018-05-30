package com.jiang.algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiang.bean.Edge;
import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * Bron-Kerbosch算法寻找最大团
 * @author cl04
 * @date   2018年5月30日
 */
public class Clique {
	public void run(Graph graph) {
		List<Vertex> vertexes = graph.getVertexes();
		Map<Integer, List<Edge>> adj = graph.getAdj();
		
		Set<Integer> r = new HashSet<>();
		Set<Integer> p = new HashSet<>();
		Set<Integer> x = new HashSet<>();
		
		for(Vertex vertex : vertexes) {
			p.add(vertex.getId());
		}
		
		run(r, p, x, adj);
		
		
	}

	/**
	 * @param r
	 * @param p
	 * @param x
	 */
	private void run(Set<Integer> r, Set<Integer> p, Set<Integer> x, Map<Integer, List<Edge>> adj) {
		if (p.isEmpty() && x.isEmpty()) {
			System.out.println(r);
			r.clear();
			return;
		}
		
		Integer pivot = getPivot(p, x);
		Set<Integer> vertexAdj = getAdjVertex(pivot, adj);
		Set<Integer> p_copy = new HashSet<>(p);;
		p_copy.removeAll(vertexAdj);
		
		Iterator<Integer> iterator = p_copy.iterator();
		while(iterator.hasNext()) {
			Set<Integer> nextR = new HashSet<>(r);
			Integer v = iterator.next();
			nextR.add(v);
			Set<Integer> vAdj = getAdjVertex(v, adj);
			Set<Integer> nextP = new HashSet<>(p);
			nextP.retainAll(vAdj);
			Set<Integer> nextX = new HashSet<>(x);
			nextX.retainAll(vAdj);
			run(nextR, nextP, nextX, adj);
			p.remove(v);
			x.add(v);
			
		}
		
	}

	/**
	 * @param v
	 * @param adj
	 * @return
	 */
	private Set<Integer> getAdjVertex(Integer v, Map<Integer, List<Edge>> adj) {
		List<Edge> vertexEdge = adj.get(v);
		Set<Integer> vertexAdj = new HashSet<>();
		for(Edge edge : vertexEdge) {
			vertexAdj.add(edge.getToVertexId());
		}
		
		return vertexAdj;
	}

	/**
	 * @param p
	 * @param x
	 * @return
	 */
	private Integer getPivot(Set<Integer> p, Set<Integer> x) {
		Set<Integer> pAndx = new HashSet<>();
		pAndx.addAll(p);
		pAndx.addAll(x);
		for(Integer id : pAndx)
			return id;
		
		return null;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(10, false);
		
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(0, 3, 1);
		graph.addEdge(0, 4, 1);
		graph.addEdge(0, 5, 1);
		graph.addEdge(0, 6, 1);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 3, 1);
		graph.addEdge(2, 4, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 1);
		graph.addEdge(2, 7, 1);
		graph.addEdge(2, 8, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(5, 6, 1);
		graph.addEdge(7, 8, 1);
		graph.addEdge(8, 9, 1);
		
		Clique obj = new Clique();
		obj.run(graph);
	}

}
