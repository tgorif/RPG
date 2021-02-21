public class ActionWait implements IStrategyAction{
    ConcreteCharacter concreteCharacter;
    int duration;
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
        setDuration();
        System.out.println("Waiting for" + duration);
    }
    public void setDuration(){
        duration=1;
    }
}
