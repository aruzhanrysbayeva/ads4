package ads4;
import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Set<V> marked = new HashSet<>();
    private final V start;

    public BreadthFirstSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(WeightedGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (Vertex<V> neighbor : graph.getVertex(current).getAdjacentVertices().keySet()) {
                V data = neighbor.getData();
                if (!marked.contains(data)) {
                    marked.add(data);
                    edgeTo.put(data, current);
                    queue.add(data);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V key) {
        return marked.contains(key);
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
