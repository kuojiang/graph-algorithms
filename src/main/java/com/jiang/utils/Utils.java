package com.jiang.utils;

import java.util.List;
import java.util.Random;

import com.jiang.bean.Graph;
import com.jiang.bean.Vertex;

/**
 * @author cl04
 * @date   2018年5月30日
 */
public class Utils {
	public static int getRandomVertex(Graph graph) {
		List<Vertex> vertexes = graph.getVertexes();
		Random random = new Random();
		int index = random.nextInt(vertexes.size());
		return vertexes.get(index).getId();
	}

}
