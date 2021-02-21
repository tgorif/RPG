import java.lang.reflect.Method;

public class ActionMove implements IStrategyAction {
    ConcreteCharacter concreteCharacter;

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
        System.out.println("move");
    }
}
