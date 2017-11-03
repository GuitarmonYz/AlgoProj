package Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphUtil {
    public Graph parseEdges(String file_path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        String line = br.readLine();
        String[] split_array = line.split(" ");
        int numVertex = Integer.parseInt(split_array[0]);
        int numEdges = Integer.parseInt(split_array[1]);
        Graph g = new Graph(numVertex);
        for (int i = 1; i <= numVertex; i++){
            line = br.readLine();
            split_array = line.split(" ");
            for (String vertex : split_array){
                g.addEdge(i, Integer.parseInt(vertex));
            }
        }
        br.close();
        //if numOfEdges does not match return null
        if (g.numOfEdges() != numEdges) g = null;
        return g;
    }
}
