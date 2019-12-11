package vip.jianjieming.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author jianjieming
 * @date 2019/11/25 14:29
 */
public class Graph {
    // 存储顶点集合
    private ArrayList<String> vertexList;
    // 存储图对应的邻结矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 记录某个顶点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 节点的个数
        int n = 5;
        String vertexs[] = {"A", "B", "C", "D", "E"};
        // 创建图
        Graph graph = new Graph(n);
        // 添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 显示邻结矩阵
        graph.showGraph();

        System.out.println("深度遍历: ");
        graph.dfs();
    }

    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    /**
     * 深度优先遍历算法
     *
     * @param i 第一次就是0
     */
    public void dfs(boolean[] isVisited, int i) {
        // 首先访问该节点,输出
        System.out.print(getValueByIndex(i) + "->");
        // 将该节点设置为已经访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        // 说明有邻接节点
        while (w != -1) {
            // 如果没有访问过
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 对dfs 进行重载,遍历所有的节点,并进行dfs
     */
    public void dfs() {
        // 遍历所有的节点, 进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 得到第一个邻接节点的下标 w
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 插入顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1 表示点的下标,即是第几个顶点 'A'-'B' 'A'->0 'B'->1
     * @param v2 表示第二个顶点对应的下标
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 返回节点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点i(下标)对应的数据 0->'A' 1->'B'
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

}
