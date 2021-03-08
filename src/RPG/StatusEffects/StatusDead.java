package RPG.StatusEffects;

import RPG.Character.CombatCharacter;

public class StatusDead extends StrategyStatus {
    StatusDead(CombatCharacter owner) {
        super(owner);
    }

    @Override
    boolean expired() {
        return false;
    }
}
