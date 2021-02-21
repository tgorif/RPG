import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    List<ConcreteCharacter> concreteCharacterList= new ArrayList<>();
    Level level;

    public GameState(List<Character> characterList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        level=new Level();
        for(Character c : characterList){
            concreteCharacterList.add(new ConcreteCharacter(c,new Position(level.x[1]/3,level.y[1]/2,0)));
        }
        for (ConcreteCharacter c : concreteCharacterList){
            List<Action> actions = c.getActions(this);
                System.out.println(c.position.x + " " + c.position.y);
                for (Action a : actions){
                    a.getMethod().invoke(this,a.concreteCharacter,a.parameter,a.parameter,a.parameter);
                }
                System.out.println(c.position.x + " " + c.position.y);
        }
    }
    public void changeCharacterPosition(ConcreteCharacter concreteCharacter,int x,int y,int z){
        concreteCharacter.position.x+=x;
        concreteCharacter.position.y+=y;
        concreteCharacter.position.z+=z;
    }
}
