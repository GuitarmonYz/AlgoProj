package FileUtil;

import Launcher.Launcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SolWriter {
    private static SolWriter sol_writer = null;
    public static String input_Filename;
    public static String algorithm_name;
    public static long cut_off_time;
    public static long randSeedParam;
    public static boolean hasRandSeed = false;

    public static SolWriter getInstance(){
        if (sol_writer == null){
            sol_writer = new SolWriter();
        }
        return sol_writer;
    }
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

    public static void writeSol(HashSet<Integer> vc){
        //write solution file
        String output_Filepath;

//        if(hasRandSeed){
//            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".sol";
//        }else{
//            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + ".sol";
//        }
//        try {
//            PrintWriter sol = new PrintWriter(output_Filepath);
//            sol.printf("%d%n",vc.size());
//            Iterator<Integer> iterator = vc.iterator();
//            sol.printf("%s",iterator.next());
//            while(iterator.hasNext()){
//                sol.printf(",%s",iterator.next());
//            }
//            sol.close();
//        }catch (IOException iex){
//        }
        if(hasRandSeed){
            output_Filepath = "./" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".sol";
        }else{
            output_Filepath = "./" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + ".sol";
        }
        try {
            PrintWriter sol = new PrintWriter(output_Filepath);
            sol.printf("%d%n",vc.size());
            Iterator<Integer> iterator = vc.iterator();
            sol.printf("%s",iterator.next());
            while(iterator.hasNext()){
                sol.printf(",%s",iterator.next());
            }
            sol.close();
        }catch (IOException iex){
        }
    }

    public static void writeTrace(double time, int size){
        //write solution file
        String output_Filepath;

//        if(hasRandSeed){
//            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".trace";
//        }else{
//            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + ".trace";
//        }
//        try {
//            PrintWriter sol = new PrintWriter(new FileOutputStream(new File(output_Filepath),true));
//            sol.printf("%s,%s%n",time,size);
//            sol.close();
//        }catch (IOException iex){
//        }
        
        String str[] = input_Filename.split("/");
        if(hasRandSeed){
            output_Filepath = "./" + str[str.length-1].substring(0,str[str.length-1].length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".trace";
        }else{
            output_Filepath = "./" + str[str.length-1].substring(0,str[str.length-1].length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + ".trace";
        }
        try {
            PrintWriter sol = new PrintWriter(new FileOutputStream(new File(output_Filepath),true));
            sol.printf("%s,%s%n",time,size);
            sol.close();
        }catch (IOException iex){
        }
    }
}
