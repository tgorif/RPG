package RPG.Character;

import RPG.Main.Position;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.StatPerk;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.FactorySkill;
import RPG.SkillSystem.StrategySkill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombatCharacter {
    public CharacterSkillManager characterSkillManager;
    public String name;
    public int HP;
    public int SPD;
    public int movement;
    public int AP;
    public int maxAP;
    public Character character;
    public Position position;
    public boolean isBlueTeam;

    public CombatCharacter(Character c, Position position) {
        //general
        character=c;
        this.name= c.name;
        this.position=position;
        this.isBlueTeam= c.isBlueTeam;

        //SkillSetup
        characterSkillManager =new CharacterSkillManager();
        characterSkillManager.setSkills(c.perks);

        //SetStats
        resolvePreCombatPerks();
    }
    private CombatCharacter(CombatCharacter combatCharacter){
        this.name= character.name;;
        this.HP= combatCharacter.HP;
        this.SPD= combatCharacter.SPD;
        this.movement= combatCharacter.movement;
        this.AP= combatCharacter.AP;
        this.maxAP= combatCharacter.maxAP;
        this.character= combatCharacter.character;
        this.characterSkillManager=combatCharacter.characterSkillManager;
        this.position= combatCharacter.position;
        this.isBlueTeam= combatCharacter.isBlueTeam;
    }
    private void resolvePreCombatPerks(){
        for(Perk perk : characterSkillManager.preCombatPerks){
            HP+=((StatPerk) perk).getHPBonus();
            SPD+=((StatPerk) perk).getSPDBonus();
            movement+=((StatPerk) perk).getMovementBonus();
        }
        maxAP=movement;
        AP=maxAP;
    }
    public void setPosition(Position target){
        this.position=target;
    }
    public void setAP(int A){
        AP=A;
    }
    public int getAP(){
        return AP;
    }
    public Position getPosition(){
        return position;
    }
    public void newTurn(){
        AP=maxAP;
    }
    public List<StrategySkill> getActions(){
        List<StrategySkill> options=getPossibleActions();
        StrategySkill best=getBestAction(options);
        List<StrategySkill> result=new ArrayList<>();
        result.add(best);
        SimulatedCharacter simulatedCharacter= new SimulatedCharacter(this);
        result.get(0).simulate(simulatedCharacter);
        if(simulatedCharacter.AP>0) result.addAll(simulatedCharacter.getActions());
        for(StrategySkill skill : result){
            skill.setCaster(this);
        }
        return result;
    }
    private List<StrategySkill> getPossibleActions(){
        List<StrategySkill> options = new ArrayList<>();
        for(String s : characterSkillManager.skillList){
            StrategySkill skill = FactorySkill.getSkill(s);
            skill.setValues(this);
            skill.prepareAction();
            if(skill.isValid()) options.add(skill);
        }
        return options;
    }
    private StrategySkill getBestAction(List<StrategySkill> options){
        Collections.shuffle(options);
        return  options.size()>0 ? options.get(0) : null;
    }
    public int resolveHit(StrategyProjectile strategyProjectile){
        int d=strategyProjectile.getDamage();
        HP-=d;
        return d;
    }
    class SimulatedCharacter extends CombatCharacter{
        CombatCharacter origin;
        public SimulatedCharacter(CombatCharacter combatCharacter){
            super(combatCharacter);
            origin=combatCharacter;

        }
        @Override
        public List<StrategySkill> getActions(){
            List<StrategySkill> options=getPossibleActions();
            StrategySkill best=getBestAction(options);
            List<StrategySkill> result=new ArrayList<>();
            result.add(best);
            SimulatedCharacter simulatedCharacter= new SimulatedCharacter(this);
            result.get(0).simulate(simulatedCharacter);
            if(simulatedCharacter.AP>0) result.addAll(simulatedCharacter.getActions());
            return result;
        }
    }
}
