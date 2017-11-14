import Algorigthms.BranchAndBound.BBMain;
import Graph.GraphUtil;
import Graph.Graph;

import java.io.IOException;

public class Launcher {
    public static void main(String args[]){
        try {
            Graph testGraph = GraphUtil.loadGraph("./Data/karate.graph");
            BBMain bnb = new BBMain(testGraph, 120);
            bnb.getVertexCover();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
