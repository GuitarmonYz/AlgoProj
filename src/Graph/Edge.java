package Graph;

/**
 * Created by zhaoyan on 9/16/17.
 */
public class Edge {
    private int u;
    private int v;
    private String id;

    public Edge(int u, int v){
        this.u = u;
        this.v = v;
        this.id = u < v ? Integer.toString(u)+v : Integer.toString(v)+u;
    }

    public int[] getTwoEnds(){
        return new int[]{u,v};
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge){
            int _u = ((Edge) obj).endPoint();
            int _v = ((Edge) obj).endPoint(_u);
            if ((_u == u && _v == v) || (_u == v) && (_v == u)) return true;
            else return false;
        }else {
            return false;
        }
    }
    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
    public String toString(){
        return this.u + " - " + this.v;
    }
}
