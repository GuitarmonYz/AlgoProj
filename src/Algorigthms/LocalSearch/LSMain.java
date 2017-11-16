package Algorigthms.LocalSearch;
import java.util.*;
import Graph.Graph;
import Graph.Edge;
import Graph.IndexMinPQ;
public class LSMain {
    //new LS branch
    private final  Graph g;
    private final HashSet<Integer> vc;


    public LSMain(Graph g) {
        this.g = new Graph(g);
        this.vc = new HashSet<>();
    }

    public void LS1(){
        long startTime = System.nanoTime();
        EdgeDeletion();

        Iterator<Integer> iterator = this.vc.iterator();
        IndexMinPQ pq = new IndexMinPQ(this.g.numOfVertices()+1);
        while(iterator.hasNext()){
            Integer i = iterator.next();
            pq.insert(i,g.getDegree(i));
        }
        ArrayList<Integer> adjV;
        while(!pq.isEmpty()){
            int curV = pq.delMin();
            adjV = g.getAdjV(curV);
            if (this.vc.containsAll(adjV)){
                this.vc.remove(curV);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("VC = "+ this.vc.size()+"   Running Time = "+ (endTime-startTime)/1e9 + " s");

    }


    private void EdgeDeletion(){
        HashSet<Edge> edges = new HashSet<>(g.getEdges());
        while(!edges.isEmpty()){
            Iterator<Edge> iterator = edges.iterator();
            Edge e = iterator.next();
            this.vc.add(e.getTwoEnds()[0]);
            this.vc.add(e.getTwoEnds()[1]);
            edges.remove(e);
            List<Edge> adjE1 = g.getAdj()[e.getTwoEnds()[0]];
            edges.removeAll(adjE1);
            List<Edge> adjE2 = g.getAdj()[e.getTwoEnds()[1]];
            edges.removeAll(adjE2);

        }
    }




}