package RPG.PerkSystem;

import java.util.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerkTree {
    Logger LOGGER =Logger.getLogger(PerkTree.class.getName());
    public String name;
    List<Node> root=new ArrayList<>();
    Map<Perk,Node> nodes = new HashMap<>();
    List<Node> nodeList = new ArrayList<>();
    private static final Map<String,PerkTree> treeMap= new HashMap<>();

    public PerkTree(String className, List<String> perks, List<String> ancestors, List<String> descendants,List<String> roots) {
        this.name=className;
        treeMap.put(this.name,this);
        setNodes(perks);
        setRoot(roots);
        setConnections(ancestors,descendants);
        isValid();
    }
    private void isValid(){
        if(name.length()==0){
            LOGGER.log(Level.SEVERE,"created PerkTree without name");
        }
        if(root.size()<1) {
            LOGGER.log(Level.SEVERE,"created PerkTree without rootNode");
        }
        for(Node node : root){
            getDescendants(node);
        }
        if(nodes.size()!=nodeList.size()){
            LOGGER.log(Level.SEVERE,"created PerkTree with different Perk Node sizes"
            +nodes.size() + " " + nodeList.size());
        }
        for(Node node : nodeList){
            if(!nodes.containsValue(node)){
                LOGGER.log(Level.SEVERE,"Node " +  node.perk.name +  " does not have a mapping");
            }
        }
        LOGGER.log(Level.FINE,"PerkTree " + name + " has been created without warnings");
    }
    private void getDescendants(Node node){
        if(!nodeList.contains(node))nodeList.add(node);
        for(Node n : node.next){
            getDescendants(n);
        }
    }
    private void setNodes(List<String> perks){
        for(String s : perks){
            Perk perk = Perk.getPerk(s);
            if(perk==null) LOGGER.log(Level.SEVERE,"PerkTree " + name + " did not find Perk " + s);
            else nodes.put(perk,new Node(perk));
        }
    }
    private void setRoot(List<String> roots){
        for (String s : roots){
            root.add(nodes.get(Perk.getPerk(s)));
        }
    }
    private void setConnections(List<String> ancestors, List<String> descendants){
        if(ancestors.size()!=descendants.size()) LOGGER.log(Level.SEVERE,"adjacencyLists have different sizes");
        for(int i=0;i<ancestors.size();i++){
            Node a =nodes.get(Perk.getPerk(ancestors.get(i)));
            Node b =nodes.get(Perk.getPerk(descendants.get(i)));
            if(a==null) LOGGER.log(Level.SEVERE,"could not find Perk " +ancestors.get(i));
            if(b==null) LOGGER.log(Level.SEVERE,"could not find Perk " +descendants.get(i));
            if(a!=null && b!=null) a.next.add(b);
        }
    }
    public static PerkTree getPerkTree(String s){
        return treeMap.get(s);
    }
    public Perk[] getRoot(){
        Perk[] result = new Perk[this.root.size()];
        int i=0;
        for(Node n : root){
            result[i]=n.perk;
            i++;
        }
        return result;
    }
    public Perk[] getDescendants(Perk p){
        if(!nodes.containsKey(p))return new Perk[0];
        Perk[] result = new Perk[nodes.get(p).next.size()];
        int i=0;
        for (Node n : nodes.get(p).next){
            result[i]=n.perk;
            i++;
        }
        return result;
    }
    public List<Perk> getAll(){
        List<Perk> result =new ArrayList<>();
        for (Map.Entry<Perk,Node> e : nodes.entrySet()){
            result.add(e.getKey());
        }
        return result;
    }
    private class Node{
        Perk perk;
        List<Node> next=new ArrayList<>();
        private Node(Perk p){
            perk=p;
        }
        private void addNode(Node n){
            next.add(n);
        }
    }
}
