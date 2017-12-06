package Algorigthms.BranchAndBound;

import java.util.*;

import FileUtil.SolWriter;
import Graph.Graph;
import Graph.GraphUtil;
import Graph.Edge;
import Graph.VertexCover;

/**
 * @author ZhaoYan
 */
public class BBMain {
    Graph g;
    PriorityQueue<VertexCover> stateStack;
    int upperBound;
    long cutOffTime;

    public BBMain(Graph g, long cutOffTime) {
        this.g = new Graph(g);
        this.stateStack  = new PriorityQueue<VertexCover>();
        this.upperBound = GraphUtil.getLowerBoundMaxMatch(g.getEdges(),g.getAdj()) * 2;
        boolean[] candidate = new boolean[g.numOfVertices()];
        VertexCover vc = new VertexCover(candidate, upperBound);
        stateStack.add(vc);
        this.cutOffTime = cutOffTime * (long) 1e9;
    }

    public void getVertexCover () {
        long start = System.nanoTime();
        long end = start + cutOffTime;
        long currentTime;
        long elipsedTime;
        boolean[] bestSol = new boolean[]{};
        try {
            while (!stateStack.isEmpty()) {
                if (System.nanoTime() >= end) break;
                VertexCover curVC = stateStack.poll();
                HashSet<Edge> unCoveredEdges = new HashSet<>(g.getEdges());
                HashSet<Integer> unUsedVertices = new HashSet<>();
                // get unCovered edges and unUsedVertices from bool array, return current vertex cover size
                int candidateSize = GraphUtil.getVCInfo(curVC.graphState ,unCoveredEdges, unUsedVertices, g.getAdj());

                while (!unCoveredEdges.isEmpty()) {
                    if (System.nanoTime() >= end) break;
                    // get node with highestDegree to branch
                    int v = GraphUtil.getHighestDegree(unUsedVertices, unCoveredEdges, g.getAdj());
                    unUsedVertices.remove(v);
                    VertexCover VC1 = new VertexCover(curVC);
                    HashSet<Edge> tmpRemoveEdge_vc1 = GraphUtil.addToVertexCover(VC1,v, unCoveredEdges, g.getAdj());
                    // calculate lower bound
                    int LB1 = candidateSize + 1 + GraphUtil.getLowerBoundMaxMatch(unCoveredEdges, g.getAdj());
                    VC1.lowerBound = LB1;
                    //unCoveredEdges.addAll(tmpRemoveEdge_vc1);
                    HashSet<Edge> tmpRemoveEdge_vc2 = new HashSet<>();
                    // vertex with all v's neighbours
                    VertexCover VC2 = new VertexCover(curVC);
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
                    } else if (LB2 > upperBound && LB1 > upperBound){
                        curVC = VC2;
                        candidateSize += v_adj.size();
                        break;
                    }
                }
                if (unCoveredEdges.isEmpty()){
                    currentTime = System.nanoTime();
                    elipsedTime = currentTime - start;
                    if (candidateSize < upperBound) {
                        upperBound = candidateSize;
                        bestSol = curVC.graphState;
                        SolWriter.writeTrace((double)elipsedTime/1e9, candidateSize);
                    }
                    System.out.println(candidateSize);
                    System.out.println((double)elipsedTime / 1000000000.0);
                }
            }
            HashSet<Integer> res = new HashSet<>();
            for (int i = 0; i < bestSol.length; i++) {
                if (bestSol[i]) res.add(i+1);
            }
            SolWriter.writeSol(res);
        }catch (OutOfMemoryError e){
            HashSet<Integer> res = new HashSet<>();
            for (int i = 0; i < bestSol.length; i++) {
                if (bestSol[i]) res.add(i+1);
            }
            SolWriter.writeSol(res);
        }

    }
}
