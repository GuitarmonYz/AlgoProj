package Graph;

import java.util.ArrayList;
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
        for (int i = 1; i <= V; i++) {
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
        this.adj = new LinkedList[V+1];
        this.coveredEdges = new HashSet<Edge>();
        this.unCoveredEdges = new HashSet<Edge>();
        this.coveredVertices = new HashSet<>();
        this.unCoveredVertices = new HashSet<>();
        for (int i = 1; i <= V ; i ++) {
            this.adj[i] = g.getAdj()[i];
        }
        for (Edge e : g.getCoveredEdges()) {
            this.coveredEdges.add(e);
        }
        for (Edge e : g.getUnCoveredEdges()) {
            this.unCoveredEdges.add(e);
        }
        for (int v : g.getCoveredVertices()) {
            this.coveredVertices.add(v);
        }
        for (int v : g.getUnCoveredVertices()) {
            this.unCoveredVertices.add(v);
        }

    }

    public void addEdge(int u, int v){
        Edge e = new Edge(u, v);
        adj[u].add(e);
        //adj[v].add(e);
        this.unCoveredEdges.add(e);
        E++;
    }

    public void visitVertex(int v){
        this.coveredVertices.add(v);
        this.unCoveredVertices.remove(v);
        if (this.unCoveredEdges.size() < this.adj[v].size()){
            for (Edge e : this.unCoveredEdges) {
                if (e.contains(v)) {
                    this.coveredEdges.add(e);
                    this.unCoveredEdges.remove(e);
                }
            }
        } else {
            for (Edge e : this.adj[v]) {
                if (this.unCoveredVertices.contains(e.endPoint(v))) {
                    this.coveredEdges.add(e);
                    this.unCoveredEdges.remove(e);
                }
            }
        }
    }

    public void undoVisitVertex (int v) {
        this.unCoveredVertices.add(v);
        this.coveredVertices.remove(v);
        for (Edge e : this.adj[v]) {
            if (!this.coveredVertices.contains(e.endPoint(v))) {
                this.coveredEdges.remove(e);
                this.unCoveredEdges.add(e);
            }
        }
    }

    public int getHighestDegree () {
        int maxDegree = 0;
        int targetVertex = -1;
        for (int i : unCoveredVertices){
            int localMax = 0;
            for (Edge e : this.adj[i]) {
                if (!this.coveredVertices.contains(e.endPoint(i))){
                    localMax++;
                }
            }
            maxDegree = maxDegree < localMax ? localMax : maxDegree;
            targetVertex = maxDegree < localMax ? i : targetVertex;
        }
        return targetVertex;
    }

    public int getDegree(int curV){
        //return the degree of current vertex
        return this.adj[curV].size();
    }

    public ArrayList<Integer> getAdjV(int curV){
        //get adjacent vertex array
        ArrayList<Integer> adjV = new ArrayList<>();
        for (Edge e : adj[curV]){
            adjV.add(e.endPoint(curV));
        }

        return adjV;
    }

    public int getVisitedVertexWithLowestDegree () {
        //For current VC solution, get the V in VC with min degree
        int minDegree = 0;
        int targetVertex = -1;
        for (int i : coveredVertices){
            int localMin = 0;
            for (Edge e : this.adj[i]) {
                if (!coveredVertices.contains(e.endPoint(i))){
                    localMin++;
                }
            }
            minDegree = minDegree > localMin ? localMin : minDegree;
            targetVertex = minDegree < localMin ? i : targetVertex;
        }
        return targetVertex;
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

