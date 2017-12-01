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
        long end;
        long elipsedTime;
        boolean[] bestSol = new boolean[]{};

        while (!stateStack.isEmpty()) {
//            if (System.nanoTime() >= end) break;
            VertexCover curVC = stateStack.poll();
            HashSet<Edge> unCoveredEdges = new HashSet<>(g.getEdges());
            HashSet<Integer> unUsedVertices = new HashSet<>();
            // get unCovered edges and unUsedVertices from bool array, return current vertex cover size
            int candidateSize = GraphUtil.getVCInfo(curVC.graphState ,unCoveredEdges, unUsedVertices, g.getAdj());

            while (!unCoveredEdges.isEmpty()) {
                // get node with highestDegree to branch
                int v = GraphUtil.getHighestDegree(unUsedVertices, unCoveredEdges, g.getAdj());
                unUsedVertices.remove(v);
                VertexCover VC1 = new VertexCover(curVC);
                HashSet<Edge> tmpRemoveEdge_vc1 = GraphUtil.addToVertexCover(VC1,v, unCoveredEdges, g.getAdj());
                // caluclate lower bound
                int LB1 = candidateSize + 1 + GraphUtil.getLowerBoundMaxMatch(unCoveredEdges, g.getAdj());
                VC1.lowerBound = LB1;
                unCoveredEdges.addAll(tmpRemoveEdge_vc1);
                HashSet<Edge> tmpRemoveEdge_vc2 = new HashSet<>();
                // vertex with all v's neighbours
                VertexCover VC2 = new VertexCover(curVC);
                //VC2.visitedVertices[v-1] = true;
                LinkedList<Edge> v_adj = g.getAdj()[v];
                // add v's neighbours to vertex cover
                for (Edge e : v_adj) {
                    tmpRemoveEdge_vc2.addAll(GraphUtil.addToVertexCover(VC2, e.endPoint(v), unCoveredEdges, g.getAdj()));
                    unUsedVertices.remove(e.endPoint(v));
                }
                // calculate lower bound
                int LB2 = candidateSize + v_adj.size() + GraphUtil.getLowerBoundMaxMatch(unCoveredEdges, g.getAdj());
                VC2.lowerBound = LB2;
                if (LB1 <= upperBound && LB2 <= upperBound) {
                    if (LB1 >= LB2) {
                        curVC = VC2;
                        candidateSize += v_adj.size();
                        stateStack.add(VC1);
                    } else {
                        curVC = VC1;
                        candidateSize += 1;
                        unCoveredEdges.addAll(tmpRemoveEdge_vc2);
                        unCoveredEdges.removeAll(tmpRemoveEdge_vc1);
                        for (Edge e : v_adj){
                            unUsedVertices.add(e.endPoint(v));
                        }
                        stateStack.add(VC2);
                    }
                } else if (LB1 <= upperBound && LB2 > upperBound){
                    curVC = VC1;
                    candidateSize += 1;
                    unCoveredEdges.addAll(tmpRemoveEdge_vc2);
                    unCoveredEdges.removeAll(tmpRemoveEdge_vc1);
                    for (Edge e : v_adj){
                        unUsedVertices.add(e.endPoint(v));
                    }
                } else if (LB2 <= upperBound && LB1 > upperBound){
                    curVC = VC2;
                    candidateSize += v_adj.size();
//                    for (Edge e: v_adj) {
//                        unUsedVertices.add(e.endPoint(v));
//                    }
                } else if (LB2 > upperBound && LB1 > upperBound){
                    break;
                }
            }
            if (unCoveredEdges.isEmpty()){
                int candidateSize_res = 0;
                for (boolean bool : curVC.graphState){
                    if (bool) candidateSize_res++;
                }
                if (candidateSize_res < upperBound) {
                    upperBound = candidateSize_res;
                    bestSol = curVC.graphState;
                }
                end = System.nanoTime();
                elipsedTime = end - start;
                System.out.println(candidateSize_res);
                System.out.println((double)elipsedTime / 1000000000.0);
            }
        }
        return bestSol;
    }
}
