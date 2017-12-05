import Algorigthms.BranchAndBound.BBMain;
import FileUtil.SolWriter;
import Graph.Edge;
import Graph.GraphUtil;
import Graph.Graph;
import Graph.VertexCover;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Test class to experiment, free to modify
 */
public class Test {

    public static void main(String[] args) {
//        Edge e1 = new Edge(1 , 0);
//        Edge e2 = new Edge(0, 1);
//        HashSet<Edge> testSet = new HashSet<>();
//        testSet.add(e1);
//        testSet.add(e2);
//        ArrayList<Edge> testList = new ArrayList<>();
//        Test test = new Test();
//        test.testFinal(testList);
//        System.out.println(testList.size());
        //test.testFinal();
//        SolWriter solWriter = SolWriter.getInstance();
//        solWriter.input_Filename = "karate.graph";
//        solWriter.algorithm_name = "bnb";
//        solWriter.cut_off_time = 5;
//        try{
//            Graph testGraph = GraphUtil.loadGraph("./Data/power.graph");
//            BBMain bnb = new BBMain(testGraph, 5);
//            bnb.getVertexCover();
//        }catch (IOException ex){
//
//        }catch (OutOfMemoryError e){
//            System.out.println("out of memory, need more memory to get opt solution");
//        }
        String test = "data";
        System.out.println(test.split("/").length);
    }
    public void testFinal(ArrayList<Edge> testList){
        testList.add(new Edge(0,1));
        testList.add(new Edge(1,2));
        //System.out.println(testList.size());
    }
}
