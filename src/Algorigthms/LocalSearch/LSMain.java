package Algorigthms.LocalSearch;
import java.util.*;

import FileUtil.SolWriter;
import Graph.Graph;
import Graph.Edge;
import Graph.IndexMinPQ;

/**
 * @author WenqingShen
 */
public class LSMain {
    //new LS branch
    //LS1 Edge Deletion + delete redundant ==>hill climbing
    //LS2 Edge Deletion + delete redundant + 2-improvement
    private final  Graph g;
    private final HashSet<Integer> vc;
    private final HashMap<Integer,Integer> tightness;
    private final long cutoff_time;
    private final long randSeed;
    boolean isLS1 = true;
    long curTime2 = System.nanoTime();
    long curTime1 = System.nanoTime();
    long totalTime=0;
    long startTime = System.nanoTime();


    public LSMain(Graph g, long time, long seed) {
        this.g = new Graph(g);
        this.vc = new HashSet<>();
        this.tightness = new HashMap<>(g.numOfVertices());
        this.cutoff_time = time;
        this.randSeed = seed;
    }

    public void LS3(){
        //System.out.println("LS2");

        long startTime = System.nanoTime();
        HashSet<Edge> edges = new HashSet<>(g.getEdges());
        LS1();
        //System.out.println(checkVC());
        calTight();


        boolean next = true;
        while (next){
            LinkedList<Integer> tight1 = new LinkedList<>();
            for (Map.Entry<Integer,Integer> entry : tightness.entrySet()) {
                if (entry.getValue()==1) {
                    tight1.add(entry.getKey());
                }
            }
            boolean run = true;
            int i =0;
            int j, k;
            int vi, vj;
            while ( i<tight1.size() && run){
                j = i+1;
                while( j<tight1.size() && run){

                    vi = tight1.get(i); vj = tight1.get(j);
                    k = 1;
                    if (!edges.contains(new Edge(vi,vj)) ){
                        while(k<=g.numOfVertices() && run){
                            if((!vc.contains(k)) && edges.contains(new Edge(vi,k)) && edges.contains(new Edge(vj,k))){
                                this.vc.remove(vi);
                                this.vc.remove(vj);
                                this.vc.add(k);
                                run = false;
                                updateTight(vi,vj,k);
                                long curTime = System.nanoTime();
                                System.out.println("VC = "+ this.vc.size()+"   " +
                                        "Running Time = "+ (curTime-startTime)/1e9 + " s" +
                                        " replace " +vi +" & " +vj +" with " +k);
                                //System.out.println(checkVC());

                            }
                            k++;
                        }
                    }
                    j++;
                }
                i++;
            }
            if(run) next=false;
        }
        long endTime = System.nanoTime();
        System.out.println("LS2  VC = "+ this.vc.size()+"   Running Time = "+ (endTime-startTime)/1e9 + " s "+checkVC());

    }
    public void LS2(){
        //System.out.println("LS2");
        this.isLS1 = false;
        curTime2 = System.nanoTime();
        curTime1 = System.nanoTime();

        startTime = System.nanoTime();
        HashSet<Edge> edges = new HashSet<>(g.getEdges());
        LS1();
        //System.out.println(checkVC());
        calTight();

        boolean next = true;
        while (next){
            LinkedList<Integer> tight1 = new LinkedList<>();
            for (Map.Entry<Integer,Integer> entry : tightness.entrySet()) {
                if (entry.getValue()==1) {
                    tight1.add(entry.getKey());
                }
            }
            Collections.shuffle(tight1,new Random(this.randSeed));
            boolean run = true;
            int i =0;
            int j, k;
            int vi, vj;
            while ( i<tight1.size() && run){
                j = i+1;
                vi = tight1.get(i);
                HashSet<Integer> vi_adj = this.g.getAdjV_hashset()[vi];
                while( j<tight1.size() && run){
                    vj = tight1.get(j);
                    HashSet<Integer> vj_adj = this.g.getAdjV_hashset()[vj];
                    Iterator<Integer> vi_neigh = vi_adj.iterator();
                    while(vi_neigh.hasNext()){
                        k = vi_neigh.next();
                        if(vj_adj.contains(k) && !vj_adj.contains(vi) && !vc.contains(k)){
                            this.vc.remove(vi);
                            this.vc.remove(vj);
                            this.vc.add(k);

                            curTime2=System.nanoTime();
                            totalTime = curTime2-curTime1+totalTime;
                            SolWriter.writeTrace((double)((totalTime)/1e9),vc.size());
                            curTime1=System.nanoTime();

                            run = false;
                            updateTight(vi,vj,k);
                            long curTime = System.nanoTime();
                            //System.out.println("VC = "+ this.vc.size()+"   " +
                            //        "Running Time = "+ (curTime-startTime)/1e9 + " s" +
                            //        " replace " +vi +" & " +vj +" with " +k);
                            //System.out.println(checkVC());

                        }
                    }
/*                    if (!this.g.getAdjV_hashset()[vi].contains(vj) ){
                        while(k<=g.numOfVertices() && run){
                            if((!vc.contains(k)) && edges.contains(new Edge(vi,k)) && edges.contains(new Edge(vj,k))){
                                this.vc.remove(vi);
                                this.vc.remove(vj);
                                this.vc.add(k);
                                run = false;
                                updateTight(vi,vj,k);
                                long curTime = System.nanoTime();
                                System.out.println("VC = "+ this.vc.size()+"   " +
                                        "Running Time = "+ (curTime-startTime)/1e9 + " s" +
                                        " replace " +vi +" & " +vj +" with " +k);
                                //System.out.println(checkVC());

                            }
                            k++;
                        }
                    }*/
                    j++;
                }
                i++;
            }
            if(run) next=false;
        }

        long endTime = System.nanoTime();
        System.out.println("LS2  VC = "+ this.vc.size()+"   Running Time = "+ (totalTime)/1e9 + " s "+checkVC());
        SolWriter.writeSol(vc);

    }

    public void calTight(){
        for(int i=1; i<=this.g.numOfVertices(); i++){
            int cur_tight = 0;
            if(vc.contains(i)){
                //ArrayList<Integer> adj_i = this.g.getAdjV(i);
                HashSet<Integer> adj_i = this.g.getAdjV_hashset()[i];
                Iterator<Integer> iterator = adj_i.iterator();
                while(iterator.hasNext()){
                    int j = iterator.next();
                    if(!vc.contains(j)) cur_tight++;
                }
            }
            tightness.put(i,cur_tight);
        }

    }
    public boolean checkVC(){
        /*for(int i=1; i<=this.g.numOfVertices();i++){
            if (g.getAdjV(i).isEmpty()) continue;
            if( !this.vc.contains(i)){
                boolean coverI = false;
                ArrayList<Integer> adj_i = this.g.getAdjV(i);
                Iterator<Integer> iterator = adj_i.iterator();
                while(iterator.hasNext()){
                    int j = iterator.next();
                    if(this.vc.contains(j)) {
                        coverI = true;
                        break;
                    }
                }
                if (!coverI) {
                    return false;
                }

            }
        }*/

        HashSet<Edge> edges = new HashSet<>(g.getEdges());
        Iterator<Edge> iterator = edges.iterator();
        while(iterator.hasNext()){
            Edge e = iterator.next();
            int i = e.getTwoEnds()[0];
            int j = e.getTwoEnds()[1];
            if(!this.vc.contains(i)  && !this.vc.contains(j)) return false;
        }


        return true;
    }

    public void updateTight(int vi,int vj, int vx){
        int cur_tight = 0;
        if(vc.contains(vx)){
            //ArrayList<Integer> adj_i = this.g.getAdjV(vx);
            HashSet<Integer> adj_i = this.g.getAdjV_hashset()[vx];
            Iterator<Integer> iterator = adj_i.iterator();
            while(iterator.hasNext()){
                int j = iterator.next();
                if(!vc.contains(j)) cur_tight++;
            }
        }

        Iterator<Integer> iterator = vc.iterator();
        HashSet<Edge> edges = new HashSet<>(g.getEdges());
        HashSet<Integer> adj_v;
        while(iterator.hasNext()){
            int v_in_vc = iterator.next();
            //if(edges.contains(new Edge(v_in_vc,vi))) tightness.put(v_in_vc,tightness.get(v_in_vc)+1);
            //if(edges.contains(new Edge(v_in_vc,vj))) tightness.put(v_in_vc,tightness.get(v_in_vc)+1);
            //if(edges.contains(new Edge(v_in_vc,vx))) tightness.put(v_in_vc,tightness.get(v_in_vc)-1);
            adj_v = this.g.getAdjV_hashset()[v_in_vc];
            if(adj_v.contains(vi)) tightness.put(v_in_vc,tightness.get(v_in_vc)+1);
            if(adj_v.contains(vj)) tightness.put(v_in_vc,tightness.get(v_in_vc)+1);
            if(adj_v.contains(vx)) tightness.put(v_in_vc,tightness.get(v_in_vc)-1);
        }
        tightness.put(vx,cur_tight);
        tightness.put(vi,0);
        tightness.put(vj,0);

    }

    public int getDegreeInCurrentVC(int i){
        Iterator<Integer> iterator = this.vc.iterator();
        int degree=0;
        while(iterator.hasNext()){
            if(g.getEdges().contains(new Edge(i,iterator.next()))) degree ++;
        }
        return degree;
    }

    public void LS1(){
        startTime = System.nanoTime();
        curTime1=System.nanoTime();
        curTime2=System.nanoTime();
        totalTime=0;
        EdgeDeletion();
        //System.out.println("VC = "+ this.vc.size());

        Iterator<Integer> iterator = this.vc.iterator();
        IndexMinPQ pq = new IndexMinPQ(this.g.numOfVertices()+1);
        while(iterator.hasNext()){
            Integer i = iterator.next();
            pq.insert(i,g.getDegree(i));
            //pq.insert(i,getDegreeInCurrentVC(i));
        }
        //ArrayList<Integer> adjV;
        HashSet<Integer> adjV;
        while(!pq.isEmpty()){
            int min_degree = (int)pq.minKey();
            int curV;
            LinkedList<Integer> curVList = new LinkedList<>();
            while(!pq.isEmpty() && min_degree == (int)pq.minKey() ) {
                curV = pq.delMin();
                curVList.add(curV);
            }
            Collections.shuffle(curVList,new Random(this.randSeed));
            Iterator<Integer> iterator1 = curVList.iterator();
            while (iterator1.hasNext()){
                curV = iterator1.next();
                //adjV = g.getAdjV(curV);
                adjV = g.getAdjV_hashset()[curV];
                if (this.vc.containsAll(adjV)){
                    this.vc.remove(curV);
                    curTime2=System.nanoTime();
                    totalTime = curTime2-curTime1+totalTime;
                    SolWriter.writeTrace((double)((totalTime)/1e9),vc.size());
                    curTime1=System.nanoTime();
                }
            }

        }

/*        for(int i=1;i<=this.g.numOfVertices();i++){
            if(g.getAdjV(i).isEmpty()) {
                this.vc.add(i);
            }

        }*/

        long endTime = System.nanoTime();
        System.out.println("LS1  VC = "+ this.vc.size()+"   Running Time = "+ (totalTime)/1e9 + " s "+checkVC());
        //System.out.println(checkVC());
        if(this.isLS1) SolWriter.writeSol(vc);

    }


    private void EdgeDeletion(){
        HashSet<Edge> edges = new HashSet<>(g.getEdges());

/*        for(int i=1;i<=this.g.numOfVertices();i++){
            if(g.getAdjV(i).isEmpty()) {
                this.vc.add(i);
            }

        }*/

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