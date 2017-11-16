package Launcher;

import Algorigthms.ApproximateAlgorithm.APPRO;
import Algorigthms.BranchAndBound.BBMain;
import Algorigthms.LocalSearch.LSMain;
import FileUtil.SolWriter;
import Graph.GraphUtil;
import Graph.Graph;

import java.io.IOException;
import java.util.HashSet;

public class Launcher {
    private String input_Filename;
    private String algorithm_name;
    private int cut_off_time;
    private int randSeedParam;

    public static void main(String args[]){
        try {
            Launcher launcher = new Launcher();
//            SolWriter.setHyperParam(launcher, args);
            Graph testGraph = GraphUtil.loadGraph("./Data/karate.graph");
//            BBMain bnb = new BBMain(testGraph, 120);
//            HashSet<Integer> sol = bnb.getVertexCover();
//            System.out.println(bnb.displayRes(sol));
            LSMain ls = new LSMain(testGraph);
            ls.LS1();
            APPRO.readgraph("karate");
            APPRO.mdgfind();
            for (int val : APPRO.result_vertex_cover){
                System.out.print(val + ", ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInput_Filename(String input_Filename){
        this.input_Filename = input_Filename;
    }
    public void setAlgorithm_name(String algorithm_name){
        this.algorithm_name = algorithm_name;
    }
    public void setCut_off_time(int cut_off_time) {
        this.cut_off_time = cut_off_time;
    }
    public void setRandSeedParam(int randSeedParam) {
        this.randSeedParam = randSeedParam;
    }
}
