package RPG.Character;

import RPG.Main.GameState;
import RPG.Main.Position;
import RPG.PerkSystem.CombatPerk;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.StatPerk;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.StrategySkill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteCharacter {
    public String name;
    public int HP;
    public int SPD;
    public int movement;
    public int AP;
    final int maxAP;
    List<StrategySkill> skillList=new ArrayList<>();
    List<Perk> preCombatPerks=new ArrayList<>();
    Position position;
    public boolean isBlueTeam;
    public ConcreteCharacter(Character c, Position position, GameState gameState) {
        this.name= c.name;
        this.position=position;
        this.isBlueTeam= c.isBlueTeam;
        for (Perk perk : c.perks){
            if(perk instanceof StatPerk){
                preCombatPerks.add(perk);
            }
            else{
                StrategySkill skill= ((CombatPerk) perk).getSkill().getNewInstance();
                skill.setValues(gameState,this);
                skillList.add(skill);
            }
        }
        resolvePreCombatPerks();
        maxAP=movement;
        AP=maxAP;
    }
    private void resolvePreCombatPerks(){
        for(Perk perk : preCombatPerks){
            HP+=((StatPerk) perk).getHPBonus();
            SPD+=((StatPerk) perk).getSPDBonus();
            movement+=((StatPerk) perk).getMovementBonus();
        }
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
        for(StrategySkill skill : skillList){
            skill.prepareAction();
            skill.validate();
        }
        int count=0;
        List<StrategySkill> result=new ArrayList<>();
        while(count<AP){
            Collections.shuffle(skillList);
            if(skillList.get(0).isValid) {
                result.add(skillList.get(0));
                count += skillList.get(0).AP;
            }
        }
        return result;
    }
    public int resolveHit(StrategyProjectile strategyProjectile){
        int d=strategyProjectile.getDamage();
        HP-=d;
        return d;
    }
}
