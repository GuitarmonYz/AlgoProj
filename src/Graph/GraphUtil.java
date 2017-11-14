package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphUtil {
    /**
     * Load Graph from file
     * @param file_path
     * @return
     * @throws IOException
     */
    public static Graph loadGraph (String file_path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        String line = br.readLine();
        String[] split_array = line.split(" ");
        int numVertex = Integer.parseInt(split_array[0]);
        int numEdges = Integer.parseInt(split_array[1]);
        Graph g = new Graph(numVertex);
        for (int i = 1; i <= numVertex; i++){
            line = br.readLine();
            split_array = line.split(" ");
            for (String vertex : split_array){
                g.addEdge(i, Integer.parseInt(vertex));
            }
        }
        br.close();
        g.setNumOfEdges(g.numOfEdges()/2);
        //if numOfEdges does not match return null
        if (g.numOfEdges() != numEdges) g = null;
        return g;
    }


    public static int getLowerBoundMaxMatch (HashSet<Edge> unCoveredEdges, LinkedList<Edge>[] adj) {
        int lowBound = 0;
        HashSet<Integer> visitedVertices = new HashSet<>();
        for (Edge e : unCoveredEdges) {
            int u = e.endPoint();
            int v = e.endPoint(u);
            if (!visitedVertices.contains(u) && !visitedVertices.contains(v)){
                lowBound += 2;
                LinkedList<Edge> u_adj = adj[u];
                LinkedList<Edge> v_adj = adj[v];
                for (Edge u_e : u_adj){
                    visitedVertices.add(u_e.endPoint(u));
                }
                for (Edge v_e : v_adj) {
                    visitedVertices.add(v_e.endPoint(v));
                }
            }
        }
        return lowBound;
    }

    public static int getHighestDegree (HashSet<Integer> unUsedVertices, HashSet<Edge> unCoveredEdges, LinkedList<Edge>[] adj) {
        int maxDegree = 0;
        int targetVertex = -1;
        for (int i : unUsedVertices){
            int localMax = 0;
            for (Edge e : adj[i]) {
                if (unCoveredEdges.contains(e)){
                    localMax++;
                }
            }
            targetVertex = maxDegree < localMax ? i : targetVertex;
            maxDegree = maxDegree < localMax ? localMax : maxDegree;
        }
        return targetVertex;
    }

    public static VertexCover addToVertexCover(VertexCover vc, LinkedList<Edge>[] adj, int v){
        vc.candidate.add(v);
        vc.unUsedVertices.remove(v);
        LinkedList<Edge> v_adj = adj[v];
        for (Edge e : v_adj) {
            int u = e.endPoint(v);
            vc.unCoveredVertices.remove(u);
            vc.unCoveredEdges.remove(e);
        }
        return vc;
    }
}
