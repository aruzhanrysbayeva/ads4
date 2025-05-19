package ads4;

import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final Set<V> visited = new HashSet<>();
    private final Map<V, V> edgeTo = new HashMap<>();
    private final V start;

    public DepthFirstSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        dfs(graph, start);
    }

    private void dfs(WeightedGraph<V> graph, V current) {
        visited.add(current);
        Vertex<V> currentVertex = graph.getVertex(current);
        if (currentVertex == null) return;

        for (Vertex<V> neighbor : currentVertex.getAdjacentVertices().keySet()) {
            V neighborData = neighbor.getData();
            if (!visited.contains(neighborData)) {
                edgeTo.put(neighborData, current);
                dfs(graph, neighborData);
            }
        }
    }

    @Override
    public boolean hasPathTo(V key) {
        return visited.contains(key);
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
