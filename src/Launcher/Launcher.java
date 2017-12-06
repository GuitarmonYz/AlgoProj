package Launcher;

import Algorigthms.ApproximateAlgorithm.APPRO;
import Algorigthms.BranchAndBound.BBMain;
import Algorigthms.LocalSearch.LSMain;
import FileUtil.SolWriter;
import Graph.GraphUtil;
import Graph.Graph;

import java.io.IOException;
import java.util.HashSet;

/**
 * @author Wenqing Shen and Zhao Yan
 * entry of the whole program
 */
public class Launcher {
    public static String input_Filename;
    public static String algorithm_name;
    public static long cut_off_time;
    public static long randSeedParam;
    public boolean hasRandSeed = false;

    public static void main(String args[])throws IOException{

        if(args.length < 6){
            System.out.println("Please enter right parameters length.");
            return;
        }

        SolWriter solWriter = SolWriter.getInstance();

        //arg 2 4 6 8 are the input
        Launcher exe = new Launcher();
        for (int i =1; i<=args.length/2;i++){
            if(args[2*i-2].equals("-inst")){
                exe.input_Filename = args[2*i-1];
                solWriter.input_Filename = args[2*i-1];
            } else if(args[2*i-2].equals("-alg")){
                exe.algorithm_name = args[2*i-1];
                solWriter.algorithm_name = args[2*i-1];
            } else if(args[2*i-2].equals("-time")){
                exe.cut_off_time = Long.parseLong(args[2*i-1]);
                solWriter.cut_off_time = Long.parseLong(args[2*i-1]);
            } else if(args[2*i-2].equals("-seed")){
                exe.randSeedParam = Long.parseLong(args[2*i-1]);
                solWriter.randSeedParam = Long.parseLong(args[2*i-1]);
                exe.hasRandSeed = true;
                solWriter.hasRandSeed = true;
            } else{
                System.out.println("Please enter right arguments.");
                return;
            }
        }

        if(args.length==6 && exe.hasRandSeed){
            System.out.println("Please enter enough parameters");
            return;
        }
        //distinguish whether input is file name or path
        String file_path;
        if (exe.input_Filename.split("/").length < 2){
            String now_dir = System.getProperty("user.dir");
            file_path = now_dir + "/Data/" + exe.input_Filename;
        }else{
            file_path = exe.input_Filename;
        }

        Graph g = GraphUtil.loadGraph(file_path);
        if(exe.algorithm_name.equals("LS1")){
            LSMain LS = new LSMain(g,exe.cut_off_time,exe.randSeedParam);
            LS.LS1();
        }else if (exe.algorithm_name.equals("LS2")){
            LSMain LS = new LSMain(g,exe.cut_off_time,exe.randSeedParam);
            LS.LS2();
        }else if (exe.algorithm_name.equals("BnB")){
            BBMain Bnb = new BBMain(g,exe.cut_off_time);
            Bnb.getVertexCover();
        }else if (exe.algorithm_name.equals("Approx")){
            APPRO.MDGsolveMVC(exe.input_Filename, (int)exe.cut_off_time);
        }else{
            System.out.println("Please enter correct algo name.");
        }
    }

}
