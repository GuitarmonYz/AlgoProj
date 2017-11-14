package Graph;

import java.util.HashSet;

public class VertexCover implements Comparable<VertexCover> {
    public HashSet<Integer> candidate;
    public HashSet<Integer> unUsedVertices;
    public HashSet<Integer> unCoveredVertices;
    public HashSet<Edge> unCoveredEdges;

    int lowerBound;

    public VertexCover(HashSet<Integer> candidate, HashSet<Integer> unUsedVertices, HashSet<Integer> unCoveredVertices, HashSet<Edge> unCoveredEdges, int lowerBound){
        this.candidate = new HashSet<>(candidate);
        this.unUsedVertices = new HashSet<>(unUsedVertices);
        this.unCoveredVertices = new HashSet<>(unCoveredVertices);
        this.unCoveredEdges = new HashSet<>(unCoveredEdges);
        this.lowerBound = lowerBound;
    }

    public VertexCover(VertexCover vc){
        this.candidate = new HashSet<>(vc.candidate);
        this.unUsedVertices = new HashSet<>(vc.unUsedVertices);
        this.unCoveredVertices = new HashSet<>(vc.unCoveredVertices);
        this.unCoveredEdges = new HashSet<>(vc.unCoveredEdges);
        this.lowerBound = vc.lowerBound;
    }

    @Override
    public int compareTo(VertexCover vc) {
        if (this.lowerBound < vc.lowerBound){
            return -1;
        }
        if (this.lowerBound > vc.lowerBound){
            return 1;
        }
        return 0;
    }
}
