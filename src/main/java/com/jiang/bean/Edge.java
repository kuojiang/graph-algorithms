package com.jiang.bean;

/**
 * @author cl04
 * @date   2018年5月29日
 */
public class Edge {
	private double weight = 1;
	private int fromVertexId;
	private int toVertexId;
	
	Edge(int fromVertexId, int toVertexId, double weight) {
		this.setFromVertexId(fromVertexId);
		this.setToVertexId(toVertexId);
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getFromVertexId() {
		return fromVertexId;
	}
	public void setFromVertexId(int fromVertexId) {
		this.fromVertexId = fromVertexId;
	}
	public int getToVertexId() {
		return toVertexId;
	}
	public void setToVertexId(int toVertexId) {
		this.toVertexId = toVertexId;
	}
	

	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", fromVertexId=" + fromVertexId + ", toVertexId=" + toVertexId + "]";
	}
	
}
