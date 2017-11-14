package Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Zhao Yan
 */
public class Graph {
    private int V;
    private int E;
    private final LinkedList<Edge>[] adj;
    private final HashSet<Edge> coveredEdges;
    private final HashSet<Edge> unCoveredEdges;
    private final HashSet<Integer> coveredVertices;
    private final HashSet<Integer> unCoveredVertices;

    /**
     * Init default Graph
     * @param V
     */
    public Graph(int V){
        this.V = V;
        adj = new LinkedList[V+1];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Edge>();
        }
        coveredEdges = new HashSet<Edge>();
        unCoveredEdges = new HashSet<Edge>();
        coveredVertices = new HashSet<>();
        unCoveredVertices = new HashSet<>();
        for (int i = 1; i <= V; i++){
            unCoveredVertices.add(i);
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
        this.coveredEdges = new HashSet<Edge>(g.coveredEdges);
        this.unCoveredEdges = new HashSet<Edge>(g.unCoveredEdges);
        this.coveredVertices = new HashSet<>(g.coveredVertices);
        this.unCoveredVertices = new HashSet<>(g.unCoveredVertices);
    }

    public void addEdge(int u, int v){
        Edge e = new Edge(u, v);
        adj[u].add(e);
        adj[v].add(e);
        this.unCoveredEdges.add(e);
        E++;
    }

    public int numOfVertices(){
        return V;
    }

    public int numOfEdges(){
        return E;
    }

    public LinkedList<Edge>[] getAdj(){
        return this.adj;
    }
    public HashSet<Edge> getEdges(){
        HashSet<Edge> edges = new HashSet<Edge>(this.coveredEdges);
        edges.addAll(this.unCoveredEdges);
        return edges;
    }
    public HashSet<Edge> getUnCoveredEdges () {
        return this.unCoveredEdges;
    }

    public HashSet<Edge> getCoveredEdges() {
        return this.coveredEdges;
    }

    public HashSet<Integer> getCoveredVertices() {
        return this.coveredVertices;
    }

    public HashSet<Integer> getUnCoveredVertices() {
        return this.unCoveredVertices;
    }

}

