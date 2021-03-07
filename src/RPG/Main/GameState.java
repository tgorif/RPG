package RPG.Main;

import RPG.Character.Character;
import RPG.Character.CombatCharacter;
import RPG.Output.StrategyOutput;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GameState {
    static Logger LOGGER =Logger.getLogger(GameState.class.getName());
    public List<CombatCharacter> combatCharacterList = new ArrayList<>();
    Level level;
    int round=0;
    final List<Character> characterList;
    boolean EndCondition=false;
    StrategyOutput output;
    private static GameState gameState;

    public GameState(List<Character> characterList,StrategyOutput strategyOutput){
        output=strategyOutput;
        level=new Level();
        this.characterList=characterList;
        gameState=this;
        createCharacters();
        prepareTurn();
        LOGGER.log(java.util.logging.Level.FINE,"Created gamestate " + GameState.getInstance().toString());
    }
    public static GameState getInstance(){
        if(gameState!=null) return gameState;
        LOGGER.log(java.util.logging.Level.SEVERE,"returned null gamestate");
        return null;
    }
    private void createCharacters(){
        for(Character c : characterList){
            combatCharacterList.add(new CombatCharacter(c,new Position(level.x[1]/3,level.y[1]/2,0)));
        }
    }
    private void prepareTurn(){
        Map<CombatCharacter,List<StrategySkill>> actions=new HashMap<>();
        for (CombatCharacter c : combatCharacterList){
            actions.put(c,c.getActions());
        }
        resolveTurn(actions,10);
    }
    private void resolveTurn(Map<CombatCharacter,List<StrategySkill>> actions, int turnTimer) {
        output.resolveingTurn(turnTimer);
        for(CombatCharacter combatCharacter : combatCharacterList){
            if(combatCharacter.AP>=turnTimer &&
                    actions.containsKey(combatCharacter)
                    &&actions.get(combatCharacter).size()>0){
                actions.get(combatCharacter).get(0).useSkill();
                actions.get(combatCharacter).remove(0);
            }
        }
        if(turnTimer>0) resolveTurn(actions,turnTimer-1);
    }
    private void setTurnOrder(){

    }
    public void changeCharacterPosition(CombatCharacter combatCharacter, Position target){
        output.CharacterMoved(combatCharacter, combatCharacter.getPosition(),target);
        combatCharacter.setPosition(target);
    }
    public void reduceAP(CombatCharacter combatCharacter, int AP){
        combatCharacter.setAP(combatCharacter.getAP()-AP);
    }
    public void createNewProjectile(CombatCharacter caster, CombatCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {
        output.CharacterRangedAttack(caster,target,strategyProjectile,skill);
        int d=target.resolveHit(strategyProjectile);
        output.CharacterTookDamage(target,d);

    }
}
