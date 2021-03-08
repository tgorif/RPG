package RPG.StatusEffects;

import RPG.Character.CombatCharacter;

public abstract class StrategyStatus {
    CombatCharacter owner;
    StrategyStatus(CombatCharacter owner){
        this.owner=owner;
    }
    StrategyStatus(){

    }
    abstract boolean expired();
}
