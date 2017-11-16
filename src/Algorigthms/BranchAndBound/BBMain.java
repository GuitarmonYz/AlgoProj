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
        this.upperBound = GraphUtil.getLowerBoundMaxMatch(g.getUnCoveredEdges(),g.getAdj()) * 2;
        HashSet<Integer> candidate = new HashSet<>();
        VertexCover vc = new VertexCover(candidate, g.getUnCoveredVertices(), g.getUnCoveredEdges(), upperBound);
        stateStack.add(vc);
        this.cutOffTime = cutOffTime;
    }

    public String displayRes(HashSet<Integer> res){
        String resString = "";
        for (int i : res){
            resString += (Integer.toString(i) + ",");
        }
        return resString;
    }

    public HashSet<Integer> getVertexCover () {
        long start = System.nanoTime();
        long end = start + cutOffTime;
        HashSet<Integer> BestSol = new HashSet<>();
        while (!stateStack.isEmpty()) {
            //if (System.nanoTime() >= end) break;
            VertexCover curVC = stateStack.poll();
            if (curVC.unCoveredEdges.isEmpty()) {
//                upperBound = Math.min(curVC.candidate.size(), upperBound) ;
//                System.out.print(displayRes(curVC.candidate));
//                System.out.println(upperBound);
                if (curVC.candidate.size() < upperBound){
                    upperBound = curVC.candidate.size();
                    BestSol = new HashSet<>(curVC.candidate);
                }
            } else if (curVC.candidate.size() < upperBound){
                int v = GraphUtil.getHighestDegree(curVC.unUsedVertices, curVC.unCoveredEdges, g.getAdj());
                VertexCover VC1 = new VertexCover(curVC);
                GraphUtil.addToVertexCover(VC1,g.getAdj(),v);
                int LB1 = VC1.candidate.size() + GraphUtil.getLowerBoundMaxMatch(VC1.unCoveredEdges, g.getAdj());
                VC1.lowerBound = LB1;
                VertexCover VC2 = new VertexCover(curVC);
                LinkedList<Edge> v_adj = g.getAdj()[v];
                for (Edge e : v_adj) {
                    GraphUtil.addToVertexCover(VC2, g.getAdj(), e.endPoint(v));
                }
                int LB2 = VC2.candidate.size() + GraphUtil.getLowerBoundMaxMatch(VC2.unCoveredEdges, g.getAdj());
                VC2.lowerBound = LB2;
                if (LB1 <= upperBound && LB2 <= upperBound) {
                    stateStack.add(VC1);
                    stateStack.add(VC2);
                }
                if (LB1 <= upperBound) {
                    stateStack.add(VC1);
                }
                if (LB2 <= upperBound) {
                    stateStack.add(VC2);
                }
            }
        }
        return BestSol;
    }
}
