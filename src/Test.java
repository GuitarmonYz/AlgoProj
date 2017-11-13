import Graph.Edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Test class to experiment, free to modify
 */
public class Test {
    private final ArrayList<Edge> testList = new ArrayList<>();
    public static void main(String[] args) {
        Edge e1 = new Edge(1 , 0);
        Edge e2 = new Edge(0, 1);
        HashSet<Edge> testSet = new HashSet<>();
        testSet.add(e1);
        testSet.add(e2);
        Test test = new Test();
        test.testFinal();

    }
    public void testFinal(){
        testList.add(new Edge(0,1));
        System.out.println(this.testList.size());
    }
}
