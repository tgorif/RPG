public interface IStrategyAction {
     void setConcreteCharacter(ConcreteCharacter concreteCharacter);
     ConcreteCharacter getConcreteCharacter();
     void resolveAction(GameState gameState);


}
