public class Ability {
    IStrategyAction action;
    public Ability(IStrategyAction action){
        this.action=action;
    }

    public IStrategyAction getAction(GameState gameState){
    return action;
    }
}
