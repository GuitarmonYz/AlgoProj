package FileUtil;

import Launcher.Launcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SolWriter {
    public static void setHyperParam(Launcher launcher, String[] args){
        String input_Filename;
        String algorithm_name;
        int cut_off_time;
        int randSeedParam;
        if(args.length != 4){
            System.out.println("Please enter right parameters.");
            return;
        }

        if(args[0].equals("-test.graph")||args[0].equals("-jazz.graph") || args[0].equals("-karate.graph") || args[0].equals("-football.graph") || args[0].equals("-as-22july06.graph") ||
                args[0].equals("-hep-th.graph") || args[0].equals("-star.graph") || args[0].equals("-star2.graph") ||
                args[0].equals("-netscience.graph") || args[0].equals("-email.graph") || args[0].equals("-delaunay_n10.graph") || args[0].equals("-power.graph")){
            input_Filename = args[0].substring(1,args[0].length() - 6);
        }
        else {
            System.out.println("Please enter right file name");
            return;
        }

        if(args[1].equals("-Approx")){
            algorithm_name = args[1].substring(1,args[1].length());
        }
        else{
            System.out.println("Please enter right algorithm name");
            return;
        }

        try{
            cut_off_time = Integer.parseInt(args[2].substring(1,args[2].length()));
            if(cut_off_time <= 0){
                System.out.println("Cut off time must be positive integer");
            }
        }catch (Exception e){
            System.out.println("Please enter a right cut off time");
            return;
        }
        try {
            randSeedParam = Integer.parseInt(args[3].substring(1, args[3].length()));
            if(randSeedParam!=0) {
                System.out.println("No Random Seed needed here. Please set it to 0.");
            }
        } catch (Exception e) {
            System.out.println("Please enter valid randdom seed.");
            return;
        }
        launcher.setAlgorithm_name(algorithm_name);
        launcher.setCut_off_time(cut_off_time);
        launcher.setInput_Filename(input_Filename);
        launcher.setRandSeedParam(randSeedParam);
    }

    public static void writeToFile(String inputFileName, String algorithmName, int cutOffTime, ArrayList<Integer> res, int running_Time){
        String output_Filepath = "./output/" + inputFileName + "_" + algorithmName + "_" + Integer.toString(cutOffTime) + ".sol";
        String trace_Filepath = "./output/" + inputFileName + "_" + algorithmName + "_" + Integer.toString(cutOffTime) + ".trace";
        try {
            PrintWriter DFS_output = new PrintWriter(output_Filepath);
            PrintWriter DFS_trace =new PrintWriter(trace_Filepath);
            DFS_output.printf("%d%n",res.size());
            for(int k = 0; k<res.size();k++){
                DFS_output.printf("%s",res.get(k));
                DFS_output.printf(",");
            }
            DFS_trace.printf("%s,%s",running_Time,res.size());
        }catch (IOException iex){

        }

    }
}