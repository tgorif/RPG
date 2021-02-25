package RPG.PerkSystem;

import RPG.PerkSystem.Perk;

public class StatPerk extends Perk {
    private int HP;
    private int SPD;
    private int Movement;
    public StatPerk(int h,int s,int m,String name){
        super(name);
        HP=h;
        SPD=s;
        Movement=m;
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
}
