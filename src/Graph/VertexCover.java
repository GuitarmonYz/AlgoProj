package Graph;

import java.util.Arrays;

public class VertexCover implements Comparable<VertexCover> {
    public boolean[] graphState;

    public int lowerBound;

    public VertexCover(boolean[] graphState, int lowerBound){
        this.graphState = Arrays.copyOf(graphState, graphState.length);
        this.lowerBound = lowerBound;
    }

    public VertexCover(VertexCover vc){
        this.graphState = Arrays.copyOf(vc.graphState, vc.graphState.length);
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
