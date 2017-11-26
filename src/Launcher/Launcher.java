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
        if(!args[0].equals("-inst")){
            System.out.println("Please enter right parameters -inst.");
            return;
        }
        exe.input_Filename = args[1];
        solWriter.input_Filename = args[1];
        if(!args[2].equals("-alg")){
            System.out.println("Please enter right parameters -alg. ");
            return;
        }
        exe.algorithm_name = args[3];
        solWriter.algorithm_name = args[3];
        if(!args[4].equals("-time")){
            System.out.println("Please enter right parameters -time.");
            return;
        }
        exe.cut_off_time = Long.parseLong(args[5]);
        solWriter.cut_off_time = Long.parseLong(args[5]);

        if(args.length==8){
            if(!args[6].equals("-seed")){
                System.out.println("Please enter right parameters -seed.");
                return;
            }
            exe.randSeedParam = Long.parseLong(args[7]);
            solWriter.randSeedParam = Long.parseLong(args[7]);
            exe.hasRandSeed = true;
            solWriter.hasRandSeed = true;
        }

        String now_dir = System.getProperty("user.dir");
        String file_path = now_dir + "/Data/" + exe.input_Filename;
        Graph g = GraphUtil.loadGraph(file_path);
        if(exe.algorithm_name.equals("LS1")){
            LSMain LS = new LSMain(g,exe.cut_off_time,exe.randSeedParam);
            LS.LS1();
        }else if (exe.algorithm_name.equals("LS2")){
            LSMain LS = new LSMain(g,exe.cut_off_time,exe.randSeedParam);
            LS.LS2();
        }else if (exe.algorithm_name.equals("BnB")){

        }else if (exe.algorithm_name.equals("Approx")){

        }else{
            System.out.println("Please enter correct algo name.");
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
