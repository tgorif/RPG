import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        Map<String, IStrategyAction> actionMap = init.loadActions();
        for (Map.Entry<String,IStrategyAction> e : actionMap.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
        Perk.perkMap=init.loadPerks(actionMap);
        Map<String,PerkTree> perkTreeMap =init.loadPerkTree();
        for (Map.Entry<String,PerkTree> e : perkTreeMap.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }


        /*
        Map<Perk,List<Perk>> adjacencyMap = new HashMap<>();
        adjacencyMap.put(baseStats,null);
        List<Perk> tmp =new ArrayList<Perk>();
        tmp.add(baseStats);
        adjacencyMap.put(wait,tmp);
        adjacencyMap.put(skipTurn,tmp);
        adjacencyMap.put(move,tmp);
        PerkTree perkTree = new PerkTree("baseclass",adjacencyMap);
        Class baseclass = new Class("baseclass");

        baseclass.learnAllPerks();
        Character character = new Character();
        character.addClass(baseclass);
        List<Character> asd = new ArrayList<>();
        asd.add(character);
        GameState g = new GameState(asd);
        */


    }
}
