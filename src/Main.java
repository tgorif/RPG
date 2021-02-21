import java.util.*;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        StatPerk baseStats =new StatPerk.StatPerkBuilder().setSPD(10).setHP(10).setMovement(10).setName("baseStats").build();
        CombatPerk wait = new CombatPerk("Wait",new AbilityWait(1));
        CombatPerk skipTurn = new CombatPerk("skipTurn",new AbilityWait(10));
        CombatPerk move = new CombatPerk("skipTurn",new AbilityMove());
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
