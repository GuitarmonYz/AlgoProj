package Algorigthms.ApproximateAlgorithm;
import java.io.*;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

import FileUtil.SolWriter;
import Graph.Line;
/**
 * Created by zhangyongzheng on 11/14/17.
 */
public class APPRO {
    private static String current_Dir = System.getProperty("user.dir");

//    private static String output_Filepath;
//    private static String trace_Filepath;
    private static PrintWriter DFS_output;
    private static PrintWriter DFS_trace;

    //private static int num_Vertices;
    public static int num_Edges;
    public static ArrayList<Integer> result_vertex_cover = new ArrayList<>();

    private static HashMap<String,ArrayList<String>> rawgraph;


//    public static void main(String[] args) throws IOException {
////        String input_Filename;
////        String algorithm_name;
////        int cut_off_time;
////        int randSeedParam;
////
////        String real_intput_Filename = input_Filename;
////        String real_algorithm_name = algorithm_name;
////        int real_cut_off_time = cut_off_time;
////
////        //rawgraph = readgraph(real_intput_Filename);
////        System.out.println(current_Dir);
////        MDGsolveMVC(real_intput_Filename,real_algorithm_name,real_cut_off_time);
//
//    }

    public static void readgraph(String real_intput_Filename)throws IOException{
        HashMap<String,ArrayList<String>> res = new HashMap<>();
        String[] after_split;
        //String now_dir = System.getProperty("user.dir");
        String file_path = "./Data/" + real_intput_Filename + ".graph";
        BufferedReader br = new BufferedReader(new FileReader(new File(file_path)));

        String ln = br.readLine();
        after_split = ln.split(" ");

        res.put("0",new ArrayList<>(Arrays.asList(after_split)));
        //num_Vertices = Integer.parseInt(after_split[0]);
        num_Edges = Integer.parseInt(after_split[1]);
        //vertex_cover = new int[num_Vertices];

        int readline = 1;
        while(readline <= Integer.parseInt(res.get("0").get(0))){
            ln = br.readLine();
            if(ln.equals("")){
                ln = ln + -1;
            }
            after_split = ln.split(" ");
            res.put(Integer.toString(readline),new ArrayList<>(Arrays.asList(after_split)));
            readline++;
        }
        res.remove("0");
        br.close();
        rawgraph = res;
    }


    public static void MDGsolveMVC(int real_cut_off_time) throws IOException{
        double running_Time = mdgfind(num_Edges);
        HashSet<Integer> resVC = new HashSet<>();
        for (int i : result_vertex_cover){
            resVC.add(i);
        }
        if(running_Time > real_cut_off_time){
            System.out.println("time out");
        }
        else {
            SolWriter.writeSol(resVC);
            SolWriter.writeTrace(running_Time,resVC.size());
        }
    }

    public static double mdgfind(int num_Edges){
        long start_time = System.nanoTime();
        while (num_Edges >0){
            HashMap<String, ArrayList<String>> temp_sorted_map = rawgraph.entrySet().stream().sorted(comparingInt(e -> e.getValue().size())).collect(toMap(
                    HashMap.Entry::getKey,
                    HashMap.Entry::getValue,
                    (a,b) -> {throw new AssertionError();},
                    LinkedHashMap::new
            ));
            ArrayList<Line> temp_list = new ArrayList<>();
            for(HashMap.Entry<String,ArrayList<String>> e: temp_sorted_map.entrySet()){
                Line item = new Line(e.getKey(),e.getValue());

                temp_list.add(item);
            }
            result_vertex_cover.add(Integer.parseInt(temp_list.get(temp_list.size()-1).key));
            num_Edges = num_Edges - temp_list.get(temp_list.size()-1).value.size();
            int removed_key = Integer.parseInt(temp_list.get(temp_list.size()-1).key);
            rawgraph.remove(temp_list.get(temp_list.size()-1).key);
            //delete edge connected with removed vertex
            for(HashMap.Entry<String,ArrayList<String>> e: temp_sorted_map.entrySet()){
                for(int i = 0;i< e.getValue().size();i++){
                    if (Integer.parseInt(e.getValue().get(i)) == removed_key){
                        e.getValue().remove(i);
                    }
                }
            }

        }
        long end_time = System.nanoTime();
        double calculate_time = (end_time - start_time)/1000000;
        return calculate_time;
    }
}