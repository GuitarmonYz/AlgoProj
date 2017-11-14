package Algorigthms.LocalSearch;

import Graph.Graph;
import Graph.Edge;
import Graph.GraphUtil;
import Graph.IndexMinPQ;

import java.util.*;

public class LSMain {
    //new LS branch
    //LS1 Edge Deletion + delete redundant ==>hill climbing
    //LS2 Edge Deletion + delete redundant + 2-improvement
    Graph g;
    HashSet<Integer> candidateList;

    public LSMain(Graph g) {
        this.g = new Graph(g);
    }

    public int LS1(){
        EdgeDeletion();
        HashSet<Integer> vc = g.getCoveredVertices();
        Iterator<Integer> iterator = vc.iterator();
        IndexMinPQ pq = new IndexMinPQ(vc.size());
        while(iterator.hasNext()){
            Integer i = iterator.next();
            pq.insert(i,g.getDegree(i));
        }
        ArrayList<Integer> adjV;
        while(!pq.isEmpty()){
            int curV = pq.delMin();
            adjV = g.getAdjV(curV);
            if (vc.containsAll(adjV)){
                g.visitVertex(curV);
                vc.remove(curV);
            }
        }

        return g.getCoveredVertices().size();

    }


    public void EdgeDeletion(){
        HashSet<Edge> edges = new HashSet<>(g.getEdges());

        int[] uv;
        while(!g.getUnCoveredEdges().isEmpty()){
            edges = g.getUnCoveredEdges();
            Edge e = edges.iterator().next();
            uv = e.getTwoEnds();
            g.visitVertex(uv[0]);
            g.visitVertex(uv[1]);
        }
    }




}
