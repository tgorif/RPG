import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    List<ConcreteCharacter> concreteCharacterList= new ArrayList<>();
    Level level;
    int round=0;
    final List<Character> characterList;
    boolean EndCondition=false;

    public GameState(List<Character> characterList){
        level=new Level();
        this.characterList=characterList;
        createCharacters();
        prepareTurn();
    }
    public void changeCharacterPosition(ConcreteCharacter concreteCharacter,int x,int y,int z){
        concreteCharacter.position.x+=x;
        concreteCharacter.position.y+=y;
        concreteCharacter.position.z+=z;
    }
    private void createCharacters(){
        for(Character c : characterList){
            concreteCharacterList.add(new ConcreteCharacter(c,new Position(level.x[1]/3,level.y[1]/2,0)));
        }
    }
    private void prepareTurn(){
        Map<ConcreteCharacter,List<IStrategyAction>> actions=new HashMap<>();
        for (ConcreteCharacter c : concreteCharacterList){
            actions.put(c,c.getActions(this));
        }
        resolveTurn(actions);
    }
    private void setTurnOrder(){

    }
    private void resolveTurn(Map<ConcreteCharacter,List<IStrategyAction>> actions) {
        for(ConcreteCharacter concreteCharacter : concreteCharacterList){
            concreteCharacter.updateStatus();
            if(actions.get(concreteCharacter).size()>0){
                IStrategyAction action= actions.get(concreteCharacter).get(0);
                action.resolveAction(this);
                actions.get(concreteCharacter).remove(0);
            }
        }
        round++;
        if(round<4) resolveTurn(actions);
    }
}
