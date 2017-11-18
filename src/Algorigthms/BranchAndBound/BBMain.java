package Algorigthms.BranchAndBound;

import java.util.*;

import Graph.Graph;
import Graph.GraphUtil;
import Graph.Edge;
import Graph.VertexCover;
public class BBMain {
    Graph g;
    PriorityQueue<VertexCover> stateStack;
    int upperBound;
    int cutOffTime;

    public BBMain(Graph g, int cutOffTime) {
        this.g = new Graph(g);
        this.stateStack  = new PriorityQueue<VertexCover>();
        this.upperBound = GraphUtil.getLowerBoundMaxMatch(g.getEdges(),g.getAdj()) * 2;
        boolean[] candidate = new boolean[g.numOfVertices()];
        VertexCover vc = new VertexCover(candidate, upperBound);
        stateStack.add(vc);
        this.cutOffTime = cutOffTime;
    }

    public boolean[] getVertexCover () {
        long start = System.nanoTime();
        //long end = start + cutOffTime;
        boolean[] bestSol = new boolean[]{};
        while (!stateStack.isEmpty()) {
//            if (System.nanoTime() >= end) break;
            VertexCover curVC = stateStack.poll();
            HashSet<Edge> unCoveredEdges = new HashSet<>(g.getEdges());
            HashSet<Integer> unUsedVertices = new HashSet<>();
            // get unCovered edges and unUsedVertices from bool array, return current vertex cover size
            int candidateSize = GraphUtil.getVCInfo(curVC.graphState, unCoveredEdges, unUsedVertices, g.getAdj());
            if (unCoveredEdges.isEmpty()) {
                // a solution has been found
                if (candidateSize < upperBound){
                    upperBound = candidateSize;
                    bestSol = curVC.graphState;
                }
                System.out.println(upperBound);
                long end = System.nanoTime();
                long elipsedTime = end - start;
                System.out.println((double)elipsedTime / 1000000000.0);
                //break;
            } else if (candidateSize < upperBound){
                // get the vertex to branch on
                int v = GraphUtil.getHighestDegree(unUsedVertices, unCoveredEdges, g.getAdj());
                // vertex cover with v
                VertexCover VC1 = new VertexCover(curVC);
                // get the edges that covered by adding v
                HashSet<Edge> tmpRemoveEdge = GraphUtil.addToVertexCover(VC1,v, unCoveredEdges, g.getAdj());
                // caluclate lower bound
                int LB1 = candidateSize + 1 + GraphUtil.getLowerBoundMaxMatch(unCoveredEdges, g.getAdj());
                VC1.lowerBound = LB1;
                // reverse adding v
                unCoveredEdges.addAll(tmpRemoveEdge);
                // vertex with all v's neighbours
                VertexCover VC2 = new VertexCover(curVC);
                LinkedList<Edge> v_adj = g.getAdj()[v];
                // add v's neighbours to vertex cover
                for (Edge e : v_adj) {
                    GraphUtil.addToVertexCover(VC2, e.endPoint(v), unCoveredEdges, g.getAdj());
                }
                // calculate lower bound
                int LB2 = candidateSize + v_adj.size() + GraphUtil.getLowerBoundMaxMatch(unCoveredEdges, g.getAdj());
                VC2.lowerBound = LB2;
                // if less then upperBound, add to vertex cover
                if (LB1 <= upperBound) {
                    stateStack.add(VC1);
                }
                if (LB2 <= upperBound) {
                    stateStack.add(VC2);
                }
            }
        }
        return bestSol;
    }
}
