package com.jiang.bean;

/**
 * @author cl04
 * @date   2018年5月29日
 */
public class Vertex {
	private int id;
	
	Vertex(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vertex [id=" + id + "]";
	}
	
}
