package RPG.PerkSystem;

import RPG.PerkSystem.Perk;

public class StatPerk extends Perk {
    private int HP;
    private int SPD;
    private int Movement;
    private int armor;
    public StatPerk(int h,int s,int m,int armor,String name){
        super(name);
        HP=h;
        SPD=s;
        Movement=m;
        this.armor=armor;
        this.name=name;
    }
    public int getHPBonus(){
        return HP;
    }
    public int getSPDBonus(){
        return SPD;
    }
    public int getMovementBonus(){
        return Movement;
    }

    public int getArmor() {
        return armor;
    }
}
