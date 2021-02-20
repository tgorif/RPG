
import java.util.*;
import java.util.Map;

public class PerkTree {
    String name;
    List<Node> root=new ArrayList<>();
    private static Map<String,PerkTree> treeMap= new HashMap<>();
    public PerkTree(String name,Map<Perk, List<Perk>> adjacencyList){
        this.name=name;
        treeMap.put(this.name,this);
        setNodes(adjacencyList);
    }
    private void setNodes(Map<Perk,List<Perk>> adjacencyList){
        Map<Perk,Node> map = new HashMap<>();
        for(Map.Entry<Perk,List<Perk>> e : adjacencyList.entrySet()){
            Node n = new Node(e.getKey());
            map.put(e.getKey(),n);
        }
        for(Map.Entry<Perk,List<Perk>> e : adjacencyList.entrySet()){
            if(e.getValue()!=null){
                for(Perk p : e.getValue()){
                    map.get(p).addOutgoingNode(map.get(e.getKey()));
                }
            }
        }
        setRoot(map);
    }
    private void setRoot(Map<Perk,Node> map){
        Map<Node,Integer> incoming = new HashMap<>();
        for(Map.Entry<Perk,Node> e : map.entrySet()){
            incoming.put(e.getValue(),0);
        }
        for(Map.Entry<Perk,Node> e : map.entrySet()) {
            for (Node n : e.getValue().outgoingNodes) {
                incoming.replace(n, incoming.get(n) + 1);
            }
        }
        for(Map.Entry<Node,Integer> e : incoming.entrySet()){
            if(e.getValue()==0) this.root.add(e.getKey());
        }
    }
    public static PerkTree getPerkTree(String s){
        return treeMap.get(s);
    }
    private class Node{
        Perk perk;
        List<Node> outgoingNodes=new ArrayList<>();
        private Node(Perk p){
            perk=p;
        }
        private void addOutgoingNode(Node n){
            outgoingNodes.add(n);
        }
    }
}
