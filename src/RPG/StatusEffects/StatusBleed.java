package RPG.StatusEffects;

import RPG.Character.CombatCharacter;

public class StatusBleed extends StrategyStatus implements Ticking,Transferable {
    int duration;
    int damagePerTick;
    StatusBleed(CombatCharacter owner,int duration,int damagePerTick) {
        super(owner);
        this.duration=duration;
        this.damagePerTick=damagePerTick;
    }

    @Override
    public void tick() {
        owner.changeHP(-damagePerTick);
        duration-=1;
    }

    @Override
    boolean expired() {
        return duration==0;
    }

    @Override
    public void transfer(CombatCharacter combatCharacter) {
        owner=combatCharacter;
        owner.statusEffects.put("Bleed",this);
    }
}
