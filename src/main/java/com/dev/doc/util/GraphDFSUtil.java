package com.dev.doc.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 有向图的拓扑排序(深度优先搜索)</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月16日下午1:47:31</p>
 */
public class GraphDFSUtil {
	/**
	 * 
			*@name 返回指定节点的拓扑排序
			*@Description  
			*@CreateDate 2015年11月16日下午1:52:38
	 */
	public static List<Long> topo(List<Long> vertexList,Map<Long, Set<Long>> vertexRelation){
		List<Long> result = new ArrayList<Long>();
		if (CollectionUtils.isEmpty(vertexRelation)) {
			return vertexList;
		}
		
		int vertexCount = vertexList.size();
		int index = 0;
		Map<Long, Integer> vertexIndex = MapUtils.newMap();
		Graph graph = new Graph(vertexCount);
		//添加顶点
		for (Long vertex : vertexList) {
			graph.addVertex(new Vertex(vertex + ""));
			vertexIndex.put(vertex, index ++);
		}
		
		//添加边
		Set<Long> tempList = null;
		int currentIndex = 0;
		for (Long vertex : vertexList) {
			currentIndex = vertexIndex.get(vertex);
			
			tempList = vertexRelation.get(vertex);
			if (CollectionUtils.isEmpty(tempList)) {
				continue ;
			}
			
			for (Long temp : tempList) {
				graph.addEdge(vertexIndex.get(temp), currentIndex);
			}
		}
		
		List<Vertex> topoList = graph.topo();
		for (Vertex vertex : topoList) {
			result.add(Long.parseLong(vertex.getLabel()));
		}
				
		return result; 
	}
	
	public static void main(String[] args) {
		Graph theGraph = new Graph(9);
		Vertex v1 = new Vertex("c0");
		Vertex v2 = new Vertex("c1");
		Vertex v3 = new Vertex("c2");
		Vertex v4 = new Vertex("c3");
		Vertex v5 = new Vertex("c4");
		Vertex v6 = new Vertex("c5");
		Vertex v7 = new Vertex("c6");
		Vertex v8 = new Vertex("c7");
		Vertex v9 = new Vertex("c8");

		theGraph.addVertex(v1); // 0
		theGraph.addVertex(v2); // 1
		theGraph.addVertex(v3); // 2
		theGraph.addVertex(v4); // 3
		theGraph.addVertex(v5); // 4
		theGraph.addVertex(v6); // 5
		theGraph.addVertex(v7); // 6
		theGraph.addVertex(v8); // 7
		theGraph.addVertex(v9); // 8
		theGraph.addEdge(0, 6); // c0->c6
		theGraph.addEdge(0, 2); // c0->c2
		theGraph.addEdge(3, 5); // c3->c5
		theGraph.addEdge(3, 8); // c3->c8
		theGraph.addEdge(1, 2); // c1->c2
		theGraph.addEdge(1, 4); // c1->c4
		theGraph.addEdge(2, 3); // c2->c3
		theGraph.addEdge(6, 7); // c6->c7
		theGraph.addEdge(7, 8); // c7->c8
		theGraph.addEdge(4, 3); // c4->c3
		theGraph.addEdge(4, 5); // c4->c5
		// theGraph.addEdge(3, 1); // c4->c5
		System.out.print("Visits: ");
		List<Vertex> vl = theGraph.topo();
		System.out.println(vl);
	}
}

/**
 * 
		* <p>Title: 有向图的邻接矩阵实现</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月16日下午1:32:07</p>
 */
class Graph {
	/** 存放顶点的数组*/
	private Vertex vertexList[]; 
	
	/** 邻接矩阵*/
	private int adjMat[][];
	
	/** 当前的顶点数*/
	private int nVerts;

	
	public Graph(int vertextCount) {
		vertexList = new Vertex[vertextCount];

		adjMat = new int[vertextCount][vertextCount];
		nVerts = 0;
		for (int y = 0; y < vertextCount; y++){
			for (int x = 0; x < vertextCount; x++){
				adjMat[x][y] = 0;
			}
		}
	}

	/**
	 * 
			*@name 在图中添加一个顶点
			*@Description  
			*@CreateDate 2015年11月16日下午1:37:24
	 */
	public void addVertex(Vertex v){
		vertexList[nVerts++] = v;
	}

	/**
	 * 
			*@name 在图中增加一条边,从start到end
			*@Description  
			*@CreateDate 2015年11月16日下午1:38:22
	 */
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;

	}

	//返回v顶点所关联的邻结点
	private Set<Vertex> getNeighbors(Vertex v) {
		Set<Vertex> vSet = new HashSet<Vertex>();
		int index = getIndex(v);
		if (index == -1){
			return null;
		}
			
		for (int i = 0; i < nVerts; i++){
			if (adjMat[index][i] == 1){
				vSet.add(vertexList[i]);
			}
		}

		return vSet;
	}

	//返回顶点在vertexList数组中的索引
	private int getIndex(Vertex v) {
		for (int i = 0; i < nVerts; i++){
			if (vertexList[i] == v){
				return i;
			}
		}
		return -1;
	}

	//全部节点设为未访问
	private void setAllUnvisited() {
		Vertex v = null;
		int len = nVerts;
		for (int i = 0; i < len; i++) {
			v = vertexList[i];
			if (v.getState() != VertexState.UNVISITED) {
				v.setState(VertexState.UNVISITED);
			}
		}
	}

	private boolean containsVertex(Vertex v) {
		int index = getIndex(v);
		return index != -1;
	}

	private VertexState getState(Vertex v) {
		return v.getState();
	}

	private VertexState setState(Vertex v, VertexState state) {
		VertexState preState = v.getState();
		v.setState(state);
		return preState;
	}

	/**
	 * 
			*@name 深度优先遍历一个顶点
			*@Description  
			*@CreateDate 2015年11月16日下午1:39:57
	 */
	public List<Vertex> dfs(Vertex v, boolean checkCycle) {
		setAllUnvisited();
		List<Vertex> vList = new ArrayList<Vertex>();
		dfsHandler(v, checkCycle, vList);
		return vList;
	}

	private void dfsHandler(Vertex v, boolean checkCycle, List<Vertex> vList) {
		Set<Vertex> neighbors = null;
		if (!containsVertex(v)) {
			throw new IllegalStateException("不存在该顶点");
		}
		setState(v, VertexState.PASSED);

		neighbors = getNeighbors(v);
		VertexState state = null;
		for (Vertex neighbor : neighbors) {
			state = getState(neighbor);
			if (state == VertexState.UNVISITED) {//未遍历，
				// System.out.println(neighbor+",");
				dfsHandler(neighbor, checkCycle, vList);
			} else if (state == VertexState.PASSED && checkCycle) {
				throw new IllegalStateException("存在一个环");
			}
		}
		//访问结束设为已访问
		setState(v, VertexState.VISITED);
		vList.add(v);
		// System.out.println("++"+v);
	}

	/**
	 * 
			*@name 图的拓扑排序
			*@Description  
			*@CreateDate 2015年11月16日下午1:40:32
	 */
	public List<Vertex> topo() {
		List<Vertex> vList = new ArrayList<Vertex>();
		setAllUnvisited();
		for (int i = 0; i < nVerts; i++) {
			if (getState(vertexList[i]) == VertexState.UNVISITED) {
				try {
					dfsHandler(vertexList[i], true, vList);
				} 
				catch (IllegalStateException e) {
					throw new IllegalStateException("图有一个环");
				}
			}
		}
		Collections.reverse(vList);
		return vList;
	}
}

/**
 * 
		* <p>Title: 顶点类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月16日下午1:31:35</p>
 */
class Vertex {
	private String label;
	/** 顶点状态*/
	private VertexState state;

	public Vertex(String lab) {
		label = lab;
		state = VertexState.UNVISITED;
	}

	public VertexState getState() {
		return state;
	}

	public void setState(VertexState state) {
		this.state = state;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String toString() {
		return label;
	}
}

/**
 * 
		* <p>Title: 顶点状态</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月16日下午1:41:51</p>
 */
enum VertexState {
	/** 未访问*/
	UNVISITED,
	
	/** 已访问*/
	VISITED,
	
	/** 已经过*/
	PASSED;
}