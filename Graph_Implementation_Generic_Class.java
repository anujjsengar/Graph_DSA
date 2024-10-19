import java.util.*;

public class Graph_Implementation_Generic_Class<V>{
    HashMap<V,HashMap<V,Integer>> graph;
    public Graph_Implementation_Generic_Class(){
        this.graph=new HashMap<>();
    }
    public void AddVertex(V vertex){
        graph.put(vertex,new HashMap<>());
    }
    public void AddEdge(V vertex1,V vertex2,int weight){
        if(!graph.containsKey(vertex1)){
            AddVertex(vertex1);
        }
        if(!graph.containsKey(vertex2)){
            AddVertex(vertex2);
        }
        graph.get(vertex1).put(vertex2,weight);
        graph.get(vertex2).put(vertex1,weight);
    }
    public void RemoveEdge(V vertex1,V vertex2){
        if(graph.get(vertex1).containsKey(vertex2)){
            graph.get(vertex1).remove(vertex2);
            graph.get(vertex2).remove(vertex1);
        }
        else{
            System.out.println("Edge Not Found");
        }
    }
    public void RemoveVertex(V vertex){
        graph.remove(vertex);
        for(V vert:graph.keySet()){
            if(graph.get(vert).containsKey(vertex)){
                graph.get(vert).remove(vertex);
            }
        }
    }
    public int EdgeCount(){
        int count=0;
        for(V vertex:graph.keySet()){
            count+=graph.get(vertex).size();
        }
        return count/2;
    }
    public int VertexCount(){
        return graph.size();
    }
    public void AllEdgePrint(){
        for(V vertex:graph.keySet()){
            for(V vert:graph.get(vertex).keySet()){
                System.out.println(vertex+" --> "+ vert);
            }
        }
    }
    public boolean IsPath(V vertex1,V vertex2,Set<V> visited){
        if(graph.get(vertex1).containsKey(vertex2)){
            return true;
        }
        visited.add(vertex1);
        for(V vertex:graph.get(vertex1).keySet()){
            if(!visited.contains(vertex) && IsPath(vertex, vertex2, visited)){
                return true;
            }
        }
        return false;
    }
    public boolean PrintPath(V vertex1,V vertex2,Set<V> visited,String ans){
        if(graph.get(vertex1).containsKey(vertex2)){
            System.out.println(ans+vertex2);
            return true;
        }
        visited.add(vertex1);
        for(V vertex:graph.get(vertex1).keySet()){
            if(!visited.contains(vertex) && PrintPath(vertex, vertex2, visited, ans+vertex)){
                return true;
            }
        }
        return false;
    }
    public static void main(String args[]){
        Graph_Implementation_Generic_Class<Integer> graph=new Graph_Implementation_Generic_Class<>();
        graph.AddEdge(1, 2, 0);
        graph.AddEdge(1, 3, 0);
        graph.AddEdge(1, 5, 0);
        graph.AddEdge(2, 4, 0);
        graph.AddEdge(3, 4, 0);
        graph.AddEdge(3, 5, 0);
        graph.AddEdge(4, 6, 0);
        System.out.println(graph.VertexCount());
        System.out.println(graph.EdgeCount());
        graph.AllEdgePrint();
        System.out.println(graph.IsPath(1, 5,new HashSet<>()));
        graph.PrintPath(1, 6, new HashSet<>(),1+"");
    }
}
