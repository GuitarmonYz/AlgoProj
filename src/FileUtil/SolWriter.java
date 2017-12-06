package FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author  ZhaoYan WenqingShen
 */
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

    /**
     * write solution to file
     * @param vc
     */
    public static void writeSol(HashSet<Integer> vc){
        String output_Filepath;

        if(hasRandSeed){
            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".sol";
        }else{
            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
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
            System.out.println("no such file");
        }

//        String str[] = input_Filename.split("/");
//        if(hasRandSeed){
//            output_Filepath = str[str.length-1].substring(0,str[str.length-1].length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".sol";
//        }else{
//            output_Filepath = str[str.length-1].substring(0,str[str.length-1].length() - 6) + "_" + algorithm_name
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
    }

    /**
     * write trace to file
     * @param time
     * @param size
     */
    public static void writeTrace(double time, int size){
        //write solution file
        String output_Filepath;

        if(hasRandSeed){
            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".trace";
        }else{
            output_Filepath = "./output/" + input_Filename.substring(0,input_Filename.length() - 6) + "_" + algorithm_name
                    + "_" + Long.toString(cut_off_time) + ".trace";
        }
        try {
            PrintWriter sol = new PrintWriter(new FileOutputStream(new File(output_Filepath),true));
            sol.printf("%s,%s%n",time,size);
            sol.close();
        }catch (IOException iex){
        }

//        String str[] = input_Filename.split("/");
//        if(hasRandSeed){
//            output_Filepath = str[str.length-1].substring(0,str[str.length-1].length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + "_" + Long.toString(randSeedParam)+".trace";
//        }else{
//            output_Filepath = str[str.length-1].substring(0,str[str.length-1].length() - 6) + "_" + algorithm_name
//                    + "_" + Long.toString(cut_off_time) + ".trace";
//        }
//        try {
//            PrintWriter sol = new PrintWriter(new FileOutputStream(new File(output_Filepath),true));
//            sol.printf("%s,%s%n",time,size);
//            sol.close();
//        }catch (IOException iex){
//        }
    }
}
