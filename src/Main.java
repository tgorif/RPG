import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        StatPerk baseStats =new StatPerk.StatPerkBuilder().setSPD(10).setHP(10).setMovement(10).setName("baseStats").build();
        CombatPerk wait = new CombatPerk("Wait",new Ability(new ActionWait()));
        CombatPerk skipTurn = new CombatPerk("skipTurn",new Ability(new ActionWait()));
        CombatPerk move = new CombatPerk("move",new Ability(new ActionMove()));
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


    }
}
