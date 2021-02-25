import RPG.Character.Character;
import RPG.Output.Console;
import RPG.Main.GameState;
import RPG.PerkSystem.PerkTree;

import java.util.*;

public class Main {

    public static void main(String[] args){
        init.start();
        Character character1=createCharacter("Test1");
        character1.isBlueTeam=true;
        Character character2=createCharacter("TestHostile1");
        character2.isBlueTeam=false;
        List<Character> characterList = new ArrayList<>();
        characterList.add(character1);
        characterList.add(character2);
        GameState gameState= new GameState(characterList,new Console());
    }
    public static Character createCharacter(String name){
        Character character= new Character(name);
        character.learn(PerkTree.getPerkTree("Base"));
        character.learn(PerkTree.getPerkTree("Archer"));
        while(character.getLearnablePerks().size()>0){
            character.learn(character.getLearnablePerks().get(0));
        }
        return character;
    }
}
