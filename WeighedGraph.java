package ads4;

import java.util.*;

public class WeightedGraph<V> {
    private final boolean directed;
    private final Map<V, Vertex<V>> vertices = new HashMap<>();

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> v1 = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> v2 = vertices.computeIfAbsent(dest, Vertex::new);
        v1.addAdjacentVertex(v2, weight);
        if (!directed) {
            v2.addAdjacentVertex(v1, weight);
        }
    }

    public Vertex<V> getVertex(V key) {
        return vertices.get(key);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }

    public boolean containsVertex(V key) {
        return vertices.containsKey(key);
    }
}
