import java.util.*;
public class Graph{
    Map<Integer, HashMap<Integer,Integer>> graph;
    public Graph(){
        this.graph=new HashMap<>();
        /* 
        for(int i=0;i<=val;i++){
            this.graph.put(val,new HashMap<>());
        }*/
    }
    public void AddEdge(int v1,int v2,int w){
        if(!graph.containsKey(v1)){
            Addvertex(v1);
        }
        if(!graph.containsKey(v1)){
            Addvertex(v2);
        }
        graph.get(v1).put(v2,w);
        graph.get(v2).put(v1,w);
    }
    public void Addvertex(int v){
        graph.put(v,new HashMap<>());
    }
    public void removeEdge(int v1,int v2){
        if(graph.get(v1).containsKey(v2)){
        graph.get(v1).remove(v2);
        graph.get(v2).remove(v1);
        }
        else{
            System.out.println("Edge Not Found!!!");
        }
    }
    public void removeVertex(int v){
        if(graph.containsKey(v)){
            graph.remove(v);
            for(int i:graph.keySet()){
                if(graph.get(i).containsKey(v)){
                    graph.get(i).remove(v);
                }
            }
        }
    }
    public int edge(){
        int count=0;
        for(int i:graph.keySet()){
            count=count+graph.get(i).size();
        }
        return count/2;
    }
    public boolean hasPath(int v1,int v2){
        if(graph.get(v1).containsKey(v2)){
            return true;
        }
        for(int i:graph.get(v1).keySet()){
            hasPath(i,v2);
        }
        return false;
    }
    public static void main(String args[]){
        Graph graph=new Graph();
        graph.AddEdge(1, 2, 0);
        graph.AddEdge(1, 3, 0);
        graph.AddEdge(2, 4, 0);
        graph.AddEdge(4, 5, 0);
        System.out.println(graph.edge());
    }
}