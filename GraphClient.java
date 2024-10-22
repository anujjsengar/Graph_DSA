import java.util.HashSet;

public class GraphClient<V> extends Graph_Implementation_Generic_Class<V>{
    public static void main(String args[]){
        GraphClient<Integer> graph=new GraphClient<>();
        graph.AddEdge(1, 2, 0);
        graph.AddEdge(1, 3, 0);
        graph.AddEdge(1, 5, 0);
        graph.AddEdge(2, 4, 0);
        graph.AddEdge(3, 4, 0);
        graph.AddEdge(3, 5, 0);
        graph.AddEdge(4, 6, 0);
        graph.AddEdge(1, 6, 0);
        System.out.println(graph.VertexCount());
        System.out.println(graph.EdgeCount());
        graph.AllEdgePrint();
        System.out.println(graph.IsPath(1, 5,new HashSet<>()));
        System.out.println(graph.IsPath(1, 7,new HashSet<>()));
        graph.PrintPath(1, 6, new HashSet<>(),"");
        graph.PrintAllPath(1, 4, new HashSet<>(),"");
        graph.PrintAllPath(1, 6, new HashSet<>(),"");
        graph.PrintAllPath(1, 5, new HashSet<>(),"");
        System.out.println(graph.PathCount(1, 4, new HashSet<>()));
        System.out.println(graph.PathCount(1, 6, new HashSet<>()));
        System.out.println(graph.PathCount(1, 5, new HashSet<>()));
        graph.BFS(4);
    }
}
