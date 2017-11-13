package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by zhaoyan on 9/16/17.
 */
public class Graph {
    private int V;
    private int E;
    private final LinkedList<Edge>[] adj;
    private final HashSet<Edge> coveredEdges;
    private final HashSet<Edge> unCoveredEdges;
    private final HashSet<Integer> coveredVertices;
    private final HashSet<Integer> unCoveredVertices;
    public Graph(int V){
        this.V = V;
        adj = new LinkedList[V];
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

    public void addEdge(int u, int v){
        Edge e = new Edge(u, v);
        adj[u].add(e);
        adj[v].add(e);
        this.unCoveredEdges.add(e);
        E++;
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

    public HashSet<Integer> getUnCoveredVerticess() {
        return this.unCoveredVertices;
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
}

