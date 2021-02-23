import java.lang.reflect.Method;

public class ActionMove implements IStrategyAction {
    ConcreteCharacter concreteCharacter;
    int duration;
    int[] vector;

    @Override
    public void setConcreteCharacter(ConcreteCharacter concreteCharacter) {
        this.concreteCharacter=concreteCharacter;
    }

    @Override
    public ConcreteCharacter getConcreteCharacter() {
        return concreteCharacter;
    }

    @Override
    public void resolveAction(GameState gameState) {
        setVector();
        gameState.changeCharacterPosition(concreteCharacter,vector);
    }
    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
    public void setVector(){
        vector=new int[3];
        vector[0]=10;
    }
}
