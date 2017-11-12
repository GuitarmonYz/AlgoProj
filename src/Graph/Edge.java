package Graph;

/**
 * Created by zhaoyan on 9/16/17.
 */
public class Edge {
    private int u;
    private int v;

    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }

    public int endPoint(){
        return v;
    }

    public int endPoint(int vertex){
        return vertex == v ? u:v;
    }

    public boolean contains(int x) {
        return this.u == x || this.v == x;
    }


}
