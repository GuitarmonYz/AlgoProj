package Algorigthms.BranchAndBound;

import java.util.ArrayList;
import java.util.LinkedList;

import Graph.Graph;
import Graph.GraphUtil;
import Graph.Edge;
public class BBMain {
    Graph g;
    ArrayList<Integer> candidateList;
    int lowerBound;
    public BBMain(Graph g) {
        this.g = new Graph(g);
        lowerBound = GraphUtil.getLowerBoundMaxMatch(this.g);
    }
    public void getVertexCover () {
        int v = g.getHighestDegree();
        this.g.visitVertex(v);
        int LB1 = 1 + GraphUtil.getLowerBoundMaxMatch(this.g);
        this.g.undoVisitVertex(v);
        LinkedList<Edge> v_adj = g.getAdj()[v];
        for (Edge e : v_adj) {
            this.g.visitVertex(e.endPoint(v));
        }
        int LB2 = v_adj.size() + GraphUtil.getLowerBoundMaxMatch(this.g);
        if (LB1 <= lowerBound && LB2 <= lowerBound) {

        }else if (LB1 <= lowerBound) {
            for (Edge e : v_adj) {
                this.g.undoVisitVertex(e.endPoint(v));
            }
            this.g.visitVertex(v);
            candidateList.add(v);
        }else if (LB2 <= lowerBound) {
            for (Edge e : v_adj) {
                candidateList.add(e.endPoint(v));
            }
        }
    }
}
