package Graph;

import java.util.*;

/**
 * @author Zhao Yan
 */
public class Graph {
    private int V;
    private int E;
    private final LinkedList<Edge>[] adj;
    private final HashSet<Integer> vertices;
    private final HashSet<Edge> edges;
    private final HashSet<Integer>[] adjV;


    /**
     * Init default Graph
     * @param V
     */
    public Graph(int V){
        this.V = V;
        //the first element in adj is dummy
        adj = new LinkedList[V+1];
        adjV = new HashSet [V+1];
        for (int i = 0; i <= V; i++) {
            adj[i] = new LinkedList<Edge>();
            adjV[i] = new HashSet<>();
        }
        edges = new HashSet<Edge>();
        vertices = new HashSet<>();
        for (int i = 1; i <= V; i++){
            vertices.add(i);
        }
    }

    /**
     * Init by deep copy given graph
     * @param g
     */
    public Graph(Graph g) {
        this.V = g.V;
        this.E = g.E;
        this.adj = Arrays.copyOf(g.adj, g.adj.length);
        this.adjV = Arrays.copyOf(g.adjV, g.adjV.length);
        this.edges = new HashSet<Edge>(g.edges);
        this.vertices = new HashSet<>(g.vertices);
    }

    public void addEdge(int u, int v){
        Edge e = new Edge(u, v);
        adj[u].add(e);
        this.edges.add(e);
        adjV[u].add(v);
        adjV[v].add(u);
        E++;
    }

    /**
     * return the degree of current vertex
     * @param curV
     * @return
     */
    public int getDegree(int curV){
        return this.adj[curV].size();
    }

    /**
     * get adjacent vertex array
     * @param curV
     * @return
     */
    public ArrayList<Integer> getAdjV(int curV){
        //get adjacent vertex array
        ArrayList<Integer> adjV = new ArrayList<>();
        for (Edge e : adj[curV]){
            adjV.add(e.endPoint(curV));
        }
        return adjV;
    }

    public int numOfVertices(){
        return V;
    }
    public void setNumOfEdges(int E) {
        this.E = E;
    }
    public int numOfEdges(){
        return E;
    }

    public LinkedList<Edge>[] getAdj(){
        return this.adj;
    }
    public HashSet<Edge> getEdges(){
        return this.edges;
    }
    public HashSet<Integer> getVertices() {
        return vertices;
    }
    public HashSet<Integer> [] getAdjV_hashset(){return this.adjV;}
}

