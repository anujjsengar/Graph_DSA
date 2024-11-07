import java.util.*;


public class Spanning_Tree<V> extends Graph_Implementation_Generic_Class<V> {
    public Spanning_Tree() {
        super();
    }
    public class Node implements Comparable<Node> {
        V key;
        V value;
        int weight;
        public Node(V key, V value, int weight) {
            this.key = key;
            this.value = value;
            this.weight = weight;
        }
        public int compareTo(Node b) {
            return this.weight - b.weight;
        }
    }
    public void MST(V start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<V> visited = new HashSet<>();
        int ans = 0;
        pq.add(new Node(start, start, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited.contains(curr.value)) {
                continue;
            }
            visited.add(curr.value);
            ans += curr.weight;
            System.out.println("Edge: " + curr.key + " - " + curr.value + " with weight: " + curr.weight);
            if (graph.containsKey(curr.value)) {
                for (V neigh : graph.get(curr.value).keySet()) {
                    if (!visited.contains(neigh)) {
                        pq.add(new Node(curr.value, neigh, graph.get(curr.value).get(neigh)));
                    }
                }
            }
        }
        System.out.println("Total weight of MST: " + ans);
    }
    public void shortest_path(V start){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        HashSet<V> visited=new HashSet<>();
        pq.add(new Node(start,start,0));
        while(!pq.isEmpty()){
            Node curr=pq.poll();
            if(visited.contains(curr.value)){
                continue;
            }
            visited.add(curr.value);
            System.out.print(curr.value+" --> ");
            if(graph.containsKey(curr.value)){
                for(V neigh:graph.get(curr.value).keySet()){
                    if(!visited.contains(neigh)){
                        pq.add(new Node(curr.value,neigh,graph.get(curr.value).get(neigh)));
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Spanning_Tree<Integer> tree = new Spanning_Tree<>();
        tree.AddEdge(1, 2, 10);
        tree.AddEdge(1, 4, 20);
        tree.AddEdge(1, 3, 25);
        tree.AddEdge(2, 4, 30);
        tree.AddEdge(2, 3, 27);
        tree.AddEdge(3, 4, 15);
        tree.MST(1);
        System.out.println("Shortest Path from a Vertex");
        tree.shortest_path(2);
    }
}
