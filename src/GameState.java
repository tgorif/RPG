import java.util.ArrayList;
import java.util.List;

public class GameState {
    List<ConcreteCharacter> concreteCharacterList= new ArrayList<>();
    Level level;

    public GameState(List<Character> characterList){
        level=new Level();
        for(Character c : characterList){
            concreteCharacterList.add(new ConcreteCharacter(c,new Position(level.x[1]/3,level.y[1]/2,0)));
        }
        for (ConcreteCharacter c : concreteCharacterList){
            for (Ability a : c.abilityList){
                System.out.println(c.position.x + " " + c.position.y);
                a.use(this,c);
                System.out.println(c.position.x + " " + c.position.y);
            }
        }
    }
}
