package Graph;

import java.util.ArrayList;

/**
 * @author Wenzheng Zhang
 * the edge used in approximate algorithm
 */
public class Line {
    public String key;
    public ArrayList<String> value;

    public Line(String key, ArrayList<String> value){
        this.key = key;
        this.value = value;
    }
}
