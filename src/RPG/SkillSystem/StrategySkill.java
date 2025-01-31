package RPG.SkillSystem;
import RPG.Character.*;
import RPG.Main.GameState;

import java.util.List;

public abstract class StrategySkill {
     public String skillName;
     public final CombatCharacter caster;
     public int cost;
     public int cooldown;
     public int lastUsed;
     public StrategySkill(SkillData skillData,CombatCharacter combatCharacter){
          this.skillName=skillData.name;
          this.cost=skillData.cost;
          this.cooldown=skillData.coolDown;
          caster=combatCharacter;
          this.lastUsed=Integer.MIN_VALUE;
     }
     public abstract void useSkill();
     public abstract boolean isValid();
     boolean canUse(){
          return caster.attributes.getAP()>=cost
                  &&lastUsed+cooldown<=GameState.getInstance().turnCounter
                  && !caster.statusEffects.containsKey("Dead");
     }
     void usedSkill(){
          lastUsed=GameState.getInstance().turnCounter;
          caster.attributes.changeAP(-cost);
          GameState.getInstance().output.SkillUsed(caster,skillName);
     }


}
