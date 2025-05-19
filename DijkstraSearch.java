package ads4;
import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, Double> distance = new HashMap<>();
    private final Map<V, V> edgeTo = new HashMap<>();
    private final V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        dijkstra(graph);
    }

    private void dijkstra(WeightedGraph<V> graph) {
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(v -> distance.get(v.getData())));
        for (Vertex<V> vertex : graph.getAllVertices()) {
            distance.put(vertex.getData(), Double.POSITIVE_INFINITY);
        }

        distance.put(start, 0.0);
        queue.add(graph.getVertex(start));

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Map.Entry<Vertex<V>, Double> neighborEntry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = neighborEntry.getKey();
                double newDist = distance.get(current.getData()) + neighborEntry.getValue();

                if (newDist < distance.get(neighbor.getData())) {
                    distance.put(neighbor.getData(), newDist);
                    edgeTo.put(neighbor.getData(), current.getData());
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V key) {
        return distance.containsKey(key) && distance.get(key) < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<V> pathTo(V key) {
        LinkedList<V> path = new LinkedList<>();
        if (!hasPathTo(key)) return path;
        for (V x = key; !x.equals(start); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
