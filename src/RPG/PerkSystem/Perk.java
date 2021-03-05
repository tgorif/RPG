package RPG.PerkSystem;

import RPG.SkillSystem.StrategySkill;

import java.util.HashMap;
import java.util.Map;
public class Perk {
    public String name;
    public static Map<String,Perk> perkMap=new HashMap<>();
    public Perk(String name){
        this.name=name;
    }
    public static class PerkBuilder{
        private int hp=0;
        private int SPD=0;
        private int movement=0;
        private String name="";
        String skill=null;
        String type;
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
        public Perk.PerkBuilder setSkill(String skill){
            this.skill=skill;
            return this;
        }
        public Perk.PerkBuilder setType(String s){
            this.type=s;
            return this;
        }
        public StatPerk buildStatPerk(){
            return new StatPerk(hp,SPD,movement,name);
        }
        public CombatPerk buildCombatPerk(){
            return new CombatPerk(name,skill);
        }
        public Perk build(){
            if(type.equals("RPG.PerkSystem.StatPerk")) return buildStatPerk();
            else return buildCombatPerk();
        }


    }
}
