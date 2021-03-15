package RPG.SkillSystem;
import RPG.Character.*;
import RPG.Main.GameState;

import java.util.List;

public abstract class StrategySkill {
     public String skillName;
     public final CombatCharacter caster;
     public int cost;
     public int cooldown;
     public StrategySkill(SkillData skillData,CombatCharacter combatCharacter){
          this.skillName=skillData.name;
          this.cost=skillData.cost;
          this.cooldown=skillData.coolDown;
          caster=combatCharacter;
     }
     public abstract void useSkill();
     public abstract boolean isValid();


}
