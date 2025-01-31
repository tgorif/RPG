package RPG.Character;

import RPG.Main.GameState;
import RPG.PerkSystem.StatPerk;
import RPG.StatusEffects.FactoryStatusEffect;
import RPG.StatusEffects.StrategyStatus;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttributeManager {
    private int HP;
    private int SPD;
    private int movement;
    private int AP;
    private int maxAP;
    private int maxHP;
    private int armor;
    private final CombatCharacter owner;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSPD() {
        return SPD;
    }

    public void setSPD(int SPD) {
        this.SPD = SPD;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getMaxAP() {
        return maxAP;
    }

    public void setMaxAP(int maxAP) {
        this.maxAP = maxAP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getArmor(){ return this.armor;}

    private AttributeManager(int hp, int SPD, int movement, int AP, int maxAP, int maxHP,int armor,CombatCharacter combatCharacter){
        this.HP=hp;
        this.SPD = SPD;
        this.movement = movement;
        this.AP = AP;
        this.maxAP = maxAP;
        this.maxHP = maxHP;
        this.armor=armor;
        this.owner=combatCharacter;
        final Logger LOGGER = Logger.getLogger(AttributeManager.class.getName());
        if(HP==0 || maxAP==0 || movement==0 || SPD==0){
            LOGGER.log(Level.SEVERE,"Created Character with 0-value Attributes"
                    + owner.characterInfo.getName()
                    + " HP " + HP
                    + " SPD " + SPD
                    + " movement " + movement
                    + " maxAP " + maxAP);
        }
    }
    private AttributeManager(AttributeManager original){
        this.HP=original.getHP();
        this.SPD = original.getSPD();
        this.movement = original.getMovement();
        this.AP = original.getAP();
        this.maxAP = original.getMaxAP();
        this.maxHP = original.getMaxHP();
        this.owner=original.owner;
    }
    public AttributeManager clone(){
        return new AttributeManager(this);
    }
    public void turnStart(){
        this.AP=maxAP;
    }
    public void changeHP(int x){
        this.HP=Math.min(this.HP+x,maxHP);
        if (this.HP<=0) {
            StrategyStatus s= FactoryStatusEffect.getStatus("Dead",owner);
            owner.statusEffects.put("Dead", s);
            owner.output.characterDied(owner);
            System.out.println(owner.statusEffects.size());
        }
    }
    public void changeAP(int x){
        this.AP+=x;
    }
    public static class AttributeBuilder{
        private int HP=0;
        private int SPD=0;
        private int movement=0;
        private int AP=0;
        private int maxAP=0;
        private int maxHP=0;
        private int armor=0;
        private CombatCharacter combatCharacter;
        public AttributeBuilder(){}
        public AttributeBuilder addStatsPerk(StatPerk perk){
            HP+=perk.getHPBonus();
            SPD+=perk.getSPDBonus();
            movement+=perk.getMovementBonus();
            armor+=perk.getArmor();
            return this;
        }
        public AttributeBuilder addStatsPerk(List<StatPerk> perks){
            for (StatPerk perk : perks) addStatsPerk(perk);
            return this;
        }
        public AttributeBuilder setOwner(CombatCharacter combatCharacter){
            this.combatCharacter=combatCharacter;
            return this;
        }
        public AttributeManager build(){
            maxAP=SPD;
            AP=0;
            maxHP=HP;
            return new AttributeManager(HP,SPD,movement,AP,maxAP,maxHP,armor,combatCharacter);
        }
    }
}
