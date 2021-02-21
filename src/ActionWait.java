public class ActionWait implements IStrategyAction{
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
        System.out.println("Wait");
    }
}
