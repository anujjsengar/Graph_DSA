import java.util.*;

public class Graph_Implementation_Generic_Class<V>{
    public class Node{
        V key;
        HashMap<V,Integer> neigh;
        public Node(V key, HashMap<V,Integer> neigh){
            this.key=key;
            this.neigh=neigh;
        }
    }
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
    public void PrintPath(V vertex1,V vertex2,Set<V> visited,String ans){
        if(vertex1.equals(vertex2)){
            System.out.println(ans+vertex2);
            return ;
        }
        visited.add(vertex1);
        for(V vertex:graph.get(vertex1).keySet()){
            if(!visited.contains(vertex)){
                PrintPath(vertex, vertex2, visited, ans+vertex1+"--->");
            }
        }
    }
    public void PrintAllPath(V vertex1,V vertex2,Set<V> visited,String ans){
        if(vertex1.equals(vertex2)){
            System.out.println(ans+vertex2);
        }
        visited.add(vertex1);
        for(V vert:graph.get(vertex1).keySet()){
            if(!visited.contains(vert)){
                PrintAllPath(vert, vertex2, visited, ans+vertex1+"--->");
            }
        }
        visited.remove(vertex1);
    }
    public int PathCount(V vertex1,V vertex2,Set<V> visited){
        int count=0;
        if(vertex1.equals(vertex2)){
            return 1;
        }
        visited.add(vertex1);
        for(V neigh:graph.get(vertex1).keySet()){
            if(!visited.contains(neigh)){
                count=count+PathCount(neigh, vertex2, visited);
            }
        }
        visited.remove(vertex1);
        return count;
    }
    public void BFS(V vertex1){
        Queue<Node> q=new LinkedList<>();
        HashSet<V> visited=new HashSet<>();
        q.add(new Node(vertex1,graph.get(vertex1)));
        visited.add(vertex1);
        while(!q.isEmpty()){
            Node curr=q.poll();
            System.out.print(curr.key+" ");
            for(V adj:curr.neigh.keySet()){
                if(!visited.contains(adj)){
                    q.add(new Node(adj,graph.get(adj)));
                    visited.add(adj);
                }
            }
        }
    }
    public void DFS(V vertex1){
        Stack<Node> q=new Stack<>();
        HashSet<V> visited=new HashSet<>();
        q.add(new Node(vertex1,graph.get(vertex1)));
        visited.add(vertex1);
        while(!q.isEmpty()){
            Node curr=q.pop();
            System.out.print(curr.key+" ");
            for(V adj:curr.neigh.keySet()){
                if(!visited.contains(adj)){
                    q.add(new Node(adj,graph.get(adj)));
                    visited.add(adj);
                }
            }
        }
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
        graph.AddEdge(1, 6, 0);
        System.out.println(graph.VertexCount());
        System.out.println(graph.EdgeCount());
        System.out.println();
        System.out.println("All Edge Print");
        graph.AllEdgePrint();
        System.err.println();
        System.out.println("IS PATH BETWEEN GIVING TWO NODE");
        System.out.println(graph.IsPath(1, 5,new HashSet<>()));
        System.out.println(graph.IsPath(1, 7,new HashSet<>()));
        System.out.println();
        System.err.println("FIRST POSSIBLE PATH");
        graph.PrintPath(1, 6, new HashSet<>(),"");
        System.out.println();
        System.out.println("All Possible Path Between Vertices");
        graph.PrintAllPath(1, 4, new HashSet<>(),"");
        graph.PrintAllPath(1, 6, new HashSet<>(),"");
        graph.PrintAllPath(1, 5, new HashSet<>(),"");
        System.out.println();
        System.out.println("Path Count Between Two Node");
        System.out.println(graph.PathCount(1, 4, new HashSet<>()));
        System.out.println(graph.PathCount(1, 6, new HashSet<>()));
        System.out.println(graph.PathCount(1, 5, new HashSet<>()));
        System.out.println("BFS");
        graph.BFS(1);
        System.out.println();
        graph.BFS(4);
        System.out.println();
        System.out.println("DFS");
        graph.DFS(1);
        System.out.println();
        graph.DFS(4);
    }
}
