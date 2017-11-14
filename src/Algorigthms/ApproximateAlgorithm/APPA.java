package Algorigthms.ApproximateAlgorithm;

import java.io.*;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

/**
 * Created by zhangyongzheng on 11/13/17.
 */
public class APPA {
//    private static String current_Dir = System.getProperty("user.dir");
//
//    private static String output_Filepath;
//    private static String trace_Filepath;
//    private static PrintWriter DFS_output;
//    private static PrintWriter DFS_trace;
//
//    private static int num_Vertices;
//    private static int vertex_cover_size = 0;
//    private static int[] vertex_cover;
//
//    public static void main(String[] args) throws IOException{
//        String input_Filename;
//        String algorithm_name;
//        int cut_off_time;
//
//        if(args.length != 4){
//            System.out.println("Please enter right parameters.");
//            return;
//        }
//
//        if(args[0].equals("-jazz.graph") || args[0].equals("-karate.graph") || args[0].equals("-football.graph") || args[0].equals("-as-22july06.graph") ||
//                args[0].equals("-hep-th.graph") || args[0].equals("-star.graph") || args[0].equals("-star2.graph") ||
//                args[0].equals("-netscience.graph") || args[0].equals("-email.graph") || args[0].equals("-delaunay_n10.graph") || args[0].equals("-power.graph")){
//            input_Filename = args[0].substring(1,args[0].length() - 6);
//        }
//        else {
//            System.out.println("Please enter right file name");
//            return;
//        }
//
//        if(args[1].equals("-Approx")){
//            algorithm_name = args[1].substring(1,args[1].length());
//        }
//        else{
//            System.out.println("Please enter right algorithm name");
//            return;
//        }
//
//        try{
//            cut_off_time = Integer.parseInt(args[2].substring(1,args[2].length()));
//            if(cut_off_time <= 0){
//                System.out.println("Cut off time must be positive integer");
//            }
//        }catch (Exception e){
//            System.out.println("Please enter a right cut off time");
//            return;
//        }
//
//        String real_intput_Filename = input_Filename;
//        String real_algorithm_name = algorithm_name;
//        int real_cut_off_time = cut_off_time;
//
//        DFSsolveMVC(real_intput_Filename,real_algorithm_name,real_cut_off_time);
//    }
//    public static HashMap<String,ArrayList<String>> readgraph(String real_intput_Filename)throws IOException{
//        HashMap<String,ArrayList<String>> res = new HashMap<String,ArrayList<String>>();
//        String[] after_split;
//        String now_dir = System.getProperty("user.dir");
//        String file_path = now_dir + "/Data/" + real_intput_Filename + ".graph";
//        BufferedReader br = new BufferedReader(new FileReader(new File(file_path)));
//
//        String ln = br.readLine();
//        after_split = ln.split(" ");
//
//        res.put("0",new ArrayList<String>(Arrays.asList(after_split)));
//        num_Vertices = Integer.parseInt(after_split[0]);
//        vertex_cover = new int[num_Vertices];
//
//        int readline = 1;
//        while(readline <= Integer.parseInt(res.get("0").get(0))){
//            ln = br.readLine();
//            after_split = ln.split(" ");
//            res.put(Integer.toString(readline),new ArrayList<String>(Arrays.asList(after_split)));
//            readline++;
//        }
//        res.remove("0");
//        br.close();
//        return res;
//
//    }
//
//    public static void DFSsolveMVC(String real_intput_Filename, String real_algorithm_name, int real_cut_off_time) throws IOException{
//        output_Filepath = current_Dir + "/output/" + real_intput_Filename + "_" + real_algorithm_name + "_" + Integer.toString(real_cut_off_time) + ".sol";
//        trace_Filepath = current_Dir + "/output/" + real_intput_Filename + "_" + real_algorithm_name + "_" + Integer.toString(real_cut_off_time) + ".trace";
//
//        DFS_output = new PrintWriter(output_Filepath);
//        DFS_trace =new PrintWriter(trace_Filepath);
//
//        double running_Time = dfsfind(readgraph(real_intput_Filename));
//    }
//
//    public static double dfsfind(HashMap<String,ArrayList<String>> g){
//        boolean[][] connected = getMatrix(g);
//        boolean[] visited = new boolean[num_Vertices + 1];
//
//        for(int i = 0; i<num_Vertices;i++){
//            visited[i] = false;
//        }
//        long start_time = System.nanoTime();
//        for(int i = 1; i<num_Vertices;i++){
//            dfs_transfer(i,visited,connected);
//        }
//        long end_time = System.nanoTime();
//        double calculate_time = (end_time - start_time)/1000000;
//        return calculate_time;
//    }
//
//    public static boolean[][] getMatrix(HashMap<String,ArrayList<String>> g){
//        boolean[][] res = new boolean[num_Vertices + 1][num_Vertices + 1];
//        for(int i = 0; i<num_Vertices; i++){
//            for(int k = 0; k<num_Vertices; k++){
//                res[i][k] = false;
//            }
//        }
//
//        String key;
//        int key_int;
//        ArrayList<String> value;
//        int index;
//        for(Map.Entry<String, ArrayList<String>> entry: g.entrySet()){
//            key = entry.getKey();
//            key_int = Integer.parseInt(key);
//            value = entry.getValue();
//            for(int i = 0; i<value.size();i++){
//                if(!value.get(i).equals("")){
//                    index = Integer.parseInt(value.get(i));
//                    res[key_int][index] = true;
//                }
//            }
//        }
//        return res;
//    }
//    public static void dfs_transfer(int index, boolean[] visited, boolean[][] connected){
//        int count = 1;
//        visited[index] = true;
//        boolean check = true;
//        while ((count < num_Vertices + 1) && (check)){
//            if(connected[index][count] && !visited[count]){
//                check = false;
//            }
//            count++;
//        }
//
//        if(!check){
//            vertex_cover_size++;
//            vertex_cover[vertex_cover_size] = index;
//        }
//        for(int k = 0; k<num_Vertices+1;k++){
//            if(connected[index][k] && !visited[k]){
//                dfs_transfer(k,visited,connected);
//            }
//        }
//
//
//    }

    static class line{
        String key;
        ArrayList<String> value;

        line(String key, ArrayList<String> value){
            this.key = key;
            this.value = value;
        }

    }

    private static String current_Dir = System.getProperty("user.dir");

    private static String output_Filepath;
    private static String trace_Filepath;
    private static PrintWriter DFS_output;
    private static PrintWriter DFS_trace;

    private static int num_Vertices;
    private static int num_Edges;
    private static int vertex_cover_size = 0;
    private static int[] vertex_cover;
    private static ArrayList<Integer> result_vertex_cover = new ArrayList<Integer>();

    private static HashMap<String,ArrayList<String>> rawgraph;
    private static HashMap<String, ArrayList<String>> sortedMap;
    private static ArrayList<line> map_list = new ArrayList<line>();


    public static void main(String[] args) throws IOException{
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

        String real_intput_Filename = input_Filename;
        String real_algorithm_name = algorithm_name;
        int real_cut_off_time = cut_off_time;

        rawgraph = readgraph(real_intput_Filename);
        //boolean[][] connected = getMatrix(rawgraph);

        sortedMap = rawgraph.entrySet().stream().sorted(comparingInt(e -> e.getValue().size())).collect(toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a,b) -> {throw new AssertionError();},
                LinkedHashMap::new
        ));

        for(HashMap.Entry<String,ArrayList<String>> e: sortedMap.entrySet()){
            line item = new line(e.getKey(),e.getValue());
            map_list.add(item);
        }

        for(Map.Entry<String,ArrayList<String>> e : sortedMap.entrySet()){
            System.out.print(e.getKey());
            System.out.print(e.getValue());
            System.out.print("\n");
        }
        MDGsolveMVC(real_intput_Filename,real_algorithm_name,real_cut_off_time);




    }
    public static HashMap<String,ArrayList<String>> readgraph(String real_intput_Filename)throws IOException{
        HashMap<String,ArrayList<String>> res = new HashMap<String,ArrayList<String>>();
        String[] after_split;
        String now_dir = System.getProperty("user.dir");
        String file_path = now_dir + "/Data/" + real_intput_Filename + ".graph";
        BufferedReader br = new BufferedReader(new FileReader(new File(file_path)));

        String ln = br.readLine();
        after_split = ln.split(" ");

        res.put("0",new ArrayList<String>(Arrays.asList(after_split)));
        num_Vertices = Integer.parseInt(after_split[0]);
        num_Edges = Integer.parseInt(after_split[1]);
        vertex_cover = new int[num_Vertices];

        int readline = 1;
        while(readline <= Integer.parseInt(res.get("0").get(0))){
            ln = br.readLine();
            after_split = ln.split(" ");
            res.put(Integer.toString(readline),new ArrayList<String>(Arrays.asList(after_split)));
            readline++;
        }
        res.remove("0");
        br.close();
        return res;

    }
    public static boolean[][] getMatrix(HashMap<String,ArrayList<String>> g){
        boolean[][] res = new boolean[num_Vertices + 1][num_Vertices + 1];
        for(int i = 0; i<num_Vertices; i++){
            for(int k = 0; k<num_Vertices; k++){
                res[i][k] = false;
            }
        }

        String key;
        int key_int;
        ArrayList<String> value;
        int index;
        for(Map.Entry<String, ArrayList<String>> entry: g.entrySet()){
            key = entry.getKey();
            key_int = Integer.parseInt(key);
            value = entry.getValue();
            for(int i = 0; i<value.size();i++){
                if(!value.get(i).equals("")){
                    index = Integer.parseInt(value.get(i));
                    res[key_int][index] = true;
                }
            }
        }
        return res;
    }



    public static void MDGsolveMVC(String real_intput_Filename, String real_algorithm_name, int real_cut_off_time) throws IOException{
        output_Filepath = current_Dir + "/output/" + real_intput_Filename + "_" + real_algorithm_name + "_" + Integer.toString(real_cut_off_time) + ".sol";
        trace_Filepath = current_Dir + "/output/" + real_intput_Filename + "_" + real_algorithm_name + "_" + Integer.toString(real_cut_off_time) + ".trace";

        DFS_output = new PrintWriter(output_Filepath);
        DFS_trace =new PrintWriter(trace_Filepath);

        double running_Time = mdgfind(rawgraph,num_Edges);

        if(running_Time > real_cut_off_time){
            DFS_trace.printf("Timeout");
            DFS_output.printf("Timeout");
        }
        else {
            DFS_output.printf("%d%n",result_vertex_cover.size());
            for(int k = 0; k<result_vertex_cover.size();k++){
                DFS_output.printf("%s",result_vertex_cover.get(k));
                DFS_output.printf(",");
            }
            DFS_trace.printf("%s,%s",running_Time,result_vertex_cover.size());
        }
        DFS_output.close();;
        DFS_trace.close();
    }

    public static double mdgfind(HashMap<String,ArrayList<String>> rawgraph,int num_Edges){

        long start_time = System.nanoTime();
        while (num_Edges >0){
            HashMap<String, ArrayList<String>> temp_sorted_map = rawgraph.entrySet().stream().sorted(comparingInt(e -> e.getValue().size())).collect(toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (a,b) -> {throw new AssertionError();},
                    LinkedHashMap::new
            ));
            ArrayList<line> temp_list = new ArrayList<line>();
            for(HashMap.Entry<String,ArrayList<String>> e: temp_sorted_map.entrySet()){
                line item = new line(e.getKey(),e.getValue());

                temp_list.add(item);
            }
            //vertex_cover[Integer.parseInt(temp_list.get(temp_list.size()).key)] = 0;
            //System.out.println(temp_list.get(temp_list.size()-1).key);
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
