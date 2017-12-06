package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Zhao Yan and Wenqing Shen
 */
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
            if(!line.equals("")) {
                split_array = line.split(" ");
                for (String vertex : split_array) {
                    g.addEdge(i, Integer.parseInt(vertex));
                }
            }
        }
        br.close();
        g.setNumOfEdges(g.numOfEdges()/2);
        //if numOfEdges does not match return null
        if (g.numOfEdges() != numEdges) g = null;
        return g;
    }

    /**
     * get lower bound by max matching
     * @param unCoveredEdges
     * @param adj
     * @return
     */
    public static int getLowerBoundMaxMatch (HashSet<Edge> unCoveredEdges, LinkedList<Edge>[] adj) {
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

    /**
     * among unused vertices, get the one that has largest number of unCoveredEdges
     * @param unUsedVertices
     * @param unCoveredEdges
     * @param adj
     * @return
     */
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

    /**
     * get uncoveredEdges, unUsedVertices and size of vertex cover in each iteration
     * @param graphState
     * @param edges
     * @param unUsedVertices
     * @param adj
     * @return
     */
    public static int getVCInfo(boolean[] graphState, HashSet<Edge> edges, HashSet<Integer> unUsedVertices, LinkedList<Edge>[] adj){
        int candidateSize = 0;
        for (int i = 0; i < graphState.length; i++){
            if (graphState[i]){
                for (Edge e : adj[i+1]){
                    edges.remove(e);
                }
                candidateSize++;
            } else {
                unUsedVertices.add(i+1);
            }
        }
        return candidateSize;
    }

    /**
     * add a vertex to vertex cover, update related graph info
     * @param vc
     * @param v
     * @param unCoveredEdges
     * @param adj
     * @return
     */
    public static HashSet<Edge> addToVertexCover(VertexCover vc, int v, HashSet<Edge> unCoveredEdges, LinkedList<Edge>[] adj){
        HashSet<Edge> tmpRemoveEdge = new HashSet<>();
        vc.graphState[v-1] = true;
        for (Edge e: adj[v]){
            if (unCoveredEdges.contains(e)) {
                tmpRemoveEdge.add(e);
                unCoveredEdges.remove(e);
            }
        }
        return tmpRemoveEdge;
    }

}
