public interface IStrategyAction extends Cloneable {
     void setConcreteCharacter(ConcreteCharacter concreteCharacter);
     ConcreteCharacter getConcreteCharacter();
     void resolveAction(GameState gameState);
     Object clone();


}
