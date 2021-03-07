package RPG.SkillSystem;

import RPG.Character.CombatCharacter;

public class SkillEndTurn extends StrategySkill{

    public SkillEndTurn(String name,int cost) {
        super(name,cost);
    }
    @Override
    public void simulate(CombatCharacter combatCharacter) {
        combatCharacter.AP=0;

    }
    @Override
    public void useSkill() {
        gameState.reduceAP(caster,AP);
    }

    @Override
    public void setValues(CombatCharacter combatCharacter) {
        this.gameState=gameState;
        caster= combatCharacter;
        AP=10;
    }
    @Override
    public void prepareAction() {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
