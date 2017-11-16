package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        //HashSet<Integer> vertexLBApprox = new HashSet<>();
        int lowerBound = 0;
        HashSet<Edge> coveredEdges = new HashSet<Edge>();
        int numEdge = unCoveredEdges.size();
        for (Edge e : unCoveredEdges) {
            if (numEdge == coveredEdges.size()) break;
            if (!coveredEdges.contains(e)){
                int u = e.endPoint();
                int v = e.endPoint(u);
                lowerBound+=2;
                coveredEdges.addAll(adj[u]);
                coveredEdges.addAll(adj[v]);
                coveredEdges.add(e);
            }
        }
        return lowerBound/2;
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
            //int u = e.endPoint(v);
            //vc.unCoveredVertices.remove(u);
            vc.unCoveredEdges.remove(e);
        }
        return vc;
    }
}
