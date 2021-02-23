public class StatPerk extends Perk{
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
    int getHPBonus(){
        return HP;
    }
    int getSPDBonus(){
        return SPD;
    }
    int getMovementBonus(){
        return Movement;
    }
}
