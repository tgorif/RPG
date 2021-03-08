package RPG.Character;

import RPG.Main.GameState;
import RPG.Main.Position;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.StatPerk;
import RPG.Projectiles.StrategyProjectile;
import RPG.SkillSystem.FactorySkill;
import RPG.SkillSystem.StrategySkill;
import RPG.StatusEffects.FactoryStatusEffect;
import RPG.StatusEffects.StatusDead;
import RPG.StatusEffects.StrategyStatus;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CombatCharacter {
    public CharacterSkillManager characterSkillManager;
    public Map<String,StrategyStatus> statusEffects=new HashMap<>();
    public String name;
    public int HP;
    public int SPD;
    public int movement;
    public int AP;
    public int maxAP;
    public int maxHP;
    public Character character;
    public Position position;
    public boolean isBlueTeam;
    private final Logger LOGGER = Logger.getLogger(CombatCharacter.class.getName());

    public CombatCharacter(Character c, Position position) {
        LOGGER.log(Level.FINE,"Creating CombatCharacter");
        //general
        character=c;
        this.name= c.name;
        this.position=position;
        this.isBlueTeam= c.isBlueTeam;

        LOGGER.log(Level.FINE,"Setting Skills");
        //SkillSetup
        characterSkillManager =new CharacterSkillManager();
        characterSkillManager.setSkills(c.perks);

        LOGGER.log(Level.FINE,"Setting Stats");
        //SetStats
        resolvePreCombatPerks();
        LOGGER.log(Level.INFO,"Created Character " + name + " with Stats HP " + HP + " SPD " + SPD + " AP " + AP );
    }
    private CombatCharacter(CombatCharacter combatCharacter){
        this.name= combatCharacter.name;;
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
        maxHP=HP;
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
        LOGGER.log(Level.FINE,"SimulatedCharacter has " + simulatedCharacter.AP + " AP left");
        if(simulatedCharacter.AP>0) result.addAll(simulatedCharacter.getActions());
        for(StrategySkill skill : result){
            skill.setCaster(this);
        }
        LOGGER.log(Level.FINE,name +"returned ActionList with size " + result.size());
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
        changeHP(-d);

        return d;
    }
    public void changeHP(int x){
        this.HP+=x;
        if (this.HP<=0) {
            StrategyStatus s= FactoryStatusEffect.getStatus("Dead",this);
            statusEffects.put("Dead", s);
            GameState.getInstance().output.characterDied(this);
        }
    }
    class SimulatedCharacter extends CombatCharacter{
        CombatCharacter origin;
        public SimulatedCharacter(CombatCharacter combatCharacter){
            super(combatCharacter);
            origin=combatCharacter;
            LOGGER.log(Level.FINE,"created SimulatedCharacter " + name
            + "with Stats " + " AP " + AP);
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
            LOGGER.log(Level.FINE,"SimulatedCharacter " + name +"returned ActionList with size " + result.size());
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
    }
}
