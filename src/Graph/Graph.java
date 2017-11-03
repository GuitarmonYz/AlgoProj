package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Created by zhaoyan on 9/16/17.
 */
public class Graph {
    private int V;
    private int E;
    private LinkedList<Edge>[] adj;
    public Graph(int V){
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(int u, int v){
        Edge e = new Edge(u, v);
        adj[u].add(e);
        adj[v].add(e);
        E++;
    }

    public int numOfVertexes(){
        return V;
    }

    public int numOfEdges(){
        return E;
    }

    public LinkedList<Edge> getEdges(){
        LinkedList<Edge> list = new LinkedList<Edge>();
        for(int i = 0; i < V; i++){
            for (Edge e:adj[i]){
                list.add(e);
            }
        }
        return list;
    }
}

