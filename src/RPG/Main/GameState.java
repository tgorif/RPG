package RPG.Main;

import RPG.Character.Character;
import RPG.Character.ConcreteCharacter;
import RPG.Output.StrategyOutput;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    public List<ConcreteCharacter> concreteCharacterList= new ArrayList<>();
    Level level;
    int round=0;
    final List<Character> characterList;
    boolean EndCondition=false;
    StrategyOutput output;

    public GameState(List<Character> characterList,StrategyOutput strategyOutput){
        output=strategyOutput;
        level=new Level();
        this.characterList=characterList;
        createCharacters();
        prepareTurn();
    }
    private void createCharacters(){
        for(Character c : characterList){
            concreteCharacterList.add(new ConcreteCharacter(c,new Position(level.x[1]/3,level.y[1]/2,0),this));
        }
    }
    private void prepareTurn(){
        Map<ConcreteCharacter,List<StrategySkill>> actions=new HashMap<>();
        for (ConcreteCharacter c : concreteCharacterList){
            actions.put(c,c.getActions());
        }
        resolveTurn(actions,10);
    }
    private void resolveTurn(Map<ConcreteCharacter,List<StrategySkill>> actions,int turnTimer) {
        output.resolveingTurn(turnTimer);
        for(ConcreteCharacter concreteCharacter : concreteCharacterList){
            if(concreteCharacter.AP>=turnTimer &&
                    actions.containsKey(concreteCharacter)
                    &&actions.get(concreteCharacter).size()>0){
                output.currentCharacterActions(concreteCharacter,actions.get(concreteCharacter).size());
                actions.get(concreteCharacter).get(0).useSkill();
                actions.get(concreteCharacter).remove(0);
            }
        }
        if(turnTimer>0) resolveTurn(actions,turnTimer-1);
    }
    private void setTurnOrder(){

    }
    public void changeCharacterPosition(ConcreteCharacter concreteCharacter,Position target){
        output.CharacterMoved(concreteCharacter,concreteCharacter.getPosition(),target);
        concreteCharacter.setPosition(target);
    }
    public void reduceAP(ConcreteCharacter concreteCharacter,int AP){
        concreteCharacter.setAP(concreteCharacter.getAP()-AP);
    }
    public void createNewProjectile(ConcreteCharacter caster, ConcreteCharacter target, StrategyProjectile strategyProjectile, StrategySkill skill) {
        System.out.println(skill.skillName);
        System.out.println(target);
        output.CharacterRangedAttack(caster,target,strategyProjectile,skill);
        int d=target.resolveHit(strategyProjectile);
        output.CharacterTookDamage(target,d);

    }
}
