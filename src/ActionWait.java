public class ActionWait implements IStrategyAction{
    ConcreteCharacter concreteCharacter;
    int duration;
    public ActionWait(int d){
        duration=d;
    }
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
        System.out.println("Waiting for" + duration);
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
    public void setDuration(int d){
        duration=d;
    }
}
