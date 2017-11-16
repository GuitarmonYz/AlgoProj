import Algorigthms.BranchAndBound.BBMain;
import Graph.GraphUtil;
import Graph.Graph;

import java.io.IOException;
import java.util.HashSet;

public class Launcher {
    public static void main(String args[]){
        try {
            Graph testGraph = GraphUtil.loadGraph("./Data/karate.graph");
            BBMain bnb = new BBMain(testGraph, 120);
            HashSet<Integer> sol = bnb.getVertexCover();
            System.out.println(bnb.displayRes(sol));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
