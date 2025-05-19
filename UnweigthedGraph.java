package ads4;

public class UnweightedGraph<V> extends WeightedGraph<V> {
    public UnweightedGraph(boolean directed) {
        super(directed);
    }

    public void addEdge(V source, V dest) {
        super.addEdge(source, dest, 1.0); // weight = 1 for unweighted graph
    }
}
