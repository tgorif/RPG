import RPG.Character.Character;
import RPG.Character.CharacterPool;
import RPG.Main.init;
import RPG.Output.Console;
import RPG.Main.GameState;

import java.util.*;

public class Main {

    public static void main(String[] args){
        init.start();
        List<Character> characterList = new ArrayList<>();
        characterList.add(new Character(CharacterPool.getNext()));
        characterList.get(characterList.size()-1).isBlueTeam=true;
        characterList.add(new Character(CharacterPool.getNext()));
        characterList.get(characterList.size()-1).isBlueTeam=false;
        GameState gameState= new GameState(characterList,new Console());
        gameState.start();
    }
}
