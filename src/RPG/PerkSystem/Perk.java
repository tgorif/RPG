package RPG.PerkSystem;

import RPG.SkillSystem.StrategySkill;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Perk {
    Logger LOGGER =Logger.getLogger(Perk.class.getName());
    public String name;
    private static final Map<String,Perk> perkMap=new HashMap<>();
    public Perk(String name){
        this.name=name;
        perkMap.put(name,this);
    }
    public static Map<String,Perk> getPerkMap(){
        return perkMap;
    }
    public static Perk getPerk(String s){
        if(perkMap.containsKey(s)) return perkMap.get(s);
        else return null;
    }
    public static class PerkBuilder{
        private int hp=0;
        private int SPD=0;
        private int movement=0;
        private int armor=0;
        private String name="";
        String skill=null;
        String type;
        private final Logger LOGGER=Logger.getLogger((Perk.PerkBuilder.class.getName()));

        public PerkBuilder(){

        }
        public Perk.PerkBuilder setHP(int h){
            hp=h;
            return this;
        }
        public Perk.PerkBuilder setSPD(int s){
            SPD=s;
            return this;
        }
        public Perk.PerkBuilder setName(String s){
            name=s;
            return this;
        }
        public Perk.PerkBuilder setMovement(int movement){
            this.movement=movement;
            return this;
        }
        public Perk.PerkBuilder setArmor(int movement){
            this.armor=armor;
            return this;
        }
        public Perk.PerkBuilder setSkill(String skill){
            this.skill=skill;
            return this;
        }
        public Perk.PerkBuilder setType(String s){
            this.type=s;
            return this;
        }
        public StatPerk buildStatPerk(){
            return new StatPerk(hp,SPD,movement,armor,name);
        }
        public CombatPerk buildCombatPerk(){
            if(name==null) LOGGER.log(Level.SEVERE,"building Perk with name==null");
            if(name.length()<1) LOGGER.log(Level.SEVERE,"building Perk with name length==0");
            return new CombatPerk(name,skill);
        }
        public Perk build(){
            if(type.equals("RPG.PerkSystem.StatPerk")) return buildStatPerk();
            else return buildCombatPerk();
        }


    }
}
