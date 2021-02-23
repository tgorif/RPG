import java.util.HashMap;
import java.util.Map;
public class Perk {
    String name;
    static Map<String,Perk> perkMap=new HashMap<>();
    public Perk(String name){
        this.name=name;
    }
    public static class PerkBuilder{
        private int hp=0;
        private int SPD=0;
        private int movement=0;
        private String name="";
        Ability ability=null;
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
        public Perk.PerkBuilder setAbility(Ability ability){
            this.ability=ability;
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
            return new CombatPerk(name,ability);
        }
        public Perk build(){
            if(type.equals("StatPerk")) return buildStatPerk();
            else return buildCombatPerk();
        }


    }
}
