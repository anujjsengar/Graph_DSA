import java.util.*;

public class Bellman_Ford<V> extends Graph_Implementation_Generic_Class<V>{
    public Bellman_Ford(){
        super();
    }
    private class pair{
        V vertex1;
        V vertex2;
        int weight;
        pair(V vertex1,V vertex2,int weight){
            this.vertex1=vertex1;
            this.vertex2=vertex2;
            this.weight=weight;
        }
    }
    public ArrayList<pair> AllEdge(){
        ArrayList<pair> cost=new ArrayList<>();
        for(V k:graph.keySet()){
            for(V j:graph.get(k).keySet()){
                cost.add(new pair(k,j,graph.get(k).get(j)));
            }
        }
        return cost;
    }
    public void bellamford(){
            ArrayList<pair> list=this.AllEdge();
            int vert=this.VertexCount();
            TreeMap<V,Integer> cost=new TreeMap<>();
            for(V ver:graph.keySet()){
                if(ver.equals(0)){
                    cost.put(ver,0);
                    continue;  
                }
                cost.put(ver,Integer.MAX_VALUE);
            }
            for(int i=1;i<vert;i++){
                for(pair p:list){
                    V v1=p.vertex1;
                    V v2=p.vertex2;
                    int weight=p.weight;
                    int v1_old_cost=cost.get(v1);
                    int v2_old_cost=cost.get(v2);
                    if(i==vert){
                        System.out.println("-ve Cycle Detected");
                        break ;
                    }
                    if(v1_old_cost+weight<v2_old_cost){
                        cost.put(v2,v1_old_cost+weight);
                    }
                }
        }
        for(V vertex:cost.keySet()){
            System.out.print(cost.get(vertex)+" ");
        }
    }
    public static void  main(String args[]){
        Bellman_Ford<Integer> dir_graph=new Bellman_Ford<>();
        dir_graph.AddEdgeDirected(0, 1,10);
        dir_graph.AddEdgeDirected(0,3,40);
        dir_graph.AddEdgeDirected(1, 2, -15);
        dir_graph.AddEdgeDirected(1, 4, 10);
        dir_graph.AddEdgeDirected(2, 4, 5);
        dir_graph.AddEdgeDirected(2, 3, 20);
        dir_graph.bellamford();
    }
}
